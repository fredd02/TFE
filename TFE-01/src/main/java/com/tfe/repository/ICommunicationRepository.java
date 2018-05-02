package com.tfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfe.model.Communication;

public interface ICommunicationRepository extends JpaRepository<Communication, Long>{
	
	List<Communication> findAllByOrderByDateDesc();
	
	//liste des communications d'un responsable par ordre chronologique
	@Query(value="SELECT * FROM COMMUNICATION com JOIN COMMUNICATION_RESPONSABLE cr ON com.id = cr.FKCOMMUNICATION WHERE cr.FKRESPONSABLE=?1", nativeQuery=true)
	List<Communication> getCommunicationsFromResponsable(String username);

}
