package com.tfe;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import org.springframework.web.multipart.support.MultipartFilter;

@Order(1)
public class SecurityApplicationInitializer extends AbstractSecurityWebApplicationInitializer{
	

	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		insertFilters(servletContext, new MultipartFilter());

	}

}
