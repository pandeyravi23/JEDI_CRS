package com.flipkart.service;

import com.flipkart.bean.Course;

public interface CourseInterface {
    public boolean addCourse(Course course);

    public boolean deleteCourse(int courseID);

    public void getCourseDetail(int courseID);

    public void assignProfessor(int courseID);
}
