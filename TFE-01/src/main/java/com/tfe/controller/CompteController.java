package com.tfe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

import com.tfe.model.Compte;
import com.tfe.model.Responsable;
import com.tfe.repository.ICompteRepository;
import com.tfe.repository.IResponsableRepository;

@Controller
@RequestMapping("/compte")
public class CompteController {
	
	//logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IResponsableRepository responsableDAO;
	
	@Autowired
	ICompteRepository compteDAO;

	
	//methode GET pour créer un compte
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String compteAddGet(@RequestParam(value="nomResponsable", required=false) String nom, Model model, Compte compte) {
		
		log.info("methode GET pour ajouter un compte");
		
		//recherche du titulaire
		List<Responsable> responsables = responsableDAO.readByNomIgnoringCase(nom);
		List<Responsable> responsablesAndCo = new ArrayList<Responsable>(responsables);
		String username;
		List<Responsable> Coresponsables = new ArrayList<Responsable>();
		log.info("etape1");
		for(Responsable responsable : responsables) {
			username = responsable.getUsername();
			log.info(username);
			Coresponsables = responsableDAO.getCoResponsablesFrom(username);
			log.info("etape2");
			for(Responsable Cores : Coresponsables) {
				responsablesAndCo.add(Cores);
			}
		}
		//elimination des doublons
		List<Responsable> responsablesAndCoDistincts = new ArrayList<Responsable>();
		for(Responsable responsable : responsablesAndCo) {
			if(!responsablesAndCoDistincts.contains(responsable)) {
				responsablesAndCoDistincts.add(responsable);
			}
		}
		
		model.addAttribute("responsables", responsablesAndCoDistincts);
		model.addAttribute("compte", compte);
		
		
		
		return "compte/compteAdd";
	}
	
	//methode POST pour créer un compte
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String compteAddPost(@Valid Compte compte, BindingResult errors, @RequestParam(value="titulaires[]", required=false)String[] titulaires,
			@RequestParam(value="responsables[]") String[] responsables, Model model, RedirectAttributes rModel) {
		
		log.info("methode POST pour créer un compte");
		
		//validation
		if(errors.hasErrors()) {
			log.info("erreurs");
			//ajout des responsables au modele
			List<Responsable> listResponsables = new ArrayList<Responsable>();
			for(String username : responsables) {
				listResponsables.add(responsableDAO.findOne(username));
			}
			model.addAttribute("responsables", listResponsables);
			return "compte/compteAdd";
		} else {
			if(titulaires == null) {
				errors.reject("titulaires", "Vous devez selectionner un moins un titulaire");
				//ajout des responsables au modele
				List<Responsable> listResponsables = new ArrayList<Responsable>();
				for(String username : responsables) {
					listResponsables.add(responsableDAO.findOne(username));
				}
				model.addAttribute("responsables", listResponsables);
				return "compte/compteAdd";
			} else {
				log.info("titulaires: " + titulaires[0].toString());
				Compte compteSaved = compteDAO.save(compte);
				for(String titulaire : titulaires) {
					Responsable responsable = responsableDAO.findOne(titulaire);
					responsable.setCompte(compteSaved);
					responsableDAO.save(responsable);
				}
				rModel.addFlashAttribute("compte", compteSaved);
				rModel.addFlashAttribute("titulaires", titulaires);
				return "redirect:/compte/" + compteSaved.getId();
				
			}
			
		}
		
		
	}
	
	
	//methode pour voir les détails d'un compte
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String compteInfosGet(@PathVariable Long id, Model model) {
		
		log.info("methode pour voir les details d'un compte");
		
		//verifie si le  modele contient dejà le compte et les titulaires suite à une redirection
		if(!model.containsAttribute("compte")) {
			Compte compte = compteDAO.findOne(id);
			model.addAttribute("compte", compte);
		}
		if(!model.containsAttribute("titulaires")) {
			List<Responsable> titulaires = responsableDAO.getTitulairesFromCompte(id);
			model.addAttribute("titulaires", titulaires);
		}
		
		
		return "compte/compte";
	}
	
	
	//methode pour voir la liste des comptes
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String comptesList(Model model) {
		List<Compte> comptesList = compteDAO.findAll();
		model.addAttribute("comptes", comptesList);
		
		return "compte/comptesList";
	}
	
	

}
