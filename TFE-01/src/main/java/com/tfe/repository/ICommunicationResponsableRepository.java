package com.tfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfe.model.CommunicationResponsable;
import com.tfe.model.CommunicationResponsable.Id;

public interface ICommunicationResponsableRepository extends JpaRepository<CommunicationResponsable, Id>{
	
	//liste des communications d'un responsable, ordre chronologique décroissant
	@Query(value="SELECT * FROM COMMUNICATION_RESPONSABLE cr JOIN COMMUNICATION c ON cr.FKCOMMUNICATION=c.id WHERE cr.FKRESPONSABLE=?1"
			+ " ORDER BY c.date DESC", nativeQuery=true)
	List<CommunicationResponsable> getCommunicationsFromResponsable(String username);
	
	//nombre de communications non lues d'un responsable
	@Query(value="SELECT COUNT(*) FROM COMMUNICATION_RESPONSABLE cr JOIN COMMUNICATION c ON cr.FKCOMMUNICATION=c.id "
			+ "WHERE cr.lu=false AND cr.FKRESPONSABLE=?1",nativeQuery=true)
	int getNbCommunicationsNonLuesFromResponsable(String username);
	
	//liste des communicationsresponsables  lues pour une communication
	@Query(value="SELECT * FROM COMMUNICATION_RESPONSABLE cr WHERE cr.FKCOMMUNICATION=?1 AND cr.lu=true", nativeQuery=true)
	List<CommunicationResponsable> getCommunicationsResponsableLuesFromIdCommunication(Long id);
	
	//liste des communicationsresponsables  non lues pour une communication triées par nom et prenom
		@Query(value="SELECT * FROM COMMUNICATION_RESPONSABLE cr JOIN TRESPONSABLE res ON cr.FKRESPONSABLE=res.USERNAME WHERE cr.FKCOMMUNICATION=?1 AND cr.lu=false"
				+ " ORDER BY res.NOM,RES.PRENOM", nativeQuery=true)
		List<CommunicationResponsable> getCommunicationsResponsableNonLuesFromIdCommunication(Long id);
		
	//liste des communicationsResponsable pour une communication
		@Query(value="SELECT * FROM COMMUNICATION_RESPONSABLE cr WHERE cr.FKCOMMUNICATION=?1", nativeQuery=true)
		List<CommunicationResponsable> getCommunicationsResponsableForCommunication(Long id);

}
