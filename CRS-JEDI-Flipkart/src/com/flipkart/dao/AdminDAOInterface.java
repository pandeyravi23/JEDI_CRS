/**
 * 
 */
package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.exception.AdminCRSException;

/**
 * @author jedi04
 *
 */
public interface AdminDAOInterface {
	public void printGrades(int studentId);
	public boolean verifyEmail(String email) throws AdminCRSException;
	public int addAdmin(String password, Admin admin);
	public int addProfessor(String password, Professor prof);
	public void allotCourses(int courseId,int professorId);
}
