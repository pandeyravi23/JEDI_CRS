package com.flipkart.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import org.apache.log4j.Logger;

import com.flipkart.bean.Student;
import com.flipkart.util.DBConnection;

public class StudentDaoOperation implements StudentDaoInterface {
	
	public static List<Student> students = new ArrayList<>();
	private static Logger logger = Logger.getLogger(StudentDaoOperation.class);
	Connection connection = null;
	PreparedStatement ps = null;

	// to get all students in database
	@Override
	public List<Student> getAllStudents() {
		return students;
	}

	// to get student object based on studentID
	@Override
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
	public void populate() {
		FileInputStream inputStream = null;
		
		try {
			String filePath = new File("").getAbsolutePath();
			inputStream = new FileInputStream(filePath.concat("/src/stu.txt"));
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

}
