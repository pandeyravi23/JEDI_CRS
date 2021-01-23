package com.flipkart.exception;

public class ProfessorCRSException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private String message;
	
	public ProfessorCRSException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
