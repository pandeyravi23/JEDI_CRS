/**
 * 
 */
package com.flipkart.RESTController;

import java.util.ArrayList;

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
