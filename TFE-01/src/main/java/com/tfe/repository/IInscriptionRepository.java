package com.tfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfe.model.Inscription;
import com.tfe.model.Inscription.Id;

public interface IInscriptionRepository extends JpaRepository<Inscription, Id>{
	
	//recuperation des inscriptions actuelles
	
	@Query(value="SELECT * FROM INSCRIPTION i WHERE i.date_sortie IS NULL", nativeQuery=true)
	List<Inscription> inscriptionsActuelles();

}
