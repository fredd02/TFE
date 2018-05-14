package com.tfe.repository;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.tfe.model.Eleve;
import com.tfe.repository.IEleveRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages= {"com.tfe.repository","com.tfe.storage","com.tfe.service"})
public class EleveRepositoryTest {
	
	
	@Autowired
	private IEleveRepository eleveDAO;

	@Test
	public void testEleveSave() {
		long cpt = eleveDAO.count();
		Date dateNaissance1 = new Date(02/02/2014);
		Date dateInscription1 = new Date(05/05/2018);
		Date dateNaissance2 = new Date(02/12/2013);
		Date dateInscription2 = new Date(10/05/2018);
		//ajout de 2 élèves
		Eleve eleve1 = new Eleve("14.02.02-326.14","APPERT","Baptiste",dateNaissance1,0,dateInscription1);
		Eleve eleve2 = new Eleve("13.12.02-152.21", "DUPONT","Lucas", dateNaissance2,0,dateInscription2);
		Eleve eleve1_saved = eleveDAO.save(eleve1);
		Eleve eleve2_saved = eleveDAO.save(eleve2);
		//verifie que les élèves sauvés dans la BD sont identiques
		assertEquals(eleve1, eleve1_saved);
		assertEquals(eleve2, eleve2_saved);
		
		//verifie l'incrémentation du compteur
		assertEquals(cpt+2, eleveDAO.count());
		
		//recharge un eleve dans la BD et verifie qu'il est identique
		Eleve eleve1_get = eleveDAO.getOne(eleve1.getId());
		assertEquals(eleve1_get, eleve1_saved);
		
		eleveDAO.delete(eleve1);
		assertEquals(cpt+1, eleveDAO.count());
		
		
		
	}

}
