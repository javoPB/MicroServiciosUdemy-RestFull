package com.in28minutes.rest.webservices.restfulwebservices.userpermiso;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserPermisosNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5312551138704379585L;

	public UserPermisosNotFoundException(String message) {
		super(message);
	}

	
}
