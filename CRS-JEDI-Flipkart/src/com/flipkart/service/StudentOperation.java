package com.flipkart.service;
import org.apache.log4j.Logger;

import com.flipkart.bean.Student;
import com.flipkart.dao.CoursesDaoOperation;
import com.flipkart.bean.Course;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * @author JEDI 04
 */

public class StudentOperation implements StudentInterface {
    private static Logger logger = Logger.getLogger(StudentOperation.class);
	CoursesDaoOperation courseListObj = new CoursesDaoOperation();

	// Show courses Details
    public void showCourses(int studentId){
    	logger.info("================AVAILABLE COURSES================\n");
    	for (Course course : CoursesDaoOperation.courses) {
    		logger.info(course.getCourseID() + " " + course.getCourseName());
    	}
    	logger.info("=================================================\n");
    }

    // view Student grades 
    public void viewGrades(int studentId){
        logger.info("Inside viewGrades Method\n");

    }

    public boolean makePayment(Student student){
//        logger.info("Inside makePayment Method\n");
    	logger.info("Available options: \n");
    	logger.info("Enter 1 to proceed via Netbanking");
        logger.info("Enter 2 to proceed via Debit card");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        
        switch(choice)
        {
        	case 1:// Payment using Netbanking
        		logger.info("Enter Bank Name : ");
        		String bank = sc.nextLine();
        		if(bank.equals(""))
        			bank = sc.nextLine();
        		logger.info("Enter Ifsc Code : ");
        		String ifsc = sc.nextLine();
        		if(ifsc.equals(""))
        			ifsc = sc.nextLine();
        		logger.info("Enter Account Number : ");
        		String account = sc.nextLine();
        		if(account.equals(""))
        			account = sc.nextLine();
        		logger.info("Payment Done Successfully");
        		logger.info("===========================================\n\n");
        		return true;
        	case 2:// Payment using Debit card 
        		logger.info("Enter Card Number : ");
        		String card = sc.nextLine();
        		if(card.equals(""))
        			card = sc.nextLine();
        		logger.info("Enter Expiration Date : ");
        		String date = sc.nextLine();
        		if(date.equals(""))
        			date = sc.nextLine();
        		logger.info("Enter cvv Number : ");
        		String cvv = sc.nextLine();
        		if(cvv.equals(""))
        			cvv = sc.nextLine();
        		logger.info("Payment Done Successfully");
        		logger.info("===========================================\n\n");
        		return true;
        	default:
        		logger.info("Invalid choice----Exiting----");
        		logger.info("===========================================\n\n");
        		return false;
        }
    }

    // updating student information 
    public boolean updateInfo(Student student){
        logger.info("================UPDATE INFO================\n");
        
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
        	default:
        		logger.info("Invalid choice----Exiting----");
        }
        logger.info("===========================================\n\n");
        return false;
    }

    //Adding new Course
    public boolean addCourse(Student student, int courseId){
        ArrayList<Integer> enrolledCourses = student.getEnrolledCourses();
        if(enrolledCourses.contains(courseId)==false){
            enrolledCourses.add(courseId);
            student.setEnrolledCourses(enrolledCourses);
            //viewRegisteredCourses(student);
            logger.info(">>>>  Course added successfully  <<<<\n");
        }
        else{
            logger.info(">>>>  Course already exists  <<<<\n");
        }
        return false;
    }

    //Dropping existing course from student enrolled courses list
    public boolean deleteCourse(Student student, int courseId){
        ArrayList<Integer> enrolledCourses = student.getEnrolledCourses();
        if(enrolledCourses.contains(courseId)){
            enrolledCourses.remove(Integer.valueOf(courseId));
            student.setEnrolledCourses(enrolledCourses);
            logger.info(">>>>  Course removed successfully  <<<<\n");
        }
        else{
            logger.info(">>>>  Course does not exists  <<<<\n");
        }
        return false;
    }

    // Register course
    public boolean registerCourses(Student student){
    	student.setIsRegistered(true);
        logger.info("================COURSE REGISTRATION================\n");
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
                logger.info("Proceed to make payment\n");
                break;
            }
        }
        logger.info("==============================================\n");
        return false;
    }

    //View Enrolled courses 
    public void viewRegisteredCourses(Student student){
        ArrayList<Integer> enrolledCourses = student.getEnrolledCourses();
        logger.info("================REGISTERED COURSES================\n");
        for(Integer i : enrolledCourses){
            logger.info(i);
        }
        logger.info("==================================================\n");
    }
}


