package com.tfe.exceptions;

public class NoAccessException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public NoAccessException(String message) {
		super(message);
	}

}
