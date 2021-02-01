package com.flipkart.helper;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;

public class AddAdminHelper {
	
	@NotNull
	@Valid
	private Admin admin;
	
	@NotNull
	@Size(min = 4, max = 30, message = "Password length should between 4 and 30 characters.")
	private String password;
	
//	{
//		"admin":{
//			"userId":116, 
//			"userName":"Admin Ayush", 
//			"role":"admin", 
//			"email":"adminayush@gmail.com", 
//			"address":"admin ka ghar", 
//			"age":20, 
//			"gender":"male", 
//			"contact":"9660054658", 
//			"nationality":"indian"
//		},
//		"password":"12345"
//	}
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
}
