package com.flipkart.service;

import org.apache.log4j.Logger;

import com.flipkart.dao.AdminDAOOperation;

public class AdminOperation implements AdminInterface{

	public static Logger logger = Logger.getLogger(AdminOperation.class);
	AdminDAOOperation adminDAO = new AdminDAOOperation();
	
	@Override
	public void generateReportCard() {
		// TODO Auto-generated method stub
		logger.info("In generate report method");
	}

	@Override
	public void addProfessor() {
		// TODO Auto-generated method stub
		logger.info("In addProfessor method");
	}

	@Override
	public void addAdmin(String username, String password) {
		// TODO Auto-generated method stub
		if(adminDAO.addAdmin(username, password))
		{
			logger.info("Admin added successfully");
		}
		else
		{
			logger.info("Unable to add admin");
		}
	}

	@Override
	public void approveStudent() {
		// TODO Auto-generated method stub
		logger.info("In addProfesoor method");
	}

	@Override
	public void addCourse() {
		// TODO Auto-generated method stub
		logger.info("In addProfesoor method");
	}

	@Override
	public void deleteCourse() {
		// TODO Auto-generated method stub
		logger.info("In addProfesoor method");
	}
	
}
