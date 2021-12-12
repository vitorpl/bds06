package com.devsuperior.movieflix.services.exceptions;

public class DatabaseException extends RuntimeException {

	private static final long serialVersionUID = 5172298641329460495L;

	public DatabaseException(String msg) {
		super(msg);
	}	
	
}
