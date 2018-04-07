package com.tfe.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Immutable;

import com.tfe.model.Inscription.Id;

@Entity
@Immutable
public class Relation {

	@Embeddable
	public static class Id implements Serializable {
		@Column(name="FKELEVE")
		protected Long eleveId;
		@Column(name="FKRESPONSABLE")
		protected Long responsableId;
		
		public Id() {}
		
		public Id(Long eleveId, Long responsableId) {
			this.eleveId = eleveId;
			this.responsableId = responsableId;
		}
		
		public boolean equals(Object o) {
			if (o != null && o instanceof Id) {
				Id that = (Id)o;
				return this.eleveId.equals(that.eleveId)
						&& this.responsableId.equals(that.responsableId);
				
			}
			return false;
		}
		
		public int hashcode() { return eleveId.hashCode() + responsableId.hashCode();
		}
		
	}
	
	@EmbeddedId
	protected Id id = new Id();
	
	@Column
	private String lienParent;
	
	@ManyToOne
	@JoinColumn(name="FKELEVE", insertable=false, updatable=false)
	protected Eleve eleve;
	
	@ManyToOne
	@JoinColumn(name="FKRESPONSABLE", insertable=false, updatable=false)
	protected Responsable responsable;

	protected Relation() {}
	
	public Relation(Eleve eleve, Responsable responsable, String lien) {
		this.eleve = eleve;
		this.responsable = responsable;
		this.lienParent = lien;
		this.id.eleveId = eleve.getId();
		this.id.responsableId = responsable.getId();
		eleve.getRelations().add(this);
		responsable.getRelations().add(this);
	}

	public String getLienParent() {
		return lienParent;
	}

	public void setLienParent(String lienParent) {
		this.lienParent = lienParent;
	}

	public Eleve getEleve() {
		return eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eleve == null) ? 0 : eleve.hashCode());
		result = prime * result + ((lienParent == null) ? 0 : lienParent.hashCode());
		result = prime * result + ((responsable == null) ? 0 : responsable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Relation other = (Relation) obj;
		if (eleve == null) {
			if (other.eleve != null)
				return false;
		} else if (!eleve.equals(other.eleve))
			return false;
		if (lienParent == null) {
			if (other.lienParent != null)
				return false;
		} else if (!lienParent.equals(other.lienParent))
			return false;
		if (responsable == null) {
			if (other.responsable != null)
				return false;
		} else if (!responsable.equals(other.responsable))
			return false;
		return true;
	}
	
	
	
	
}
