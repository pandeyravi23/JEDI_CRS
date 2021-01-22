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
			default:
				logger.info("Invalid choice\n");
				break;
			}
		} while (choice != -1);
	}

	public void init(String email) {
		Professor professor = professorOperation.getProfessorByEmail(email);
		professorClient(professor);
	}

	// method to grade stduent
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
				ProfessorOperation professorOperation = new ProfessorOperation();
				logger.info("Enter courseId");
				int courseId = sc.nextInt();
				professorOperation.gradeStudents(courseId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// method to view alloted course
	public void allotedCourse(int professorId) {
		ProfessorOperation professorOperation = new ProfessorOperation();
		professorOperation.showCourses(professorId);
	}

	public static void showChoices() {
		logger.info("Please select an operation: ");
		logger.info("1. Grade Students");
		logger.info("2. View Alloted Courses");
		logger.info("3. View Students Enrolled in a course ");
		logger.info("-1 to Logout");
	}

	// method to add course
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

			ProfessorOperation professorOperation = new ProfessorOperation();
			boolean status = professorOperation.updateStudentGrade(courseId, studentId, grade);
			logger.info("Update Grade Status : " + status + "\n\n");

		} catch (Exception e) {
			e.printStackTrace();
		}
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
		}
	}
}