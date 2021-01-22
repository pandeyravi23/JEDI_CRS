package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Admin;
import com.flipkart.service.AdminOperation;

public class AdminMenu {
	private static Logger logger = Logger.getLogger(AdminMenu.class);
	private AdminOperation adminOperation = new AdminOperation();
	private Admin admin = new Admin();
	
	public void AdminClient()
	{
		logger.info("Welcome Admin.");
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		do
		{		
			showChoices();
			choice = sc.nextInt();
			
			switch(choice)
			{
				case 1:
					adminOperation.generateReportCard();
					break;
				case 2:
					adminOperation.addProfessor();
					break;
				case 3:
//					logger.info("Enter the new email : ");
//					sc.nextLine();
//					
//					String userName = sc.nextLine();
//					
//					String pwd1 = "abc";
//					String pwd2 = "xyz";
//					
//					while(!pwd1.equals(pwd2)) {
//						logger.info("Enter password : ");
//						pwd1 = sc.nextLine();
//						logger.info("Re-enter password : ");
//						pwd2 = sc.nextLine();
//						if(!pwd1.equals(pwd2))
//						{
//							logger.info("Passwords do not match. Please re-enter.");
//						}
//					}
//					
					adminOperation.addAdmin();
					break;
				case 4:
					adminOperation.approveStudent();
					break;
				case 5:
					adminOperation.addCourse();
					break;
				case 6:
					adminOperation.deleteCourse();
					break;
				case -1:
					logger.info("Logged Out Successfully");
					break;
				default:
					logger.info("Invalid Choice");
					break;
			}
		}
		while(choice != -1);
		
	}
	
	public void showChoices()
	{
		logger.info("Select an operation");
		logger.info("Press 1 to generate report card of a student");
		logger.info("Press 2 to add professor");
		logger.info("Press 3 to add Admin");
		logger.info("Press 4 to approve students");
		logger.info("Press 5 to add course");
		logger.info("Press 6 to delete a course");
		logger.info("Press -1 to exit");
	}
}
