package com.devsuperior.movieflix.services.exceptions;

import java.io.Serializable;

/** Criamos essa classe para retornar o erro 403 - Não autorizado */
public class ForbiddenException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 5142480104075648871L;

	public ForbiddenException(String message) {
		super(message);
	}

	
}
