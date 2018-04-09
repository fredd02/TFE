package com.tfe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
	
	//logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value= {"/","/home"}, method = RequestMethod.GET)
	public String home() {
		
		log.info("controleur home");
		
		return "home";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		log.info("login controleur");
		log.info("error: " + error + "|");
		if(error !=null)
			model.addAttribute("error", "Your username and password is invalid");
		
		if(logout != null)
			model.addAttribute("message","You have been logged out successfully");
		
		return "login";
	}

	

}
