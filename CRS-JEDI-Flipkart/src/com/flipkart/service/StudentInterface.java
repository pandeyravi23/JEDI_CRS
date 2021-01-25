package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.ArrayList;

public interface StudentInterface {

    public void showCourses();
    
    public ArrayList<Course> getAllCourses();

    public void viewGrades(int studentId);

    public void makePayment(Student student);

    public boolean updateInfo(Student student);

    public boolean addCourse(Student student,int courseId);

    public boolean deleteCourse(Student student,int courseId);
    
    public boolean registerCourses(ArrayList<Integer> courseCart, Student student);

    public void viewRegisteredCourses(Student student);
    
    public Student getStudentByEmail(String email);

    public void setRegistrationStatus(Student student);
    
    public int getNumberOfEnrolledCourses(Student student);

    public boolean getRegistrationSystemStatus();

}
