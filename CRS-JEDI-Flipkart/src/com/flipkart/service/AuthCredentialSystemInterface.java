package com.flipkart.service;


import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

public interface AuthCredentialSystemInterface {
	public int login(String username, String password);
	
	public boolean checkEmailAvailability(String email);

	public void registerStudent(User user, Student student, String password);

	public void registerProfessor(Professor professor, String password);

	public void registerAdmin(Admin admin, String password);
	
	public int registerUser(User user, String password);
}
