package com.flipkart.service;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Admin;
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

		if(adminDAO.verifyEmail(email)==false)
		{
			logger.info("Email already exists.Please retry.");
			return;
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
		
		logger.info("Please enter name : ");
		Professor prof = new Professor();
		prof.setEmail(email);
		prof.setUserName(sc.nextLine());
		logger.info("Enter designation : ");
		prof.setRole(sc.nextLine());
		logger.info("Enter department : ");
		prof.setDepartment(sc.nextLine());
		logger.info("Enter address : ");
		prof.setAddress(sc.nextLine());
		logger.info("Enter Age : ");
		prof.setAge(Integer.parseInt(sc.nextLine()));
		logger.info("Enter Gender : (male/female) : ");
		prof.setGender(sc.nextLine());
		logger.info("Enter contact number : ");
		prof.setContact(sc.nextLine());
		logger.info("Enter nationality : ");
		prof.setNationality(sc.nextLine());
		
		
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
	public void addAdmin() {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		logger.info("Enter the new email : ");
		
		String email = sc.nextLine();
		if(adminDAO.verifyEmail(email)==false)
		{
			logger.info("Email already exists.Please retry.");
			return;
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
		
		Admin admin = new Admin();
		admin.setEmail(email);
		logger.info("Enter name : ");
		admin.setUserName(sc.nextLine());
		logger.info("Enter address : ");
		admin.setAddress(sc.nextLine());
		logger.info("Enter Age : ");
		admin.setAge(Integer.parseInt(sc.nextLine()));
		logger.info("Enter Gender : (male/female) : ");
		admin.setGender(sc.nextLine());
		logger.info("Enter contact number : ");
		admin.setContact(sc.nextLine());
		logger.info("Enter nationality : ");
		admin.setNationality(sc.nextLine());
		
		int res = adminDAO.addAdmin(pwd1, admin);
		if(res == 1)
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
