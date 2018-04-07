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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tfe.model.Eleve;
import com.tfe.model.Relation;
import com.tfe.model.Responsable;
import com.tfe.repository.IEleveRepository;
import com.tfe.repository.IRelationRepository;
import com.tfe.repository.IResponsableRepository;

@Controller
@RequestMapping("/responsable")
public class ResponsableController {
	
	//logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IEleveRepository eleveDAO;
	
	@Autowired
	IResponsableRepository responsableDAO;
	
	@Autowired
	IRelationRepository relationDAO;

	
	/**
	 * methode GET pour ajouter un responsable
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add/{id}", method=RequestMethod.GET)
	String ResponsableAddGet(@PathVariable Long id, Model model) {
		
		log.info("methode GET pour ajouter un responsable");
		Responsable responsable = new Responsable();
	
		Eleve eleve = eleveDAO.getOne(id);
		model.addAttribute("eleve", eleve);
		model.addAttribute("responsable", responsable);
		
		return "responsable/responsableAdd";
		
	}
	
	/**
	 * methode POST pour ajouter un responsable
	 * 
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	String ResponsableAddPost(@RequestParam("eleve_id") Long eleve_id,@RequestParam("lienParent") String lienParent,Responsable responsable, Model model,
			RedirectAttributes rModel) {
		log.info("methode POST pour ajouter un responsable");
		log.info("id du responsable:" + responsable.getId());
		log.info("eleve_id: " + eleve_id);
		
		Responsable responsableSaved = responsableDAO.save(responsable);
		//Eleve eleve = eleveDAO.eleveWithInscriptions(eleve_nrn);
		Eleve eleve = eleveDAO.findOne(eleve_id);
		log.info("eleve recupere");
		log.info("id de l'eleve: " + eleve.getId());
		
		Relation relation = new Relation(eleve,responsableSaved, lienParent);
		relationDAO.save(relation);
		
		
		rModel.addFlashAttribute(responsableSaved);
		
		
		return "redirect:/responsable/"+ responsableSaved.getNrn();
		
		
	}
	
	/**
	 * methode GET pour afficher les details d'un responsable
	 */
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	String responsableInfos(@PathVariable("id") Long id, Model model) {
		
		log.info("methode pour afficher les infos d'un responsable");
		
		if(!model.containsAttribute("responsable")) {
			Responsable responsable = responsableDAO.findOne(id);
			model.addAttribute("responsable", responsable);
		}
		
		List<Relation> relations = relationDAO.relationsFromResponsable(id);
		model.addAttribute("relations", relations);
		
		
		return "responsable/responsable";
	}

}
