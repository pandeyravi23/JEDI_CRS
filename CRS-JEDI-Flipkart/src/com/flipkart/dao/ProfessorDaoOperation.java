package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.flipkart.bean.Professor;
import com.flipkart.util.DBConnection;
import com.mysql.cj.protocol.Resultset;


// @Author Jedi04
public class ProfessorDaoOperation implements ProfessorDaoInterface {
	private static Logger logger = Logger.getLogger(ProfessorDaoOperation.class);
	Connection con;
	PreparedStatement stmt;

//	public static void main(String [] args) {
//		ProfessorDaoOperation obj = new ProfessorDaoOperation();
//		Professor p = obj.getProfessorByEmail("pf1@crs.com");
//		System.out.println(p.getRole());
//	}

	/*
	 * Method creates professor object using email ID from the database
	 */
	public Professor getProfessorByEmail(String email) {
		Professor professor = new Professor();
		try {

			con = DBConnection.getConnection();
			stmt = con.prepareStatement("select * from professor where email=?");
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
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

	public void showCourses(int professorId) {
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareStatement("Select id,name,credits from course where professorId=?");
			stmt.setInt(1, professorId);
			ResultSet rs = stmt.executeQuery();	
			logger.info("===============================================");
			logger.info("ID		CourseName		Credits");
			while (rs.next()) {
				logger.info(rs.getInt(1) + "		"	+ rs.getString(2)+ "		"+rs.getInt(3));
			}
			logger.info("================================================\n\n");
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
