package com.tfe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@org.hibernate.annotations.Immutable
public class Inscription {
	
	@Embeddable
	public static class Id implements Serializable {
		@Column(name="FKELEVE")
		protected Long eleveId;
		@Column(name="FKCLASSE")
		protected String classeId;
		
		public Id() {}
		
		public Id(Long eleveId, String classeId) {
			this.eleveId = eleveId;
			this.classeId = classeId;
		}
		
		public boolean equals(Object o) {
			if (o != null && o instanceof Id) {
				Id that = (Id)o;
				return this.eleveId.equals(that.eleveId)
						&& this.classeId.equals(that.classeId);
				
			}
			return false;
		}
		
		public int hashcode() { return eleveId.hashCode() + classeId.hashCode();
		}
		
	}
		
		@EmbeddedId
		protected Id id = new Id();
		
		@Column(updatable = true)
		@NotNull
		protected Date dateEntree;
		
		@Column(updatable = true)
		protected Date dateSortie;
		
		@ManyToOne
		@JoinColumn(name="FKELEVE", insertable=false, updatable=false)
		protected Eleve eleve;
		
		@ManyToOne
		@JoinColumn( name="FKCLASSE", insertable=false, updatable=false)
		protected Classe classe;
		
		protected Inscription() {};
		
		public Inscription(Eleve eleve, Classe classe, Date dateEntree) {
			this.eleve = eleve;
			this.classe = classe;
			this.dateEntree = dateEntree;
			this.id.eleveId = eleve.getId();
			this.id.classeId = classe.getCode();
			eleve.getInscriptions().add(this);
			classe.getInscriptions().add(this);
		}

		public Date getDateEntree() {
			return dateEntree;
		}

		public void setDateEntree(Date dateEntree) {
			this.dateEntree = dateEntree;
		}

		public Date getDateSortie() {
			return dateSortie;
		}

		public void setDateSortie(Date dateSortie) {
			this.dateSortie = dateSortie;
		}

		public Eleve getEleve() {
			return eleve;
		}

		public void setEleve(Eleve eleve) {
			this.eleve = eleve;
		}

		public Classe getClasse() {
			return classe;
		}

		public void setClasse(Classe classe) {
			this.classe = classe;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((classe == null) ? 0 : classe.hashCode());
			result = prime * result + ((dateEntree == null) ? 0 : dateEntree.hashCode());
			result = prime * result + ((dateSortie == null) ? 0 : dateSortie.hashCode());
			result = prime * result + ((eleve == null) ? 0 : eleve.hashCode());
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
			Inscription other = (Inscription) obj;
			if (classe == null) {
				if (other.classe != null)
					return false;
			} else if (!classe.equals(other.classe))
				return false;
			if (dateEntree == null) {
				if (other.dateEntree != null)
					return false;
			} else if (!dateEntree.equals(other.dateEntree))
				return false;
			if (dateSortie == null) {
				if (other.dateSortie != null)
					return false;
			} else if (!dateSortie.equals(other.dateSortie))
				return false;
			if (eleve == null) {
				if (other.eleve != null)
					return false;
			} else if (!eleve.equals(other.eleve))
				return false;
			return true;
		}
		
		
		
		

	


}
