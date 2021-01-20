package com.flipkart.client;

import com.flipkart.bean.Student;
import com.flipkart.dao.CoursesDaoOperation;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentOperation;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentCRSMenu {

    private static Logger logger = Logger.getLogger(StudentCRSMenu.class);
    private static StudentInterface studentOperation = new StudentOperation();
    public static Student student = new Student();
    //public static ArrayList<Integer> al = new ArrayList<>();

    public static void main(String[] args){

        student.setUserId(101);
        student.setUserName("Chinmay");
        student.setEmail("xyz@gmail.com");
        student.setRollNo(10275);
        student.setBranch("ECE");


        //populate
        CoursesDaoOperation obj = new CoursesDaoOperation();
        obj.populate();

        
        showChoices();
        Scanner input = new Scanner(System.in);
        int choice;

        do{
            choice = input.nextInt();
        switch (choice){
            case 1:
                studentOperation.showCourses(student.getUserId());
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
                studentOperation.updateInfo(student.getUserId());
                break;
            default:
                logger.info("Invalid choice");
                break;
        }}while (choice!=-1);
    }

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
    }

    public static void addCourse(){
        logger.info("Enter course ID to be added");
        Scanner input = new Scanner(System.in);
        int courseID = input.nextInt();
        studentOperation.addCourse(student,courseID);
    }

    public static void dropCourse(){
        logger.info("Enter course ID to be dropped");
        Scanner input = new Scanner(System.in);
        int courseID = input.nextInt();
        studentOperation.deleteCourse(student,courseID);
    }

    public static void makePayment(){
        logger.info("Choose a payment method: ");
        logger.info("1. Net Banking");
        logger.info("2. Credit card");
        logger.info("3. Scholarship");

        Scanner input = new Scanner(System.in);
        int paymentMethod = input.nextInt();
        studentOperation.makePayment(student);
    }
}
