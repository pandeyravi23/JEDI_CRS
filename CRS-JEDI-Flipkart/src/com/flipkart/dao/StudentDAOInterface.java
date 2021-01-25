package com.flipkart.dao;

import java.util.*;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grades;
import com.flipkart.bean.Student;


public interface StudentDAOInterface {
	public Student getStudentByEmail(String email);
	public void addCourse(Student student, int courseID);
	public void dropCourse(Student student, int courseID);
	public ArrayList<Course> getEnrolledCourses(Student student);

	public void setRegistrationStatus(Student student);

	public ArrayList<Grades> getGrades(int studentID);

	public int getNoOfCourses(Student student);

	public boolean getCourse(Student student, int courseID);
	
	public void setPaymentStatus(Student student);
	
	public void registerStudent(Student student, int id);

	public void addCourseToGrades(int studentID, int courseID);

	public void deleteCourseFromGrades(int studentID, int courseID);

	public void updateInfo(Student student);

	public boolean getRegistrationSystemStatus();
}
