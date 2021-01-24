package com.flipkart.client;

import com.flipkart.bean.*;
import com.flipkart.dao.*;
import com.flipkart.service.*;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author JEDI04
 *  User Interactive menu if user logs in
 *  is a professor
 */
public class ProfessorMenu {
	private static Logger logger = Logger.getLogger(ProfessorMenu.class);
	private ProfessorInterface professorOperation = ProfessorOperation.getInstance();

	/**
	 *  @author JEDI04
	 *  Manages the major roles of professor
	 *  based on user inputs
	 *  
	 *  @param Professor(object)
	 *  @return none
	 */
	public void professorClient(Professor professor) {
		logger.info("Welcome Professor " + professor.getUserName() + "!!");
		int choice;
		Scanner input = new Scanner(System.in);
		do {
			showChoices();
			choice = input.nextInt();

			switch (choice) {
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
			case 4:
				viewGrades();
				break;
			default:
				logger.info("Invalid choice\n");
				break;
			}
		} while (choice != -1);
	}

	/**
	 * @author JEDI04
	 * Initializes professor object with 
	 * values in the database
	 * fetching from the email
	 * 
	 * @param email(String)
	 * @return none
	 */
	public void init(String email) {
		Professor professor = professorOperation.getProfessorByEmail(email);
		professorClient(professor);
	}

	/**
	 * @author JEDI04
	 * Grade students with 2 options
	 * 1) Grade Students with a particular courseId
	 * 2) Update student grades by providing courseId and studentId
	 * 
	 * @param none
	 * @return none
	 */
	public void gradeStudents() {
		Scanner sc = new Scanner(System.in);
		logger.info("Please select an operation: ");
		logger.info("1. Grade Students by CourseId");
		logger.info("2. Update Grade of Particular Student");
		int choice = sc.nextInt();
		if (choice == 2) {
			updateStudentGrades();
		} 
		else {
			try {
				logger.info("Enter courseId");
				int courseId = sc.nextInt();
				professorOperation.gradeStudents(courseId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @author JEDI04
	 * Displays List of all courses that are allotted
	 * to the professor carrying a particular professorId
	 * 
	 * @param professorId(integer)
	 * @return none
	 */
	public void allotedCourse(int professorId) {
		professorOperation.showCourses(professorId);
	}

	/**
	 * @author JEDI04
	 * Displays menu for professor interaction
	 * to perform various operations
	 * 
	 * @param none
	 * @return none
	 */
	public static void showChoices() {
		logger.info("Please select an operation: ");
		logger.info("1. Grade Students");
		logger.info("2. View Alloted Courses");
		logger.info("3. View Students Enrolled in a course ");
		logger.info("4. View Students Grade");
		logger.info("-1 to Logout");
	}

	/**
	 * @author JEDI04
	 * Updates grades for a student with given courseId
	 * and studentId
	 * 
	 * @param none
	 * @return none
	 */
	public void updateStudentGrades() {
		logger.info("Inside Grade Student Method");
		Scanner sc = new Scanner(System.in);
		try {

			logger.info("Enter courseId");
			int courseId = sc.nextInt();
			logger.info("Enter studentId");
			int studentId = sc.nextInt();
			logger.info("Enter Grade");
			String grade = sc.next();

			boolean status = professorOperation.updateStudentGrade(courseId, studentId, grade);
			logger.info("Update Grade Status : " + status + "\n\n");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 
	 * @author JEDI04
	 * Method to view enrolled student in a particular course
	 * whose grades are currently Not Available
	 * 
	 * @param none
	 * @return none
	 */
	public void viewStudents() {
		Scanner sc = new Scanner(System.in);
		logger.info("Inside View Student Method");
		try {
//			ProfessorOperation professorOperation = new ProfessorOperation();
			logger.info("	Enter courseId");
			int courseId = sc.nextInt();
			professorOperation.viewStudentsEnrolled(courseId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author JEDI04
	 * View grades of all students in a course 
	 *  by providing the courseId
	 *  
	 *  @param none
	 *  @return none
	 */
	public void viewGrades() {
		Scanner sc = new Scanner(System.in);
		logger.info("Inside View Student Method");
		try {
			logger.info("Enter courseId");
			int courseId = sc.nextInt();
			professorOperation.viewGrades(courseId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}