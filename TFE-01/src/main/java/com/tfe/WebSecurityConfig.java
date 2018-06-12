package com.tfe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);
		http
			.authorizeRequests()
				.antMatchers("/eleve/add","eleve/**/update","/eleve/**/sup").hasAnyAuthority("ADMIN","DIRECTEUR")
				.antMatchers("/enseignant/add").hasAnyAuthority("ADMIN","DIRECTEUR")
				.antMatchers("/eleve/**","/enseignant/**","/classe/**","/responsable/**").hasAnyAuthority("ADMIN","DIRECTEUR","ENSEIGNANT")
				.antMatchers("/compte/add","/compte/**/credit").hasAnyAuthority("ADMIN","DIRECTEUR")
				.antMatchers("/compte/responsable/**").hasAnyAuthority("PARENT")
				.antMatchers("/compte/**","/compte/list").hasAnyAuthority("ADMIN","DIRECTEUR","ENSEIGNANT")
				.antMatchers("/cantine/inscription/**","/cantine/repas/**").hasAnyAuthority("PARENT")
				.antMatchers("/cantine/inscriptions/**","/cantine/selectEleve/**","cantine/desinscrire/**").hasAnyAuthority("ADMIN","DIRECTEUR","ENSEIGNANT")
				.antMatchers("/**").access("permitAll")
				.and()
					.formLogin().loginPage("/login")
				.and()
					.logout().logoutUrl("/logout").logoutSuccessUrl("/login")
				.and()
					.exceptionHandling().accessDeniedPage("/403");
			
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

}
