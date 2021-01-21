package com.flipkart.dao;

import java.sql.ResultSet;

public interface UserDaoInterface {
	public int verifyLoginCredentials(String email, String password);
}
