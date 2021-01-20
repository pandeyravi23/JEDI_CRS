package com.flipkart.business;

import org.apache.log4j.Logger;

public class ProfessorOperation implements ProfessorInterface {
	
	private static Logger logger = Logger.getLogger(ProfessorOperation.class);
	
	@Override
	public void viewStudentsEnrolled() {
		logger.info("Inside viewStudentEnrolled");
	}

	@Override
	public void viewGrades() {
		logger.info("Inside viewGrades");
	}

	@Override
	public Boolean gradeStudents() {
		logger.info("Inside Grade Students");
		return true;
	}

	@Override
	public void showCourses() {
		logger.info("Inside showCourses");
	}

}
