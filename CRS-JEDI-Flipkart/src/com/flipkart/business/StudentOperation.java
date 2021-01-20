package com.flipkart.business;
import org.apache.log4j.Logger;

public class StudentOperation implements StudentInterface {
    private static Logger logger = Logger.getLogger(StudentOperation.class);

    public void showcourses(int studentId){
        looger.info("Inside showCourse Method");
    }

    public void viewGrades(int studentId){
        looger.info("Inside viewGrades Method");
    }

    public boolean makePayment(Sudent student){
        looger.info("Inside makePayment Method");
    }

    public boolean updateInfo(int studentId){
        looger.info("Inside updateInfo Method");
    }

    public boolean addCourse(int studentId,int courseId){
        looger.info("Inside addCourse Method");
    }

    public boolean deleteCourse(int studentId,int courseId){
        looger.info("Inside deleteCourse Method");
    }

    public boolean registerCourses(Student student){
        looger.info("Inside registerCourse Method");
    }
}


