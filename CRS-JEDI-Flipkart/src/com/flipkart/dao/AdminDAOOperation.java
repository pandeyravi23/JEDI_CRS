package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.util.DBConnection;

public class AdminDAOOperation implements AdminDAOInterface {
	
	private static Logger logger = Logger.getLogger(AdminDAOOperation.class);
	Connection connection = DBConnection.getConnection();
	PreparedStatement ps = null;
	
//	returns false if the email is already present in the database. Returns true otherwise.
	public boolean verifyEmail(String email)
	{
		try
		{
			String sqlQuery = "select email from credentials where email = ?";
			ps = connection.prepareStatement(sqlQuery);
			
			ps.setString(1, email);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next())
				return false;
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
		return true;
	}
	
	public int addAdmin(String password, Admin admin)
	{
		try
		{
			String sqlQuery = "insert into credentials(role, email, password, isApproved, address, age, gender, contact, nationality) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ps = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, 3);
			ps.setString(2,  admin.getEmail());
			ps.setString(3, password);
			ps.setInt(4,  1);
			ps.setString(5, admin.getAddress());
			ps.setInt(6, admin.getAge());
			ps.setString(7, admin.getGender());
			ps.setString(8,  admin.getContact());
			ps.setString(9, admin.getNationality());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
			rs.next();
			admin.setUserId(rs.getInt(1));
			
			String adminQuery = "insert into admin values(?, ?, ?)";
			ps = connection.prepareStatement(adminQuery);
			ps.setInt(1, admin.getUserId());
			ps.setString(2, admin.getUserName());
			ps.setString(3, admin.getEmail());
			
			ps.executeUpdate();
			return 1;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public int addProfessor(String password, Professor prof)
	{
		try
		{
			String credQuery = "insert into credentials(role, email, password, isApproved, address, age, gender, contact, nationality) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ps = connection.prepareStatement(credQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 2);
			ps.setString(2,  prof.getEmail());
			ps.setString(3, password);
			ps.setInt(4,  1);
			ps.setString(5, prof.getAddress());
			ps.setInt(6, prof.getAge());
			ps.setString(7, prof.getGender());
			ps.setString(8,  prof.getContact());
			ps.setString(9, prof.getNationality());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
			rs.next();
			prof.setUserId(rs.getInt(1));
			
			String profQuery = "insert into professor values(?, ?, ?, ?, ?)";
			ps = connection.prepareStatement(profQuery);
			ps.setInt(1, prof.getUserId());
			ps.setString(2, prof.getUserName());
			ps.setString(3, prof.getEmail());
			ps.setString(4, prof.getRole());
			ps.setString(5,  prof.getDepartment());
			ps.executeUpdate();
			
			return 1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
}
