package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constant.SQLQueriesConstant;
import com.flipkart.util.DBConnection;
import com.mysql.cj.protocol.Resultset;

// @Author Jedi04
public class ProfessorDaoOperation implements ProfessorDaoInterface {
	private static Logger logger = Logger.getLogger(ProfessorDaoOperation.class);
	Connection con;
	PreparedStatement stmt;

	/*
	 * Method creates and returns professor object using email ID from the database
	 */
	public Professor getProfessorByEmail(String email) {
		Professor professor = new Professor();
		try {

			con = DBConnection.getConnection();
			stmt = con.prepareStatement(SQLQueriesConstant.professorGetProfessorByEmailQuery);
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
		return professor;
	}

	/*
	 * Method Displays list of courses alloted to the 
	 * professor after getting from the database
	 */
	public void showCourses(int professorId) {
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareStatement(SQLQueriesConstant.professorShowCoursesQuery);
			stmt.setInt(1, professorId);
			ResultSet rs = stmt.executeQuery();
			logger.info("===============================================");
			logger.info("ID		CourseName		Credits");
			while (rs.next()) {
				logger.info(rs.getInt(1) + "		" + rs.getString(2) + "		" + rs.getInt(3));
			}
			logger.info("================================================\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Returns list of all student objects associated with the particular
	 * course id whose grade is Not Available from the database
	 */
	public ArrayList<Student> getEnrolledStudents(int courseId) {
		ArrayList<Student> al = new ArrayList<Student>();
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareStatement(SQLQueriesConstant.professorGetEnrolledStudentListQuery);
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
			e.printStackTrace();
		}
		return al;
	}
	
	/*
	 * Updates grades of multiple student belonging
	 * to a particular courseId whose grades are currently
	 * not available
	 */
	public void setGrades(ArrayList<Student>toGrade,int courseId) {
		Scanner sc = new Scanner(System.in);
		try {
			con = DBConnection.getConnection();
			for(Student st : toGrade) {
				logger.info("Please Enter Grade for " + st.getUserName());
				String grd = sc.next();
				if(grd=="")
					grd = sc.next();
				String str = SQLQueriesConstant.professorSetGradesQuery;
				stmt = con.prepareStatement(str);
				stmt.setString(1, grd);
				stmt.setInt(2, st.getUserId());
				stmt.setInt(3, courseId);
				int isUpdated = stmt.executeUpdate();
				if(isUpdated > 0) {
					logger.info("Uploaded grade");
				}
				else {
					logger.info("Couldn't upload try again");
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return ;
	}
	
	/*
	 * Updates grades of a single student belonging
	 * to a particular courseId
	 */
	public boolean updateStudentGrades(int courseId,int studentId, String grades) {
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareStatement(SQLQueriesConstant.professorUpdateGradesQuery);
			stmt.setString(1,grades);
			stmt.setInt(2,courseId);
			stmt.setInt(3,studentId);
			int status = stmt.executeUpdate();
			if (status>0) {
				return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * Show Grades of all enrolled students associated with the 
	 * courseId
	 */
	public void showGrades(ArrayList<Student>enolledStudent,int courseId) {
		try {
			con = DBConnection.getConnection();
			logger.info("===================================");
			logger.info("UserId    UserName    Grade Obtained");
			for(Student st : enolledStudent) {
				String str = SQLQueriesConstant.professorShowGradesQuery;
				stmt = con.prepareStatement(str);
				stmt.setInt(1, st.getUserId());
				stmt.setInt(2, courseId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					logger.info(st.getUserId() + "        " + st.getUserName() + "        " + rs.getString("grade"));
				}
			}
			logger.info("===================================");
		} catch (Exception e){
			e.printStackTrace();
		}
		return ;
	}
	
	
	
	/*
	 * Returns list of all student objects associated with the particular
	 * course id from the database
	 */
	public ArrayList<Student> getStudents(int courseId) {
		ArrayList<Student> al = new ArrayList<Student>();
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareStatement(SQLQueriesConstant.professorGetStudentsQuery);
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

		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}
	
}
