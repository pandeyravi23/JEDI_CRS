/**
 * 
 */
package com.flipkart.dao;

import java.util.ArrayList;

import org.json.JSONObject;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.exception.AdminCRSException;

/**
 * Interface to Show the structure of
 * AdminDAO Operation Class
 * @author JEDI04
 */
public interface AdminDAOInterface {
	public ArrayList<JSONObject> printGrades(int studentId);
	public boolean verifyEmail(String email) throws AdminCRSException;
	public int addAdmin(String password, Admin admin);
	public int addProfessor(String password, Professor prof);
	public boolean allotCourses(int courseId,int professorId);
	public boolean addCourse(Course course); 
	public boolean deleteCourse(int courseId); 
	public void approveStudent();
	public boolean startRegistrationWindow();
	public boolean closeRegistrationWindow();
}
