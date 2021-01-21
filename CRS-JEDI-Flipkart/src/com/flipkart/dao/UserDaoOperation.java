package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDaoOperation implements UserDaoInterface {

	Connection conn;
	PreparedStatement ps;
	/*
	 * Gets db connection, verifies login credentials,
	 * returns RoleId if login verified else Return -1;
	 */
	public int verifyLoginCredentials(String username, String password) {
		return -1;
	}

}
