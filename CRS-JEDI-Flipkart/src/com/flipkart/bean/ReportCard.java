package com.flipkart.bean;

import java.util.ArrayList;

public class ReportCard {
	private int studentId;
	private ArrayList<String> grades;
	
	
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
