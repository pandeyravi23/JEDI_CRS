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
        logger.info("Welcome " + student.getUserName() + "!\n");
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
                    updateInfo();
                    //studentOperation.updateInfo(student);
                    break;
                default:
                    logger.info("Invalid choice.\n");
                    break;
            }
        }while (choice!=-1);
    }
    
    
    
    public void init(String email) {
    	student = studentOperation.getStudentByEmail(email);
    	if(student.isApproved()) {
    		studentClient();
    	}
    	else {
    		logger.info("Student not yet approved by Admin.\n");
    	}
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
        logger.info("8. Update personal info");
        logger.info("-1 to Logout");
    }


    // method to add course
    public void addCourse(){
    	if (!student.getIsRegistered()) {
        	logger.info("Student needs to start registration to add course.\n");
        	return;
        }
    	else if(studentOperation.getNumberOfEnrolledCourses(student) >= 6) {
    		logger.info("Cannot add more courses. You already have 6 courses.\n");
    		return;
    	}
    	
        logger.info("Enter course ID to be added");
        Scanner input = new Scanner(System.in);
        int courseID = input.nextInt();
        studentOperation.addCourse(student,courseID);
    }

    // method to drop a course
    public void dropCourse(){
    	if (!student.getIsRegistered()) {
        	logger.info("Student needs to start registration to drop course\n");
        	return;
        }
    	else if(studentOperation.getNumberOfEnrolledCourses(student) == 4) {
    		logger.info("Cannot drop the course. You only have 4 courses\n");
    		return;
    	}
    	
        logger.info("Enter course ID to be dropped");
        Scanner input = new Scanner(System.in);
        int courseID = input.nextInt();
        studentOperation.deleteCourse(student,courseID);
    }

    // method to make payment
    public void makePayment(){
    	if(!student.getIsRegistered()) {
    		logger.info("Please complete your course registration to make payment\n");
    	}
    	else if(student.getPaymentStatus()) {
    		logger.info("Payment already made");
    	}
    	else {
        	logger.info("Available options: \n");
        	logger.info("1. To pay via Netbanking");
            logger.info("2. To pay via Debit card");
            logger.info("3. To use Scholarship");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            // operations based on payment method
            switch(choice)
            {
            	case 1:   // for net banking method
            		logger.info("You have chosen net banking");
            		break;
            	case 2:   // for credit card method
            		logger.info("You have chosen debit card");
            		break;
            	case 3: // for scholarship
            		logger.info("You have chosen to use Scholarship");
            		break;
            	default:
            		logger.info("Invalid choice----Exiting----");
            		logger.info("===========================================\n\n");
            }
            
            logger.info(">>> Proceed to make payment <<<");
            studentOperation.makePayment(student);
    	}
    }

    public void updateInfo(){
        logger.info("================UPDATE INFO================\n");
        // Name, age, address, contact, gender, nationality

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            logger.info("Enter 1 to update Name.");
            logger.info("Enter 2 to update Age.");
            logger.info("Enter 3 to update Address.");
            logger.info("Enter 4 to update Contact.");
            logger.info("Enter 5 to update Gender.");
            logger.info("Enter 6 to update Nationality.");
            logger.info("Enter 7 to confirm update.");
            logger.info("Enter -1 to exit.");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case -1:
                    logger.info(">>>>> Exiting <<<<<\n");
                    break;
                case 1:
                    logger.info("Enter name:");
                    String name = sc.nextLine();
                    student.setUserName(name);
                    break;
                case 2:
                    logger.info("Enter age:");
                    int age = Integer.parseInt(sc.nextLine());
                    student.setAge(age);
                    break;
                case 3:
                    logger.info("Enter address:");
                    String address = sc.nextLine();
                    student.setAddress(address);
                    break;
                case 4:
                    logger.info("Enter contact:");
                    String contact = sc.nextLine();
                    student.setContact(contact);
                    break;
                case 5:
                    logger.info("Enter gender:");
                    String gender = sc.nextLine();
                    student.setGender(gender);
                    break;
                case 6:
                    logger.info("Enter nationality:");
                    String nationality = sc.nextLine();
                    student.setNationality(nationality);
                    break;
                case 7:
                    studentOperation.updateInfo(student);
                    logger.info(">>>>>>> Student Information Updated. <<<<<<<\n");
                    break;
                default:
                    logger.info("Invalid choice.\n");
            }

        }while(choice != -1);
    }
}
