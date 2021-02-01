package com.flipkart.service;

import java.util.ArrayList;
import java.util.Scanner;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.dao.AdminDAOOperation;
import com.flipkart.exception.AdminCRSException;
import com.flipkart.util.ValidationOperation;

/**
 * Performs All the Admin Operations
 * and Extends Admin Interface
 * @author JEDI04
 *
 */

public class AdminOperation implements AdminInterface {

	public static Logger logger = Logger.getLogger(AdminOperation.class);
	AdminDAOOperation adminDAO = AdminDAOOperation.getInstance();
	
	// Method for lazy loading
	private static AdminOperation instance = null;
	
	private AdminOperation()
	{
		
	}
	
	synchronized public static AdminOperation getInstance()
	{
		if(instance == null)
		{
			instance = new AdminOperation();
		}
		return instance;
	}

	/**
	 * Generate Report Card for a given Student Id
	 * 
	 */
	@Override
	public ArrayList<JSONObject> generateReportCard(int sid) {
//		if(adminDAO.getRegisteredStudents().size() == 0) {
//			logger.info("   =======================================");
//			return ;
//		}
		logger.info("Please Enter Student ID to Generate Report Card");
//		Scanner sc = new Scanner(System.in);
//		int sid = sc.nextInt();
		ArrayList<JSONObject> reportCard =  adminDAO.printGrades(sid);
		return reportCard;
	}
	
	public ArrayList<JSONObject> getRegisteredStudents()
	{
		return adminDAO.getRegisteredStudents();
	}

	/**
	 * Adds New Professor to the table and throws Exception 
	 * if Failed to Add
	 */
	public int addProfessor(String password, @Valid Professor prof) {
		// TODO Auto-generated method stub
		
		int res = 0;
		try {
//			Scanner sc = new Scanner(System.in);
//	
//			logger.info("Enter the new email : ");
//			
//			String email = ValidationOperation.readEmail();
//			if (email.equals("-1")){
//				throw new AdminCRSException("Professor Not Added\n");
//			}
//			String pwd1 = ValidationOperation.readPassword();
//	
//			logger.info("Please enter name : ");
//			Professor prof = new Professor();
//			prof.setEmail(email);
//			prof.setUserName(sc.nextLine());
//			logger.info("Enter designation : ");
//			prof.setRole(sc.nextLine());
//			logger.info("Enter department : ");
//			prof.setDepartment(sc.nextLine());
//			logger.info("Enter address : ");
//			prof.setAddress(sc.nextLine());
//			logger.info("Enter Age : ");
//			prof.setAge(Integer.parseInt(sc.nextLine()));
//			logger.info("Enter Gender : (male/female) : ");
//			prof.setGender(sc.nextLine());
//			logger.info("Enter contact number : ");
//			prof.setContact(sc.nextLine());
//			logger.info("Enter nationality : ");
//			prof.setNationality(sc.nextLine());
	
			res = adminDAO.addProfessor(password, prof);
			if (res == 1) {
				logger.info("Professor successfully added.");
			} else {
				logger.info("Unable to add professor.");
			}
//		}catch(AdminCRSException e) {
//			logger.warn(e.getMessage());
		}
		catch(Exception e) {
			logger.warn(e.getMessage());
		}
		
		return res;
	}

	/**
	 * Adds New Admin to the table and throws Exception
	 * if Failed to Add
	 */
	@Override
	public int addAdmin(Admin admin, String pwd1) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
//			Scanner sc = new Scanner(System.in);
//			logger.info("Enter the new email : ");
//	
//			
//			String email = ValidationOperation.readEmail();
//			if (email.equals("-1")){
//				throw new AdminCRSException("Admin Not Added\n");
//			}
//			String pwd1 = ValidationOperation.readPassword();
//	
//			Admin admin = new Admin();
//			admin.setEmail(email);
//			logger.info("Enter name : ");
//			admin.setUserName(sc.nextLine());
//			logger.info("Enter address : ");
//			admin.setAddress(sc.nextLine());
//			logger.info("Enter Age : ");
//			admin.setAge(Integer.parseInt(sc.nextLine()));
//			logger.info("Enter Gender : (male/female) : ");
//			admin.setGender(sc.nextLine());
//			logger.info("Enter contact number : ");
//			admin.setContact(sc.nextLine());
//			logger.info("Enter nationality : ");
//			admin.setNationality(sc.nextLine());
	
