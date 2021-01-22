package com.flipkart.client;

import com.flipkart.bean.*;
import com.flipkart.dao.*;
import com.flipkart.service.*;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * @author JEDI 04
 */
public class StudentCRSMenu {
    private static Logger logger = Logger.getLogger(StudentCRSMenu.class);
    private StudentInterface studentOperation = new StudentOperation();
    private  Student student = new Student();
    //public static ArrayList<Integer> al = new ArrayList<>();

    public void studentClient(){
        Scanner input = new Scanner(System.in);

        int choice;

        do{
            showChoices();
            choice = input.nextInt();

            switch (choice){
                case -1:
                    logger.info(".....Logged Out.....\n");
                    break;
                case 1:
                    studentOperation.showCourses();
                    break;
                case 2:
                    studentOperation.registerCourses(student);
                    break;
                case 3:
                    addCourse();
                    break;
                case 4:
                    dropCourse();
                    break;
                case 5:
                    studentOperation.viewRegisteredCourses(student);
                    break;
                case 6:
                    studentOperation.viewGrades(student.getUserId());
                    break;
                case 7:
                    makePayment();
                    break;
                case 8:
                    studentOperation.updateInfo(student);
                    break;
                default:
                    logger.info("Invalid choice\n");
                    break;
            }
        }while (choice!=-1);
    }
    
    
    
    public void init(String email) {
    	student = studentOperation.getStudentByEmail(email);
    	studentClient();
    }

    //showing available choices for student
    public static void showChoices(){
        logger.info("Select an operation: ");
        logger.info("1. Show courses");
        logger.info("2. Register courses");
        logger.info("3. Add a course");
        logger.info("4. Drop a course");
        logger.info("5. View registered courses");
        logger.info("6. View grades");
        logger.info("7. Make payment");
        logger.info("8. Update info");
        logger.info("-1 to Logout");
    }


    // method to add course
    public void addCourse(){
    	if (student.getIsRegistered()==false) {
        	logger.info("Student needs to start registration to add course\n");
        	return;
        }
    	
        logger.info("Enter course ID to be added");
        Scanner input = new Scanner(System.in);
        int courseID = input.nextInt();
        studentOperation.addCourse(student,courseID);
    }

    // method to drop a course
    public void dropCourse(){
    	if (student.getIsRegistered()==false) {
        	logger.info("Student needs to start registration to drop course\n");
        	return;
        }
    	
        logger.info("Enter course ID to be dropped");
        Scanner input = new Scanner(System.in);
        int courseID = input.nextInt();
        studentOperation.deleteCourse(student,courseID);
    }

    // method to make payment
    public void makePayment(){
    	if(student.getEnrolledCourses().size()==0) {
    		logger.info("Please Register courses to Make Payment: ");
    		return ;
    	}
    	
        logger.info("Choose a payment method: ");
        studentOperation.makePayment(student);
    }
}
