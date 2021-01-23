package com.flipkart.exception;

public class StudentNotFoundException extends Exception {
	private String message;
	
	public StudentNotFoundException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
