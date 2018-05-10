package com.tfe.controller;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tfe.exceptions.NoAccessException;
import com.tfe.exceptions.NotFoundExceptionInt;
import com.tfe.model.Classe;
import com.tfe.model.Communication;
import com.tfe.model.CommunicationResponsable;
import com.tfe.model.CommunicationResponsable.Id;
import com.tfe.model.Responsable;
import com.tfe.repository.IClasseRepository;
import com.tfe.repository.ICommunicationRepository;
import com.tfe.repository.ICommunicationResponsableRepository;
import com.tfe.repository.IResponsableRepository;
import com.tfe.service.IStorageService;

@Controller
@RequestMapping("/communication")
public class CommunicationController {
	
	//logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IStorageService storageService;
	
	@Autowired
	IClasseRepository classeDAO;
	
	@Autowired
	ICommunicationRepository communicationDAO;
	
	@Autowired
	IResponsableRepository responsableDAO;
	
	@Autowired
	ICommunicationResponsableRepository communicationResponsableDAO;
	
	//methode GET pour ajouter une communication
	@RequestMapping(value="add", method=RequestMethod.GET)
	public String communicationAddGet(Communication communication, Model model) {
		
		log.info("methode GET pour ajouter une communication");
		
		model.addAttribute("communication", communication);
		
		//liste des liens vers les fichiers
		model.addAttribute("files", storageService.loadAll().map(
				path ->  path.getFileName().toString())
				.collect(Collectors.toList()));
		
		//liste des classes
		List<Classe> classes = classeDAO.getClassesOrderedByCode();
		model.addAttribute("classes", classes);
		
		
		return "communication/communicationAdd";
		
	}
	/**
	 * methode POST pour envoyer une communication
	 * @param communication
	 * 		objet Communication reçu par le formulaire
	 * @param nomFichier
	 * 		nom du fichier selectionné dans le formulaire
	 * @param classes
	 * 		classes concernées par la commuication
	 * @param Model
	 * 			objet Model qui sera renvoyé à la vue
	 * @return
	 * 		chemin logique de la page jsp
	 */
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String communicationAddPost(@Valid Communication communication, BindingResult errors,@RequestParam(value="nomFichier")String nomFichier,
				@RequestParam(value="classeCode", required=false)String[] classesSelect, Model model) {
		
		log.info("methode POST pour envoyer une communication");
		
		//verifie si au moins une classe a été sélectionnée
		if(classesSelect == null) {
			errors.rejectValue("classes", "classe.selection.min");
		}

		if(errors.hasErrors()) {
			model.addAttribute("communication", communication);
			
			//liste des liens vers les fichiers
			model.addAttribute("files", storageService.loadAll().map(
					path ->  path.getFileName().toString())
					.collect(Collectors.toList()));
			
			//liste des classes
			List<Classe> classes = classeDAO.getClassesOrderedByCode();
			model.addAttribute("classes", classes);
			return "communication/communicationAdd";
			
		}
			
		
		
		//URI du fichier
		Path path = storageService.load(nomFichier);
		
		String fileURI = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
				"serveFile", path.getFileName().toString()).build().toString();
		
		communication.setLienFichier(fileURI);
		
		Date dateNow = new Date();
		communication.setDate(dateNow);
		Classe classeToAdd;
		Set<Classe> listClasses = new HashSet<Classe>();
		
		for(String classe : classesSelect) {
			classeToAdd = classeDAO.findOne(classe);
			listClasses.add(classeToAdd);
		}
		
		communication.setClasses(listClasses);
		
		Communication communicationSaved = communicationDAO.save(communication);
		
		//recuperation de la liste des responsables concernés
		Set<Responsable> responsables = new HashSet<Responsable>();
		List<Responsable> responsablesClasse = new ArrayList<Responsable>();
		CommunicationResponsable communicationResponsable;
		
		for(Classe classe : listClasses) {
			responsablesClasse = responsableDAO.getResponsablesFromClasse(classe.getCode());
			responsables.addAll(responsablesClasse);
		}
		
		for(Responsable responsable : responsables) {
			communicationResponsable = new CommunicationResponsable(responsable,communicationSaved,false);
			communicationResponsableDAO.save(communicationResponsable);
			
		}
		
