package com.tfe.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.tfe.Tfe01Application;
import com.tfe.model.Classe;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages= {"com.tfe.repository","com.tfe.storage","com.tfe.service"})
@SpringBootTest
public class ClasseRepositoyTest {

	@Autowired
	private IClasseRepository classeRepository;
	
	@Test
	public void saveClasseTest() {
		
		long cpt = classeRepository.count();
		Classe classe1 = new Classe("M0", "Acceuil maternelle");
		Classe classe2 = new Classe("M1", "Premiere maternelle");
		Classe classe1_saved = classeRepository.save(classe1);
		assertEquals(classe1,classe1_saved);
		cpt++;
		Classe classe1_found = classeRepository.findOne(classe1.getCode());
		assertNotNull(classe1_found);
		assertEquals(classe1,classe1_found);
		
		Classe classe2_saved = classeRepository.save(classe2);
		cpt++;
		classeRepository.delete("M1");
		cpt--;
		assertEquals(cpt, classeRepository.count());
	}

}
