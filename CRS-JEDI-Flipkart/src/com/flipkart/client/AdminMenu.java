package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;
import com.flipkart.service.AdminOperation;

/**
 * The Client Side Application for displaying and going forward with Administrator related operations and 
 * functionalities.
 * 
 * @author jedi04
 */
public class AdminMenu {
	private static Logger logger = Logger.getLogger(AdminMenu.class);
	private AdminOperation adminOperation = AdminOperation.getInstance();
	
	/**
	 * Main Function to display, choose and then call the respective Administrator Operations.
	 */
	public void AdminClient()
	{
		logger.info("Welcome Admin.");
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
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
						adminOperation.addAdmin();
						break;
					case 4:
						adminOperation.approveStudents();
						break;
					case 5:
						adminOperation.addCourse();
						break;
					case 6:
						adminOperation.deleteCourse();
						break;
					case 7:
						adminOperation.allotCourse();
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
		catch(Exception e) {
			logger.info(e.getMessage() + "\n");
		}
	}
	
	
	/**
	 * Utility Function to display Administrator operations, which can then be called from AdminClient
	 */
	public void showChoices()
	{
		logger.info("Select an operation");
		logger.info("Press 1 to generate report card of a student");
		logger.info("Press 2 to add professor");
		logger.info("Press 3 to add Admin");
		logger.info("Press 4 to approve students");
		logger.info("Press 5 to add course");
		logger.info("Press 6 to delete a course");
		logger.info("Press 7 to allot a course to the professor");
		logger.info("Press -1 to exit");
	}
}
