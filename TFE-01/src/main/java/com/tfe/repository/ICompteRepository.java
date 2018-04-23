package com.tfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfe.model.Compte;

public interface ICompteRepository extends JpaRepository<Compte, Long>{
	
	//recuperation du compte d'un eleve
	@Query(value="SELECT * FROM COMPTE cpt JOIN TRESPONSABLE res ON cpt.id = res.FKCOMPTE"
			+" JOIN RELATION rel ON res.username = rel.FKRESPONSABLE JOIN USERS u ON res.username = u.username"
			+ " WHERE rel.FKELEVE=?1", nativeQuery=true)
	public Compte getCompteFromEleve(Long id);
	
	//recuperation de l'id du compte d'un responsable
	@Query(value="SELECT cpt.id FROM COMPTE cpt JOIN TRESPONSABLE res ON cpt.id = res.FKCOMPTE JOIN USERS u ON res.username = u.username"
			+ " WHERE res.username=?1", nativeQuery=true)
	public Long getCompteIdFromResponsable(String username);

}
