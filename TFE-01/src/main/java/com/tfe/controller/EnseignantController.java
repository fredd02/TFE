package com.tfe.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tfe.exceptions.NoAccessException;
import com.tfe.exceptions.NotFoundException;
import com.tfe.model.Enseignant;
import com.tfe.model.Role;
import com.tfe.repository.IEnseignantRepository;
import com.tfe.repository.IRoleRepository;
import com.tfe.service.UserValidator;

@Controller
@RequestMapping("/enseignant")
public class EnseignantController {
	
	//logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());
		
	@Autowired
	IEnseignantRepository enseignantDAO;
	
	@Autowired
	IRoleRepository roleDAO;
	
	@Autowired
	UserValidator userValidator;
	
	
	/**
	 * methode GET pour inscrire un enseignant
	 * @param model
	 * @param enseignant
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String enseignantAddGet(Model model, Enseignant enseignant) {
		log.info("methode GET pour ajouter un enseignant");
		
		model.addAttribute("enseignant", enseignant);
		
		return "enseignant/enseignantAdd";
	}
	
	/**
	 * methode POST pour enregistrer un enseignant
	 * @param enseignant
	 * @param model
	 * @param error
	 * @param rModel
	 * @return
	 */
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String enseignantAddPost(@Valid Enseignant enseignant, BindingResult errors, Model model,  RedirectAttributes rModel) {
		log.info("methode POST pour inscrire un enseignant");
		
		//userValidator.validate(enseignant, errors);
		
		if(errors.hasErrors()) {
			return "enseignant/enseignantAdd";
		} else {
			
			//ajout de la date du jour pour l'inscription
			Date dateNow = new Date();
			enseignant.setDateInscription(dateNow);
			
			
			Role role = roleDAO.findRoleByName("ENSEIGNANT");
			log.info(role.toString());
			enseignant.getRoles().add(role);
			log.info("enseignant: " + enseignant.toString());
			
			log.info("test2");
			
			
						
			Enseignant enseignantSaved = enseignantDAO.save(enseignant);
			
			
			rModel.addAttribute(enseignantSaved);
			
			return "redirect:/enseignant/list";
			
			
		}
	}
	
	
	
	
	
	/**
	 * methode GET pour afficher la liste des enseignants
	 * @return
	 */
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String enseignantsList(Model model) {
		log.info("methode pour afficher la liste des enseignants");
		
		List<Enseignant> enseignantsList = enseignantDAO.findAll();
		model.addAttribute("enseignantsList",enseignantsList);
		
		return "enseignant/enseignantList";
	}
	
	
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public String enseignantInfos(@PathVariable String username, Enseignant enseignant, Model model) {
		
		enseignant = enseignantDAO.getOne(username);
		model.addAttribute("enseignant", enseignant);
		
		
		
		return "enseignant/enseignant";
		
	}
	
	//methode pour supprimer un enseignant
		@RequestMapping(value="/{username}/delete", method = RequestMethod.POST)
		public String enseignantDelete(@PathVariable String username) {
			log.info("methode POST pour supprimer un enseignant");
			
			//vérifie si l'enseignant existe
			if(!enseignantDAO.exists(username))
				throw new NotFoundException("enseignant non trouvé", username);
			try {
				enseignantDAO.delete(username);
			} catch (DataIntegrityViolationException e) {
				log.error("SQL", e);
				throw new NoAccessException("suppression impossible: cet enseignant possède des dépendances");
			}
			log.info("suppression de l'enseignant: " + username);
			return "redirect:/enseignant/list";
		}
		
		//methode pour modifier un enseignant
		@RequestMapping(value="/{username}/update", method = RequestMethod.GET)
		public String enseignantUpdateGet(@PathVariable String username, Model model) {
			log.info("methode GET pour updater un enseignant");
			
			//verifie si l'enseignant existe
			if(!enseignantDAO.exists(username))
				throw new NotFoundException("enseignant non trouvé pour modification", username);
			
			Enseignant enseignant = enseignantDAO.getOne(username);
			model.addAttribute("enseignant", enseignant);
			
			return "enseignant/enseignantUpdate";
		}
		
		
			
		

	

}
