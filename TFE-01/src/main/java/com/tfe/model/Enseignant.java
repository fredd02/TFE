package com.tfe.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity(name="TENSEIGNANT")
public class Enseignant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Pattern(regexp = "[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}\\-[0-9]{3}\\.[0-9]{2}")
	private String nrn;
	
	@Column(nullable=false)
	private String nom;
	
	@Column(nullable=false)
	private String prenom;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column
	private Date dateNaissance;
	
	@Column
	private Integer sexe;
	
	@Column
	private String email;
	
	@Column
	private String telephone;
	
	@Column
	private String rue;
	
	@Column
	private Integer numero;
	
	@Column
	private Integer codePostal;
	
	@Column
	private String ville;
	
	@Column
	private Date dateInscription;

	

}
