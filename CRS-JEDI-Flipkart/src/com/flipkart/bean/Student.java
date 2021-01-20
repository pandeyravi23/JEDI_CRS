package com.flipkart.bean;
import java.util.ArrayList;

public class Student extends User{
	
	private int rollNo;
	private boolean isRegistered;
	private ArrayList<Integer> enrolledCourses = new ArrayList<>();
	public String branch;
	
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	
	public int getRollNo() {
		return rollNo;
	}
	
	public boolean getIsRegistered() {
		return isRegistered;
	}
	
	public void setIsRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}
	
	public ArrayList<Integer> getEnrolledCourses() {
		return enrolledCourses;
	}
	
	public void setEnrolledCourses(ArrayList<Integer> enrolledCourses) {
		this.enrolledCourses = enrolledCourses;
	}
	
	public String getBranch() {
		return branch;
	}
	
	public void setBranch(String branch) {
		this.branch = branch;
	}
}
