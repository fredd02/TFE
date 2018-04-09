package com.tfe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfe.model.User;
import com.tfe.repository.IUserRepository;

@Controller
@RequestMapping("/utils")
public class UtilsController {
	
	//logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	IUserRepository<User> userDAO;

	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String setUser(@RequestParam(value="username") String login,@RequestParam(value="password") String password) {
		
		log.info("methode pour creer un user");
		
		String pwEncrypted = bCryptPasswordEncoder.encode(password);
		
		User user = new User(login,password);
		userDAO.save(user);
		
		
		
		return "login";
		
	}

}
