package com.tfe.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tfe.exceptions.NoAccessException;
import com.tfe.exceptions.NotFoundException;
import com.tfe.model.Classe;
import com.tfe.model.Enseignant;
import com.tfe.model.Role;
import com.tfe.repository.IClasseRepository;
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
	
	@Autowired
	IClasseRepository classeDAO;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	
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
			
			//met l'enseignant comme actif
			enseignant.setActif(true);
			
			
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
		
		log.info("1");
		
		return "enseignant/enseignantList";
	}
	
	//methode GET pour afficher la liste des enseignants actifs
	@RequestMapping(value="/actifslist", method=RequestMethod.GET)
	public String enseignantsActifsList(Model model) {
		log.info("methode pour afficher la liste des enseignants actifs");
		
		List<Enseignant> enseignantsActifsList = enseignantDAO.findByActifTrue();
		log.info("nb enseignants actifs: " + enseignantsActifsList.size());
		model.addAttribute("enseignantsActifsList", enseignantsActifsList);
		
		return "enseignant/enseignantActifsList";
	}
	
	
	//methode pour afficher les détails d'un enseignant
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public String enseignantInfos(@PathVariable String username, Enseignant enseignant, Model model) {
		log.info("methode pour afficher les détails d'un enseignant");
		log.info("username: " + username);
		
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
				throw new NotFoundException("enseignant.notFound", username);
			try {
				enseignantDAO.delete(username);
			} catch (DataIntegrityViolationException e) {
				log.error("SQL", e);
				throw new NoAccessException("suppression impossible: cet enseignant possède des dépendances");
			}
			log.info("suppression de l'enseignant: " + username);
			return "redirect:/enseignant/list";
		}
		
		//methode pour desinscrire un enseignant
		@RequestMapping(value="/{username}/unsubscribe", method=RequestMethod.POST)
		public String enseignantUnsubscribe(@PathVariable String username) {
			log.info("methode POST pour desinscrire un enseignant");
			
			//vérifie si l'enseignant existe
			if(!enseignantDAO.exists(username))
				throw new NotFoundException("enseignant non trouvé", username);
			
				
			
				//verifie si l'enseignant est titulaire d'une ou plusieurs classes
				List<Classe> classes = classeDAO.getClassesFromTitulaire(username);
				if(!classes.isEmpty()) {
					for(Classe classe : classes) {
						classe.setTitulaire(null);
					}
				}
			
			
				Enseignant enseignant = enseignantDAO.findOne(username);
				enseignant.setActif(false);
				enseignantDAO.save(enseignant);
				
				return "redirect:/enseignant/actifslist";
				
			
			
			
			
		}
		
		//methode GET pour modifier un enseignant
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
		
		//methode POST pour modifier un enseignant
		@RequestMapping(value="/{username}/update", method=RequestMethod.POST)
		public String enseignantUpdatePost(@Valid Enseignant enseignant,BindingResult errors,@PathVariable String username, Model model) {
			log.info("methode POST pour updater un enseignant");
			
			if(errors.hasErrors()) {
				log.info("erreurs dans les données de l'enseignant");
				return "enseignant/enseignantUpdate";
				
			} else {
				String password = enseignant.getPassword();
				log.info("nom: " + enseignant.getNom());
				log.info("password: " + password);
				Enseignant enseignantSaved = enseignantDAO.save(enseignant);
				
				return "redirect:/enseignant/" + username;
				
			}
		}
		
		
			
		

	

}
