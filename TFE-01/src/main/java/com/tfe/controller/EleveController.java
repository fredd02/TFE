package com.tfe.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tfe.exceptions.NotFoundException;
import com.tfe.exceptions.NotFoundExceptionInt;
import com.tfe.model.Classe;
import com.tfe.model.Eleve;
import com.tfe.model.Inscription;
import com.tfe.model.Relation;
import com.tfe.model.Responsable;
import com.tfe.repository.IClasseRepository;
import com.tfe.repository.IEleveRepository;
import com.tfe.repository.IInscriptionRepository;
import com.tfe.repository.IRelationRepository;
import com.tfe.repository.IResponsableRepository;

@Controller
@RequestMapping("/eleve")
public class EleveController {
	
	//afin que les inputs vides soient convertis en null
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	//logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IEleveRepository eleveDAO;
	
	@Autowired
	IClasseRepository classeDAO;
	
	@Autowired
	IInscriptionRepository inscriptionDAO;
	
	@Autowired
	IRelationRepository relationDAO;
	
	@Autowired
	IResponsableRepository responsableDAO;
	
	@InitBinder
	public void initBinder1(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	/**
	 * methode GET pour inscrire un élève
	 * @param model
	 * 		modèle transmis à la vue
	 * @param eleve
	 * 		objet Eleve qui contiendra les informations et sera transmis à la vue grâce au modèle
	 * @return
	 * 		lien vers la page jsp contenant le formulaire d'inscription
	 */
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String eleveAddGet(Model model, Eleve eleve) {
		
		log.info("methode GET pour ajouter un eleve");
		
		List<Classe> listeClasses = classeDAO.getClassesOrderedByCode();
		
		model.addAttribute("eleve", eleve);
		model.addAttribute("listeClasses",listeClasses);
		
		return "eleve/eleveAdd";
	}
	
	/**
	 * méthode GET pour modifier les informations d'un élève
	 * @param idEleve
	 * 		id de l'élève à modifier
	 * @param model
	 * 		modèle qui sera transmis à la vue
	 * @return
	 * 		lien vers la page jsp permettant de modifier les informations
	 */
	
	@RequestMapping(value="{idEleve}/update", method=RequestMethod.GET)
	public String updateEleveGet(@PathVariable Long idEleve, Model model) {
		
		log.info("methode GET pour modifier un eleve");
		
		
		if(!eleveDAO.exists(idEleve))
			throw new NotFoundExceptionInt("eleve.invalide", idEleve);
		
		
		Eleve eleve = eleveDAO.findOne(idEleve);
		model.addAttribute("eleve", eleve);
		
		//recuperation de la date d'inscription 
		// si ce n'est la date du jour, on ne peut pas modifier la classe
		Date dateInscription = eleve.getDateInscription();
		log.info("date inscription: " + dateInscription);
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowStr = sdf.format(now);
		log.info("date today: " + nowStr);
		Boolean  inscriptionToday = nowStr.equals(dateInscription.toString());
		log.info("inscription now: " + inscriptionToday);
		model.addAttribute("inscriptionToday", inscriptionToday);
		
		List<Classe> listeClasses = classeDAO.getClassesOrderedByCode();
		model.addAttribute("listeClasses",listeClasses);
		
		//recuperation de l'inscription actuelle de l'eleve
		Inscription inscription = inscriptionDAO.inscriptionActuelleFromEleve(idEleve);
		model.addAttribute("inscription", inscription);
		
		
		return "eleve/eleveUpdate";
	}
	
	/**
	 * methode POST pour inscrire un élève
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String eleveAddPost(@Valid Eleve eleve,  BindingResult errors, @RequestParam(value="codeClasse") String codeClasse,
			Model model, RedirectAttributes rModel) {
		
		log.info("methode POST pour inscrire un élève");
		log.info(eleve.getPrenom());
	
		
		//validation
		if(errors.hasErrors()) {
			
			
			List<Classe> listeClasses = classeDAO.getClassesOrderedByCode();
			model.addAttribute("listeClasses",listeClasses);
			log.info("erreurs dans les données de l'eleve");
			return "eleve/eleveAdd";
		}else {
			//ajout de la date du jour pour l'inscription
			Date dateNow = new Date();
			log.info("date inscription: " + dateNow);
			eleve.setDateInscription(dateNow);
			
			eleve.setActif(true);
			Eleve eleveSaved = eleveDAO.save(eleve);
			log.info("recuperation de la classe");
			Classe classe = classeDAO.findOne(codeClasse);
			log.info("inscription:");
			Inscription inscription = new Inscription(eleveSaved,classe, dateNow);
			
			log.info("enregistrement de l'inscription:" );
			Inscription inscriptionSaved = inscriptionDAO.save(inscription);
			
			rModel.addFlashAttribute(eleveSaved);
			
			return "redirect:/eleve/"+eleveSaved.getId();
			
		}
		
		
	}
	
	//methode POST pour modifier un eleve
	@RequestMapping(value="{idEleve}/update", method=RequestMethod.POST)
	public String updateElevePost(@Valid Eleve eleve, BindingResult errors, @RequestParam(value="codeClasse", required=false) String codeClasse, 
			Model model, RedirectAttributes rModel)
					 {
		log.info("methode POST pour modifier un eleve");
		log.info("id de l'eleve: " + eleve.getId());
		
		
		//validation
		if(errors.hasErrors()) {
			List<Classe> listeClasses = classeDAO.getClassesOrderedByCode();
			model.addAttribute("listeClasses",listeClasses);
			log.info("erreurs dans les données de l'eleve");
			return "eleve/eleveUpdate";
			
		} else {
			log.info("save eleve");
			Eleve eleveSaved = eleveDAO.save(eleve);
			log.info("code classe: " + codeClasse);
			
			//gestion de l'inscription
			if(codeClasse != null) {
				log.info("modification de l'inscription---");
				Classe classe = classeDAO.findOne(codeClasse);
				
				 Inscription inscriptionActuelle = inscriptionDAO.inscriptionActuelleFromEleve(eleve.getId());
				
				//si inscription
				if(inscriptionActuelle != null) {
					log.info("suppression de l'inscription");
				    inscriptionDAO.delete(inscriptionActuelle);
				    log.info("end of delete");
				    Inscription inscriptionUpdate = new Inscription(inscriptionActuelle.getEleve(),classe, inscriptionActuelle.getDateEntree());
				    
				    log.info("code: " +inscriptionUpdate.getClasse().getCode());
				    inscriptionDAO.save(inscriptionUpdate);
				    inscriptionDAO.flush();
					
				} else {
					//si pas encore d'inscription
					log.info("pas encore d'inscription");
					Date now = new Date();
					Inscription inscription = new Inscription(eleve, classe,now);
					inscriptionDAO.save(inscription);
				}
				
			    
			    
			
			
			}
		
			//rModel.addFlashAttribute(eleveSaved);
			return "redirect:/eleve/"+eleveSaved.getId();
			
			
		}
		
	}
	/**
	 * methode GET pour afficher la liste des élèves
	 * @return
	 */
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String eleveList(Model model) {
		log.info("methode GET pour afficher la liste des élèves");
		
		List<Eleve> elevesList = eleveDAO.getElevesActuels();
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
		
		//recuperation des inscriptions
		List<Inscription> inscriptions = inscriptionDAO.getInscriptionsFromEleve(id);
		model.addAttribute("inscriptions", inscriptions);
		
		//recuperation des relations
		List<Relation> relations = relationDAO.getRelationsFromEleve(id);
		model.addAttribute("relations", relations);
		
		return "eleve/eleve";
	}
	
	/**
	 * methode GET pour passer l'eleve en classe superieure
	 */
	@RequestMapping(value="/{id}/sup" , method=RequestMethod.GET)
	public String eleveSup(@PathVariable Long id, Model model, Inscription inscription) {
		log.info("methode pour passer l'eleve en année superieure");
		
		//recuperation de la classe actuelle de l'élève
		String codeClasse = eleveDAO.classeFromEleve(id);
		
		List<Classe> listeClasses = classeDAO.getClassesOrderedByCode();
		
		Eleve eleve = eleveDAO.findOne(id);
		model.addAttribute("eleve", eleve);
		model.addAttribute("classe", codeClasse);
		model.addAttribute("listeClasses", listeClasses);
		model.addAttribute("inscription", inscription);
		
		return "eleve/eleveSup";
		
	}
	
	//methode POST pour passer l'eleve en classe superieure
	@RequestMapping(value="/{id}/sup", method=RequestMethod.POST)
	public String eleveSupPost(@PathVariable Long id, @RequestParam(value="codeClasse") String codeClasse,
			@RequestParam(value="dateEntree") Date dateEntree) {
		
		log.info("methode POST pour changer inscription");
		
		
		
		//recuperation de l'inscription actuelle
		Inscription inscriptionActuelle = inscriptionDAO.inscriptionActuelleFromEleve(id);
		
		inscriptionActuelle.setDateSortie(dateEntree);
		inscriptionDAO.save(inscriptionActuelle);
		
		Classe classe = classeDAO.findOne(codeClasse);
		Eleve eleve = eleveDAO.findOne(id);
		Inscription inscription = new Inscription(eleve, classe, dateEntree);
		
		log.info("inscription: " + inscription.toString());
		log.info("classe: " + inscription.getClasse().getCode());
		inscriptionDAO.save(inscription);
		
		
		
		return "redirect:/eleve/" + id;
	}
	
	
	//methode POST pour desinscrire un élève
	@RequestMapping(value="/{idEleve}/desinscrire", method=RequestMethod.POST)
	public String eleveDelete(@PathVariable Long idEleve) {
		
		log.info("methode POST pour desinscrire un élève");
		log.info("id eleve: " + idEleve);
		
		Date now = new Date();
		Inscription inscription = inscriptionDAO.inscriptionActuelleFromEleve(idEleve);
		inscription.setDateSortie(now);
		inscriptionDAO.save(inscription);
		
		
	
		
			
		
		
		
		
		
		return "redirect:/eleve/list";
	}
	
	
	
		
		

	

}
