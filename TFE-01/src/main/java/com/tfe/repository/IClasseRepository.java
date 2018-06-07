package com.tfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfe.model.Classe;

public interface IClasseRepository extends JpaRepository<Classe, String>{
	
	@Query(value="SELECT * FROM TCLASSE c LEFT JOIN TENSEIGNANT e ON c.titulaire = e.username ORDER BY c.code", nativeQuery=true)
	List<Classe> getClassesWithTitulaire();
	
	//liste des classes par ordre alphabétique code
	@Query(value="SELECT * FROM TCLASSE c ORDER BY c.code", nativeQuery=true)
	List<Classe> getClassesOrderedByCode();
	
	//classes de maternelle ou primaire
	List<Classe> findByCodeStartingWith(String code);
	
	//classe d'un eleve
	@Query(value="SELECT * FROM TCLASSE c JOIN INSCRIPTION i ON c.code=i.fkclasse WHERE i.FKELEVE=?1 AND i.date_sortie is null", nativeQuery=true)
	Classe getClasseFromEleve(Long id);
	
	//liste des classes d'un titulaire
	@Query(value="SELECT * FROM TCLASSE c WHERE c.titulaire=?1", nativeQuery=true)
	List<Classe> getClassesFromTitulaire(String username);

}
