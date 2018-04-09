package com.tfe.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tfe.model.Role;
import com.tfe.model.User;
import com.tfe.repository.IUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	//logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserRepository userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("userDetails");
		User user = userDAO.findByUsernameWithRoles(username);
		log.info(user.getUsername());
		log.info("nb de roles");
		log.info("roles: " + String.valueOf(user.getRoles().size()));
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for(Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),grantedAuthorities);
	}

}
