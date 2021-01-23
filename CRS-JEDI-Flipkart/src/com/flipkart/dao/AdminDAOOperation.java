package com.flipkart.dao;

import java.sql.Connection;
import com.flipkart.bean.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.util.DBConnection;

public class AdminDAOOperation implements AdminDAOInterface {
	
	private static Logger logger = Logger.getLogger(AdminDAOOperation.class);
	Connection connection = DBConnection.getConnection();
	PreparedStatement ps = null;
	
//	returns false if the email is already present in the database. Returns true otherwise.
	public boolean verifyEmail(String email)
	{
		try
		{
			String sqlQuery = "select email from credentials where email = ?";
			ps = connection.prepareStatement(sqlQuery);
			
			ps.setString(1, email);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next())
				return false;
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
		return true;
	}
	
	public int addAdmin(String password, Admin admin)
	{
		try
		{
			String sqlQuery = "insert into credentials(role, email, password, isApproved, address, age, gender, contact, nationality) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			
			String adminQuery = "insert into admin values(?, ?, ?)";
			ps = connection.prepareStatement(adminQuery);
			ps.setInt(1, admin.getUserId());
			ps.setString(2, admin.getUserName());
			ps.setString(3, admin.getEmail());
			
			ps.executeUpdate();
			return 1;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public int addProfessor(String password, Professor prof)
	{
		try
		{
			String credQuery = "insert into credentials(role, email, password, isApproved, address, age, gender, contact, nationality) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			
			String profQuery = "insert into professor values(?, ?, ?, ?, ?)";
			ps = connection.prepareStatement(profQuery);
			ps.setInt(1, prof.getUserId());
			ps.setString(2, prof.getUserName());
			ps.setString(3, prof.getEmail());
			ps.setString(4, prof.getRole());
			ps.setString(5,  prof.getDepartment());
			ps.executeUpdate();
			
			return 1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public void printGrades(int studentId) {
		try {
//			ArrayList<Student> arr = new ArrayList<Student>();
			String str = "select name from student where id = ?";
			ps = connection.prepareStatement(str);
			ps.setInt(1, studentId);
			ResultSet rs =  ps.executeQuery();
			String name = "";
			rs.next();
			name = rs.getString("name");
			str = "select grade,courseId from grades where studentId=?";
			ps = connection.prepareStatement(str);
			ps.setInt(1, studentId);
			rs =  ps.executeQuery();
			logger.info("=======================================");
			logger.info("        Report Card of " + name + " :");
			logger.info("=======================================");
			logger.info("CourseID     Course Name     Grade");
			while(rs.next()) {
				String sr = "select courseName from courseCatalog where courseId = ? ";
				ps = connection.prepareStatement(sr);
				ps.setInt(1,rs.getInt("courseId"));
				ResultSet res = ps.executeQuery();
				res.next();
				logger.info(rs.getInt("courseId") + "          " + res.getString("courseName") + "         " + rs.getString("grade"));
			}
			logger.info("=======================================");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ;
	}
	
	public void approveStudent() {
		try {
			Scanner sc = new Scanner(System.in);
			String str = "select * from credentials where isApproved=0";
			ps = connection.prepareStatement(str);
			ResultSet rs = ps.executeQuery();
			logger.info("=======================================");
			while(rs.next()) {
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
					String res = "update credentials set isApproved=1 where id = ?";
					ps = connection.prepareStatement(res);
					ps.setInt(1, rs.getInt("id"));
					ps.executeUpdate();
					logger.info("Student Approved !!");
					logger.info("=======================================");
				}
				else{
					String res = "Delete from credentials where id = ?";
					ps = connection.prepareStatement(res);
					ps.setInt(1, rs.getInt("id"));
					ps.executeUpdate();
					res = "Delete from student where id = ?";
					ps = connection.prepareStatement(res);
					ps.setInt(1, rs.getInt("id"));
					ps.executeUpdate();
					logger.info("Student Rejected !!");
					logger.info("=======================================");
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Query to add a course to a courseCatalog by admin
	 * only if course id provided is unique
	 */
	
	public boolean addCourse(Course course) {
		try {
			String str = "insert into courseCatalog (courseName,courseId,credits) values (?,?,?)";
			ps = connection.prepareStatement(str);
			ps.setString(1, course.getCourseName());
			ps.setInt(2,course.getCourseID());
			ps.setInt(3, course.getCredits());
			int val = ps.executeUpdate();
			if(val>0) {
				return true;
			}
		}
		catch(Exception e) {
			//// Write exception here
			logger.info("Course ID's cannot be duplicate");
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
			String str = "delete from courseCatalog where courseId = ?";
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
		catch(Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * Assign course with particular course ID to the
	 * professor with ID entered by the admin
	 */
	
	public void allotCourses(int courseId,int professorID) {
		try {
			logger.info("Inside Allot courses method-------------");
			String str = "select name from course where id=?";
			ps = connection.prepareStatement(str);
			ps.setInt(1,courseId);
			ResultSet rs = ps.executeQuery();
//			if(rs.next()) {
//				logger.info(rs.getString("name"));
//			}
//			else {
//				logger.info("No course Available");
//			}
			if(rs.next()) {
				logger.info("Inside update professorID method-------------");
				str = "update course set professorId = ? where id=?";
				ps = connection.prepareStatement(str);
				ps.setInt(1,professorID);
				ps.setInt(2, courseId);
				int status = ps.executeUpdate();
				if(status>0) {
					logger.info("Updated Successfully");
				}
				else {
					logger.info("Failed");
				}
			}
			else {
				str = "select courseName,credits from courseCatalog where courseId=?";
				ps = connection.prepareStatement(str);
				ps.setInt(1,courseId);
				rs = ps.executeQuery();
				while(rs.next()) {
					str = "insert into course(id,name,professorId,credits) values(?,?,?,?)";
					ps = connection.prepareStatement(str);
					ps.setInt(1,courseId);
					ps.setString(2,rs.getString("courseName"));
					ps.setInt(3,professorID);
					ps.setInt(4,rs.getInt("credits"));
					ps.executeUpdate();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
