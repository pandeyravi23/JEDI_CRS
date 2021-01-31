package com.flipkart.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Class to interact with variables present in Grades Class
 * using Getters and Setters Methods
 * @author JEDI04
 */


@XmlRootElement(name = "grades")
public class Grades {
	
	@NotNull 
	private int studentId;
	
	@NotNull 
	private int courseID;
	
	@NotNull 
	@Size(min = 2, max = 30, message = "The length of Course Name should be between 2 to 25")
	private String courseName;
	
	@NotNull 
	@Size(min = 2, max = 30, message = "The length of Grade should be between 2 to 25")
	private String grade;
	
	
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
	
}
