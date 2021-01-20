package com.flipkart.business;

import org.apache.log4j.Logger;

public class AdminOperation implements AdminInterface{

	public static Logger logger = Logger.getLogger(AdminOperation.class);
	
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
	public void addAdmin() {
		// TODO Auto-generated method stub
		logger.info("In addProfesoor method");
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
