package com.tfe.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class LigneCompte {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private Date date;
	
	@Column
	private String designation;
	
	@Column
	private Double montant;
	
	@Column
	private String remarque;
	
	
	@ManyToOne
	@JoinColumn(name="FKEleve")
	private Eleve eleve;
	
	@ManyToOne
	@JoinColumn(name="FKCompte")
	private Compte compte;

	public LigneCompte() {
		
	}
	
	public LigneCompte(Compte compte,Date date, String designation, Double montant, Eleve eleve) {
		this.compte=compte;
		this.date=date;
		this.designation=designation;
		this.montant=montant;
		this.eleve=eleve;
	}
	
	public LigneCompte(Compte compte, Date date, String designation, Double montant) {
		this.compte=compte;
		this.date=date;
		this.designation=designation;
		this.montant=montant;
	}

}
