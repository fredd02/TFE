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
import com.tfe.model.Inscription;
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
		log.info("id du responsable:" + responsable.getUsername());
		log.info("eleve_id: " + eleve_id);
		
		Responsable responsableSaved = responsableDAO.save(responsable);
		//Eleve eleve = eleveDAO.eleveWithInscriptions(eleve_nrn);
		Eleve eleve = eleveDAO.findOne(eleve_id);
		log.info("eleve recupere");
		log.info("id de l'eleve: " + eleve.getId());
		
		Relation relation = new Relation(eleve,responsableSaved, lienParent);
		relationDAO.save(relation);
		
		
		rModel.addFlashAttribute(responsableSaved);
		
		
		return "redirect:/responsable/"+ responsableSaved.getUsername();
		
		
	}
	
	
	//methode GET pour rechercher un responsable existant
	@RequestMapping(value="/search/{id}", method=RequestMethod.GET)
	public String responsableExistantAdd(@PathVariable Long id, Model model) {
		
		log.info("methode GET pour rechercher un responsable");
		
		Eleve eleve = eleveDAO.getOne(id);
		model.addAttribute("eleve", eleve);
		
		
		return "responsable/responsableExistantAdd";
		
	}
	
	//methode POST pour rechercher un responsable existant
	@RequestMapping(value="/search/{id}", method=RequestMethod.POST)
	public String responsableExistantAddPost(@PathVariable Long id,@RequestParam("nom") String nom, Model model) {
		log.info("methode POST pour rechercher un responsable");
		
		List<Responsable> responsables = responsableDAO.readByNomIgnoringCase(nom);
		model.addAttribute("responsables", responsables);
		
		Eleve eleve = eleveDAO.getOne(id);
		model.addAttribute("eleve", eleve);
		
		return "responsable/responsableExistantAdd";
		
		
	
	}
	
	
	//methode POST pour ajouter les responsables recherchés
	@RequestMapping(value="addSelect/{id}", method=RequestMethod.POST)
	public String responsablesFoundAdd(@PathVariable Long id, @RequestParam(value="responsable[]") String[] responsablesId,
					@RequestParam(value="lien[]") String[] liens) {
		
		
		if(responsablesId.length !=0) {
			log.info("nb de responsables selectionnés: " + responsablesId.length);
			Eleve eleve = eleveDAO.getOne(id);
			
			
			for(String responsableId : responsablesId) {
				int i=0;
				log.info(responsableId.toString());
				Responsable responsable = responsableDAO.getOne(responsableId);
				String lien = liens[i];
				Relation relation = new Relation(eleve, responsable, lien);
				relationDAO.save(relation);
				
								
			}
			
			
		}
		
		return "redirect:/eleve/{id}";
	}
	
	/**
	 * methode GET pour afficher les details d'un responsable
	 */
	
	
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	String responsableInfos(@PathVariable("username") String username, Model model) {
		
		log.info("methode pour afficher les infos d'un responsable");
		
		if(!model.containsAttribute("responsable")) {
			Responsable responsable = responsableDAO.getOne(username);
			model.addAttribute("responsable", responsable);
		}
		
		List<Relation> relations = relationDAO.relationsFromResponsable(username);
		model.addAttribute("relations", relations);
		
		
		return "responsable/responsable";
	}

}
