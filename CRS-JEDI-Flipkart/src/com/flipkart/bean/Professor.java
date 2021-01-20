package com.flipkart.bean;

import java.util.ArrayList;

public class Professor extends User {
	private String department;
	private ArrayList<String> listOfCourseAssigned = new ArrayList<>();
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public ArrayList<String> getListOfCourseAssigned() {
		return listOfCourseAssigned;
	}

	public void setListOfCourseAssigned(ArrayList<String> listOfCourseAssigned) {
		this.listOfCourseAssigned = listOfCourseAssigned;
	}
	
}
