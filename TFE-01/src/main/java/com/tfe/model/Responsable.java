package com.tfe.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	
	
	@Pattern(regexp = "[0-9]{2}.[0-9]{2}.[0-9]{2}-[0-9]{3}.[0-9]{2}",message="le nrn n'est pas valide")
	private String nrn;
	
	@NotNull
	@Size(min=2, max=30, message ="{nom.size}")
	@Column(length=30, nullable=false)
	private String nom;
	
	@NotNull
	@Size(min=2, max=30, message ="{prenom.size}")
	@Column(length=30, nullable=false)
	private String prenom;
	
	@NotNull(message="la date de naissance doit être renseignée")
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
	
	@ManyToOne
	@JoinColumn(name="FKCOMPTE")
	private Compte compte;
	
	public Set<Relation> getRelations(){
		return relations;
	}
	
	public Responsable() {
		
	}
	
	public Responsable(String username, String nom, String prenom)
	{
		this.username=username;
		this.nom=nom;
		this.prenom=prenom;
	}

}
