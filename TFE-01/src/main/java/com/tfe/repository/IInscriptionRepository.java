package com.tfe.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tfe.model.Eleve;
import com.tfe.model.Inscription;
import com.tfe.model.Inscription.Id;

@Repository
//@Transactional
public interface IInscriptionRepository extends JpaRepository<Inscription, Id>{
	
	//recuperation des inscriptions actuelles
	
	@Query(value="SELECT * FROM INSCRIPTION i WHERE i.date_sortie IS NULL", nativeQuery=true)
	List<Inscription> inscriptionsActuelles();
	
	//inscription actuelle d'un eleve
	@Query(value="SELECT * FROM INSCRIPTION i WHERE i.date_sortie IS NULL AND i.FKELEVE=?1",nativeQuery=true)
	Inscription inscriptionActuelleFromEleve(Long id);
	
	//liste des inscriptions d'un eleve
	@Query(value="SELECT * FROM INSCRIPTION i WHERE i.fkeleve=?1", nativeQuery=true)
	List<Inscription> getInscriptionsFromEleve(Long id);
	
	//inscription actuelle d'un eleve
	Inscription findByEleveAndDateSortie(Eleve eleve, Date date);

}
