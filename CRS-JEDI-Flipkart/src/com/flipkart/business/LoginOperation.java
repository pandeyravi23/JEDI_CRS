package com.flipkart.business;

import org.apache.log4j.Logger;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

public class LoginOperation {
	
	private Logger logger = Logger.getLogger(LoginOperation.class);
	
	public String login(String username, String password) {
		logger.info("login");
		return "";
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
