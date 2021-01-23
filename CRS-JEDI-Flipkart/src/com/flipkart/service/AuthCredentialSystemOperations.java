package com.flipkart.service;

import org.apache.log4j.Logger;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.dao.StudentDAOOperation;
import com.flipkart.dao.UserDAOOperation;

// @Author JedI04

public class AuthCredentialSystemOperations implements AuthCredentialSystemInterface {

	private Logger logger = Logger.getLogger(AuthCredentialSystemOperations.class);
	private static UserDAOOperation userDaoOperation = new UserDAOOperation();
	private static StudentDAOOperation studentDaoOperation = new StudentDAOOperation();
	

	/*
	 * Returns RoleId if login success
	 * else Return -1;
	 */
	public int login(String username, String password) {
		return userDaoOperation.verifyLoginCredentials(username, password);
	}
	
	public boolean checkEmailAvailability(String email) {
		boolean available = false;
		try {
			available = userDaoOperation.checkEmailAvailability(email);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return available;
	}

	public void registerStudent(User user, Student student, String password) {
		try {
			int id = registerUser(user, password);
			studentDaoOperation.registerStudent(student, id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void registerProfessor(Professor professor, String password) {
		logger.info("registerProfessor");
	}

	public void registerAdmin(Admin admin, String password) {
		logger.info("registerAdmin");
	}

	@Override
	public int registerUser(User user, String password) {
		int id = -1;
		try {
			id = userDaoOperation.registerUser(user, password);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	
}
