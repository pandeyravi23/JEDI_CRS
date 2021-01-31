package com.flipkart.service;

import java.util.ArrayList;

import org.json.JSONObject;

import com.flipkart.bean.Professor;

/**
 * Interface to Show the structure of
 * Admin Operation Class
 * @author JEDI04
 */

public interface AdminInterface {
	
	public ArrayList<JSONObject> generateReportCard(int sid);
	public boolean allotCourse(int courseID, int professorID);
	public int addProfessor(String password, Professor prof);
	public void addAdmin();
	public boolean approveStudents(int studentID);
	public void addCourse();
	public boolean deleteCourse(int courseID);
	public boolean startRegistrationWindow();
	public boolean closeRegistrationWindow();

}
