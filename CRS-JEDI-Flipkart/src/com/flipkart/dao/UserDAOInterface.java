package com.flipkart.dao;

import com.flipkart.bean.User;

public interface UserDAOInterface {

	public int verifyLoginCredentials(String email, String password);
	public boolean checkEmailAvailability(String email);
	public int registerUser(User user, String password);
}
