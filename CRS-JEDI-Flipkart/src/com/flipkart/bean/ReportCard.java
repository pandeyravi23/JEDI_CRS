package com.flipkart.bean;

import java.util.ArrayList;

/**
 * Manages the setting, retrieving and updating the attributes
 * of the ReportCard
 * @author JEDI04
 *
 */
public class ReportCard {
	private int studentId;
	private ArrayList<String> grades = new ArrayList<>();
	
	
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public ArrayList<String> getGrades() {
		return grades;
	}

	public void setGrades(ArrayList<String> grades) {
		this.grades = grades;
	}
	
	
}
