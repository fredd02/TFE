package com.tfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfe.model.Responsable;

public interface IResponsableRepository extends IUserRepository<Responsable>{
	
	//recherche d'un responsable par son nom
	List<Responsable> readByNomContainingIgnoringCase(String nom);
	
	
	
	//recherche des co-responsables
	@Query(value="SELECT * FROM TRESPONSABLE res JOIN RELATION rel ON res.username = rel.FKRESPONSABLE"
			+" JOIN USERS u ON res.USERNAME=u.USERNAME WHERE"
			+ " rel.FKELEVE IN (SELECT rel.FKELEVE FROM RELATION rel JOIN TRESPONSABLE res ON rel.FKRESPONSABLE = res.USERNAME"
			+ " WHERE res.USERNAME=?1)", nativeQuery=true)
	List<Responsable> getCoResponsablesFrom(String username);
	
	//recherche des titulaires d'un compte
	@Query(value="SELECT * FROM TRESPONSABLE res JOIN COMPTE cp ON res.FKCOMPTE = cp.id JOIN USERS u ON res.USERNAME=u.USERNAME WHERE cp.id=?1",nativeQuery=true)
	List<Responsable> getTitulairesFromCompte(Long id);
	
	//liste des responsables d'une classe
	@Query(value="SELECT * FROM TRESPONSABLE res JOIN USERS u ON res.username = u.username JOIN RELATION rel on res.username=rel.FKRESPONSABLE"
			+ " JOIN INSCRIPTION ins ON rel.FKELEVE=ins.FKELEVE WHERE  ins.FKCLASSE=?1", nativeQuery=true)
	List<Responsable> getResponsablesFromClasse(String code);
	
	//liste des responsables d'un élève
	@Query(value="SELECT * FROM TRESPONSABLE res JOIN RELATION rel ON res.username= rel.FKRESPONSABLE"
			+ " WHERE rel.FKELEVE=?1", nativeQuery=true)
	List<Responsable> getResponsablesFromEleve(Long id);

}
