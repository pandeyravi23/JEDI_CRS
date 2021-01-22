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
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	// to add course in db based on student ID
	public void addCourse(Student student, int courseID){
		if(getNoOfCourses(student)>=6){
			logger.info("Cannot add more course. You have already added 6 courses.");
		}
		else if(getCourse(student, courseID)){
			logger.info("You have already added this course.");
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
					logger.info("Course " + courseID + " added successfully");
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	// to delete a course from db based on student ID
	public void dropCourse(Student student, int courseID){
		if(!getCourse(student, courseID)){
			logger.info("You have not registered for this course.");
		}
		else if(getNoOfCourses(student)==4){
			logger.info("Only 4 courses registered. Cannot drop a course.");
		}
		else {
			try {
				connection = DBConnection.getConnection();
				//String SQLQuery = "DELETE FROM RegisteredCourses where studentID = ? and courseID = ?";
				ps = connection.prepareStatement(SQLQueriesConstant.DROP_COURSE_STUDENT_QUERY);

				ps.setInt(1, student.getUserId());
				ps.setInt(2, courseID);

				int dropped = ps.executeUpdate();
				logger.info("Course " + courseID + " deleted successfully");
			} catch (Exception e) {
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
				Course course = coursesDaoOperation.getCourseByID(resultSet.getInt("courseID"));
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
}
