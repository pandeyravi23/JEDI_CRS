package com.flipkart.service;

import com.flipkart.bean.Professor;


public interface ProfessorInterface {
	public void viewGrades();
	public Boolean gradeStudents();
	public void showCourses(int professorId);
	public Professor getProfessorByEmail(String email);
	void viewStudentsEnrolled(int courseId);
}
