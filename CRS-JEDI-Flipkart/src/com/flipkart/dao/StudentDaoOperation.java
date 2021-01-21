package com.flipkart.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import com.flipkart.bean.Student;

/*
 * @author JEDI 04
 */

public class StudentDaoOperation implements StudentDaoInterface {
	
	// creating students list 
	public static List<Student> students = new ArrayList<>();

	@Override
	public List<Student> getAllStudents() {
		return students;
	}

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

	@Override
	// creating student objects using stu.txt file and adding to students list 
	public void populate() {
		FileInputStream inputStream = null;
		
		try {
			inputStream = new FileInputStream("stu.txt");
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
