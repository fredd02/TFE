package com.tfe.exceptions;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Elément non trouvé !")
public class NotFoundExceptionInt extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	//identifiant de l'objet recherché
	private Long id;

	public NotFoundExceptionInt(String message, Long id) {
		super(message);
		this.id = id;
		
	}
	
	public Long getId() {
		return id;
	}

}
