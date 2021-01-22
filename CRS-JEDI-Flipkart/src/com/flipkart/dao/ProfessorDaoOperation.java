package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
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
				logger.info(rs.getInt(1) + "		" + rs.getString(2) + "		" + rs.getInt(3));
			}
			logger.info("================================================\n\n");
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public ArrayList<Student> getEnrolledStudents(int courseId) {
		ArrayList<Student> al = new ArrayList<Student>();
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareStatement("Select studentId from grades where courseId=? and grade='NA'");
			stmt.setInt(1, courseId);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Integer> al2 = new ArrayList<Integer>();
			while (rs.next()) {
				al2.add(rs.getInt(1));
			}
			if (al2.size() == 0) {
				return al;
			}
			String listOfStudentIds = "(";
			String str2 = "";
			for (int i = 0; i < al2.size(); i++) {
				listOfStudentIds += al2.get(i);
				str2 += "?";
				if (i != al2.size() - 1) {
					listOfStudentIds += ",";
					str2 += ",";
				}
			}
			listOfStudentIds += ")";
			str2 += ")";
			String str = "Select id,name,email,branch from student where id in (";
			str += str2;
			stmt = con.prepareStatement(str);
			
			for (int i = 1; i <= al2.size(); i++) {
				stmt.setInt(i, al2.get(i-1));
			}
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Student st = new Student();
				st.setUserId(rs.getInt("id"));
				st.setUserName(rs.getString("name"));
				st.setEmail(rs.getString("email"));
				st.setBranch(rs.getString("branch"));
				al.add(st);
			}
			al.size();

		} catch (Exception e) {

		}
		return al;
	}

}
