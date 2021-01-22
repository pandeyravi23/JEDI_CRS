package com.flipkart.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.flipkart.bean.Course;
import org.apache.log4j.Logger;

import com.flipkart.bean.Student;
import com.flipkart.util.DBConnection;

/*
 * @author JEDI 04
 */

public class StudentDaoOperation implements StudentDaoInterface {
	
	// creating students list 
	public static List<Student> students = new ArrayList<>();
	private static Logger logger = Logger.getLogger(StudentDaoOperation.class);
	Connection connection = null;
	PreparedStatement ps = null;
	CoursesDaoOperation coursesDaoOperation = new CoursesDaoOperation();

	// to get all students in database
	@Override
	public List<Student> getAllStudents() {
		return students;
	}

	// to get student object based on studentID
	@Override
	// get student information using student id
	public Student getStudentById(int studentId) {
		Student st = null;
		for(Student student: students) {
			if(student.getUserId() == studentId) {
				st = student;
				break;
			}
		}
		return st;
	}

	// read student database and populate students list
	@Override
	// creating student objects using stu.txt file and adding to students list 
	public void populate() {
		FileInputStream inputStream = null;
		
		try {
//			String filePath = new File("").getAbsolutePath();
			inputStream = new FileInputStream("stu.txt");
//			inputStream = new FileInputStream("C:\\Users\\chinm\\OneDrive\\Desktop\\Flipkart_Internship\\JEDI_Bootcamp\\JAVA\\student.txt");
			Scanner scanner = new Scanner(inputStream);
			while(scanner.hasNext()) {
				String line = scanner.nextLine();
				String elements[] = line.split(" ");
				
				Student newStudent = new Student();
				newStudent.setUserId(Integer.parseInt(elements[0]));
				newStudent.setUserName(elements[1]);
				newStudent.setRole(elements[2]);
				newStudent.setEmail(elements[3]);
				newStudent.setRollNo(Integer.parseInt(elements[4]));
				newStudent.setBranch(elements[5]);
				students.add(newStudent);
			}
			
			scanner.close();
			inputStream.close();
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Student getStudentByEmail(String email) {
		Student student = new Student();
		try {
			connection = DBConnection.getConnection();
			String sqlQuery = "SELECT * FROM student WHERE email=?";
			ps = connection.prepareStatement(sqlQuery);
			
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
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return student;
	}


	public void addCourse(Student student, int courseID){
		try{
			connection = DBConnection.getConnection();
			String SQLQuery = "INSERT INTO RegisteredCourses(studentID, courseID) values(?,?)";
			ps = connection.prepareStatement(SQLQuery);

			ps.setInt(1,student.getUserId());
			ps.setInt(2,courseID);

			int added = ps.executeUpdate();
			if(added>0){
				logger.info("Course " + courseID + " added successfully");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public void dropCourse(Student student, int courseID){
		try{
			connection = DBConnection.getConnection();
			String SQLQuery = "DELETE FROM RegisteredCourses where studentID = ? and courseID = ?";
			ps = connection.prepareStatement(SQLQuery);

			ps.setInt(1,student.getUserId());
			ps.setInt(2,courseID);

			int dropped = ps.executeUpdate();
			logger.info("Course " + courseID + " deleted successfully");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public ArrayList<Course> getEnrolledCourses(Student student){
		ArrayList<Course> enrolledCourses = new ArrayList<>();

		try{
			connection = DBConnection.getConnection();
			String SQLQuery = "SELECT courseID FROM RegisteredCourses WHERE studentID=?";
			ps = connection.prepareStatement(SQLQuery);

			ps.setInt(1,student.getUserId());
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				Course course = coursesDaoOperation.getCourseByID(resultSet.getInt("courseID"));
				enrolledCourses.add(course);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return enrolledCourses;
	}

	public void setRegistrationStatus(Student student){
		try{
			connection = DBConnection.getConnection();
			String SQLQuery = "UPDATE student SET isRegistered = 1 where id = ?";
			ps = connection.prepareStatement(SQLQuery);

			ps.setInt(1,student.getUserId());
			ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
