/**
 * 
 */
package com.flipkart.constant;

/**
 * @author jedi04
 *
 */
public class SQLQueriesConstant {
	public static final String professorGetProfessorByEmailQuery = "select * from professor where email=?";
	public static final String professorShowCoursesQuery = "Select id,name,credits from course where professorId=?";
	public static final String professorGetEnrolledStudentListQuery = "Select studentId from grades where courseId=? and grade='NA'";
	public static final String professorSetGradesQuery = "update grades set grade = ? where studentId = ? and courseId = ?";
	public static final String professorUpdateGradesQuery = "update grades set grade=? where courseId =? and studentId=?";
	public static final String professorShowGradesQuery = "Select grade from grades where studentId = ? and courseId = ?";
	public static final String professorGetStudentsQuery = "Select studentId from grades where courseId=?";
}