			res = adminDAO.addAdmin(pwd1, admin);
			if (res == 1) {
				logger.info("Admin added successfully");
			} else {
				throw new AdminCRSException("Unable to add admin");
			}
		}catch(AdminCRSException e)
		{
			logger.warn(e.getMessage());
		}
		catch(Exception e)
		{
			logger.warn(e.getMessage());
		}
		
		return res;
	}

	/**
	 * Approves New Student's Registration After Viewing
	 * the details 
	 */
	@Override
	public boolean approveStudents(int studentID) {
		// TODO Auto-generated method stub
		return adminDAO.approveStudent(studentID);
	}

	/**
	 * Adds course to catalog with the entered details
	 * throws exception if duplicate course id is
	 * provided by the admin
	 */
	@Override
	public void addCourse() {
		// TODO Auto-generated method stub
		//// Exception related to existing course ID
		try {
			logger.info("In addCourse method");
			Course course = new Course();
			Scanner sc = new Scanner(System.in);
			logger.info("Enter Course Name");
			course.setCourseName(sc.nextLine());
			logger.info("Enter Course Id");
			course.setCourseID(sc.nextInt());
			logger.info("Enter Number of Credits");
			course.setCredits(sc.nextInt());
			boolean res = adminDAO.addCourse(course);
			if(res==false)
				throw new AdminCRSException("Failed to Add New Course");
			logger.info("====================================");
			logger.info("Add Course Status : " + res);
			logger.info("====================================");
		} catch (AdminCRSException e) {
			logger.info(e.getMessage());
		}
		catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * Deletes course from course catalog and 
	 * course tables with the entered course id 
	 * by admin
	 */
//	@Override
	public boolean deleteCourse(int courseID) {
		// TODO Auto-generated method stub
		try {
			boolean res = adminDAO.deleteCourse(courseID);
			return res;
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return false;
	}
	
	
	
	/**
	 * Allot course to professor with the entered 
	 * course ID and Professor ID
	 * by Admin
	 */
//	@Override
	public boolean allotCourse(int courseID, int professorID) {
		try {
			adminDAO.showcourses();
			adminDAO.showprofessor();
			return adminDAO.allotCourses(courseID,professorID);
		} catch(Exception e) {
			logger.info(e.getMessage());
		}
		return false;
	}
	
	/**
	 * Opens registration window.
	 */
	public boolean startRegistrationWindow()
	{
		boolean res = false;
		try {
			res = adminDAO.startRegistrationWindow();
		}
		catch(Exception e)
		{
			logger.info(e.getMessage());
		}
		return res;
	}
	
	
	/**
	 * Closes registration window.
	 */
	public boolean closeRegistrationWindow()
	{
		boolean res = false;
		try {
			res = adminDAO.closeRegistrationWindow();
		}
		catch(Exception e)
		{
			logger.info(e.getMessage());
		}
		return res;
	}
	
	/**
	 * Displays list of registered students
	 */
	
	public boolean addCourse2(Course course) {
		// TODO Auto-generated method stub
		//// Exception related to existing course ID
		boolean res = false;
		try {
			res = adminDAO.addCourse(course);
			if(res==false)
				throw new AdminCRSException("Failed to Add New Course");
			logger.info("====================================");
			logger.info("Add Course Status : " + res);
			logger.info("====================================");
		} catch (AdminCRSException e) {
			logger.info(e.getMessage());
		}
		catch (Exception e) {
			logger.info(e.getMessage());
		}
		return res;
	}
}
