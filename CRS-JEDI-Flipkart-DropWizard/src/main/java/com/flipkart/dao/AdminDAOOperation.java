package com.flipkart.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.util.DBConnection;
import com.flipkart.constant.SQLQueriesConstant;
import com.flipkart.exception.AdminCRSException;
import com.flipkart.exception.StudentCRSException;

/**
 * Data Access object for Admin class which is responsible for all the
 * interactions that happen with the SQL database.
 * 
 * @author JEDI04
 *
 */

public class AdminDAOOperation implements AdminDAOInterface {

	private static Logger logger = Logger.getLogger(AdminDAOOperation.class);
	Connection connection = DBConnection.getConnection();
	PreparedStatement ps = null;

//	code for lazy loading
	private static AdminDAOOperation instance = null;

	private AdminDAOOperation() {

	}

	/**
	 * Singleton implementation.
	 * 
	 * @return Instance of AdminDAOOperatioon class.
	 */

	synchronized public static AdminDAOOperation getInstance() {
		if (instance == null) {
			instance = new AdminDAOOperation();
		}
		return instance;
	}

	/**
	 * 
	 * Verifies Email Address at the time of registration
	 * 
	 * 
	 * @param email Email address of the new user to be added.
	 * @return False if the email already exists in database else returns true.
	 * 
	 */
	
