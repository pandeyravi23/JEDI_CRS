package com.flipkart.dao;

import java.sql.*;

import com.flipkart.constant.SQLQueriesConstant;
import org.apache.log4j.Logger;

import com.flipkart.bean.User;
import com.flipkart.util.DBConnection;

public class UserDAOOperation implements UserDAOInterface {
	
	private static Logger logger = Logger.getLogger(UserDAOOperation.class);
	Connection connection = null;
	PreparedStatement ps = null;
	/*
	 * Gets db connection, verifies login credentials,
	 * returns RoleId if login verified else Return -1;
	 */
	public int verifyLoginCredentials(String email, String password) {
		try {
			connection = DBConnection.getConnection();
			//String sqlQuery = "SELECT role FROM credentials WHERE email=? AND password=?";
			ps = connection.prepareStatement(SQLQueriesConstant.VERIFY_LOGIN_CREDENTIALS_QUERY);
			
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
	
	@Override
	public boolean checkEmailAvailability(String email) {
		try {
			connection = DBConnection.getConnection();
			//String sqlQuery = "SELECT * FROM credentials WHERE email=?";
			ps = connection.prepareStatement(SQLQueriesConstant.CHECK_EMAIL_AVAILABILITY_QUERY);
			ps.setString(1, email);
			
			ResultSet result = ps.executeQuery();
			if(result.next())
				return false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			// connection.close();
		}
		return true;
	}
	
	@Override
	public int registerUser(User user, String password) {
		int id = -1;
		try {
			connection = DBConnection.getConnection();
			//String sqlQuery = "INSERT INTO credentials(role, email, password, isApproved, address, age, gender, contact, nationality) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ps = connection.prepareStatement(SQLQueriesConstant.REGISTER_USER_QUERY, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, Integer.parseInt(user.getRole()));
			ps.setString(2,  user.getEmail());
			ps.setString(3,  password);
			ps.setBoolean(4,  user.isApproved());
			ps.setString(5, user.getAddress());
			ps.setInt(6, user.getAge());
			ps.setString(7, user.getGender());
			ps.setString(8,  user.getContact());
			ps.setString(9, user.getNationality());
			
			ps.executeUpdate();
			ResultSet resultSet = ps.getGeneratedKeys();
			
			resultSet.next();
			id = resultSet.getInt(1);
			return id;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			// connection.close();
		}
		return id;
	}

}
