package com.flipkart.service;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDaoOperation;


public class ProfessorOperation implements ProfessorInterface {
	
	private static Logger logger = Logger.getLogger(ProfessorOperation.class);
	private ProfessorDaoOperation professorDaoOperation = new ProfessorDaoOperation();
	@Override
	public void viewStudentsEnrolled(int courseId) {
		logger.info("Inside viewStudentEnrolled");
		ArrayList<Student> studentsEnrolled = professorDaoOperation.getEnrolledStudents(courseId);
		if (studentsEnrolled.size()>0) {
			logger.info("\n\n");
			logger.info("====================================================================================================");

			logger.info("StudentID		StudentName		StudentEmail			Branch");
			for (Student st : studentsEnrolled) {
				logger.info(st.getUserId() + "			" + st.getUserName()+"			"+st.getEmail()+"		"+st.getBranch());
			}
			logger.info("====================================================================================================");
		}
	}

	@Override
	public void viewGrades(int courseId) {
		ArrayList<Student> studentsEnrolled = professorDaoOperation.getStudents(courseId);
		if (studentsEnrolled.size()>0) {
			professorDaoOperation.showGrades(studentsEnrolled,courseId);
		}
		else {
			logger.info("No Student present !!");
		}
	}

	@Override
	public Boolean gradeStudents (int courseId) {
		ArrayList<Student> studentsEnrolled = professorDaoOperation.getEnrolledStudents(courseId);
		if (studentsEnrolled.size()>0) {
			professorDaoOperation.setGrades(studentsEnrolled,courseId);
		}
		else {
			logger.info("No Student to Grade");
		}
		return true;
	}

	@Override
	public void showCourses(int professorId) {
		logger.info("Inside showCourses");
		professorDaoOperation.showCourses(professorId);
	}

	public Professor getProfessorByEmail(String email) {
		Professor p = professorDaoOperation.getProfessorByEmail(email);
		return p;
	}
	
	/*
	 *  Updates Student grade and returns upadte status
	 *  as boolean
	 */
	public boolean updateStudentGrade(int courseId,int studentId, String grade) {
		return professorDaoOperation.updateStudentGrades(courseId, studentId, grade);
	}
}
