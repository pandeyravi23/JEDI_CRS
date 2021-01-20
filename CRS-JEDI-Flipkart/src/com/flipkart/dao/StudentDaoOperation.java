package com.flipkart.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import com.flipkart.bean.Student;

public class StudentDaoOperation implements StudentDaoInterface {
	
	public static List<Student> students = new ArrayList<>();

	@Override
	public List<Student> getAllStudents() {
		return students;
	}

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

	@Override
	public void populate() {
		FileInputStream inputStream = null;
		
		try {
//			inputStream = new FileInputStream("D:/JEDI/testingJava/db/students.txt");
			//inputStream = new FileInputStream("/Users/bhavya/Desktop/stu.txt");
			//inputStream = new FileInputStream("C:\\Users\\chinm\\OneDrive\\Desktop\\Flipkart_Internship\\JEDI_Bootcamp\\JAVA\\data.txt");

			//inputStream = new FileInputStream("C:\\Users\\chinm\\OneDrive\\Desktop\\Flipkart_Internship\\JEDI_Bootcamp\\JEDI_SRS\\CRS-JEDI-Flipkart\\src\\data.txt");
			//inputStream = new FileInputStream("C:\\Users\\chinm\\OneDrive\\Desktop\\Flipkart_Internship\\JEDI_Bootcamp\\JAVA\\student.txt");


			inputStream = new FileInputStream("/home/ravi/Desktop/uml/CRS-JEDI-Flipkart/src/stu.txt");
//			String filePath = new File("").getAbsolutePath();
//			inputStream = new FileInputStream(filePath.concat("/src/stu.txt"));
//			
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

}
