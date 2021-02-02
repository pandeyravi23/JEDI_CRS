package com.flipkart.service;

import java.util.ArrayList;

import org.json.JSONObject;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.exception.StudentCRSException;

/**
 * Interface to Show the structure of
 * Admin Operation Class
 * @author JEDI04
 */

public interface AdminInterface {
	
	public ArrayList<JSONObject> generateReportCard(int sid) throws StudentCRSException, Exception;
	public boolean allotCourse(int courseID, int professorID);
	public int addProfessor(String password, Professor prof);
	public int addAdmin(Admin admin, String pwd1);
	public boolean approveStudents(int studentID);
	public void addCourse();
	public boolean deleteCourse(int courseID);
	public boolean startRegistrationWindow();
	public boolean closeRegistrationWindow();

}