		model.addAttribute("message","la communication a bien été envoyée");
		
		return "communication/status";
		
	}
	
//	@RequestMapping(value="status", method=RequestMethod.GET)
//	public String communicationStatus() {
//		
//		
//		return"communication/status";
//	}
	
	//liste des communications
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String listCommunications(Model model) {
		
		List<Communication> communications = communicationDAO.findAllByOrderByDateDesc();
		
		model.addAttribute("communications", communications);
		
		return "communication/communicationList";
		
	}
	
	//liste des communications d'un responsable
	@RequestMapping(value="{responsable}/list", method = RequestMethod.GET)
	public String listCommunicationsResponsable(@PathVariable(value="responsable") String username,Model model) {
		
		List<CommunicationResponsable> communications = communicationResponsableDAO.getCommunicationsFromResponsable(username);
		
		model.addAttribute("communications", communications);
		
		//nb de communications non lues
		int nbCommunicationsNonLues = communicationResponsableDAO.getNbCommunicationsNonLuesFromResponsable(username);
		
		model.addAttribute("communicationsNonLues", nbCommunicationsNonLues);
		
		return "communication/communicationFromResponsable";
	}
	
	
	//methode pour marquer courrier lu
	@RequestMapping(value="{username}/read/{idCourrier}", method=RequestMethod.GET)
	public String getCourrierRead(@PathVariable(value="username") String username, @PathVariable(value="idCourrier") int idCourrier) {
		log.info("methode GET pour marquer courrier lu");
		
		//idCourrier=idCourrier.substring(1);
		log.info("id courrier: " + idCourrier);
		Long idCour = Long.valueOf(idCourrier);
		Id id = new Id(username,idCour);
		
		
		CommunicationResponsable communication = communicationResponsableDAO.findOne(id);
		log.info(communication.getLu().toString());
		communication.setLu(true);
		log.info(communication.getLu().toString());
		communicationResponsableDAO.delete(id);
		communicationResponsableDAO.save(communication);
		
		return"redirect:/communication/{username}/list";
	}
	
	//methode pour afficher le suivi des communications
	@RequestMapping(value="{idCommunication}/suivi", method=RequestMethod.GET)
	public String getCommunicationSuivi(@PathVariable Long idCommunication, Model model) {
		
		log.info("methode GET pour afficher le suivi des communications");
		log.info("id de la communication: " + idCommunication);
		Communication communication = communicationDAO.getOne(idCommunication);
		
		model.addAttribute("communication", communication);
		
		List<CommunicationResponsable> communicationsResponsableLues = communicationResponsableDAO.getCommunicationsResponsableLuesFromIdCommunication(idCommunication);
		model.addAttribute("communicationsLues", communicationsResponsableLues);
		
		List<CommunicationResponsable> communicationsResponsableNonLues = communicationResponsableDAO.getCommunicationsResponsableNonLuesFromIdCommunication(idCommunication);
		model.addAttribute("communicationsNonLues", communicationsResponsableNonLues);
		
		return "communication/suivi";
		
	}
	
	//methode POST pour supprimer une communication
	@RequestMapping(value="{idCommunication}/delete", method=RequestMethod.POST)
	public String PostCommunicationDelete(@PathVariable Long idCommunication) {
		log.info("methode POST pour supprimer une communication");
		
		if(!communicationDAO.exists(idCommunication))
			throw new NotFoundExceptionInt("communication non trouvée pour suppression ",idCommunication);
		try {
			
			//suppression des dépendances dans la table communicationResponsable
			List<CommunicationResponsable> communicationsResponsable = communicationResponsableDAO.getCommunicationsResponsableForCommunication(idCommunication);
			for(CommunicationResponsable communication : communicationsResponsable) {
				communicationResponsableDAO.delete(communication);
			}
			
			communicationDAO.delete(idCommunication);
		} catch (DataIntegrityViolationException e) {
			log.error("SQL", e);
			throw new NoAccessException("communication.suppression.dependance");
		}
		
		return "redirect:/communication/list";
		
	}

	

}
