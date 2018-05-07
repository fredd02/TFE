package com.tfe.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
    private MessageSource messageSource;
	
	//logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value= {"/","/home"}, method = RequestMethod.GET)
	public String home() {
		
		log.info("controleur home");
		
		return "home";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model, String error, String logout, Locale locale) {
		log.info("login controleur");
		log.info("error: " + error + "|");
		if(error !=null) {
			String message = messageSource.getMessage("login.error",null, "", locale);
			model.addAttribute("error", message);
			
		}
			
		
		if(logout != null)
			model.addAttribute("message","You have been logged out successfully");
		
		return "login";
	}
	
	@RequestMapping("projetPedagogique")
	public String projetPedagogique() {
		return "projetPedagogique";
	}
	
	@RequestMapping("contact")
	public String contact() {
		return "contact";
	}
	
	@RequestMapping("/403")
	public String error403() {
		return "accessDenied";
	}

	

}
