package com.example.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entities.Compte;
import com.example.demo.Entities.Operation;
import com.example.demo.Service.IbanqueMetier;


@RestController
public class BanqueController {   

 
	@Autowired
	private IbanqueMetier iBanqueMetier;
	
	@RequestMapping("/operations") 
	public String index() {   
		 return "comptes"; 
	 }
	
	
	@RequestMapping("/consultercompte")  
	public String consulterCompte(Model model, String codeCompte,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="5")int size) { 
		
		model.addAttribute("codeCompte",codeCompte);
		
		try {
			
			Compte compte=iBanqueMetier.consulterCompte(codeCompte);
			Page<Operation>  pageOperations = iBanqueMetier.listOperation (codeCompte ,page, size);
			model.addAttribute("listOperations",pageOperations.getContent());
			int[] pages=new int[pageOperations.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("compte",compte);
	 
		}catch (Exception e) {
			model.addAttribute("exception",e);
		}
		

		return "comptes"; 
	}
	
	
	
	@RequestMapping(value="/saveOperation",method=RequestMethod.POST)   
	public String saveOperation(Model model,String typeOperation, String codeCompte,double montant, String codeCompte2) {   //cette methode retourne une vue tous simplement
		
		try {
			if(typeOperation.equals("VERS")) {
				
				iBanqueMetier.verser(codeCompte,montant);
			} else if(typeOperation.equals("RETR")) {
				
				iBanqueMetier.retirer(codeCompte,montant);
			} 
		}catch (Exception e) {
			model.addAttribute("error",e);
			return "redirect:/consultercompte?codeCompte="+codeCompte+"&error="+e.getMessage();  
		}
		 
		
		return "redirect:/consultercompte?codeCompte="+codeCompte;  
	}
	

}
