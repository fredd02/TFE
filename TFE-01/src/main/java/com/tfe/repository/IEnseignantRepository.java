package com.tfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfe.model.Enseignant;

public interface IEnseignantRepository extends IUserRepository<Enseignant>{
	
	//liste des enseignants actifs
	List<Enseignant> findByActifTrue();

}
