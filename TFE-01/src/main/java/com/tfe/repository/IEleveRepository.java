package com.tfe.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tfe.model.Eleve;
import com.tfe.model.Responsable;

public interface IEleveRepository extends JpaRepository<Eleve, Long>{
	
	//liste des eleves inscrits actuellement
	@Query(value="SELECT * FROM TELEVE e JOIN INSCRIPTION i ON e.id=i.fkeleve WHERE i.date_sortie is null", nativeQuery=true)
	List<Eleve> getElevesActuels();
	
	//liste des élèves inscrits dans une classe
	@Query(value="SELECT * FROM TELEVE e JOIN INSCRIPTION i on e.id = i.fkeleve WHERE i.date_sortie is null and i.fkclasse = ?1",nativeQuery=true)
	List<Eleve> elevesFromClasse(String code);
	
	//liste des eleves d'un titulaire
	@Query(value="SELECT * FROM TELEVE e JOIN INSCRIPTION i on e.id = i.fkeleve JOIN TCLASSE cl ON i.FKCLASSE=cl.code WHERE i.date_sortie is null and cl.titulaire = ?1",nativeQuery=true)
	List<Eleve> elevesFromTitulaire(String username);
	
	
	//classe actuelle de l'eleve
	@Query(value="SELECT i.fkclasse FROM INSCRIPTION i WHERE i.date_sortie is null and i.fkeleve=?1",nativeQuery=true)
	String classeFromEleve(Long id);
	
	//eleve avec ses inscription
	@Query(value="SELECT * FROM TELEVE e JOIN INSCRIPTION i on e.id=i.fkeleve WHERE e.id=?1", nativeQuery=true)
	Eleve eleveWithInscriptions(Long id);
	
	//liste des eleves d'un responsable
	@Query(value="SELECT * FROM TELEVE e JOIN RELATION r on e.id = r.fkeleve WHERE r.fkresponsable=?1",nativeQuery=true)
	List<Eleve> getElevesFromResponsable(String username);
	
	//eleves inscrits à la cantine pour une date
	@Query(value="SELECT * FROM TELEVE e JOIN INSCRIPTION_CANTINE i on e.id = i.FKELEVE WHERE i.date=?1", nativeQuery=true)
	List<Eleve> getElevesCantineForDate(Date date);
	
	//recherche d'un eleve par son nom
	List<Eleve> readByNomContainingIgnoringCaseAndActifIsTrue(String nom);
	
	//recherche du numero de compte d'un eleve
	@Query(value="SELECT res.fkcompte FROM TELEVE e JOIN RELATION rel ON e.id=rel.fkeleve JOIN TRESPONSABLE res ON rel.FKRESPONSABLE = "
			+ "res.USERNAME WHERE e.id=?1", nativeQuery=true)
	Long getCompteFromEleve(Long id);
	
	
	
	

}
