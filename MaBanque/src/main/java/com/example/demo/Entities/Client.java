package com.example.demo.Entities;

import java.util.List;

public class Client {

	private Long code;
	private String nom;
	private String prenom;
	private List <Compte> comptes;
	
	public Client() {
		super();
	}
	public Client(Long code, String nom, String prenom) {
		super();
		this.code = code;
		this.nom = nom;
		this.prenom = prenom;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public List<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	
	
}
