package com.flipkart.bean;

import java.util.ArrayList;

public class Grades {
	private int studentId;
	private ArrayList<String> recordedGrades;
	
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public ArrayList<String> getRecordedGrades() {
		return recordedGrades;
	}
	public void setRecordedGrades(ArrayList<String> recordedGrades) {
		this.recordedGrades = recordedGrades;
	}
	
	
}
