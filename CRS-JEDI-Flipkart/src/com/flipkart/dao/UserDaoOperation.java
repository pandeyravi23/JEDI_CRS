package com.flipkart.dao;

import java.sql.Connection;
import java.sql.*;

import org.apache.log4j.Logger;

import com.flipkart.util.DBConnection;

public class UserDaoOperation implements UserDaoInterface {
	
	private static Logger logger = Logger.getLogger(UserDaoOperation.class);
	Connection connection = null;
	PreparedStatement ps = null;
	/*
	 * Gets db connection, verifies login credentials,
	 * returns RoleId if login verified else Return -1;
	 */
	public int verifyLoginCredentials(String email, String password) {
		try {
			connection = DBConnection.getConnection();
			String sqlQuery = "SELECT role FROM credentials WHERE email=? AND password=?";
			ps = connection.prepareStatement(sqlQuery);
			
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet result = ps.executeQuery();
			int role = -1;
			while(result.next()) {
				role = result.getInt("role");
			}
			return role;
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return -1;
	}

}
