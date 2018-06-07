package com.tfe.controller;

import static org.assertj.core.api.Assertions.registerCustomDateFormat;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tfe.model.Classe;
import com.tfe.model.Compte;
import com.tfe.model.Eleve;
import com.tfe.model.Enseignant;
import com.tfe.model.Inscription;
import com.tfe.model.InscriptionCantine;
import com.tfe.model.LigneCompte;
import com.tfe.repository.IClasseRepository;
import com.tfe.repository.ICompteRepository;
import com.tfe.repository.IEleveRepository;
import com.tfe.repository.IEnseignantRepository;
import com.tfe.repository.IInscriptionCantineRepository;
import com.tfe.repository.ILigneCompteRepository;

import net.minidev.json.parser.ParseException;

@Controller
@RequestMapping("cantine")
public class CantineController {
	
	//logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IEleveRepository eleveDAO;
	
	@Autowired
	IClasseRepository classeDAO;
	
	@Autowired
	IEnseignantRepository enseignantDAO;
	
	@Autowired
	IInscriptionCantineRepository inscriptionCantineDAO;
	
	@Autowired
	ICompteRepository compteDAO;
	
	@Autowired
	ILigneCompteRepository ligneCompteDAO;
	
	@RequestMapping(value="/")
	public String cantine() {
		
		log.info("methode GET cantine");
		return "cantine/cantine";
	}
	
