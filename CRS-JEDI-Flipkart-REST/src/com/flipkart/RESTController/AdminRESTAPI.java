package com.flipkart.RESTController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.flipkart.dao.AdminDAOOperation;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.flipkart.bean.Course;
import com.flipkart.service.AdminOperation;
import com.flipkart.util.ResponseHelpers;
import com.flipkart.util.ValidationOperation;


@Path("/admin")
public class AdminRESTAPI {
	AdminOperation adminOperation = AdminOperation.getInstance();
	
	@GET
	@Path("/getReportCard")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReportCard(@QueryParam("id") Integer id)
	{
		ArrayList<JSONObject> reportCard = adminOperation.generateReportCard(id);
		if(reportCard.size() == 0)
			return Response.status(400).entity("No students found.").build();
		return Response.status(200).entity(reportCard.toString()).build();
		
	}
	
	@GET
	@Path("/getRegisteredStudents")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRegisteredStudents()
	{
		ArrayList<JSONObject> students =  adminOperation.getRegisteredStudents();
		if(students.size() == 0)
		{
			return Response.status(400).entity("No students found").build();
		}
		return Response.status(200).entity(students.toString()).build();
	}
	

	@PUT
	@Path("/openRegistration")
	@Produces(MediaType.APPLICATION_JSON)
	public Response openRegistration() {
		boolean res = adminOperation.startRegistrationWindow();
		if(res){
			return ResponseHelpers.success(null, "Registration Closed");
		}
		return ResponseHelpers.badRequest(null, "Request to close registration failed");

	}

	@PUT
	@Path("/closeRegistration")
	@Produces(MediaType.APPLICATION_JSON)
	public Response closeRegistration() {
		boolean res = adminOperation.closeRegistrationWindow();
		if(res){
			return ResponseHelpers.success(null, "Registration Closed");
		}
		return ResponseHelpers.badRequest(null, "Request to close registration failed");
	}
	

	@POST
	@Path("/addCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(Course course) {
		boolean res = adminOperation.addCourse2(course);
		if(res){
			return ResponseHelpers.successPost(course, "Course added successfully");
		}
		return ResponseHelpers.badRequestPost(null, "Course Add Failed");
	}
}
