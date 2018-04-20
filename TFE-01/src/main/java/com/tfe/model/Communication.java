package com.tfe.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Communication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private Date date;
	
	@Column
	private String sujet;
	
	@Column
	private String corps;
	
	@Column
	private String lienFichier;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="communicationToClasse", joinColumns=@JoinColumn(name="FKCommunication"),
		inverseJoinColumns=@JoinColumn(name="FKClasse"))
	protected Set<Classe> classes = new HashSet<Classe>();

	public Communication() {
		
	}

}
