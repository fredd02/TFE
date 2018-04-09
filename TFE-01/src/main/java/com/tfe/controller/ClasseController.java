package com.tfe.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfe.model.Classe;
import com.tfe.model.Eleve;
import com.tfe.model.Enseignant;
import com.tfe.repository.IClasseRepository;
import com.tfe.repository.IEleveRepository;
import com.tfe.repository.IEnseignantRepository;

@Controller
@RequestMapping("classe")
public class ClasseController {
	
	//logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IClasseRepository classeDAO;
	
	@Autowired
	IEleveRepository eleveDAO;
	
	@Autowired
	IEnseignantRepository enseignantDAO;

	
	/**
	 * methode GET pour afficher la liste des classes
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String classeList(Model model) {
		log.info("methode pour afficher la liste des classes");
		
		List<Classe> classesList = classeDAO.getClassesWithTitulaire();
		
		model.addAttribute("classesList", classesList);
		
		return "classe/classeList";
		
	}
	
	/**
	 * methode pour afficher les details d'une classe
	 * @param code
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{code}", method=RequestMethod.GET)
	public String classeInfos(@PathVariable String code, Model model) {
		log.info("methode pour afficher les infos de la classe");
		
		Classe classe = classeDAO.findOne(code);
		log.info("classe trouvée");
		List<Eleve> listEleves = eleveDAO.elevesFromClasse(code);
		log.info("nb d'eleves:" + listEleves.size() ); 
		
		model.addAttribute("classe", classe);
		model.addAttribute("eleves", listEleves);
		
		return "classe/classe";
	}
	
	//methode pour ajouter un titulaire à la classe
	@RequestMapping (value="/{code}/titulaire/add", method=RequestMethod.GET)
	public String classeTitulaireAddGet(@PathVariable String code, Model model) {
		
		List<Enseignant> enseignants = enseignantDAO.findAll();
		
		Classe classe = classeDAO.getOne(code);
		
		model.addAttribute("classe", classe);
		model.addAttribute("enseignants", enseignants);
		
		return "classe/titulaireAdd";
	}
	
	@RequestMapping(value="/{code}/titulaire/add", method=RequestMethod.POST)
	public String classeTitulaireAddPost(@PathVariable String code,@RequestParam(value="enseignant_id") String username) {
		
		log.info("methode POST pour encoder un titulaire");
		log.info(username);
		
		Classe classe = classeDAO.getOne(code);
		
		Enseignant titulaire = enseignantDAO.getOne(username);
		classe.setTitulaire(titulaire);
		classeDAO.save(classe);
		
		
		return "redirect:/classe/list";
		
	}

}
