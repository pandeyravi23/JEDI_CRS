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
		return Response.status(200).entity(al.toString()).build();
	}
	
	@GET
	@Path("/enrolledStudents")
	@Produces(MediaType.APPLICATION_JSON)
	public void getEnrolledStudents(@QueryParam("courseID") Integer courseID) { 
		professorOperation.viewStudentsEnrolled(courseID);
	}
	
	
	@GET
	@Path("/viewGrades")
	@Produces(MediaType.APPLICATION_JSON)
	public void viewGrades(@QueryParam("courseID") Integer courseID) { 
		professorOperation.viewGrades(courseID);
	}
	
	
	@PUT
	@Path("/updateGrade")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateGrade(@FormParam("courseID") Integer courseID,@FormParam("studentID") Integer studentID,@FormParam("grade") String grade) {
		System.out.print(grade);
		professorOperation.updateStudentGrade(courseID,studentID,grade);
	}
}
