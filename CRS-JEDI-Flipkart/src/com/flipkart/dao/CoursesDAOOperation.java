package com.flipkart.dao;

import java.io.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import java.util.*;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constant.SQLQueriesConstant;
import com.flipkart.exception.CommonException;
import com.flipkart.util.DBConnection;

/*
 * @author JEDI 04
 */
public class CoursesDAOOperation implements CoursesDAOInterface {

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
			//String SQLQuery = "SELECT * FROM course WHERE id=?";
			ps = connection.prepareStatement(SQLQueriesConstant.GET_COURSE_BY_ID_QUERY);

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

	public ArrayList<Course> getAllCourses() throws CommonException {
		ArrayList<Course> courses = new ArrayList<>();

		try{
			connection = DBConnection.getConnection();
			//String SQLQuery = "SELECT cc.courseId, cc.courseName, cc.credits, c.professorId FROM courseCatalog AS cc INNER JOIN course AS c ON cc.courseId = c.id";
			ps = connection.prepareStatement(SQLQueriesConstant.GET_ALL_COURSES_QUERY);

			ResultSet resultSet = ps.executeQuery();

			while(resultSet.next()){
				Course course = new Course();
				course.setCourseID(resultSet.getInt("courseId"));
				course.setCourseName(resultSet.getString("courseName"));
				course.setCredits(resultSet.getInt("credits"));
				course.setProfessorAllotted(resultSet.getInt("professorId"));
				courses.add(course);
			}
		}
		catch (Exception e){
			throw new CommonException("Some Internal Error Occurred: " + e.getMessage());
		}

		return courses;
	}

	// to get number of students enrolled in a course based on course ID from DB
	public int noOfEnrolledStudents(int courseID){
		int students = 0;

		try{
			connection = DBConnection.getConnection();
			//String SQLQuery = "SELECT COUNT(*) FROM RegisteredCourses WHERE courseID=?";
			ps = connection.prepareStatement(SQLQueriesConstant.NO_ENROLLED_STUDENTS_QUERY);

			ps.setInt(1,courseID);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()){
				students = resultSet.getInt(1);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return students;
	}
}
