package com.kodigo.quiz.exceptions;

public class DataUnavailableException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataUnavailableException (String errorMessage) {
		super(errorMessage);
	}
}
