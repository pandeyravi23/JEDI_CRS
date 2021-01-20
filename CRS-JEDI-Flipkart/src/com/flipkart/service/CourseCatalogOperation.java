package com.flipkart.service;

import com.flipkart.bean.Course;

import org.apache.log4j.Logger;

public class CourseCatalogOperation implements CourseCatalogInterface{

    private static Logger logger = Logger.getLogger(CourseCatalogOperation.class);

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
}
