package com.devsuperior.movieflix.services.exceptions;

/** Para retornar um 401 customizado */
public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 3810104724255910569L;

	public UnauthorizedException(String msg) {
		super(msg);
	}	
	
}
