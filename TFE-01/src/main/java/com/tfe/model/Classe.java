package com.tfe.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude= {"inscriptions","titulaire"})
@Entity(name="TCLASSE")
public class Classe {

	@Id
	@Column
	private String code;
	
	@Column
	private String nom;
	
	@ManyToOne
	@JoinColumn(name="TITULAIRE")
	private Enseignant titulaire;
	
	@OneToMany(mappedBy = "classe")
	private Set<Inscription> inscriptions = new HashSet<>();
	
	public Set<Inscription> getInscriptions() {
		return inscriptions;
	}
	
	public Classe() {
		
	}
	
	public Classe(String code, String nom) {
		this.code = code;
		this.nom = nom;
	}

}
