package com.restful.restfulservice.resident.exception;

@SuppressWarnings("serial")
public class EmailAlreadyExistsException extends RuntimeException {
	
	public EmailAlreadyExistsException(String email) {
		super("Email address + '" + email + "' already exists");
	}
}
