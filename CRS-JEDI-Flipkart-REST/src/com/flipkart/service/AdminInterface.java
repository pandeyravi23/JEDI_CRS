package com.flipkart.service;

import java.util.ArrayList;

import org.json.JSONObject;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.exception.AdminCRSException;

/**
 * Interface to Show the structure of
 * Admin Operation Class
 * @author JEDI04
 */

public interface AdminInterface {
	
	public ArrayList<JSONObject> generateReportCard(int sid);
	public boolean allotCourse(int courseID, int professorID) throws AdminCRSException, Exception;
	public int addProfessor(String password, Professor prof);
	public int addAdmin(Admin admin, String pwd1);
	public boolean approveStudents(int studentID) throws AdminCRSException, Exception;
	public void addCourse();
	public boolean deleteCourse(int courseID) throws AdminCRSException, Exception;
	public boolean startRegistrationWindow();
	public boolean closeRegistrationWindow();

}
