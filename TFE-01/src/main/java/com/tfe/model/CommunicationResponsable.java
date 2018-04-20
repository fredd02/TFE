package com.tfe.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@org.hibernate.annotations.Immutable
public class CommunicationResponsable {
	
	@Embeddable
	public static class Id implements Serializable {
		@Column(name="FKRESPONSABLE")
		protected String username;
		@Column(name="FKCOMMUNICATION")
		protected Long communicationId;
		
		public Id() {}
		
		public Id(String username, Long communicationId) {
			this.username=username;
			this.communicationId=communicationId;
		}
		
		public boolean equals(Object o) {
			if (o != null && o instanceof Id) {
				Id that = (Id) o;
				return this.username.equals(that.username)
						&& this.communicationId.equals(that.communicationId);
			}
			return false;
		}
		public int hashCode() { return username.hashCode() + communicationId.hashCode();
	}
		

}
	
	@EmbeddedId
	protected Id id = new Id();
	
	@Column(updatable=true)
	@NotNull
	protected Boolean lu;
	
	@ManyToOne
	@JoinColumn(name="FKRESPONSABLE", insertable=false,updatable=false)
	protected Responsable responsable;
	
	@ManyToOne
	@JoinColumn(name="FKCOMMUNICATION", insertable=false,updatable=false)
	protected Communication communication;
	
	protected CommunicationResponsable() {}
	
	//constructeur
	public CommunicationResponsable(Responsable responsable, Communication communication,Boolean lu ) {
		this.lu = lu;
		this.responsable = responsable;
		this.communication = communication;
		this.id.username = responsable.getUsername();
		this.id.communicationId = communication.getId();
	}
	
	public Boolean getLu() {
		return lu;
	}
	
	public void setLu(Boolean lu) {
		this.lu = lu;
	}
	public Responsable getResponsable() {
		return responsable;
	}
	
	public Communication getCommunication() {
		return communication;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((communication == null) ? 0 : communication.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lu == null) ? 0 : lu.hashCode());
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
		CommunicationResponsable other = (CommunicationResponsable) obj;
		if (communication == null) {
			if (other.communication != null)
				return false;
		} else if (!communication.equals(other.communication))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lu == null) {
			if (other.lu != null)
				return false;
		} else if (!lu.equals(other.lu))
			return false;
		if (responsable == null) {
			if (other.responsable != null)
				return false;
		} else if (!responsable.equals(other.responsable))
			return false;
		return true;
	}
	
	
}
