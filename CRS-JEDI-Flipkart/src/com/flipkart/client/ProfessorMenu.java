package com.flipkart.client;

import com.flipkart.bean.*;
import com.flipkart.dao.*;
import com.flipkart.service.*;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class ProfessorMenu {
    private static Logger logger = Logger.getLogger(ProfessorMenu.class);
    private ProfessorInterface professorOperation = new ProfessorOperation();

    public void professorClient(Professor professor){
    	logger.info("Welcome Professor " + professor.getUserName());
        int choice;
        do{
            showChoices();
            choice = input.nextInt();

            switch (choice){
                case -1:
                    logger.info(".....Exiting Menu.....\n");
                    break;
                case 1:
                    viewGrades();
                    break;
                case 2:
                    allotedCourse();
                    break;
                case 3:
                    viewStudents();
                    break;
                default:
                    logger.info("Invalid choice\n");
                    break;
            }
        }while (choice!=-1);
    }
    
    
    
    public void init(String email) {
    	Professor professor = professorOperation.getProfessorByEmail(email);
    	professorClient(professor);
    }

    public static void showChoices(){
        logger.info("Please select an operation: ");
        logger.info("1. Grade Students");
        logger.info("2. View Alloted Courses");
        logger.info("3. View Students Enrolled in a course ");
        logger.info("-1 to exit menu");
    }

    // method to add course
    public void viewCourse(){
        logger.info("Inside View Course Method");
    }

    // method to drop a course
    public void allotedCourse(){
        logger.info("Inside Alloted Course Method");
    }

    // method to make payment
    public void viewStudents(){
    	logger.info("Inside View Student Method");
    }
}
