package com.tfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfe.model.CommunicationResponsable;
import com.tfe.model.CommunicationResponsable.Id;

public interface ICommunicationResponsableRepository extends JpaRepository<CommunicationResponsable, Id>{
	
	//liste des communications d'un responsable
	@Query(value="SELECT * FROM COMMUNICATION_RESPONSABLE cr JOIN COMMUNICATION c ON cr.FKCOMMUNICATION=c.id WHERE cr.FKRESPONSABLE=?1", nativeQuery=true)
	List<CommunicationResponsable> getCommunicationsFromResponsable(String username);

}
