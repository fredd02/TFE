package com.tfe.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity(name="TENSEIGNANT")
public class Enseignant extends User{
	
	private static final long serialVersionUID = 1L;
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
	
	@Pattern(regexp = "[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}\\-[0-9]{3}\\.[0-9]{2}",message="le nrn n''est pas valide")
	private String nrn;
	
	@NotNull
	@Size(min=2, max=30, message ="{nom.size}")
	@Column(nullable=false)
	private String nom;
	
	@NotNull
	@Size(min=2, max=30, message ="{prenom.size}")
	@Column(nullable=false)
	private String prenom;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column
	private Date dateNaissance;
	
	@Column
	private Integer sexe;
	
	@Email
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
	
	@Column
	private Boolean actif;

	

}
