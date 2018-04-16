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

}
