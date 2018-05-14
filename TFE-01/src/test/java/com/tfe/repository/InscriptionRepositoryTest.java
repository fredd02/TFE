package com.tfe.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.tfe.model.Classe;
import com.tfe.model.Eleve;
import com.tfe.model.Inscription;
import com.tfe.model.Inscription.Id;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages= {"com.tfe.repository","com.tfe.storage","com.tfe.service"})
public class InscriptionRepositoryTest {
	
	@Autowired
	IEleveRepository eleveDAO;
	
	@Autowired
	IClasseRepository classeDAO;
	
	@Autowired
	IInscriptionRepository inscriptionDAO;

	@Test
	public void testInscription() throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance1 = sdf.parse("02/02/2014");
		Date dateInscription1 = sdf.parse("01/09/2016");
		Date dateInscription2 = sdf.parse("01/09/2017");
		Eleve eleve1 = new Eleve("14.02.02-326.14","APPERT","Baptiste",dateNaissance1,0,dateInscription1);
		eleveDAO.save(eleve1);
		Classe classe1 = new Classe("M0", "Accueil Maternelle");
		Classe classe2 = new Classe("M1", "Premiere maternelle");
		classeDAO.save(classe1);
		classeDAO.save(classe2);
		
		Inscription inscription = new Inscription(eleve1, classe1,dateInscription1);
		inscription.setDateSortie(dateInscription2);
		Inscription inscription_saved = inscriptionDAO.save(inscription);
		assertEquals(inscription, inscription_saved);
		
		Id id_inscription = new Id(eleve1.getId(), classe1.getCode());
		Inscription inscription_get = inscriptionDAO.getOne(id_inscription);
		assertEquals(inscription, inscription_get);
		
		
		Inscription inscription2 = new Inscription(eleve1, classe2, dateInscription2);
		inscriptionDAO.save(inscription2);
		
		//liste des inscriptions de l'élève
		List<Inscription> inscriptionsEleve1 = inscriptionDAO.getInscriptionsFromEleve(eleve1.getId());
		assertThat(inscriptionsEleve1.size(), is(2));
		
		//inscription actuelle de l'élève
		Inscription inscriptionActuelle = inscriptionDAO.inscriptionActuelleFromEleve(eleve1.getId());
		assertEquals(inscriptionActuelle, inscriptionsEleve1.get(1));
			
	}
	
}
