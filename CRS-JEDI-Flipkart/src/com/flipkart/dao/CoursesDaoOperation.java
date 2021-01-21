package com.flipkart.dao;

import java.io.*;

import java.util.*;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

/*
 * @author JEDI 04
 */
public class CoursesDaoOperation implements CoursesDaoInterface {
	public static List<Course> courses = new ArrayList<>();

	// to get all the courses in course catalog
	@Override
	// return all courses details
	public List<Course> getAllCourseDetails() {
		return courses;
	}

	@Override
	// get list of enrolled students;
	public ArrayList<Student> getEnrolledStudents(int courseId) {
		ArrayList<Student> enrolledStudents = new ArrayList<Student>();
		for(Course course: courses) {
			if(course.getCourseID() == courseId) {
				enrolledStudents = course.getListOfEnrolledStudents();
				break;
			}
		}
		return enrolledStudents;
	}

	// read the course catalog and populate the courses list
	@Override
	
	//Populate All Courses from data.txt file to courses list 
	public void populate() {
		FileInputStream inputStream = null;
		
		try {	
//			String filePath = new File("").getAbsolutePath();
			inputStream = new FileInputStream("data.txt");
//			inputStream = new FileInputStream("C:\\Users\\chinm\\OneDrive\\Desktop\\Flipkart_Internship\\JEDI_Bootcamp\\JAVA\\data.txt");
			Scanner scanner = new Scanner(inputStream);
			while(scanner.hasNext()) {
				String line = scanner.nextLine();
				String elements[] = line.split(" ");
				
				Course newCourse = new Course();
				newCourse.setCourseID(Integer.parseInt(elements[0]));
				newCourse.setCourseName(elements[1]);
				newCourse.setProfessorAllotted(elements[2]);
				newCourse.setCredits(Integer.parseInt(elements[3]));
				
				ArrayList<Student> studentsEnrolled = new ArrayList<>();
				for(int i = 4; i < elements.length; i+=2) {
					Student newStudent = new Student();
					newStudent.setUserName(elements[i]);
					newStudent.setUserId(Integer.parseInt(elements[i + 1]));
					studentsEnrolled.add(newStudent);
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
