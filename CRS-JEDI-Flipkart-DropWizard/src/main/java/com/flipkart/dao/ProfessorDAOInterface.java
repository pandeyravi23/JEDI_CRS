package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.ArrayList;

import org.json.JSONObject;

/**
 * Interface to Show the structure of
 * ProfessorDAO Operation Class
 * @author JEDI04
 */
public interface ProfessorDAOInterface {
	public Professor getProfessorByEmail(String email);
	public String getProfessorById(int professorID);
	public ArrayList<JSONObject> showCourses(int professorId);
	public ArrayList<JSONObject> getEnrolledStudents(int courseId);
	public void setGrades(ArrayList<Student>toGrade,int courseId);
	public boolean updateStudentGrades(int courseId,int studentId, String grades);
	public ArrayList<JSONObject> showGrades(ArrayList<Student>enrolledStudent,int courseId);
	public ArrayList<Student> getStudents(int courseId);
}
