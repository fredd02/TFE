package com.tfe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.tfe.exceptions.NoAccessException;
import com.tfe.exceptions.NotFoundException;
import com.tfe.exceptions.NotFoundExceptionInt;

@ControllerAdvice
public class CentralExceptionHandle {

	@ExceptionHandler({NoAccessException.class,
		NotFoundException.class, NotFoundExceptionInt.class})
	private ModelAndView doublonHandler(HttpServletRequest req, Exception e) {
		ModelAndView m = new ModelAndView();
		m.addObject("exception", e);
		m.addObject("url", req.getRequestURL());
		m.setViewName("error");// nom logique de la page d'erreur
		return m;
	}

}
