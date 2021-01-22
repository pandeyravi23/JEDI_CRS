package com.flipkart.service;

import com.flipkart.bean.Student;
import com.flipkart.bean.Course;

public interface StudentInterface {

    public void showCourses();

    public void viewGrades(int studentId);

    public void makePayment(Student student);

    public boolean updateInfo(Student student);

    public boolean addCourse(Student student,int courseId);

    public boolean deleteCourse(Student student,int courseId);

    public boolean registerCourses(Student student);

    public void viewRegisteredCourses(Student student);
    
    public Student getStudentByEmail(String email);

    public void setRegistrationStatus(Student student);
    
    public int getNumberOfEnrolledCourses(Student student);

}
