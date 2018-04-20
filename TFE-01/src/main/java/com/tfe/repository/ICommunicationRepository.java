package com.tfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfe.model.Communication;

public interface ICommunicationRepository extends JpaRepository<Communication, Long>{
	
	//liste des communications d'un responsable par ordre chronologique
	@Query(value="SELECT * FROM COMMUNICATION com JOIN COMMUNICATION_TO_CLASSE cc ON com.id = cc.FKCOMMUNICATION"
			+ " JOIN TCLASSE cl ON cc.FKCLASSE = cl.code JOIN INSCRIPTION ins ON ins.FKCLASSE = cl.code"
			+ " JOIN RELATION rel ON ins.FKELEVE = rel.FKELEVE WHERE rel.FKRESPONSABLE=?1 ORDER BY com.date", nativeQuery=true)
	List<Communication> getCommunicationsFromResponsable(String username);

}
