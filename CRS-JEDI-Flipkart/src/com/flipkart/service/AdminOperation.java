package com.flipkart.service;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Professor;
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
		Scanner sc = new Scanner(System.in);
		
		logger.info("Enter the new email : ");
		
		String email = sc.nextLine();
		
		if(!adminDAO.verifyEmail(email))
		{
			logger.info("Email already exists.Please retry.");
		}
		
		String pwd1 = "abc";
		String pwd2 = "xyz";
		
		while(!pwd1.equals(pwd2)) {
			logger.info("Enter password : ");
			pwd1 = sc.nextLine();
			logger.info("Re-enter password : ");
			pwd2 = sc.nextLine();
			if(!pwd1.equals(pwd2))
			{
				logger.info("Passwords do not match. Please re-enter.");
			}
		}
		
		
		logger.info("Please enter your name : ");
		Professor prof = new Professor();
		prof.setUserName(sc.nextLine());
		logger.info("Enter your designation : ");
		prof.setRole(sc.nextLine());
		logger.info("Enter your department : ");
		prof.setDepartment(sc.nextLine());
		prof.setEmail(email);
		
		int res = adminDAO.addProfessor(pwd1, prof);
		
		if(res == 1)
		{
			logger.info("Professor successfully added.");
		}
		else
		{
			logger.info("Unable to add professor.");
		}
	}

	@Override
	public void addAdmin(String username, String password) {
		// TODO Auto-generated method stub
		int res = adminDAO.addAdmin(username, password);
		if(res == 1)
		{
			logger.info("Admin added successfully");
		}
		else if(res == 2)
		{
			logger.info("Email already exists. Please retry.");
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
