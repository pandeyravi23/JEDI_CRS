/**
 * 
 */
package com.flipkart.constant;

import java.security.PublicKey;

/**
 * @author jedi04
 *
 */
public class SQLQueriesConstant {

	// SQL Queries for Student
	public static final String GET_STUDENT_BY_EMAIL_QUERY = "SELECT * FROM student WHERE email=?";
	public static final String ADD_COURSE_STUDENT_QUERY = "INSERT INTO RegisteredCourses(studentID, courseID) values(?,?)";
	public static final String DROP_COURSE_STUDENT_QUERY = "DELETE FROM RegisteredCourses where studentID = ? and courseID = ?";
	public static final String GET_NO_OF_COURSES_QUERY = "SELECT COUNT(*) FROM RegisteredCourses WHERE studentID=?";
	public static final String GET_COURSE_QUERY = "SELECT COUNT(*) FROM RegisteredCourses WHERE studentID=? AND courseID=?";
	public static final String GET_ENROLLED_COURSES_QUERY = "SELECT courseID FROM RegisteredCourses WHERE studentID=?";
	public static final String SET_REGISTRATION_STATUS_QUERY = "UPDATE student SET isRegistered = 1 where id = ?";
	public static final String GET_GRADES_QUERY = "SELECT grades.courseId, course.name as courseName, grades.grade, grades.studentId FROM grades INNER JOIN course ON grades.courseId = course.id AND grades.studentId=?";


	// SQL Queries for Professor
	public static final String professorGetProfessorByEmailQuery = "select * from professor where email=?";
	public static final String professorShowCoursesQuery = "Select id,name,credits from course where professorId=?";
	public static final String professorGetEnrolledStudentListQuery = "Select studentId from grades where courseId=? and grade='NA'";
	public static final String professorSetGradesQuery = "update grades set grade = ? where studentId = ? and courseId = ?";
	public static final String professorUpdateGradesQuery = "update grades set grade=? where courseId =? and studentId=?";
	public static final String professorShowGradesQuery = "Select grade from grades where studentId = ? and courseId = ?";
	public static final String professorGetStudentsQuery = "Select studentId from grades where courseId=?";
}
