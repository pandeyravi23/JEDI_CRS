package com.flipkart.service;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDaoOperation;


public class ProfessorOperation implements ProfessorInterface {
	
	private static Logger logger = Logger.getLogger(ProfessorOperation.class);
	
	@Override
	public void viewStudentsEnrolled(int courseId) {
		logger.info("Inside viewStudentEnrolled");
		ProfessorDaoOperation professorDaoOperation = new ProfessorDaoOperation();
		ArrayList<Student> studentsEnrolled = professorDaoOperation.getEnrolledStudents(courseId);
		if (studentsEnrolled.size()>0) {
			logger.info("\n========================================");
			logger.info("StudentID		StudentName		StudentEmail		Branch");
			for (Student st : studentsEnrolled) {
				logger.info(st.getUserId() + "		" + st.getUserName()+"		"+st.getEmail()+"		"+st.getBranch());
			}
			logger.info("========================================\n");
		}
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
