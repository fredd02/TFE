package com.tfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfe.model.Compte;

public interface ICompteRepository extends JpaRepository<Compte, Long>{

}
