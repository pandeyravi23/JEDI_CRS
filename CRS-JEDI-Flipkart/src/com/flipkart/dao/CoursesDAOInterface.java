package com.flipkart.dao;

import java.util.*;

import com.flipkart.bean.Course;
import com.flipkart.exception.CommonException;

public interface CoursesDAOInterface {
	public Course getCourseByID(int courseID);
	public ArrayList<Course> getAllCourses() throws CommonException;

	public int noOfEnrolledStudents(int courseID);
}
