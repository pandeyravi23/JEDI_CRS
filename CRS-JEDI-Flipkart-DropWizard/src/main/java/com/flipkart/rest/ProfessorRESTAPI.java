/**
 * 
 */
package com.flipkart.rest;

import java.util.ArrayList;
import javax.validation.constraints.DecimalMin;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.flipkart.bean.Course;
import com.flipkart.service.ProfessorOperation;
import com.flipkart.util.ResponseHelpers;

/**
 * @author JEDI04
 *
 */

@Path("/professor")
public class ProfessorRESTAPI {
	ProfessorOperation professorOperation = ProfessorOperation.getInstance();
	
	/**
	 * Get the list of alloted courses
	 * @param professorId
	 * @return Response
	 * @throws ValidationException
	 */
	
	@GET
	@Path("/allottedCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllottedCourses(
			@NotNull
			@QueryParam("professorId") Integer professorId) throws ValidationException{ 
		ArrayList<JSONObject> al = professorOperation.showCourses(professorId);
		if (al.size()>0) {
			return ResponseHelpers.success(al,"Success");
		}
		return ResponseHelpers.badRequest(null, "No Allotted Courses");
	}
	
	/**
	 * Method to view enrolled students
	 * @param courseID course Id
	 * @return Response containing list of enrolled students
	 * @throws ValidationException
	 */
	@GET
	@Path("/enrolledStudents")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEnrolledStudents(
			@NotNull
			@QueryParam("courseID") Integer courseID) throws ValidationException{ 
		ArrayList<JSONObject> arr = professorOperation.viewStudentsEnrolled(courseID);
		if(arr.size()==0) {
			return ResponseHelpers.badRequest(arr, "No Enrolled Students Found in course id " + courseID);
		}
		return ResponseHelpers.success(arr, "Success");
	}
	
	/**
	 * Method to view student grades
	 * @param courseID course id
	 * @return Response returns students grades
	 * @throws ValidationException
	 */
	@GET
	@Path("/viewGrades")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewGrades(
			@NotNull
			@QueryParam("courseID") Integer courseID) throws ValidationException{ 
		ArrayList<JSONObject> al = professorOperation.viewGrades(courseID);
		JSONObject obj = new JSONObject();
		if (al.size()>0) {
			return ResponseHelpers.success(al, "Success");
		}
		return ResponseHelpers.badRequest(null, "No student to view grades");
	}
	
	/**
	 * Method to view update grade
	 * @param courseID course id
	 * @param studentID student id
	 * @param grade grade
	 * @return response containing status of grade updated
	 * @throws ValidationException
	 */
	@PUT
	@Path("/updateGrade")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateGrade(
			@NotNull
			@FormParam("courseID") Integer courseID,
			@NotNull
			@FormParam("studentID") Integer studentID,
			@NotNull
		    @Size(min = 1, max = 2, message = "The length of Grade should be between 1 to 2")
			@FormParam("grade") String grade) throws ValidationException{
		boolean res = professorOperation.updateStudentGrade(courseID,studentID,grade);
		if (res) {
			return ResponseHelpers.success("Grade Updated", "Success");
		}
		return ResponseHelpers.badRequest(null, "Update Grade Failed");

	}
}
