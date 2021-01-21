package com.flipkart.service;
import org.apache.log4j.Logger;

import com.flipkart.bean.Student;
import com.flipkart.dao.CoursesDaoOperation;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.bean.Course;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentOperation implements StudentInterface {
    private static Logger logger = Logger.getLogger(StudentOperation.class);
	CoursesDaoOperation coursesDaoOperation = new CoursesDaoOperation();
    StudentDaoOperation studentDaoOperation = new StudentDaoOperation();

	// operation to show available courses in course catalog
    public void showCourses(){

        try{
            ArrayList<Course> courses = coursesDaoOperation.getAllCourses();
            logger.info("================AVAILABLE COURSES================\n");
            logger.info("Course ID\tCourse Name\tCredits");
            for (Course course : courses) {
                logger.info(course.getCourseID() + "\t\t" + course.getCourseName() + "\t\t" + course.getCredits());
            }
            logger.info("=================================================\n");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void viewGrades(int studentId){
        logger.info("Inside viewGrades Method\n");

    }

    // operation to make payment
    public boolean makePayment(Student student){
        logger.info("Inside makePayment Method\n");
    	logger.info("Available options: \n");
    	logger.info("Enter 1 to proceed via Netbanking");
        logger.info("Enter 2 to proceed via Debit card");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        // operations based on payment method
        switch(choice)
        {
        	case 1:   // for net banking method
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
        	case 2:   // for credit card method
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

    // operation to update student info
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

    // operation to add course to registered courses
    public boolean addCourse(Student student, int courseId){
        /*ArrayList<Integer> enrolledCourses = student.getEnrolledCourses();
        if(enrolledCourses.contains(courseId)==false){
            enrolledCourses.add(courseId);
            student.setEnrolledCourses(enrolledCourses);
            //viewRegisteredCourses(student);
            logger.info(">>>>  Course added successfully  <<<<\n");
        }
        else{
            logger.info(">>>>  Course already exists  <<<<\n");
        }*/


        try{
            studentDaoOperation.addCourse(student,courseId);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    // operation to delete course from registered courses
    public boolean deleteCourse(Student student, int courseId){
        /*ArrayList<Integer> enrolledCourses = student.getEnrolledCourses();
        if(enrolledCourses.contains(courseId)){
            enrolledCourses.remove(Integer.valueOf(courseId));
            student.setEnrolledCourses(enrolledCourses);
            logger.info(">>>>  Course removed successfully  <<<<\n");
        }
        else{
            logger.info(">>>>  Course does not exists  <<<<\n");
        }*/


        try{
            studentDaoOperation.dropCourse(student,courseId);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    // operation to register for courses
    public boolean registerCourses(Student student){
    	if(student.getIsRegistered()){
    	    logger.info("You have already registered");
    	    return true;
        }

    	int courseCounter = 0;
        logger.info("================COURSE REGISTRATION================\n");
        while(true){
            logger.info("Enter 1 to add course");
            logger.info("Enter 2 to delete course");
            logger.info("Enter 3 to finish registration process");
            Scanner input = new Scanner(System.in);
            int operation = input.nextInt();

            if(operation==1){
                logger.info("Enter course ID to be added: ");
                int courseID = input.nextInt();
                addCourse(student,courseID);
                courseCounter++;
            }
            else if(operation==2){
                logger.info("Enter course ID to be dropped: ");
                int courseID = input.nextInt();
                deleteCourse(student,courseID);
                courseCounter--;
            }
            else if(operation==3){
                if(courseCounter>=4 && courseCounter<=6){
                    logger.info("Proceed to make payment\n");
                    setRegistrationStatus(student);
                    student.setIsRegistered(true);
                    break;
                }
                else if(courseCounter<4){
                    logger.info("Less than 4 courses registered. Add more courses");
                }
                else if(courseCounter>6){
                    logger.info("More than 6 courses registered. Drop few courses");
                }
            }
        }
        logger.info("==============================================\n");
        return false;
    }


    // operation to show registered courses
    public void viewRegisteredCourses(Student student){

        try{
            ArrayList<Course> enrolledCourses = studentDaoOperation.getEnrolledCourses(student);
            logger.info("================REGISTERED COURSES================\n");
            logger.info("Course ID\tCourse Name");
            for(Course course : enrolledCourses){
                logger.info(course.getCourseID() + "\t\t" + course.getCourseName());
            }
            logger.info("==================================================\n");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    
    public Student getStudentByEmail(String email) {
    	StudentDaoOperation studentOperation = new StudentDaoOperation();
    	Student st = studentOperation.getStudentByEmail(email);
    	return st;
    }

    public void setRegistrationStatus(Student student){
        try{
            studentDaoOperation.setRegistrationStatus(student);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}


