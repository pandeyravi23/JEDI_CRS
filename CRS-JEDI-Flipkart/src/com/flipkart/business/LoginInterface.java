package com.flipkart.business;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

public interface LoginInterface {
	public boolean login(String username, String password);

	public void registerStudent(Student student, String password);

	public void registerProfessor(Professor professor, String password);

	public void registerAdmin(Admin admin, String password);
}
