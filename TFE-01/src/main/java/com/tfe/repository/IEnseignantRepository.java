package com.tfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfe.model.Enseignant;

public interface IEnseignantRepository extends JpaRepository<Enseignant, Long>{

}
