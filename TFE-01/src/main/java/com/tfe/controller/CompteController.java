package com.tfe.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tfe.exceptions.NotFoundExceptionInt;
import com.tfe.model.Compte;
import com.tfe.model.Eleve;
import com.tfe.model.LigneCompte;
import com.tfe.model.Responsable;
import com.tfe.repository.ICompteRepository;
import com.tfe.repository.IEleveRepository;
import com.tfe.repository.ILigneCompteRepository;
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
	
	@Autowired
	ILigneCompteRepository ligneCompteDAO;
	
	@Autowired
	IEleveRepository eleveDAO;

	
	//methode GET pour créer un compte
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String compteAddGet(@RequestParam(value="nomResponsable", required=false) String nom, Model model, Compte compte) {
		
		log.info("methode GET pour ajouter un compte");
		
		//recherche du titulaire
		if(nom != null) {
			List<Responsable> responsables = responsableDAO.readByNomContainingIgnoringCase(nom);
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
			
		}
		
		
		
		model.addAttribute("compte", compte);
		
		
		
		return "compte/compteAdd";
	}
	
	//methode POST pour créer un compte
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String compteAddPost(@Valid Compte compte, BindingResult errors, @RequestParam(value="titulaires[]", required=false)String[] titulaires,
			@RequestParam(value="responsables[]") String[] responsables, @RequestParam(value="apport", required=false) String montant,
			Model model, RedirectAttributes rModel) {
		
		log.info("methode POST pour créer un compte");
		if(titulaires == null) 
			errors.reject("titulaires", "Vous devez selectionner un moins un titulaire");
		
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
				//creation du compte
				log.info(montant);
				log.info("titulaires: " + titulaires[0].toString());
				compte.setSolde(0.0);
				Compte compteSaved = compteDAO.save(compte);
				for(String titulaire : titulaires) {
					Responsable responsable = responsableDAO.findOne(titulaire);
					responsable.setCompte(compteSaved);
					responsableDAO.save(responsable);
				}
				//ajout d'une ligne de compte
				Date now = new Date();
				Double montantDble = Double.parseDouble(montant);
				LigneCompte ligneCredit = new LigneCompte(compteSaved,now,"CREDIT",montantDble);
				ligneCompteDAO.save(ligneCredit);
				//rModel.addFlashAttribute("compte", compteSaved);
				//rModel.addFlashAttribute("titulaires", titulaires);
				return "redirect:/compte/" + compteSaved.getId();
				
			}
			
		}
		
		
	}
	
	
	//methode pour voir les détails d'un compte à partir de son id
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String compteInfosGet(@PathVariable Long id, Model model) {
		
		log.info("methode pour voir les details d'un compte");
		
		//verifie si le  modele contient dejà le compte et les titulaires suite à une redirection
		if(!model.containsAttribute("compte")) {
			log.info("le model ne contient pas l'attribut compte");
			Compte compte = compteDAO.findOne(id);
			model.addAttribute("compte", compte);
		}
		if(!model.containsAttribute("titulaires")) {
			log.info("le model ne contient pas l'attribut titulaires");
			List<Responsable> titulaires = responsableDAO.getTitulairesFromCompte(id);
			model.addAttribute("titulaires", titulaires);
		}
		
		log.info("model: " + model.toString());
		
		//recuperation des 10 dernieres lignes
		List<LigneCompte> lignes = ligneCompteDAO.get10LastLignesFromCompte(id);
		model.addAttribute("last10Lignes", lignes);
		
		
		return "compte/compte";
	}
	
	
	//methode pour voir la liste de tous les comptes
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String comptesList(Model model) {
		List<Compte> comptesList = compteDAO.findAll();
		model.addAttribute("comptes", comptesList);
		
		return "compte/comptesList";
	}
	
	//methode pour voir la liste des comptes relatifs à un enseignant
	@RequestMapping(value="/{username}/list", method=RequestMethod.GET)
	public String comptesEnseignantList(@PathVariable String username,Model model) {
		log.info("methode pour voir la liste des comptes d'un enseignant");
		
		List<Eleve> eleves = eleveDAO.elevesFromTitulaire(username);
		Set<Compte> comptes = new HashSet<Compte>();
		Compte compte;
		for(Eleve eleve : eleves) {
			compte = compteDAO.getCompteFromEleve(eleve.getId());
			if(compte != null)
			comptes.add(compteDAO.getCompteFromEleve(eleve.getId()));
		}
		model.addAttribute("comptes", comptes);
		
		return "compte/comptesList";
	}
	
	//methode pour voir le détail d'un compte à partir du username du responsable
	@RequestMapping(value="/responsable/{username}", method=RequestMethod.GET)
	public String getCompteFromUsername(@PathVariable("username") String username, Model model, Authentication authentication) {
		
		//verification si le username correspond 
		if(!authentication.getName().equals(username)) {
			return "accessDenied";
		}
		
		//recuperation de l'id du compte
		Long id = compteDAO.getCompteIdFromResponsable(username);
		
		if(id != null) {
			Compte compte = compteDAO.findOne(id);
			model.addAttribute("compte", compte);
		
			List<Responsable> titulaires = responsableDAO.getTitulairesFromCompte(id);
			model.addAttribute("titulaires", titulaires);
		
			//recuperation des 30 dernieres lignes
				List<LigneCompte> lignes = ligneCompteDAO.get30LastLignesFromCompte(id);
				model.addAttribute("last10Lignes", lignes);
			
			
		} else {
			
			model.addAttribute("pasDeCompte", "Vous n'avez pas de compte.");
		}
		
		
		
		return "compte/compte";
		
		
	}
	
	//methode GET pour créditer un compte
	@RequestMapping(value="/{idCompte}/credit", method=RequestMethod.GET)
	public String getCompteCredit(@PathVariable Long idCompte, LigneCompte ligneCompte, Model model) {
		log.info("methode GET pour crediter un compte");
		
		//verifie si le compte existe
		if(!compteDAO.exists(idCompte)) 
			throw new NotFoundExceptionInt("compte.invalide", idCompte);
		
		Compte compte = compteDAO.findOne(idCompte);
		model.addAttribute("compte", compte);
		model.addAttribute("ligneCompte", ligneCompte);
		
		return "compte/compteCredit";
			
		
	}
	
	//methode POST pour créditer un compte
	@RequestMapping(value="/{idCompte}/credit", method=RequestMethod.POST)
	public String postCompteCredit(@PathVariable Long idCompte, @Valid LigneCompte ligneCompte, BindingResult errors, Model model) {
		
		log.info("methode post pour créditer un compte");
		
		if(errors.hasErrors()) {
			log.info("erreurs dans la ligne de compte");
			Compte compte = compteDAO.findOne(idCompte);
			model.addAttribute("compte", compte);
			model.addAttribute("ligneCompte", ligneCompte);
			return"compte/compteCredit";
		}
		
		if (ligneCompte.getMontant()<=0) {
			errors.rejectValue("montant", "compte.credit","le montant doit être positif");
			return "compte/compteCredit";
		}
		
		//ajout de la ligne de compte
		Date date=new Date();
		ligneCompte.setDate(date);
		ligneCompte.setDesignation("CREDIT");
		Compte compte = compteDAO.findOne(idCompte);
		ligneCompte.setCompte(compte);
		ligneCompteDAO.save(ligneCompte);
		
		return "redirect:/compte/{idCompte}";
	}
	
	

}
