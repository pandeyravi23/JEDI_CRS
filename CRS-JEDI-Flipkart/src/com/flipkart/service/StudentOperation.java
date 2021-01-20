package com.flipkart.service;
import org.apache.log4j.Logger;

import com.flipkart.bean.Student;
import com.flipkart.dao.CoursesDaoOperation;
import com.flipkart.bean.Course;

import java.util.ArrayList;
import java.util.Scanner;

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

    public boolean updateInfo(Student student){
        logger.info("Inside updateInfo Method");
        
        logger.info("Enter 1 to update email");
        logger.info("Enter 2 to update name");
        
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        
        switch(choice)
        {
        	case 1:
        		logger.info("Enter the new email : ");
        		String email = sc.nextLine();
        		if(email.equals(""))
        			email = sc.nextLine();
        		student.setEmail(email);
        		logger.info("Email Successully updated");
        		break;
        	case 2:
        		logger.info("Enter the new name : ");
        		String name = sc.nextLine();
        		if(name.equals(""))
        			name = sc.nextLine();
        		student.setUserName(name);
        		logger.info("Name Successully updated");
        		break;
        }
        
        return false;
    }

    public boolean addCourse(Student student, int courseId){
        logger.info("Inside addCourse Method");
        ArrayList<Integer> enrolledCourses = student.getEnrolledCourses();
        if(enrolledCourses.contains(courseId)==false){
            enrolledCourses.add(courseId);
            student.setEnrolledCourses(enrolledCourses);
            //viewRegisteredCourses(student);
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
            enrolledCourses.remove(Integer.valueOf(courseId));
            student.setEnrolledCourses(enrolledCourses);
            logger.info("Course removed successfully");
        }
        else{
            logger.info("Course does not exists");
        }

        return false;
    }

    public boolean registerCourses(Student student){
    	student.setIsRegistered(true);
        logger.info("Inside registerCourse Method");
        while(true){
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
            else if(operation==3){
                logger.info("Proceed to make payment");
                break;
            }
        }
        return false;
    }

    public void viewRegisteredCourses(Student student){
        ArrayList<Integer> enrolledCourses = student.getEnrolledCourses();
        logger.info("Registered Course IDs: ");
        for(Integer i : enrolledCourses){
            logger.info(i);
        }
    }
}


