package com.flipkart.business;

public interface StudentInterface {

    public void showcourses(int studentId);

    public void viewGrades(int studentId);

    public boolean makePayment(Sudent student);

    public boolean updateInfo(int studentId);

    public boolean addCourse(int studentId,int courseId);

    public boolean deleteCourse(int studentId,int courseId);

    public boolean registerCourses(Student student);

}
