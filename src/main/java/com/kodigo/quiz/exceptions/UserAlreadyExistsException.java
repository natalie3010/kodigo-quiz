package com.kodigo.quiz.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException (String errorMessage){
		super(errorMessage);
	}
}
