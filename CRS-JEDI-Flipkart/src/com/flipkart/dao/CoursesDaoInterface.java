package com.flipkart.dao;

import java.util.*;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

public interface CoursesDaoInterface {
	public List<Course> getAllCourseDetails();
	public ArrayList<Student> getEnrolledStudents(int courseId);
	public void populate(); // Dummy method
}
