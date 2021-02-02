package com.flipkart.service;

import java.util.ArrayList;

import org.json.JSONObject;

import com.flipkart.bean.Professor;
import com.flipkart.exception.ProfessorCRSException;


/**
 * Interface to Show the structure of
 * Professor operation Class
 * @author JEDI04
 */
public interface ProfessorInterface {
	public ArrayList<JSONObject> viewGrades(int courseId);
	public ArrayList<JSONObject> showCourses(int professorId);
	public Professor getProfessorByEmail(String email);
	public ArrayList<JSONObject> viewStudentsEnrolled(int courseId);
	public boolean updateStudentGrade(int courseId,int studentId, String grade);
	public String getProfessorById(int id);
}
