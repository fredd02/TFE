package com.tfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfe.model.Responsable;

public interface IResponsableRepository extends JpaRepository<Responsable, Long>{
	
	//recherche d'un responsable par son nom
	List<Responsable> readByNomIgnoringCase(String nom);

}
