package com.tfe.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true,exclude= {"relations"})
@Entity(name="TRESPONSABLE")
public class Responsable extends User{
	
	private static final long serialVersionUID = 1L;
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
	
	
	@Pattern(regexp = "[0-9]{2}.[0-9]{2}.[0-9]{2}-[0-9]{3}.[0-9]{2}")
	private String nrn;
	
	@NotNull
	@Column(length=30, nullable=false)
	private String nom;
	
	@NotNull
	@Column(length=30, nullable=false)
	private String prenom;
	
	@NotNull
	@Column(nullable=false)
	private Date dateNaissance;
	
	@NotNull
	@Column(nullable=false)
	private Integer sexe;
	
	@Column
	private String email;
	
	@NotNull
	@Column(nullable=false)
	private String telephone;
	
	@Column
	private String profession;
	
	@Column
	private String rue;
	
	@Column
	private Integer numero;
	
	@Column
	private Integer codePostal;
	
	@Column
	private String ville;
	
	@OneToMany(mappedBy ="responsable")
	private Set<Relation> relations = new HashSet<>();
	
	public Set<Relation> getRelations(){
		return relations;
	}

}
