package com.flipkart.service;

import org.apache.log4j.Logger;

import com.flipkart.bean.Professor;
import com.flipkart.dao.ProfessorDaoOperation;

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
	public void showCourses(int professorId) {
		logger.info("Inside showCourses");
		ProfessorDaoOperation professorDaoOperation = new ProfessorDaoOperation();
		professorDaoOperation.showCourses(professorId);
	}

	public Professor getProfessorByEmail(String email) {
		ProfessorDaoOperation professorDaoOperation = new ProfessorDaoOperation();
		Professor p = professorDaoOperation.getProfessorByEmail(email);
		return p;
	}
}
