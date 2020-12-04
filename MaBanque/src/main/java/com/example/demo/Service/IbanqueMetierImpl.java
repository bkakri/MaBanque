package com.example.demo.Service;

import java.util.Date;
import java.util.Optional;

import javax.print.attribute.standard.PageRanges;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import com.example.demo.Entities.Compte;
import com.example.demo.Entities.Operation;
import com.example.demo.Entities.Retrait;
import com.example.demo.Entities.Versement;
import com.example.demo.Repository.CompteRepository;
import com.example.demo.Repository.OperationRepository;

@Service
@Transactional
public class IbanqueMetierImpl implements IbanqueMetier {

	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	
	@Override
	public Compte consulterCompte(String codeCompte) {
		Compte cp=compteRepository.getOne(codeCompte);
		if(cp==null) throw new RuntimeException("compte introuvable");
		return cp;
	}

	@Override
	public void verser(String codeCompte, double montant) {
		Compte cp=consulterCompte(codeCompte);
		Versement V=new Versement(new Date(),montant,cp); 
		operationRepository.save(V);
		cp.setSolde(cp.getSolde()+montant);
		compteRepository.save(cp);
	}

	@Override
	public void retirer(String codeCompte, double montant) {
		Compte cp=consulterCompte(codeCompte);
		if(cp.getSolde()<montant) 
		throw new RuntimeException("solde insufisant");
		Retrait V=new Retrait(new Date(),montant,cp); 
		operationRepository.save(V);
		cp.setSolde(cp.getSolde()-montant);
		compteRepository.save(cp);
		
	}

	@Override
	public Page<Operation> listOperation(String codeCpt, int page, int size) {
	   return operationRepository.listoperation(codeCpt,PageRequest.of(page, size) );
	}

}