	public boolean verifyEmail(String email) {
		try {
			String sqlQuery = SQLQueriesConstant.GET_STUDENT_ID_BY_EMAIL;
			ps = connection.prepareStatement(sqlQuery);

			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return true;
	}

	/**
	 * addAdmin is used to add the new Admin details in Credentials table and Admin
	 * table
	 * 
	 * @param password User provided password for the new Admin to be added.
	 * @param admin    Other details of the admin sent inside Admin bean class by
	 *                 using its attributes.
	 * 
	 * @return Returns 1 if new admin is successfully added. Else returns 0.
	 */
	
	public int addAdmin(String password, Admin admin) {
		try {
			String sqlQuery = SQLQueriesConstant.ADD_USER_TO_CREDENTIALS;
			ps = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, 3);
			ps.setString(2, admin.getEmail());
			ps.setString(3, password);
			ps.setInt(4, 1);
			ps.setString(5, admin.getAddress());
			ps.setInt(6, admin.getAge());
			ps.setString(7, admin.getGender());
			ps.setString(8, admin.getContact());
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
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return 0;
	}

	/**
	 * It is responsible for adding the new Professor in Credentials table and
	 * Professor table
	 * 
	 * @param password New password of the professor.
	 * @param prof     Details of the new professor sent using the Professor bean
	 *                 class' attributes.
	 * @return Returns 1 if professor is successfully added. Else returns 0.
	 */
	
	public int addProfessor(String password, Professor prof) {
		try {
			String credQuery = SQLQueriesConstant.ADD_USER_TO_CREDENTIALS;
			ps = connection.prepareStatement(credQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 2);
			ps.setString(2, prof.getEmail());
			ps.setString(3, password);
			ps.setInt(4, 1);
			ps.setString(5, prof.getAddress());
			ps.setInt(6, prof.getAge());
			ps.setString(7, prof.getGender());
			ps.setString(8, prof.getContact());
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
			ps.setString(5, prof.getDepartment());
			ps.executeUpdate();

			return 1;
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return 0;
	}

	/**
	 * printGrades Prints the Report of card of a particular Student given its
	 * Student ID.
	 * 
	 * @param studentId StudentID of the student whose report card is to be
	 *                  generated.
	 * @return List of json objects containing grades information
	 */
	
	public ArrayList<JSONObject> printGrades(int studentId) {
		ArrayList<JSONObject> grades = new ArrayList<JSONObject>();
		try {
			String str = SQLQueriesConstant.GET_GRADES_BY_STUDENT_ID;
			ps = connection.prepareStatement(str);
			ps.setInt(1, studentId);
			ResultSet rs = ps.executeQuery();
			
			String name = "";
			if (rs.next())
				name = rs.getString("sname");
			else {
				logger.info("Student with ID " + studentId + " has not registered for any course!");
				logger.info("=======================================");
				return grades;
			}
			logger.info("=======================================");
			logger.info("        Report Card of " + name + " :");
			logger.info("=======================================");
			logger.info("CourseID     Course Name     Grade");
			if (name != "") {
				do {
					logger.info(rs.getInt("courseId") + "          " + rs.getString("name") + "         "
							+ rs.getString("grade"));
					
					JSONObject grade = new JSONObject();
					grade.put("courseId", rs.getInt("courseId"));
					grade.put("name", rs.getString("name"));
					grade.put("grade",  rs.getString("grade"));
					
					grades.add(grade);
				} while (rs.next());
			}
			logger.info("=======================================");
			
//			while(rs.next())
//			{
//				JSONObject grade = new JSONObject();
//				grade.put("courseId", rs.getInt("courseId"));
//				grade.put("name", rs.getString("name"));
//				grade.put("grade",  rs.getString("grade"));
//				
//				grades.add(grade);
//			}

		} catch (SQLException e) {
			logger.info(e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return grades;
	}

	/**
	 * approveStudent Used to approve the newly registered students in order to
	 * allow them to login and register for courses.
	 * @param studentID
	 * @return true if student approved successfully else false
	 */
	public boolean approveStudent(int studentID) {
		try {
			String str = SQLQueriesConstant.UPDATE_USER_IN_CREDENTIALS;
			ps = connection.prepareStatement(str);
			ps.setInt(1,studentID);
			ps.executeUpdate();
			return true;
		}catch (SQLException e) {
			logger.info(e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return false;
	}

	/**
	 * Add a new course to the courseCatalog only if course id provided by the Admin
	 * is unique
	 * 
	 * @param course The details of the course encapsulated in the course class
	 *               attributes.
	 * 
	 * 
	 * @return True if the course is successfully added. False otherwise.
	 */
	public boolean addCourse(Course course) {
		try {
			String str = SQLQueriesConstant.ADD_COURSE_IN_CATALOG;
			ps = connection.prepareStatement(str);
			ps.setString(1, course.getCourseName());
			ps.setInt(2, course.getCourseID());
			ps.setInt(3, course.getCredits());
			int val = ps.executeUpdate();
			if (val > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return false;
	}

	/**
	 * Deletes a course from course catalog and course tables where id matches the
	 * given courseId.
	 * 
	 * @param courseId Course Id of the course to be deleted.
	 * @return True if the course is successfully deleted. False otherwise.
	 */
	
	public boolean deleteCourse(int courseId) {
		try {
			String str = SQLQueriesConstant.DELETE_COURSE_IN_CATALOG;
			ps = connection.prepareStatement(str);
			ps.setInt(1, courseId);
			int val = ps.executeUpdate();
			str = SQLQueriesConstant.DELETE_COURSE_BY_ID;
			ps = connection.prepareStatement(str);
			ps.setInt(1, courseId);
			ps.executeUpdate();
			if (val > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return false;
	}

	/**
	 * Assign course with particular course ID to the corresponding professor with
	 * ID entered by the Admin. In case, already some other professor is assigned to
	 * the course, it is modified.
	 * 
	 * @param courseId    Course ID of the course to which the professor is to be
	 *                    assigned/modified.
	 * @param professorID Professor ID of the course.
	 * 
	 * @return true if course allotted successfully else false
	 */
	public boolean allotCourses(int courseId, int professorID) {
		try {
			String str = SQLQueriesConstant.GET_PROFESSOR_NAME_BY_ID;
			ps = connection.prepareStatement(str);
			ps.setInt(1, professorID);
			ResultSet rs = ps.executeQuery();
			if (rs.next() == false) {
				return false;
			}
			str = SQLQueriesConstant.GET_COURSE_NAME_BY_ID;
			ps = connection.prepareStatement(str);
			ps.setInt(1, courseId);
			rs = ps.executeQuery();
			if (rs.next()) {
				str = SQLQueriesConstant.UPDATE_PROFESSOR_IN_COURSE;
				ps = connection.prepareStatement(str);
				ps.setInt(1, professorID);
				ps.setInt(2, courseId);
				int status = ps.executeUpdate();
				if (status > 0) {
					return true;
				} else {
					return false;
				}
			} else {
				str = SQLQueriesConstant.GET_COURSE_CATALAOG_QUERY;
				ps = connection.prepareStatement(str);
				ps.setInt(1, courseId);
				rs = ps.executeQuery();
				if (rs.next() == false) {
					return false;
				}
				do {
					str = SQLQueriesConstant.ADD_NEWCOURSE_IN_COURSE;
					ps = connection.prepareStatement(str);
					ps.setInt(1, courseId);
					ps.setString(2, rs.getString("courseName"));
					ps.setInt(3, professorID);
					ps.setInt(4, rs.getInt("credits"));
					ps.executeUpdate();
				} while (rs.next());
				return true;
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return false;
	}

	/**
	 * Opens registration window.
	 * 
	 * @return true if registration status updated else false
	 */
	public boolean startRegistrationWindow() {
		boolean res = false;
		try {
			ps = connection.prepareStatement(SQLQueriesConstant.OPEN_REGISTRATION_WINDOW);
			if (ps.executeUpdate() > 0) {
				res = true;
				logger.info("Registration Window Started.\n");
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return res;
	}

	/**
	 * Closes registration window.
	 * @return true if registration status updated else false
	 */
	public boolean closeRegistrationWindow() {
		boolean res = false;
		try {
			ps = connection.prepareStatement(SQLQueriesConstant.CLOSE_REGISTRATION_WINDOW);
			if (ps.executeUpdate() > 0) {
				res = true;
				logger.info("Registration Window Closed.\n");
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return res;
	}
	

	/**
	 * shows All available courses
	 */
	public void showcourses() {
		try {
			logger.info("=======================================");
			String s = SQLQueriesConstant.GET_COURSE_INFO_BY_ID;
			ps = connection.prepareStatement(s);
			ResultSet courl = ps.executeQuery();
			if (courl.next() == false) {
				logger.info("No Course Exists!!");
				logger.info("=======================================");
			} else {
				logger.info("CourseID           CourseName");
				do {
					logger.info(courl.getInt("courseId") + "                " + courl.getString("courseName"));
				} while (courl.next());
				logger.info("=======================================");
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * shows All available professors
	 */
	
	public void showprofessor() {
		try {
			String s = SQLQueriesConstant.GET_PROFESSOR_INFO_BY_ID;
			ps = connection.prepareStatement(s);
			ResultSet profl = ps.executeQuery();
			if (profl.next() == false) {
				logger.info("No Professor Exists!!");
				logger.info("=======================================");
			} else {
				logger.info("ProfessorID        ProfessorName");
				do {
					logger.info(profl.getInt("id") + "                " + profl.getString("name"));
				} while (profl.next());
				logger.info("=======================================");
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * Displays List of all registered students
	 * @return List of Json objects with list of enrolled students
	 */
	public ArrayList<JSONObject> getRegisteredStudents() {
		ArrayList<JSONObject> students = new ArrayList<JSONObject>();
		try {
			String  s = SQLQueriesConstant.GET_REGISTERED_STUDENTS;
			ps = connection.prepareStatement(s);
			ResultSet stl = ps.executeQuery();
			logger.info("=======================================");
			if (stl.next() == false) {
				throw new StudentCRSException("No Student Exists!!");
			} else {
				logger.info(String.format("%-10s\t%-15s", "StudentID", "Student Name"));
				do {
					logger.info(String.format("%-10d\t%-15s", stl.getInt("id"), stl.getString("name")));
					JSONObject student = new JSONObject();
					student.put("studentId", stl.getInt("id"));
					student.put("name", stl.getString("name"));
					
					students.add(student);
				} while (stl.next());
				logger.info("=======================================");
				return students;
			}		
		} catch (StudentCRSException e) {
			logger.info(e.getMessage());
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return students;
	}

	@Override
	public void approveStudent() {
		// TODO Auto-generated method stub
		
	}
}
