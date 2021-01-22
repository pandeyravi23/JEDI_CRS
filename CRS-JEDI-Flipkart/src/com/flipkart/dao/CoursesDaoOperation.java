package com.flipkart.dao;

import java.io.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import java.util.*;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.util.DBConnection;

/*
 * @author JEDI 04
 */
public class CoursesDaoOperation implements CoursesDaoInterface {

	Connection connection = null;
	PreparedStatement ps = null;


	@Override
	// get list of enrolled students;
	public ArrayList<Student> getEnrolledStudents(int courseId) {
		ArrayList<Student> enrolledStudents = new ArrayList<Student>();
		/*for(Course course: courses) {
			if(course.getCourseID() == courseId) {
				enrolledStudents = course.getListOfEnrolledStudents();
				break;
			}
		}*/
		return enrolledStudents;
	}


	public Course getCourseByID(int courseID){
		Course course = null;

		try{
			connection = DBConnection.getConnection();
			String SQLQuery = "SELECT * FROM course WHERE id=?";
			ps = connection.prepareStatement(SQLQuery);

			ps.setInt(1,courseID);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				course = new Course();
				course.setCourseID(resultSet.getInt("id"));
				course.setCourseName(resultSet.getString("name"));
				course.setCredits(resultSet.getInt("credits"));
				course.setProfessorAllotted(resultSet.getInt("professorId"));
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}

		return course;
	}

	public ArrayList<Course> getAllCourses(){
		ArrayList<Course> courses = new ArrayList<>();

		try{
			connection = DBConnection.getConnection();
			String SQLQuery = "SELECT * FROM courseCatalog";
			ps = connection.prepareStatement(SQLQuery);

			ResultSet resultSet = ps.executeQuery();

			while(resultSet.next()){
				Course course = new Course();
				course.setCourseID(resultSet.getInt("courseId"));
				course.setCourseName(resultSet.getString("courseName"));
				course.setCredits(resultSet.getInt("credits"));
				courses.add(course);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return courses;
	}
}
