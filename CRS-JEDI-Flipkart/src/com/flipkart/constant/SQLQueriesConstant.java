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

	// SQL Queries for User
	public static final String VERIFY_LOGIN_CREDENTIALS_QUERY = "SELECT role FROM credentials WHERE email=? AND password=?";
	public static final String CHECK_EMAIL_AVAILABILITY_QUERY = "SELECT * FROM credentials WHERE email=?";
	public static final String REGISTER_USER_QUERY = "INSERT INTO credentials(role, email, password, isApproved, address, age, gender, contact, nationality) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";


	// SQL Queries for Student
	public static final String GET_STUDENT_BY_EMAIL_QUERY = "SELECT st.email, st.name, st.rollno, st.branch, st.id, st.isRegistered, cr.isApproved, st.paymentStatus FROM student as st INNER JOIN credentials as cr ON st.id = cr.id AND st.email=?";
	public static final String ADD_COURSE_STUDENT_QUERY = "INSERT INTO RegisteredCourses(studentID, courseID) values(?,?)";
	public static final String DROP_COURSE_STUDENT_QUERY = "DELETE FROM RegisteredCourses where studentID = ? and courseID = ?";
	public static final String GET_NO_OF_COURSES_QUERY = "SELECT COUNT(*) FROM RegisteredCourses WHERE studentID=?";
	public static final String GET_COURSE_QUERY = "SELECT COUNT(*) FROM RegisteredCourses WHERE studentID=? AND courseID=?";
	public static final String GET_ENROLLED_COURSES_QUERY = "SELECT rc.courseID, c.name FROM RegisteredCourses as rc INNER JOIN course as c ON rc.courseID = c.id AND studentID=?";
	public static final String SET_REGISTRATION_STATUS_QUERY = "UPDATE student SET isRegistered = 1 where id = ?";
	public static final String GET_GRADES_QUERY = "SELECT grades.courseId, course.name as courseName, grades.grade, grades.studentId FROM grades INNER JOIN course ON grades.courseId = course.id AND grades.studentId=?";
	public static final String SET_PAYMENT_STATUS_QUERY = "UPDATE student set paymentStatus = true WHERE id=?";
	public static final String ADD_COURSE_TO_GRADES_QUERY = "INSERT INTO grades(studentId, courseId) values(?,?)";
	public static final String DELETE_COURSE_FROM_GRADES_QUERY = "DELETE FROM grades WHERE studentID = ? AND courseID = ?";

	// SQL Queries for Professor
	public static final String professorGetProfessorByEmailQuery = "select * from professor where email=?";
	public static final String professorShowCoursesQuery = "Select id,name,credits from course where professorId=?";
	public static final String professorGetEnrolledStudentListQuery = "Select studentId from grades where courseId=? and grade='NA'";
	public static final String professorSetGradesQuery = "update grades set grade = ? where studentId = ? and courseId = ?";
	public static final String professorUpdateGradesQuery = "update grades set grade=? where courseId =? and studentId=?";
	public static final String professorShowGradesQuery = "Select grade from grades where studentId = ? and courseId = ?";
	public static final String professorGetStudentsQuery = "Select studentId from grades where courseId=?";

	// SQL Queries for Course
	public static final String GET_COURSE_BY_ID_QUERY = "SELECT * FROM course WHERE id=?";
	public static final String GET_ALL_COURSES_QUERY = "SELECT * FROM courseCatalog";
	public static final String NO_ENROLLED_STUDENTS_QUERY = "SELECT COUNT(*) FROM RegisteredCourses WHERE courseID=?";
}
