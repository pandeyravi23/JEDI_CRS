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
	public void allotCourse();
	public int addProfessor(String password, Professor prof);
	public void addAdmin();
	public void approveStudents();
	public void addCourse();
	public void deleteCourse();
	public boolean startRegistrationWindow();
	public boolean closeRegistrationWindow();
}
