package com.tfe.controller;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tfe.model.Classe;
import com.tfe.model.Communication;
import com.tfe.model.CommunicationResponsable;
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
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String communicationAddPost(Communication communication,@RequestParam(value="nomFichier", required=false)String nomFichier,
				@RequestParam(value="classeCode")String[] classes, RedirectAttributes rModel) {
		
		log.info("methode POST pour communication");
		
		if(nomFichier != null)
			log.info("nom du fichier: " + nomFichier);
		
		//URI du fichier
		Path path = storageService.load(nomFichier);
		log.info("path du fichier: " + path.toString());
		
		String fileURI = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
				"serveFile", path.getFileName().toString()).build().toString();
		
		log.info(fileURI);
		communication.setLienFichier(fileURI);
		
		
		
		
		Date dateNow = new Date();
		communication.setDate(dateNow);
		Classe classeToAdd;
		Set<Classe> listClasses = new HashSet<Classe>();
		
		for(String classe : classes) {
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
		
		rModel.addFlashAttribute("message","la communication a bien été envoyée");
		
		
		
		return "redirect:status";
		
	}
	
	@RequestMapping(value="status", method=RequestMethod.GET)
	public String communicationStatus() {
		
		
		return"communication/status";
	}
	
	//liste des communications
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String listCommunications(Model model) {
		
		List<Communication> communications = communicationDAO.findAll();
		
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

	

}
