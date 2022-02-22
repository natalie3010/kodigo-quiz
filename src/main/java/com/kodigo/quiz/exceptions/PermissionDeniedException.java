package com.kodigo.quiz.exceptions;

public class PermissionDeniedException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PermissionDeniedException (String errorMessage) {
		super(errorMessage);
	}
}
