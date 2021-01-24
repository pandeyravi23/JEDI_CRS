package com.flipkart.dao;

import java.sql.Connection;
import com.flipkart.bean.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.util.DBConnection;
import com.flipkart.constant.SQLQueriesConstant;
import com.flipkart.service.AdminOperation;


/**
 * @author Jedi04
 * 
 * Admin
 */

public class AdminDAOOperation implements AdminDAOInterface {
	
	private static Logger logger = Logger.getLogger(AdminDAOOperation.class);
	Connection connection = DBConnection.getConnection();
	PreparedStatement ps = null;
	
//	code for lazy loading
	private static AdminDAOOperation instance = null;
	
	private AdminDAOOperation()
	{
		
	}
	
	synchronized public static AdminDAOOperation getInstance()
	{
		if(instance == null)
		{
			instance = new AdminDAOOperation();
		}
		return instance;
	}
	
/**
 * 
 * Verifies Email Address at the time of registration
 * returns false if it already exists in database
 * else returns true.
 * 
 * @param 
 * 
 * 
 */
	
	public boolean verifyEmail(String email)
	{
		try
		{
			String sqlQuery = SQLQueriesConstant.GET_STUDENT_ID_BY_EMAIL;
			ps = connection.prepareStatement(sqlQuery);
			
			ps.setString(1, email);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next())
			{
				return false;
			}
		}
		catch(SQLException e)
		{
			logger.info(e.getMessage());
		}
		catch(Exception e)
		{
			logger.info(e.getMessage());
		}	
		return true;
	}
	
	
	/**
	 * addAdmin adds the new Admin in Credentials table 
	 * and Admin table 
	 * 
	 * @param password User provided password
	 * @param admin Admin details sent inside admin class.
	 * 
	 * @return Returns 1 if new admin is successfully added. Else returns 0.
	 */
	public int addAdmin(String password, Admin admin)
	{
		try
		{
			String sqlQuery = SQLQueriesConstant.ADD_USER_TO_CREDENTIALS;
			ps = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, 3);
			ps.setString(2,  admin.getEmail());
			ps.setString(3, password);
			ps.setInt(4,  1);
			ps.setString(5, admin.getAddress());
			ps.setInt(6, admin.getAge());
			ps.setString(7, admin.getGender());
			ps.setString(8,  admin.getContact());
			ps.setString(9, admin.getNationality());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
			rs.next();
			admin.setUserId(rs.getInt(1));
			
			String adminQuery = SQLQueriesConstant.ADD_NEW_ADMIN;
			ps = connection.prepareStatement(adminQuery);
			ps.setInt(1, admin.getUserId());
			ps.setString(2, admin.getUserName());
			ps.setString(3, admin.getEmail());
			
			ps.executeUpdate();
			return 1;
		}
		catch(SQLException e)
		{
			logger.info(e.getMessage());
		}
		catch(Exception e)
		{
			logger.info(e.getMessage());
		}	
		return 0;
	}
	

	/**
	 * It is responsible for adding professor to the database.
	 * addProfessor adds the new Professor in Credentials table 
	 * and Professor table 
	 * 
	 * @param password The password provided by user
	 * @param prof Professor details provided by the user
	 * 
	 * @return Returns 1 if professor is successfully added. Else returns 0.
	 */
	
	public int addProfessor(String password, Professor prof)
	{
		try
		{
			String credQuery = SQLQueriesConstant.ADD_USER_TO_CREDENTIALS;
			ps = connection.prepareStatement(credQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 2);
			ps.setString(2,  prof.getEmail());
			ps.setString(3, password);
			ps.setInt(4,  1);
			ps.setString(5, prof.getAddress());
			ps.setInt(6, prof.getAge());
			ps.setString(7, prof.getGender());
			ps.setString(8,  prof.getContact());
			ps.setString(9, prof.getNationality());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
			rs.next();
			prof.setUserId(rs.getInt(1));
			
			String profQuery = SQLQueriesConstant.ADD_NEW_PROFESSOR;
			ps = connection.prepareStatement(profQuery);
			ps.setInt(1, prof.getUserId());
			ps.setString(2, prof.getUserName());
			ps.setString(3, prof.getEmail());
			ps.setString(4, prof.getRole());
			ps.setString(5,  prof.getDepartment());
			ps.executeUpdate();
			
			return 1;
		}
		catch(SQLException e)
		{
			logger.info(e.getMessage());
		}
		catch(Exception e)
		{
			logger.info(e.getMessage());
		}	
		return 0;
	}
	
	
	/**
	 * printGrades Prints the Report of 
	 * a Particular Student given its
	 * Student ID
	 */
	public void printGrades(int studentId) {
		try {
			String str = SQLQueriesConstant.GET_GRADES_BY_STUDENT_ID ; 
			ps = connection.prepareStatement(str);
			ps.setInt(1, studentId);
			ResultSet rs =  ps.executeQuery();
			String name="";
			if(rs.next())
				name = rs.getString("sname");
			else {
				logger.info("No Student with Student ID: " + studentId + " Exists!!");
				logger.info("=======================================");
				return ;
			}
			logger.info("=======================================");
			logger.info("        Report Card of " + name + " :");
			logger.info("=======================================");
			logger.info("CourseID     Course Name     Grade");
			if(name!="") {
				do{
					logger.info(rs.getInt("courseId") + "          " + rs.getString("name") + "         " + rs.getString("grade"));
				}while(rs.next());
			}
			logger.info("=======================================");
			
		}
		catch(SQLException e)
		{
			logger.info(e.getMessage());
		}
		catch(Exception e)
		{
			logger.info(e.getMessage());
		}	
		return ;
	}
	
	
	/*
	 * approveStudent Approves the Student Registration
	 * and Allows them to login and Register Courses
	 */
	public void approveStudent() {
		try {
			Scanner sc = new Scanner(System.in);
			String str = SQLQueriesConstant.GET_STUDENT_DETAILS;
			ps = connection.prepareStatement(str);
			ResultSet rs = ps.executeQuery();
			logger.info("Student details are as follows - ");
			logger.info("=======================================");
			while(rs.next()) {
				logger.info("Student Name: " + rs.getString("name"));
				logger.info("Student ID: " + rs.getInt("id"));
				logger.info("Email: " + rs.getString("email"));
				logger.info("Address: " + rs.getString("address"));
				logger.info("Age: " + rs.getInt("age"));
				logger.info("Gender: " + rs.getString("gender"));
				logger.info("Contact Number: " + rs.getString("contact"));
				logger.info("Nationality: " + rs.getString("nationality"));
				logger.info("=======================================");
				logger.info("Enter 'yes' to Approve, 'no' to Reject");
				String choice = sc.next();
				if(choice.equals("yes")) {
					String res = SQLQueriesConstant.UPDATE_USER_IN_CREDENTIALS;
					ps = connection.prepareStatement(res);
					ps.setInt(1, rs.getInt("id"));
					ps.executeUpdate();
					logger.info("Student Approved !!");
					logger.info("=======================================");
				}
				else{
					String res = SQLQueriesConstant.DELETE_USER_FROM_CREDENTIALS;
					ps = connection.prepareStatement(res);
					ps.setInt(1, rs.getInt("id"));
					ps.executeUpdate();
					res = SQLQueriesConstant.DELETE_STUDENT_BY_ID;
					ps = connection.prepareStatement(res);
					ps.setInt(1, rs.getInt("id"));
					ps.executeUpdate();
					logger.info("Student Rejected !!");
					logger.info("=======================================");
				}
			}
			
		}
		catch(SQLException e)
		{
			logger.info(e.getMessage());
		}
		catch(Exception e)
		{
			logger.info(e.getMessage());
		}	
	}
	
	
	/*
	 * Query to add a course to a courseCatalog by Admin
	 * only if course id provided is unique
	 */
	
	public boolean addCourse(Course course) {
		try {
			String str = SQLQueriesConstant.ADD_COURSE_IN_CATALOG;
			ps = connection.prepareStatement(str);
			ps.setString(1, course.getCourseName());
			ps.setInt(2,course.getCourseID());
			ps.setInt(3, course.getCredits());
			int val = ps.executeUpdate();
			if(val>0) {
				return true;
			}
		}
		catch(SQLException e)
		{
			logger.info(e.getMessage());
		}
		catch(Exception e)
		{
			logger.info(e.getMessage());
		}	
		return false;
	}
	
	
	/*
	 * Delete course from course catalog and course tables
	 * where id matches the given courseId;
	 */
	
	public boolean deleteCourse(int courseId) {
		try {
			String str = SQLQueriesConstant.DELETE_COURSE_IN_CATALOG;
			ps = connection.prepareStatement(str);
			ps.setInt(1, courseId);
			int val = ps.executeUpdate();
			str = "delete from course where id = ?";
			ps = connection.prepareStatement(str);
			ps.setInt(1, courseId);
			int val2 = ps.executeUpdate();
			if (val>0) {
				return true;
			}
		}
		catch(SQLException e)
		{
			logger.info(e.getMessage());
		}
		catch(Exception e)
		{
			logger.info(e.getMessage());
		}	
		return false;
	}
	
	/*
	 * Assign course with particular course ID to the
	 * professor with ID entered by the Admin
	 */
	
	public void allotCourses(int courseId,int professorID) {
		try {
//			logger.info("Inside Allot courses method-------------");
			String str = SQLQueriesConstant.GET_COURSE_NAME_BY_ID;
			ps = connection.prepareStatement(str);
			ps.setInt(1,courseId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
//				logger.info("Inside update professorID method-------------");
				str = SQLQueriesConstant.UPDATE_PROFESSOR_IN_COURSE;
				ps = connection.prepareStatement(str);
				ps.setInt(1,professorID);
				ps.setInt(2, courseId);
				int status = ps.executeUpdate();
				if(status>0) {
					logger.info("Course Alloted Successfully");
					logger.info("=======================================");
				}
				else {
					logger.info("Failed");
					logger.info("=======================================");
				}
			}
			else {
				str = SQLQueriesConstant.GET_COURSE_CATALAOG_QUERY;
				ps = connection.prepareStatement(str);
				ps.setInt(1,courseId);
				rs = ps.executeQuery();
				if(rs.next()==false) {
					logger.info("No course with Course ID: " + courseId + " Exists!!");
					logger.info("=======================================");
					return ;
				}
				 do{
					str = SQLQueriesConstant.ADD_NEWCOURSE_IN_COURSE;
					ps = connection.prepareStatement(str);
					ps.setInt(1,courseId);
					ps.setString(2,rs.getString("courseName"));
					ps.setInt(3,professorID);
					ps.setInt(4,rs.getInt("credits"));
					ps.executeUpdate();
				}while(rs.next());
				logger.info("Course Alloted Successfully");
				logger.info("=======================================");
			}
		}
		catch(SQLException e)
		{
			logger.info(e.getMessage());
		}
		catch(Exception e)
		{
			logger.info(e.getMessage());
		}	
	}
}
