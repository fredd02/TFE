package com.tfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfe.model.SegmentHoraire;


public interface ISegmentHoraireRepository extends JpaRepository<SegmentHoraire, Long>{
	
	@Query(value="SELECT * FROM segment_horaire s WHERE s.jour=?1 AND s.fkclasse=?2", nativeQuery=true)
	List<SegmentHoraire> getSegmentsHorairesJourClasse(Integer jour, String code);
	

}
