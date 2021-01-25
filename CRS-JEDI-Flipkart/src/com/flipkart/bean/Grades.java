package com.flipkart.bean;

import java.util.ArrayList;

/**
 * Manages the setting, retrieving and updating the attributes
 * of a grade
 * @author JEDI04
 *
 */
public class Grades {
	private int studentId;
	private int courseID;
	private String courseName;
	private String grade;
	//private ArrayList<String> recordedGrades = new ArrayList<>();
	
	
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseId(){
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getCourseName(){
		return courseName;
	}

	public void setCourseName(String courseName){
		this.courseName = courseName;
	}

	public String getGrade(){
		return grade;
	}

	public void setGrade(String grade){
		this.grade = grade;
	}

	/*public ArrayList<String> getRecordedGrades() {
		return recordedGrades;
	}

	public void setRecordedGrades(ArrayList<String> recordedGrades) {
		this.recordedGrades = recordedGrades;
	}*/
	
	
}
