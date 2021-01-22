package com.flipkart.service;

public interface AdminInterface {
	
	public void generateReportCard();
	public void addProfessor();
	public void addAdmin(String username, String password);
	public void approveStudent();
	public void addCourse();
	public void deleteCourse();
}
