package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.flipkart.bean.Professor;
import com.flipkart.util.DBConnection;

// @Author Jedi04
public class ProfessorDaoOperation implements ProfessorDaoInterface {
	Connection con;
	PreparedStatement stmt;
	
	
//	public static void main(String [] args) {
//		ProfessorDaoOperation obj = new ProfessorDaoOperation();
//		Professor p = obj.getProfessorByEmail("pf1@crs.com");
//		System.out.println(p.getRole());
//	}
	
	/*
	 *  Method creates professor object using email ID
	 *  from the database
	 */
	public Professor getProfessorByEmail(String email) {
		Professor professor = new Professor();
		try {
			
			con = DBConnection.getConnection();
			stmt = con.prepareStatement("select * from professor where email=?");
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				professor = new Professor();
				professor.setUserId(rs.getInt(1));
				professor.setUserName(rs.getString(2));
				professor.setEmail(rs.getString("email"));
				professor.setRole(rs.getString("role"));
				professor.setDepartment(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();

		} 
//			finally {
//
//			try {
//				con.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//
//			try {
//				stmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		return professor;
	}
	

}
