package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.flipkart.util.DBConnection;

public class AdminDAOOperation {
	
	private static Logger logger = Logger.getLogger(AdminDAOOperation.class);
	Connection connection = null;
	PreparedStatement ps = null;
	
	public boolean addAdmin(String userName, String password)
	{
		try
		{
			connection = DBConnection.getConnection();
			String sqlQuery = "insert into credentials(role, email, password) values(?, ?, ?)";
			ps = connection.prepareStatement(sqlQuery);
			
			ps.setInt(1, 3);
			ps.setString(2,  userName);
			ps.setString(3, password);
			
			ps.executeUpdate();
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
}
