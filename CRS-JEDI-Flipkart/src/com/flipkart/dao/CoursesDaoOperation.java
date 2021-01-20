package com.flipkart.dao;

import java.io.*;
import java.util.*;

import com.flipkart.bean.Course;

public class CoursesDaoOperation implements CoursesDaoInterface {
	List<Course> courses = new ArrayList<>();

	@Override
	public List<Course> getAllCourseDetails() {
		return courses;
	}

	@Override
	public ArrayList<String> getEnrolledStudentsID(int courseId) {
		ArrayList<String> enrolledStudentsID = new ArrayList<String>();
		for(Course course: courses) {
			if(course.getCourseID() == courseId) {
				enrolledStudentsID = course.getListOfEnrolledStudents();
				break;
			}
		}
		return enrolledStudentsID;
	}
	
	@Override
	public void populate() {
		FileInputStream inputStream = null;
		
		try {	
			inputStream = new FileInputStream("D:/JEDI/testingJava/db/courses.txt");
			Scanner scanner = new Scanner(inputStream);
			while(scanner.hasNext()) {
				String line = scanner.nextLine();
				String elements[] = line.split(" ");
				
				Course newCourse = new Course();
				newCourse.setCourseID(Integer.parseInt(elements[0]));
				newCourse.setCourseName(elements[1]);
				newCourse.setProfessorAllotted(elements[2]);
				newCourse.setCredits(Integer.parseInt(elements[3]));
				
				ArrayList<String> studentsEnrolled = new ArrayList<>();
				for(int i = 4; i < elements.length; ++i) {
					studentsEnrolled.add(elements[i]);
				}
				newCourse.setListOfEnrolledStudents(studentsEnrolled);
				
				courses.add(newCourse);
			}
			
			scanner.close();
			inputStream.close();
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
