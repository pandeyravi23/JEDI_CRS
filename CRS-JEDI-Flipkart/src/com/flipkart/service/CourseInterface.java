package com.flipkart.service;

import com.flipkart.bean.Course;

public interface CourseInterface {

    public int noOfEnrolledStudents(int courseID);
    
    public Course getCourseById(int courseID);
}