	@RequestMapping(value= "", method = RequestMethod.GET)
	public String redirectionVersHome() {
		return "home";
	}
	
	
	@RequestMapping(value="/download", method=RequestMethod.GET)
	public void getFile(HttpServletResponse response) {
		try {
			DefaultResourceLoader loader = new DefaultResourceLoader();
			InputStream is = loader.getResource("file:C:\\Users\\fred\\Desktop\\Accepted.pdf").getInputStream();
            IOUtils.copy(is, response.getOutputStream());
            response.setHeader("Content-Disposition", "attachment; filename=Accepted.pdf");
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }
	
	/**
	 * méthode GET pour inscrire les enfants d'un responsable à la cantine
	 * @param username
	 * 		username du responsable
	 * @param model
	 * 		objet model à envoyer à la vue
	 * @return
	 * 		nom logique de la vue jsp
	 */
	@RequestMapping(value="/inscription/{username}", method=RequestMethod.GET)
	public String inscriptionCantineGet(@PathVariable String username, Model model) {
		
		//recuperation des eleves du responsable
		List<Eleve> eleves = eleveDAO.getElevesFromResponsable(username);	
		model.addAttribute("eleves", eleves);
	
		GregorianCalendar calendar = new GregorianCalendar();
		Date lundi;
		calendar.setTime(new Date());
		int today = calendar.get(calendar.DAY_OF_WEEK);
		//verifie si on est entre le lundi et vendredi
		if(today >=2 & today <=6) {
			calendar.add(Calendar.DATE, (2-today)); 
			lundi = calendar.getTime();
		} else {
			if(today == 7)
				calendar.add(Calendar.DATE, 2);
			else
				calendar.add(Calendar.DATE, 1);
			lundi = calendar.getTime();
		}
		calendar.add(calendar.DATE, 1);
		Date mardi = calendar.getTime();
		calendar.add(calendar.DATE, 2);
		Date jeudi = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date vendredi = calendar.getTime();
		model.addAttribute("lundi", lundi);
		model.addAttribute("mardi", mardi);
		model.addAttribute("jeudi", jeudi);
		model.addAttribute("vendredi", vendredi);
		
		//verifie les jours qui sont déjà passés
		Date now = new Date();
		if(!lundi.after(now))
			model.addAttribute("lundiPast", true);
		if(!mardi.after(now))
			model.addAttribute("mardiPast", true);
		if(!jeudi.after(now))
			model.addAttribute("jeudiPast", true);
		if(!vendredi.after(now))
			model.addAttribute("vendrediPast", true);
		
		//recuperation des inscriptions
		List<InscriptionCantine> inscriptions = inscriptionCantineDAO.inscriptionFromResponsableBeetwen2Dates(username, lundi, vendredi);
		model.addAttribute("inscriptions", inscriptions);
		return "cantine/cantineInscription";
	}
	
	
	/**
	 * methode POST pour inscrire les élèves à la cantine
	 * @param username
	 * 		username du parent qui effectue la requête
	 * @param lundi
	 * 		recupère les inscriptions du lundi
	 * @param mardi
	 * 		recupère les inscriptions du mardi
	 * @param jeudi
	 * 		recupère les inscriptions du jeudi
	 * @param vendredi
	 * 		recupère les inscriptions du vendredi
	 * @param lundi_SA
	 * 		récupère la date du lundi de la semaine affichée par la vue jsp
	 * 		
	 * @param vendredi_SA
	 * 		récupère la date du vendredi de la semaine affichée par la vue jsp
	 * @param model
	 * 		FlashAttribute permettant de transférer des données lors de la redirection
	 * @return
	 * 		redirige vers la méthode GET d'inscription à la cantine
	 */
	@RequestMapping(value="/inscription/{username}", method=RequestMethod.POST)
	String inscriptionCantinePost(@PathVariable String username, @RequestParam(value="lundi[]", required=false) String[] lundi,
			@RequestParam(value="mardi[]", required=false) String[] mardi, @RequestParam(value="jeudi[]", required=false) String[] jeudi,
			@RequestParam(value="vendredi[]", required=false) String[] vendredi, 
			@RequestParam(value="lundi_SA") String lundi_SA, @RequestParam(value="vendredi_SA") String vendredi_SA,RedirectAttributes model) {
		
		//regroupement des inscriptions dans une liste
		List<String> inscriptions = new ArrayList<String>();
		if(lundi != null)
			inscriptions.addAll(Arrays.asList(lundi));
		if(mardi != null)
			inscriptions.addAll(Arrays.asList(mardi));
		if(jeudi != null)
			inscriptions.addAll(Arrays.asList(jeudi));
		if(vendredi != null)
			inscriptions.addAll(Arrays.asList(vendredi));
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		List<Eleve> eleves = eleveDAO.getElevesFromResponsable(username);
		
		//ajout des inscriptions dans la base de données
		for(Eleve eleve : eleves) {
			for(String inscription : inscriptions) {	
				if(inscription.contains(eleve.getId()+"_")) {
					//String dateString = inscription.substring(3);
					String dateString = inscription.split("_")[1];
					log.info("eleve: " + eleve.getPrenom() + " " + dateString  );
					Date date;
					InscriptionCantine insc;
					
					try {
						date = dateFormat.parse(dateString);
						Long id = inscriptionCantineDAO.getInscriptionCantineWithEleveAndDate(eleve.getId(), date);
						log.info("id: " + id);
						if(id == null) {
							insc = new InscriptionCantine(eleve,date);
							 inscriptionCantineDAO.save(insc);
							
						}
						 
					} catch (java.text.ParseException e) {
						
						e.printStackTrace();
					}
					
					} 
						
				}
			
		}
		
		//supression des inscriptions décochées
		Date lundi_SAD;
		Date vendredi_SAD;
		String idEleve;
		String dateInscription;
		Boolean existe;
		try {
			lundi_SAD = dateFormat.parse(lundi_SA);
			vendredi_SAD = dateFormat.parse(vendredi_SA);
			
			List<InscriptionCantine> inscriptionsBD = inscriptionCantineDAO.inscriptionFromResponsableBeetwen2Dates(username, lundi_SAD, vendredi_SAD);
			for(InscriptionCantine inscriptionBD : inscriptionsBD) {
				existe=false;
				for(String inscription : inscriptions) {
					
					idEleve = inscription.split("_")[0];
					dateInscription = inscription.split("_")[1];
					if ((inscriptionBD.getEleve().getId().toString().equals(idEleve)) && 
							(dateFormat.format(inscriptionBD.getDate()).toString().equals(dateInscription))) {
						existe = true;
						
					}
					
				}
				
				if(!existe)
					inscriptionCantineDAO.delete(inscriptionBD);
					
			}
		} catch (java.text.ParseException e) {
			
			e.printStackTrace();
		}
	
		
		
		String message = "inscription enregistrée";
		
		model.addFlashAttribute("message",message);
		return "redirect:/cantine/inscription/"+username;
	
	}
	
	//methode GET pour afficher la liste des repas facturés pour un responsable
	@RequestMapping(value="/repas/{username}", method=RequestMethod.GET)
	public String getRepasFromResponsable(@PathVariable String username, Model model) {
		
		List<InscriptionCantine> inscriptionsFacturees = inscriptionCantineDAO.inscriptionsFactureesFromResponsable(username);
		log.info("nb d'inscriptions trouvées: " + inscriptionsFacturees.size());
		model.addAttribute("inscriptionsFacturees", inscriptionsFacturees);
		
		return "cantine/repasResponsable";
	}
	
	
	//methode GET pour afficher les eleves inscrits aux repas
	@RequestMapping(value="/inscriptions/{date}", method=RequestMethod.GET)
	public String getInscriptionsFromCantine(@PathVariable @DateTimeFormat(pattern="ddMMyyyy")Date date, Model model, Authentication authentication) {
		
		log.info("methode pour afficher les inscriptions aux repas");
		
		Collection<? extends GrantedAuthority>	 authorities = authentication.getAuthorities();
		log.info(authorities.toString());
		
		log.info("date" + date);
		Date nextDay = DateUtils.addDays(date, 1);
		log.info("nexDay:" + nextDay);
		Date previousDay = DateUtils.addDays(date, -1);
		
		Date now = new Date();
		
		model.addAttribute("date",date);
		model.addAttribute("nextDay",nextDay);
		model.addAttribute("previousDay",previousDay);
		
		//verifie si la date du repas est passée
		Boolean passe = (date.before(now));
		model.addAttribute("date_passee", passe);
		
		//liste des eleves inscrits
		List<InscriptionCantine> inscriptions = inscriptionCantineDAO.getInscriptionsFromDate(date);
		
		//si role ENSEIGNANT, recuperation des eleves de l'enseignant
		for(GrantedAuthority authority : authorities) {
			log.info(authority.getAuthority().toString());
			if(authority.getAuthority().equals("ENSEIGNANT")) {
				log.info("enseigant");
				inscriptions = inscriptionCantineDAO.getInscriptionsFromEnseignantForDate(authentication.getName(), date);
				Enseignant enseignant = enseignantDAO.findOne(authentication.getName());
				model.addAttribute("enseignant", enseignant);
			}
			
		}
		
		//verifie si des repas sont à facturer
		Boolean repasAFacturer = false;
		
		Iterator<InscriptionCantine> iterator = inscriptions.iterator();
		while(iterator.hasNext() && !repasAFacturer) {
			if(!(iterator.next().isPaye()))
				repasAFacturer = true;
		}
		model.addAttribute("repasAFacturer", repasAFacturer);
		
		log.info("nb d'eleves inscrits" + inscriptions.size());
		model.addAttribute("inscriptions", inscriptions);
		
		return "cantine/cantineInscriptionsJour";
		
	}
	
	//methode GET pour facturer la cantine
	@RequestMapping(value="/inscriptions/{date}/facturer", method=RequestMethod.GET)
	public String inscriptionsCantineFacture(@PathVariable @DateTimeFormat(pattern="ddMMyyyy")Date date) {
		
		log.info("methode pour facturer la cantine");
		log.info("date: " + date);
		
		//recuperation des inscriptions non payees du jour
		List<InscriptionCantine> inscriptions = inscriptionCantineDAO.getInscriptionsNonFactureesFromDate(date);
		log.info("nb d'inscriptions: " + inscriptions.size());
		for(InscriptionCantine inscription : inscriptions) {
			
				//recuperation du compte de l'eleve
				Compte compte = compteDAO.getCompteFromEleve(inscription.getEleve().getId());
				Eleve eleve = inscription.getEleve();
				log.info("id eleve: " + eleve.getId());
				Double prix = classeDAO.getClasseFromEleve(eleve.getId()).getPrixCantine();
				log.info("prix cantine: " + prix);
				LigneCompte ligne = new LigneCompte(compte,date,"cantine",-prix,eleve);
				ligneCompteDAO.save(ligne);
				log.info("ligne enregistree");
				
				//mise à jour de l'inscription
				int nbmUpdates = inscriptionCantineDAO.setPayeForInscriptionsFromDate(date);
				log.info("inscription mise à jour");
			
		}
		
		
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		String strDate = format.format(date);
		log.info("date formattee:" + strDate);
		
		
		return "redirect:/cantine/inscriptions/"+strDate;
		
	}
	
	//methode GET pour inscrire un élève à la cantine
	@RequestMapping(value="selectEleve/{dateString}", method=RequestMethod.GET)
	public String addEleveCantine(@PathVariable String dateString, Model model) {
		
		log.info("methode GET pour ajouter un eleve à la cantine");
		log.info("date: " + dateString);
		
		Date date=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		
		try {
			date = formatter.parse(dateString);
			log.info("date: " + date);
		} catch (java.text.ParseException e) {
			
			e.printStackTrace();
		}
		
		model.addAttribute("date", date);
		
		return "cantine/cantineAjoutEleve";
	}
	
	//methode POST pour rechercher l'eleve dans la DB
	@RequestMapping(value="selectEleve/{dateString}", method=RequestMethod.POST)
	public String selectEleveCantine(@RequestParam(value="nom") String nom, 
			@PathVariable String dateString,Model model) {
		log.info("methode POST pour rechercher eleve dans la DB");
		
		log.info("date: " + dateString);
		
		
		
		//List<Eleve> eleves= eleveDAO.readByNomIgnoringCase(nom);
		List<Eleve> eleves= eleveDAO.readByNomContainingIgnoringCaseAndActifIsTrue(nom);
		
		//liste de ces eleves ayant un compte
		List<Eleve> elevesWithCompte = new ArrayList<Eleve>();
		for(Eleve eleve : eleves) {
			if (eleveDAO.getCompteFromEleve(eleve.getId())!= null) {
				elevesWithCompte.add(eleve);
				
			}
		}
		
		
		model.addAttribute("eleves", eleves);
		model.addAttribute("elevesWithCompte", elevesWithCompte);
		
		//liste des inscriptions pour ce jour
		Date date=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		
		try {
			date = formatter.parse(dateString);
			log.info("date: " + date);
		} catch (java.text.ParseException e) {
			
			e.printStackTrace();
		}
		
		model.addAttribute("date", date);
		List<InscriptionCantine> inscriptions = inscriptionCantineDAO.getInscriptionsFromDate(date);
		model.addAttribute("inscriptions", inscriptions);
		
		
		return "cantine/cantineAjoutEleve";
	}
	
	//methode POST pour inscrire les eleves à la cantine
	@RequestMapping(value="inscriptionEleve/{dateString}", method=RequestMethod.POST)
	public String addEleveCantine(@PathVariable String dateString,@RequestParam(value="elevesId[]") String[] elevesId
			) {
		
		log.info("methode POST pour inscrire les eleves à la cantine");
		
		log.info("dateString: " + dateString);
		//formatage de la date
		Date date = new Date();
		
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		
		try {
			date = formatter.parse(dateString);
			log.info("date: " + date);
		} catch (java.text.ParseException e) {
			
			e.printStackTrace();
		}
        

        
		
		//recuperation des eleves selectionnés
		if(elevesId != null) {
			
			
			for(String idString : elevesId) {
				
				Long id = Long.parseLong(idString);
				Eleve eleve = eleveDAO.findOne(id);
				log.info("eleve selectionné: " + eleve.getPrenom());
				InscriptionCantine inscriptionCantine = new InscriptionCantine(eleve,date);
				inscriptionCantineDAO.save(inscriptionCantine);
				
			}
		}
		
		return "redirect:/cantine/inscriptions/"+dateString;
		
	}
	
	
	//methode pour désinscrire un élève de la cantine
	@RequestMapping(value="desinscrire/{dateString}/{eleveId}", method=RequestMethod.GET)
	public String desinscriptionEleveCantine(@PathVariable("dateString") String dateString, @PathVariable("eleveId") String eleveId) {
		
		log.info("methode pour desinscrire un eleve de la cantine");
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		
		try {
			date = formatter.parse(dateString);
			log.info("date: " + date);
		} catch (java.text.ParseException e) {
			
			e.printStackTrace();
		}
		
		Long id = Long.parseLong(eleveId);
		Long inscId= inscriptionCantineDAO.getInscriptionCantineWithEleveAndDate(id, date);
		inscriptionCantineDAO.delete(inscId);
	
		
		return "redirect:/cantine/inscriptions/"+dateString;
	}
	
	//methode GET pour modifier le prix
	@RequestMapping(value="/prix", method=RequestMethod.GET)
	public String getPrixCantine(Model model) {
		log.info("methode GET pour modifier le prix");
		
		Double prixMaternelles = classeDAO.findOne("M0").getPrixCantine();
		Double prixPrimaires = classeDAO.findOne("P1").getPrixCantine();
		log.info("prix maternelles: " + prixMaternelles);
		
		model.addAttribute("prixMaternelles", prixMaternelles);
		model.addAttribute("prixPrimaires", prixPrimaires);
		
		return "cantine/cantinePrix";
	}
	
	//methode POST pour modifier le prix
	@RequestMapping(value="prix", method=RequestMethod.POST)
	public String postPrixCantine(@RequestParam(value="prixMaternelles", required=false) String prixMat,
			@RequestParam(value="prixPrimaires", required=false) String prixPrim, RedirectAttributes rModel) {
		log.info("methode POST pour modifier le prix");
		
		if(prixMat.isEmpty() || prixPrim.isEmpty()) {
			log.info("un champ est nul");
			rModel.addFlashAttribute("erreur", "les champs ne peuvent être vides");
			return "redirect:/cantine/prix";
			
		}
		
		log.info(prixMat);
		Double prixMatDouble = Double.parseDouble(prixMat);
		Double prixPrimDouble = Double.parseDouble(prixPrim);
		
		//modification du prix pour classes de maternelle
		List<Classe> maternelles = classeDAO.findByCodeStartingWith("M");
		for(Classe classe : maternelles) {
			classe.setPrixCantine(prixMatDouble);
			classeDAO.save(classe);
		}
		
		//modification du prix pour classes de primaire
				List<Classe> primaires = classeDAO.findByCodeStartingWith("P");
				for(Classe classe : primaires) {
					classe.setPrixCantine(prixPrimDouble);
					classeDAO.save(classe);
				}
				
				rModel.addFlashAttribute("success", "la modification a été enregistrée");
			
		
		return "redirect:/cantine/prix";
		
		
	}
			

	

}
