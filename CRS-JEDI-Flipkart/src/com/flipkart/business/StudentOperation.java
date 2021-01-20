package com.flipkart.business;
import org.apache.log4j.Logger;

import com.flipkart.bean.Student;

public class StudentOperation implements StudentInterface {
    private static Logger logger = Logger.getLogger(StudentOperation.class);

    public void showCourses(int studentId){
        logger.info("Inside showCourse Method");
    }

    public void viewGrades(int studentId){
        logger.info("Inside viewGrades Method");
    }

    public boolean makePayment(Student student){
        logger.info("Inside makePayment Method");
        return false;
    }

    public boolean updateInfo(int studentId){
        logger.info("Inside updateInfo Method");
        return false;
    }

    public boolean addCourse(int studentId,int courseId){
        logger.info("Inside addCourse Method");
        return false;
    }

    public boolean deleteCourse(int studentId,int courseId){
        logger.info("Inside deleteCourse Method");
        return false;
    }

    public boolean registerCourses(Student student){
        logger.info("Inside registerCourse Method");
        return false;
    }
}


