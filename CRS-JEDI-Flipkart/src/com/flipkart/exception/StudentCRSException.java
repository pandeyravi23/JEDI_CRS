package com.flipkart.exception;

public class StudentCRSException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private String message;
	
	public StudentCRSException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
