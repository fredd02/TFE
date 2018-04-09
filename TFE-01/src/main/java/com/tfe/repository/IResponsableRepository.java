package com.tfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfe.model.Responsable;

public interface IResponsableRepository extends IUserRepository<Responsable>{
	
	//recherche d'un responsable par son nom
	List<Responsable> readByNomIgnoringCase(String nom);

}
