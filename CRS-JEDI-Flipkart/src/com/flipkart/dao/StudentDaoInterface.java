package com.flipkart.dao;

import java.util.*;

import com.flipkart.bean.Student;


public interface StudentDaoInterface {
	public List<Student> getAllStudents();
	public Student getStudentById(int studentId);
	public void populate();
	public Student getStudentByEmail(String email);
}
