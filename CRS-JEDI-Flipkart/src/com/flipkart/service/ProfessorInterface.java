package com.flipkart.service;

import com.flipkart.bean.Professor;


public interface ProfessorInterface {
	public void viewStudentsEnrolled();
	public void viewGrades();
	public Boolean gradeStudents();
	public void showCourses();
	public Professor getProfessorByEmail(String email);
}
