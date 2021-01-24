package com.flipkart.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grades;
import com.flipkart.constant.SQLQueriesConstant;
import org.apache.log4j.Logger;

import com.flipkart.bean.Student;
import com.flipkart.util.DBConnection;

/*
 * @author JEDI 04
 */

public class StudentDAOOperation implements StudentDAOInterface {
	private static Logger logger = Logger.getLogger(StudentDAOOperation.class);
	Connection connection = null;
	PreparedStatement ps = null;
	CoursesDAOOperation coursesDaoOperation = new CoursesDAOOperation();

	// to get the student details from db based on email entered during login
	public Student getStudentByEmail(String email) {
		Student student = new Student();
		try {
			connection = DBConnection.getConnection();
			//String sqlQuery = "SELECT * FROM student WHERE email=?";
			ps = connection.prepareStatement(SQLQueriesConstant.GET_STUDENT_BY_EMAIL_QUERY);
			
			ps.setString(1, email);
			
			ResultSet result = ps.executeQuery();
			result.next();
			student.setEmail(result.getString("email"));
			student.setBranch(result.getString("branch"));
			student.setUserId(result.getInt("id"));
			boolean bool = result.getInt("isRegistered") == 1 ? true : false;
			student.setIsRegistered(bool);
			student.setUserName(result.getString("name"));
			student.setRollNo(result.getInt("rollno"));	
			student.setApproved(result.getBoolean("isApproved"));
			student.setPaymentStatus(result.getBoolean("paymentStatus"));
			student.setAge(result.getInt("age"));
			student.setContact(result.getString("contact"));
			student.setNationality(result.getString("nationality"));
			student.setGender(result.getString("gender"));
			student.setAddress(result.getString("address"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	// to add course in db based on student ID
	public void addCourse(Student student, int courseID){
		Course course = coursesDaoOperation.getCourseByID(courseID);
		if(course == null) {
			logger.info(">>>>>>>> Invalid Course ID <<<<<<<<<<\n");
		}
//		else if(getNoOfCourses(student)>=6){
//			logger.info("Cannot add more course. You have already added 6 courses.");
//		}
		else if(getCourse(student, courseID)){
			logger.info("You have already added this course.\n");
		}
		else if(coursesDaoOperation.noOfEnrolledStudents(courseID)>=10){
			logger.info("Course " + courseID + " is full. Please add some other course.\n");
		}
		else{
			try{
				connection = DBConnection.getConnection();
				//String SQLQuery = "INSERT INTO RegisteredCourses(studentID, courseID) values(?,?)";
				ps = connection.prepareStatement(SQLQueriesConstant.ADD_COURSE_STUDENT_QUERY);

				ps.setInt(1,student.getUserId());
				ps.setInt(2,courseID);

				int added = ps.executeUpdate();
				if(added>0){
					logger.info("Course " + courseID + " added successfully.\n");
				}

				addCourseToGrades(student.getUserId(),courseID);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	// to delete a course from db based on student ID
	public void dropCourse(Student student, int courseID){
		Course course = coursesDaoOperation.getCourseByID(courseID);
		if(course == null) {
			logger.info(">>>>>>>> Invalid Course ID <<<<<<<<<<\n");
		}
		else if(!getCourse(student, courseID)){
			logger.info("You have not registered for this course.\n");
		}
		/*else if(getNoOfCourses(student)==4){
			logger.info("Only 4 courses registered. Cannot drop a course.");
		}*/
		else {
			try {
				connection = DBConnection.getConnection();
				//String SQLQuery = "DELETE FROM RegisteredCourses where studentID = ? and courseID = ?";
				ps = connection.prepareStatement(SQLQueriesConstant.DROP_COURSE_STUDENT_QUERY);

				ps.setInt(1, student.getUserId());
				ps.setInt(2, courseID);

				int dropped = ps.executeUpdate();
				if(dropped>0) {
					logger.info("Course " + courseID + " deleted successfully\n");
				}

				deleteCourseFromGrades(student.getUserId(),courseID);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// to get the no of courses in which a student is enrolled
	public int getNoOfCourses(Student student){
		int count = 0;

		try{
			connection = DBConnection.getConnection();
			//String SQLQuery = "SELECT COUNT(*) FROM RegisteredCourses WHERE studentID=?";
			ps = connection.prepareStatement(SQLQueriesConstant.GET_NO_OF_COURSES_QUERY);

			ps.setInt(1,student.getUserId());
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()) {
				count = resultSet.getInt(1);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return count;
	}

	public boolean getCourse(Student student, int courseID){
		int count = 0;

		try{
			connection = DBConnection.getConnection();
			//String SQLQuery = "SELECT COUNT(*) FROM RegisteredCourses WHERE studentID=? AND courseID=?";
			ps = connection.prepareStatement(SQLQueriesConstant.GET_COURSE_QUERY);

			ps.setInt(1,student.getUserId());
			ps.setInt(2,courseID);

			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()) {
				count = resultSet.getInt(1);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return count>0;
	}

	// to add student and its courses to grades table in db
	public void addCourseToGrades(int studentID, int courseID){
		try{
			connection = DBConnection.getConnection();
			//String SQLQuery = "INSERT INTO grades(studentId, courseId) values(?,?)";
			ps = connection.prepareStatement(SQLQueriesConstant.ADD_COURSE_TO_GRADES_QUERY);

			ps.setInt(1,studentID);
			ps.setInt(2,courseID);

			ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void deleteCourseFromGrades(int studentID, int courseID){
		try{
			connection = DBConnection.getConnection();
			//String SQLQuery = "DELETE FROM grades WHERE studentID = ? AND courseID = ?";
			ps = connection.prepareStatement(SQLQueriesConstant.DELETE_COURSE_FROM_GRADES_QUERY);

			ps.setInt(1,studentID);
			ps.setInt(2,courseID);

			ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


	// to get all coursed in which a student is enrolled from db
	public ArrayList<Course> getEnrolledCourses(Student student){
		ArrayList<Course> enrolledCourses = new ArrayList<>();

		try{
			connection = DBConnection.getConnection();
			//String SQLQuery = "SELECT courseID FROM RegisteredCourses WHERE studentID=?";
			ps = connection.prepareStatement(SQLQueriesConstant.GET_ENROLLED_COURSES_QUERY);

			ps.setInt(1,student.getUserId());
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				Course course = new Course();
				course.setCourseID(resultSet.getInt("courseID"));
				course.setCourseName(resultSet.getString("name"));
				enrolledCourses.add(course);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return enrolledCourses;
	}

	// to set registration status of a student in db
	public void setRegistrationStatus(Student student){
		try{
			connection = DBConnection.getConnection();
			//String SQLQuery = "UPDATE student SET isRegistered = 1 where id = ?";
			ps = connection.prepareStatement(SQLQueriesConstant.SET_REGISTRATION_STATUS_QUERY);

			ps.setInt(1,student.getUserId());
			ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	// to fetch grades of a student based on student ID
	public ArrayList<Grades> getGrades(int studentID){
		ArrayList<Grades> grades = new ArrayList<>();

		try{
			connection = DBConnection.getConnection();
			//String SQLQuery = "SELECT grades.courseId, course.name as courseName, grades.grade, grades.studentId FROM grades INNER JOIN course ON grades.courseId = course.id AND grades.studentId=?";
			ps = connection.prepareStatement(SQLQueriesConstant.GET_GRADES_QUERY);

			ps.setInt(1,studentID);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				Grades grade = new Grades();
				grade.setCourseID(resultSet.getInt("courseId"));
				grade.setCourseName(resultSet.getString("courseName"));
				grade.setGrade(resultSet.getString("grade"));
				grade.setStudentId(resultSet.getInt("studentId"));
				grades.add(grade);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return grades;
	}
	
	public void setPaymentStatus(Student student) {
		try{
			connection = DBConnection.getConnection();
			ps = connection.prepareStatement(SQLQueriesConstant.SET_PAYMENT_STATUS_QUERY);

			ps.setInt(1, student.getUserId());
			ps.executeUpdate();
//			if(paid > 0) {
//				logger.info("Payment Successful\n");
//			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void registerStudent(Student student, int id) {
		try {
			connection = DBConnection.getConnection();
			//String sqlQuery = "INSERT INTO student(id, name, email, rollno, branch, isRegistered, paymentStatus) values(?, ?, ?, ?, ?, ?, ?)";
			ps = connection.prepareStatement(SQLQueriesConstant.REGISTER_STUDENT_QUERY);

			ps.setInt(1, id);
			ps.setString(2,  student.getUserName());
			ps.setString(3,  student.getEmail());
			ps.setInt(4, student.getRollNo());
			ps.setString(5, student.getBranch());
			ps.setBoolean(6, student.getIsRegistered());
			ps.setBoolean(7, student.getPaymentStatus());
			
			int count = ps.executeUpdate();
			if(count > 0) {
				// Registered
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			// connection.close();
		}
	}

	public void updateInfo(Student student){
		try{
			connection = DBConnection.getConnection();
			//String sqlQuery = "UPDATE credentials SET age=?,address=?,contact=?,gender=?,nationality=? WHERE id=?";
			ps = connection.prepareStatement(SQLQueriesConstant.UPDATE_STUDENT_CREDENTIAL_QUERY);

			ps.setInt(1,student.getAge());
			ps.setString(2,student.getAddress());
			ps.setString(3,student.getContact());
			ps.setString(4,student.getGender());
			ps.setString(5,student.getNationality());
			ps.setInt(6,student.getUserId());

			ps.executeUpdate();


			ps = connection.prepareStatement(SQLQueriesConstant.UPDATE_STUDENT_QUERY);
			ps.setString(1, student.getUserName());
			ps.setInt(2,student.getUserId());

			ps.executeUpdate();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
