package com.flipkart.service;

import org.apache.log4j.Logger;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.UserDaoOperation;

// @Author JedI04

public class AuthCredentialSystemOperations implements AuthCredentialSystemInterface {

	private Logger logger = Logger.getLogger(AuthCredentialSystemOperations.class);

	/*
	 * Returns RoleId if login success
	 * else Return -1;
	 */
	public int login(String username, String password) {
		UserDaoOperation userDaoOperation = new UserDaoOperation();
		return userDaoOperation.verifyLoginCredentials(username, password);
	}

	public void registerStudent(Student student, String password) {
		logger.info("registerStudent");
	}

	public void registerProfessor(Professor professor, String password) {
		logger.info("registerProfessor");
	}

	public void registerAdmin(Admin admin, String password) {
		logger.info("registerAdmin");
	}

	
}
