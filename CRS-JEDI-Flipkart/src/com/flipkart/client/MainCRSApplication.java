package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;


import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.service.AuthCredentialSystemOperations;
import com.flipkart.service.NotificationSystemOperation;

/**
 * The Main Entry Point of our application and shows choices for Registration or Login
 * 
 * @author jedi04
 */
public class MainCRSApplication {
	private static Logger logger = Logger.getLogger(MainCRSApplication.class);
	private static AuthCredentialSystemOperations auth = new AuthCredentialSystemOperations();
	private static Scanner sc = null;

	/**
	 * The Main method for MainCRSApplication
	 * 
	 * @param args Unused
	 * @return Nothing
	 */
	public static void main(String[] args) {
		logger.info("Welcome!!!");
		try {
			sc = new Scanner(System.in);
			int choice;
			do {
				logger.info("1. Student Registration");
				logger.info("2. Login");
				logger.info("3. Exit Application");
				
				choice = sc.nextInt();
				sc.nextLine();
				
				switch(choice) {
					case 1: // Student Registrations
						studentRegistration();
						break;
					case 2: // Login
						login();
						break;
					case 3:
						logger.info(">>>>>>> Exiting Application <<<<<<<<");
						break;
					default:
						logger.info("Invalid choice.\n");
				}
			}while(choice != 3);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Utility Function to Read Email Input
	 * 
	 * @return The email that was read
	 */
	public static String readEmail() {
		String str = "-1";
		try {
			boolean available = false;
			do {
				str = sc.nextLine();
				if(str.compareTo("-1") == 0) 
					break;
				
				available = auth.checkEmailAvailability(str);
				if(!available) {
					logger.info(">>>> Email in use. Enter a new email or -1 to exit. <<<<<<<");
				}
			}while(!available);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * Utility Function to Read Password Input
	 * 
	 * @return The password that was read
	 */
	public static String readPassword() {
		String pwd1 = "-1", pwd2 = "-1";
		try {
			do {
				logger.info("Enter password: ");
				pwd1 = sc.nextLine();
				
//				if(pwd1 == "-1") 
//					break;
				
				logger.info("Re enter password: ");
				pwd2 = sc.nextLine();
				
				if(pwd1.compareTo(pwd2) != 0) {
					logger.info("Passwords dont match.\n\n");
				}
			}while(pwd1.compareTo(pwd2) != 0);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return pwd1;
	}
	
	/**
	 * 
	 * Student Registration Dialogues
	 */
	public static void studentRegistration() {
		logger.info("================ Student Registration =============");
		try {
			
			logger.info("Enter email: ");
			String email = readEmail();
			if(email.compareTo("-1") == 0)
				return;
			
			String password = readPassword();
			
			User user = new User();
			Student student = new Student();
			
			user.setEmail(email);
			student.setEmail(email);
			
			logger.info("Please enter name : ");
			user.setUserName(sc.nextLine());
			student.setUserName(user.getUserName());
			
			logger.info("Enter address : ");
			user.setAddress(sc.nextLine());
			student.setAddress(user.getAddress());
			
			logger.info("Enter Age : ");
			user.setAge(Integer.parseInt(sc.nextLine()));
			student.setAge(user.getAge());
			
			logger.info("Enter Gender : (male/female) : ");
			user.setGender(sc.nextLine());
			student.setGender(user.getGender());
			
			logger.info("Enter contact number : ");
			user.setContact(sc.nextLine());
			student.setContact(user.getContact());
			
			logger.info("Enter nationality : ");
			user.setNationality(sc.nextLine());
			student.setNationality(user.getNationality());
			
			logger.info("Enter branch : ");
			student.setBranch(sc.nextLine());
			
			logger.info("Enter rollno : ");
			student.setRollNo(Integer.parseInt(sc.nextLine()));
			
			user.setApproved(false);
			user.setRole("1");
			student.setIsRegistered(false);
			student.setPaymentStatus(false);
			
			auth.registerStudent(user, student, password);
			
			logger.info(">>>>>>>>>>>>> Student Registration Successful. Admin will approve you within 24hrs. <<<<<<<<<\n");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Login Dialogue
	 */
	public static void login() {
		try {
			logger.info("============Login============");
			
			logger.info("Enter your email: ");
			String email = sc.nextLine();
			
			logger.info("Enter password: ");
			String pass = sc.nextLine();
			
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
					AdminMenu adminClient = new AdminMenu();
					adminClient.AdminClient();
					break;
					
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
