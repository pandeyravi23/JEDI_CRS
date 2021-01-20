package com.flipkart.business;

import com.flipkart.bean.Student;

public interface StudentInterface {

    public void showCourses(int studentId);

    public void viewGrades(int studentId);

    public boolean makePayment(Student student);

    public boolean updateInfo(int studentId);

    public boolean addCourse(int studentId,int courseId);

    public boolean deleteCourse(int studentId,int courseId);

    public boolean registerCourses(Student student);

}
