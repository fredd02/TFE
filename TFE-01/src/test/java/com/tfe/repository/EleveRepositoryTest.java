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
import org.springframework.test.context.junit4.SpringRunner;

import com.tfe.model.Eleve;
import com.tfe.repository.IEleveRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EleveRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private IEleveRepository eleveDAO;

	@Test
	public void testEleveSave() {
		Date dateNaissance = new Date(02/02/2014);
		Date dateInscription = new Date(05/05/2018);
		Eleve eleve1 = new Eleve("14.02.02-326.14","APPERT","Baptiste",dateNaissance,0,dateInscription);
		entityManager.persist(eleve1);
		entityManager.flush();
		
		
	}

}
