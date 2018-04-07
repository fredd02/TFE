package com.tfe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
	
	//logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String home() {
		
		log.info("controleur home");
		
		return "home";
	}

	

}
