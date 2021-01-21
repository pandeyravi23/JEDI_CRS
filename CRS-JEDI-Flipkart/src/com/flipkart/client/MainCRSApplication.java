package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.service.AuthCredentialSystem;

public class MainCRSApplication {
	private static Logger logger = Logger.getLogger(MainCRSApplication.class);
	
	public static void main(String[] args) {
		logger.info("Welcome!!!");
		Scanner sc = null;
		
		try {
			logger.info("Login");
			sc = new Scanner(System.in);
			
			logger.info("Enter your email: ");
			String email = sc.nextLine();
			
			logger.info("Enter password: ");
			String pass = sc.nextLine();
			
			AuthCredentialSystem auth = new AuthCredentialSystem();
			int res = auth.login(email, pass);
			
			switch(res) {
				case 1: // Student
					StudentCRSMenu studentClient = new StudentCRSMenu();
					studentClient.init(email);
					break;
				case 2: // Professor
					ProfessorMenu professorClient = new ProfessorMenu();
					professorClient.init(email);
					break;
				case 3: // Admin
				default: 
						logger.info("Invalid credentials");
						break;
			}
			// res.getEmail();
			// res.getRole();
		}
		catch(Exception e) {
			logger.warn(e.getMessage());
		}
	}
}
