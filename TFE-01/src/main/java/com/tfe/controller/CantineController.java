package com.tfe.controller;

import static org.assertj.core.api.Assertions.registerCustomDateFormat;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tfe.model.Compte;
import com.tfe.model.Eleve;
import com.tfe.model.Inscription;
import com.tfe.model.InscriptionCantine;
import com.tfe.model.LigneCompte;
import com.tfe.repository.ICompteRepository;
import com.tfe.repository.IEleveRepository;
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
	
	//methode GET pour inscrire les eleves à la cantine
	@RequestMapping(value="/inscription/{username}", method=RequestMethod.GET)
	public String inscriptionCantineGet(@PathVariable String username, Model model) {
		
		//recuperation des eleves du responsable
		List<Eleve> eleves = eleveDAO.getElevesFromResponsable(username);
		log.info("nb d'eleves: " + eleves.size());
		
		model.addAttribute("eleves", eleves);
		
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/YYYY");
		Date lundi;
		calendar.setTime(new Date());
		int today = calendar.get(calendar.DAY_OF_WEEK);
		log.info("jour: " + today);
		if(today >=2 & today <=6) {
			calendar.add(Calendar.DATE, (2-today)); 
			log.info(calendar.getTime().toString());
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
		
		//recuperation des inscriptions
		List<InscriptionCantine> inscriptions = inscriptionCantineDAO.inscriptionFromResponsableBeetwen2Dates(username, lundi, vendredi);
		
		log.info("nb d'inscriptions: " +String.valueOf(inscriptions.size()));
		
		model.addAttribute("inscriptions", inscriptions);
			
		
		
		
		
		return "cantine/cantineInscription";
	}
	
	
	//methode POST pour inscrire à la cantine
	@RequestMapping(value="/inscription/{username}", method=RequestMethod.POST)
	String inscriptionCantinePost(@PathVariable String username, @RequestParam(value="lundi[]", required=false) String[] lundi,
			@RequestParam(value="mardi[]", required=false) String[] mardi, @RequestParam(value="jeudi[]", required=false) String[] jeudi,
			@RequestParam(value="vendredi[]", required=false) String[] vendredi, 
			@RequestParam(value="lundi_SA") String lundi_SA, @RequestParam(value="vendredi_SA") String vendredi_SA,RedirectAttributes model) {
		
		log.info("methode POST pour inscrire à la cantine");
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
					String dateString = inscription.substring(3);
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					} 
						
				}
			
		}
		
		//supression des inscriptions décochées
		log.info("suppression des inscriptions");
		log.info(lundi_SA);
		log.info(vendredi_SA);
		Date lundi_SAD;
		Date vendredi_SAD;
		String idEleve;
		String dateInscription;
		Boolean existe;
		try {
			lundi_SAD = dateFormat.parse(lundi_SA);
			vendredi_SAD = dateFormat.parse(vendredi_SA);
			log.info("lundi: " + lundi_SAD);
			log.info("vendredi: " + vendredi_SAD);
			List<InscriptionCantine> inscriptionsBD = inscriptionCantineDAO.inscriptionFromResponsableBeetwen2Dates(username, lundi_SAD, vendredi_SAD);
			for(InscriptionCantine inscriptionBD : inscriptionsBD) {
				log.info("inscriptionBD:");
				log.info(inscriptionBD.getEleve().getId().toString());
				log.info(dateFormat.format(inscriptionBD.getDate()).toString());
				existe=false;
				for(String inscription : inscriptions) {
					
					idEleve = inscription.split("_")[0];
					log.info("id eleve: " + idEleve);
					//log.info(inscriptionBD.getEleve().getId().toString());
					dateInscription = inscription.split("_")[1];
					log.info("date inscription: " + dateInscription);
					//log.info(dateFormat.format(inscriptionBD.getDate()).toString());
					
					if ((inscriptionBD.getEleve().getId().toString().equals(idEleve)) && 
							(dateFormat.format(inscriptionBD.getDate()).toString().equals(dateInscription))) {
						existe = true;
						
					}
					
				}
				log.info("existe: " + existe);
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
	public String getInscriptionsFromCantine(@PathVariable @DateTimeFormat(pattern="ddMMyyyy")Date date, Model model) {
		
		log.info("methode pour afficher les inscriptions aux repas");
		log.info("date" + date);
		Date nextDay = DateUtils.addDays(date, 1);
		log.info("nexDay:" + nextDay);
		Date previousDay = DateUtils.addDays(date, -1);
		
		model.addAttribute("date",date);
		model.addAttribute("nextDay",nextDay);
		model.addAttribute("previousDay",previousDay);
		
		//liste des eleves inscrits
		List<InscriptionCantine> inscriptions = inscriptionCantineDAO.getInscriptionsFromDate(date);
		
		//verifie si des respas sont à facturer
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
				LigneCompte ligne = new LigneCompte(compte,date,"cantine",-3.80,eleve);
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
			

	

}
