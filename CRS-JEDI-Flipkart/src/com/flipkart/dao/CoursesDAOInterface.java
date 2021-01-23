package com.flipkart.dao;

import java.io.IOException;
import java.util.*;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.exception.CommonException;

public interface CoursesDAOInterface {
	public ArrayList<Student> getEnrolledStudents(int courseId);
	public Course getCourseByID(int courseID);
	public ArrayList<Course> getAllCourses() throws CommonException;

	public int noOfEnrolledStudents(int courseID);
}
