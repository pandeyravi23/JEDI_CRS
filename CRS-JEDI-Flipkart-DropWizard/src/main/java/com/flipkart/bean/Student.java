package com.flipkart.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class to interact with variables present in Student Class
 * using Getters and Setters Methods
 * @author JEDI04
 */

@XmlRootElement(name = "student")
public class Student extends User{
	
	@NotNull 
	private int rollNo;
	
	private boolean isRegistered;
	
	@NotNull 
	@Size(min = 2, max = 30, message = "The length of Branch should be between 2 to 25")
	private String branch;
	
	private boolean paymentStatus;
	
	public boolean getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

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
	
	
	public String getBranch() {
		return branch;
	}
	
	public void setBranch(String branch) {
		this.branch = branch;
	}
}
