package com.flipkart.service;

import com.flipkart.bean.Professor;


public interface ProfessorInterface {
	public void viewGrades();
	public Boolean gradeStudents(int courseId);
	public void showCourses(int professorId);
	public Professor getProfessorByEmail(String email);
	public void viewStudentsEnrolled(int courseId);
	public boolean updateStudentGrade(int courseId,int studentId, String grade);
}
