package com.flipkart.service;

import org.apache.log4j.Logger;

import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.dao.StudentDAOOperation;
import com.flipkart.dao.UserDAOOperation;

/**
 * Class to handle all authentication processes.
 *
 * @author JEDI 04
 */

public class AuthCredentialSystemOperations implements AuthCredentialSystemInterface {

	private Logger logger = Logger.getLogger(AuthCredentialSystemOperations.class);
	private static UserDAOOperation userDaoOperation = new UserDAOOperation();
	private static StudentDAOOperation studentDaoOperation = new StudentDAOOperation();

	private static AuthCredentialSystemOperations instance = null;

	private AuthCredentialSystemOperations()
	{

	}

	synchronized public static AuthCredentialSystemOperations getInstance()
	{
		if(instance == null)
		{
			instance = new AuthCredentialSystemOperations();
		}
		return instance;
	}

	/**
	 * Method to login into the application and get the role of the user
	 *
	 * @param username Email of the user trying to login
	 * @param password Password of the user trying to login
	 * @return Role of the used
	 */
	public int login(String username, String password) {
		int role = -1;

		try{
			role = userDaoOperation.verifyLoginCredentials(username, password);
		}
		catch (Exception e){
			logger.warn(e.getMessage());
		}

		return role;
	}

	/**
	 * Method to verify whether the email entered exists or not
	 *
	 * @param email Email of the user trying to login
	 * @return True if the email exists else False
	 */
	public boolean checkEmailAvailability(String email) {
		boolean available = false;
		try {
			available = userDaoOperation.checkEmailAvailability(email);
		}
		catch(Exception e) {
			logger.warn(e.getMessage());
		}
		return available;
	}

	/**
	 * Method to register a new student into the system
	 *
	 * @param user Object containing all information about user
	 * @param student Object containing all information about student
	 * @param password Password of the user
	 */
	public void registerStudent(User user, Student student, String password) {
		try {
			int id = registerUser(user, password);
			studentDaoOperation.registerStudent(student, id);
		}
		catch(Exception e) {
			logger.warn(e.getMessage());
		}
	}

	/**
	 * Method to register a new professor or admin into the system
	 *
	 * @param user Object containing all information about user
	 * @param password Password of the user
	 * @return The id of the newly registered user
	 */
	@Override
	public int registerUser(User user, String password) {
		int id = -1;
		try {
			id = userDaoOperation.registerUser(user, password);
		}
		catch(Exception e) {
			logger.warn(e.getMessage());
		}
		return id;
	}

	
}
