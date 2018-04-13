package com.tfe.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tfe.model.InscriptionCantine;

public interface IInscriptionCantineRepository extends JpaRepository<InscriptionCantine, Long>{
	
	
	//recuperation de l'incription pour un eleve et une date
	@Query(value="SELECT i.id FROM INSCRIPTION_CANTINE i WHERE i.FKELEVE=?1 AND i.DATE=?2", nativeQuery=true)
	public Long getInscriptionCantineWithEleveAndDate(Long id, Date date);
	
	//recuperation des inscriptions d'un eleve entre deux dates
	@Query(value="SELECT * FROM INSCRPTION_CANTINE i WHERE i.FKELEVE=?1 AND i.DATE BETWEEN ?2 AND ?3", nativeQuery=true)
	public List<InscriptionCantine> inscriptionFromEleveBeetwen2Dates(Long id, Date date1, Date date2);
	
	//recuperation des inscriptions des eleves d'un responsable entre deux dates
	@Query(value="SELECT * FROM INSCRIPTION_CANTINE i JOIN RELATION r ON i.FKELEVE = r.FKELEVE "
			+" JOIN TRESPONSABLE re ON r.FKRESPONSABLE = re.USERNAME WHERE re.USERNAME=?1 AND i.DATE BETWEEN ?2 AND ?3", nativeQuery=true)
	public List<InscriptionCantine> inscriptionFromResponsableBeetwen2Dates(String username, Date date1, Date date2);
	
	//recuperation des repas facturées d'un responsable
	@Query(value="SELECT * FROM INSCRIPTION_CANTINE i JOIN RELATION r ON i.FKELEVE = r.FKELEVE "
			+" JOIN TRESPONSABLE re ON r.FKRESPONSABLE = re.USERNAME WHERE re.USERNAME=?1 AND i.paye=true ORDER BY i.date DESC, i.FKELEVE", nativeQuery=true)
	public List<InscriptionCantine> inscriptionsFactureesFromResponsable(String username);
	
	//inscriptions à la cantine pour une date
	@Query(value="SELECT * FROM TELEVE e JOIN INSCRIPTION_CANTINE i on e.id = i.FKELEVE WHERE i.date=?1", nativeQuery=true)
	public List<InscriptionCantine> getInscriptionsFromDate(Date date);
	
	//inscriptions non facturées à la cantine pour une date
	@Query(value="SELECT * FROM TELEVE e JOIN INSCRIPTION_CANTINE i on e.id = i.FKELEVE WHERE i.date=?1 AND i.paye=false", nativeQuery=true)
	public List<InscriptionCantine> getInscriptionsNonFactureesFromDate(Date date);
	
	//update paye à true pour inscriptions cantine pour une date
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="UPDATE INSCRIPTION_CANTINE SET paye=true where date=?1", nativeQuery=true)
	public int setPayeForInscriptionsFromDate(Date date);
	
	
	

}
