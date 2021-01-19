package com.flipkart.demo;
import java.util.ArrayList;

public class Student extends User{
	
	private int rollNo;
	private boolean isRegistered;
	private ArrayList<Integer> enrolledCourses;
	public String branch;
	
	public void setrollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	
	public int getrollNo() {
		return rollNo;
	}
	
	public boolean getisRegistered() {
		return isRegistered;
	}
	
	public void setisRegistered(boolean isRegistered) {
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
