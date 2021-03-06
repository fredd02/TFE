package com.tfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfe.model.Relation;
import com.tfe.model.Relation.Id;

public interface IRelationRepository extends JpaRepository<Relation, Id>{
	
	
	//liste des relations pour un responsable
	@Query(value="SELECT * FROM TELEVE e JOIN RELATION r ON e.id = r.fkeleve WHERE r.fkresponsable=?1", nativeQuery=true)
	List<Relation> relationsFromResponsable(String username);
	
	//liste des relations pour un eleve
	@Query(value="SELECT *  FROM TRESPONSABLE res JOIN RELATION r ON res.username=r.fkresponsable JOIN users u on u.username=res.username"
			+ " WHERE r.fkeleve=?1", nativeQuery=true)
	List<Relation> getRelationsFromEleve(Long id);

}
