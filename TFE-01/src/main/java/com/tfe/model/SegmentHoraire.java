package com.tfe.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name="segment_horaire")
public class SegmentHoraire {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String matiere;
	
	@DateTimeFormat(pattern="HH:mm")
	@Column
	private Date heureDebut;
	
	@DateTimeFormat(pattern="HH:mm")
	@Column
	private Date heureFin;
	
	@Column
	private Integer jour;
	
	@ManyToOne
	@JoinColumn(name="FKClasse")
	private Classe classe;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMatiere() {
		return matiere;
	}
	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}
	public Date getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}
	public Date getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}
	public Integer getJour() {
		return jour;
	}
	public void setJour(Integer jour) {
		this.jour = jour;
	}
	
	
	
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	public SegmentHoraire() {
		
	}

	

}
