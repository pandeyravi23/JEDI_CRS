package com.flipkart.dao;

import java.util.*;

import com.flipkart.bean.Course;

public interface CoursesDaoInterface {
	public List<Course> getAllCourseDetails();
	public ArrayList<String> getEnrolledStudentsID(int courseId);
	public void populate(); // Dummy method
}
