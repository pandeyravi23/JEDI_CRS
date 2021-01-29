/**
 * 
 */
package com.flipkart.RESTController;

import java.util.ArrayList;

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
	public Response getAllottedCourses(@QueryParam("id") Integer id) { 
		ArrayList<JSONObject> al = professorOperation.showCourses(id);
		if (al.size()>0) {
			return Response.status(200).entity(al.toString()).build();
		}
		return Response.status(400).entity("No Alooted Courses").build();
	}
	
	@GET
	@Path("/enrolledStudents")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEnrolledStudents(@QueryParam("courseID") Integer courseID) { 
		ArrayList<JSONObject> arr = professorOperation.viewStudentsEnrolled(courseID);
		if(arr.size()==0) {
			return Response.status(400).entity("No Students Enrolled in this Course".toString()).build();
		}
		return Response.status(200).entity(arr.toString()).build();
	}
	
	
	@GET
	@Path("/viewGrades")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewGrades(@QueryParam("courseID") Integer courseID) { 
		ArrayList<JSONObject> al = professorOperation.viewGrades(courseID);
		JSONObject obj = new JSONObject();
		if (al.size()>0) {
			obj.put("Status",true);
			obj.put("Values",al);
			return Response.status(200).entity(obj.toString()).build();
		}
		return Response.status(400).entity("No students to view").build();
	}
	
	
	@PUT
	@Path("/updateGrade")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateGrade(@FormParam("courseID") Integer courseID,@FormParam("studentID") Integer studentID,@FormParam("grade") String grade) {
		boolean res = professorOperation.updateStudentGrade(courseID,studentID,grade);
		if (res) {
			return Response.status(200).entity("Grade updated").build();
		}
		return Response.status(400).entity("Grade Update failed").build();

	}
}
