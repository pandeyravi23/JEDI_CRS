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
    	logger.info("Welcome Professor " + professor.getUserName() + "!!");
        int choice;
        Scanner input = new Scanner(System.in);
        do{
            showChoices();
            choice = input.nextInt();

            switch (choice){
                case -1:
                    logger.info(".....Logging Out.....\n");
                    break;
                case 1:
                    gradeStudents();
                    break;
                case 2:
                    allotedCourse(professor.getUserId());
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
        logger.info("-1 to Logout");
    }

    // method to grade stduent
    public void gradeStudents(){
        Scanner sc = new Scanner(System.in);
        logger.info("Inside Grade Student Method");
		try {
			ProfessorOperation professorOperation = new ProfessorOperation();
			logger.info("	Enter courseId");
			int courseId = sc.nextInt();
			professorOperation.gradeStudents(courseId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
    }

    // method to view alloted course
    public void allotedCourse(int professorId){
    	ProfessorOperation professorOperation = new ProfessorOperation();
    	professorOperation.showCourses(professorId);
    }

	// method to view enrolled student in a particular course
	public void viewStudents() {
		Scanner sc = new Scanner(System.in);
		logger.info("Inside View Student Method");
		try {
			ProfessorOperation professorOperation = new ProfessorOperation();
			logger.info("	Enter courseId");
			int courseId = sc.nextInt();
			professorOperation.viewStudentsEnrolled(courseId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}
}