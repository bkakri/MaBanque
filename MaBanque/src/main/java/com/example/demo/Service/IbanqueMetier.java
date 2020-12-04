package com.example.demo.Service;

import org.springframework.data.domain.Page;

import com.example.demo.Entities.Compte;
import com.example.demo.Entities.Operation;

public interface IbanqueMetier {
	
	public Compte consulterCompte(String codeCompte);
	public void verser(String codeCompte,double montant);
	public void retirer(String codeCompte,double montant);
	public Page<Operation> listOperation(String codeCpt,int page,int size);

}
