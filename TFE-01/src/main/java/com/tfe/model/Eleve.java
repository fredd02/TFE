package com.tfe.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude= {"inscriptions", "relations"})
@Entity(name="TELEVE")
public class Eleve {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Pattern(regexp = "[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}\\-[0-9]{3}\\.[0-9]{2}", message="le nrn n'est pas valide")
	@Column(nullable=false)
	private String nrn;
	
	@NotNull
	@Size(min=2, max=30, message="Le nom doit faire entre 2 et 30 caractères")
	@Column(length=30, nullable=false)
	private String nom;
	
	@NotNull
	@Size(min=2, max=30, message="Le prénom doit faire entre 2 et 30 caractères")
	@Column(length=30, nullable=false)
	private String prenom;
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(nullable=false)
	private Date dateNaissance;
	
	@NotNull
	@Column(nullable=false)
	private Integer sexe;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false, updatable=false)
	private Date dateInscription;
	
	@OneToMany(mappedBy = "eleve")
	private Set<Inscription> inscriptions = new HashSet<>();
	
	public Set<Inscription> getInscriptions(){
		return inscriptions;
	}
	
	@OneToMany(mappedBy ="eleve")
	private Set<Relation> relations = new HashSet<>();
	
	public Set<Relation> getRelations(){
		return relations;
	}
	
	
	
	public Eleve() {}

	
	
	

	

}
