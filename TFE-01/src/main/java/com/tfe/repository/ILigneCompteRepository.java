package com.tfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfe.model.LigneCompte;

public interface ILigneCompteRepository extends JpaRepository<LigneCompte, Long>{

	
	//liste des 10 dernieres operations d'un compte
	@Query(value="SELECT * FROM LIGNE_COMPTE lc WHERE lc.FKCOMPTE=?1 ORDER BY lc.DATE DESC LIMIT 10", nativeQuery=true)
	public List<LigneCompte> get10LastLignesFromCompte(Long id);
	
	//liste des 30 dernieres operations d'un compte
		@Query(value="SELECT * FROM LIGNE_COMPTE lc WHERE lc.FKCOMPTE=?1 ORDER BY lc.DATE DESC LIMIT 30", nativeQuery=true)
		public List<LigneCompte> get30LastLignesFromCompte(Long id);
}
