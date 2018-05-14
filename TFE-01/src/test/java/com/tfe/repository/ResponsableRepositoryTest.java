package com.tfe.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages= {"com.tfe.repository","com.tfe.storage","com.tfe.service"})
public class ResponsableRepositoryTest {

	@Autowired
	IEleveRepository eleveDAO;
	
	@Autowired
	IClasseRepository classeDAO;
	
	@Autowired
	IResponsableRepository responsableDAO;
	
	@Test
	public void testResponsablesave() {
		
	}

}
