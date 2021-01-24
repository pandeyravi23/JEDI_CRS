package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.dao.CoursesDAOOperation;

import org.apache.log4j.Logger;

public class CourseOperation implements CourseInterface {

    private static Logger logger = Logger.getLogger(CourseOperation.class);
    
    CoursesDAOOperation coursesDaoOperation = new CoursesDAOOperation();

    @Override
    public boolean addCourse(Course course) {
        logger.debug("In add course method");
        return true;
    }

    @Override
    public boolean deleteCourse(int courseID) {
        logger.debug("In delete course method");
        return true;
    }

    @Override
    public void getCourseDetail(int courseID) {
        logger.debug("In get course detail method");
    }

    @Override
    public void assignProfessor(int courseID) {
        logger.debug("In assign professor method");
    }

	@Override
	public int noOfEnrolledStudents(int courseID) {
		int count = -1;
		try {
			count = coursesDaoOperation.noOfEnrolledStudents(courseID);
		}
		catch (Exception e) {
			
		}
		return count;
	}

	@Override
	public Course getCourseById(int courseID) {
		Course course = null;
		try {
			course = coursesDaoOperation.getCourseByID(courseID);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return course;
	}
	
	
    
    
}
