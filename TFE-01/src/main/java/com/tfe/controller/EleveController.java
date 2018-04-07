package com.tfe.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tfe.model.Classe;
import com.tfe.model.Eleve;
import com.tfe.model.Inscription;
import com.tfe.repository.IClasseRepository;
import com.tfe.repository.IEleveRepository;
import com.tfe.repository.IInscriptionRepository;

@Controller
@RequestMapping("/eleve")
public class EleveController {
	
	//logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IEleveRepository eleveDAO;
	
	@Autowired
	IClasseRepository classeDAO;
	
	@Autowired
	IInscriptionRepository inscriptionDAO;
	
	/**
	 * methode GET pour inscrire un élève
	 * @param model
	 * @param eleve
	 * @return
	 */
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String eleveAddGet(Model model, Eleve eleve) {
		
		log.info("methode GET pour ajouter un eleve");
		
		List<Classe> listeClasses = classeDAO.findAll();
		
		model.addAttribute("eleve", eleve);
		model.addAttribute("listeClasses",listeClasses);
		
		return "eleve/eleveAdd";
	}
	
	/**
	 * methode POST pour inscrire un élève
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String eleveAddPost(@Valid Eleve eleve,  BindingResult errors, @RequestParam(value="codeClasse") String codeClasse,Model model, RedirectAttributes rModel) {
		
		log.info("methode POST pour inscrire un élève");
		
		//validation
		if(errors.hasErrors()) {
			return "eleve/eleveAdd";
		}else {
			//ajout de la date du jour pour l'inscription
			Date dateNow = new Date();
			log.info("date inscription: " + dateNow);
			eleve.setDateInscription(dateNow);
			Eleve eleveSaved = eleveDAO.save(eleve);
			log.info("recuperation de la classe");
			Classe classe = classeDAO.findOne(codeClasse);
			log.info("inscription:");
			Inscription inscription = new Inscription(eleveSaved,classe, dateNow);
			
			log.info("enregistrement de l'inscription:" );
			Inscription inscriptionSaved = inscriptionDAO.save(inscription);
			
			
			
			
			
			rModel.addFlashAttribute(eleveSaved);
			
			
			return "redirect:/eleve/list";
			
		}
		
		
	}
	/**
	 * methode GET pour afficher la liste des élèves
	 * @return
	 */
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String eleveList(Model model) {
		log.info("methode GET pour afficher la liste des élèves");
		
		List<Eleve> elevesList = eleveDAO.findAll();
		//recuperation des inscriptions actuelles
		List<Inscription> inscriptionsActuelles = inscriptionDAO.inscriptionsActuelles();
		
		model.addAttribute("inscriptionsActuelles", inscriptionsActuelles);
		model.addAttribute("elevesList", elevesList);
		
		return "eleve/eleveList";
	}
	
	/**
	 * methode pour afficher les infos sur un eleve
	 * @param nrn
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String eleveInfos(@PathVariable Long id, Model model) {
		log.info("id: " + id);
		
		
		Eleve eleve = eleveDAO.findOne(id);
		log.info("nom de l'eleve: " + eleve.getNom());
		
		model.addAttribute("eleve", eleve);
		
		return "eleve/eleve";
	}
	
	/**
	 * methode pour passer l'eleve en classe superieure
	 */
	@RequestMapping(value="/{id}/sup" , method=RequestMethod.GET)
	public String eleveSup(@PathVariable Long id, Model model) {
		log.info("methode pour passer l'eleve en année superieure");
		
		//recuperation de la classe actuelle de l'élève
		String codeClasse = eleveDAO.classeFromEleve(id);
		
		Eleve eleve = eleveDAO.findOne(id);
		model.addAttribute("eleve", eleve);
		model.addAttribute("classe", codeClasse);
		
		return "eleve/eleveSup";
		
	}
	
	
	
		
		

	

}
