package com.flipkart.service;
import org.apache.log4j.Logger;

import com.flipkart.bean.Student;
import com.flipkart.dao.CoursesDaoOperation;
import com.flipkart.bean.Course;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;

public class StudentOperation implements StudentInterface {
    private static Logger logger = Logger.getLogger(StudentOperation.class);
	CoursesDaoOperation courseListObj = new CoursesDaoOperation();

    public void showCourses(int studentId){
    	logger.info("Inside showCourse Method");
    	for (Course course : CoursesDaoOperation.courses) {
    		logger.info(course.getCourseID() + " " + course.getCourseName());
    	}
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

    public boolean addCourse(Student student, int courseId){
        logger.info("Inside addCourse Method");
        ArrayList<Integer> enrolledCourses = student.getEnrolledCourses();
        if(!enrolledCourses.contains(courseId)){
            enrolledCourses.add(courseId);
            student.setEnrolledCourses(enrolledCourses);
            logger.info("Course added successfully");
        }
        else{
            logger.info("Course already exists");
        }

        return false;
    }

    public boolean deleteCourse(Student student, int courseId){
        logger.info("Inside deleteCourse Method");

        ArrayList<Integer> enrolledCourses = student.getEnrolledCourses();
        if(enrolledCourses.contains(courseId)){
            enrolledCourses.remove(courseId);
            student.setEnrolledCourses(enrolledCourses);
            logger.info("Course removed successfully");
        }
        else{
            logger.info("Course does not exists");
        }

        return false;
    }

    public boolean registerCourses(Student student){
        logger.info("Inside registerCourse Method");
        logger.info("Enter 1 to add course");
        logger.info("Enter 2 to delete course");
        logger.info("Enter 3 to finish registration process");
        Scanner input = new Scanner(System.in);
        int operation = input.nextInt();
        if(operation==1){
            logger.info("Enter course ID: ");
            int courseID = input.nextInt();
            addCourse(student,courseID);
        }
        else if(operation==2){
            logger.info("Enter course ID: ");
            int courseID = input.nextInt();
            deleteCourse(student,courseID);
        }

        return false;
    }

    public void viewRegisteredCourses(int studentID){
        ArrayList<Integer> enrolledCourses = student.getEnrolledCourses();
    }
}


