package com.tfe.exceptions;

public class NotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	//identifiant de l'objet recherché
	private String username;

	public NotFoundException(String message, String username) {
		super(message);
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

}
