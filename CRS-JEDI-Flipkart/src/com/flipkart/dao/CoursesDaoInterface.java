package com.flipkart.dao;

import java.util.*;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

public interface CoursesDaoInterface {
	public ArrayList<Student> getEnrolledStudents(int courseId);

	public Course getCourseByID(int courseID);
	public ArrayList<Course> getAllCourses();

	public int noOfEnrolledStudents(int courseID);
}
