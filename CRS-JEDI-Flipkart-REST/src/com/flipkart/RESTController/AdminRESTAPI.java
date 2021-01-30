package com.flipkart.RESTController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import com.flipkart.bean.Professor;
import com.flipkart.service.AdminOperation;
import com.google.gson.Gson;


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
	
//	@POST
//	@Path("/addProfessor")
////	@Consumes("application/json")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response addProfessor(@FormParam("password") String password, @FormParam("professor") Professor prof)
//	{
//		
////		{"userId":115, "userName":"Prof Bhavya", "role":"Testprof", "email":"profbhavya@gmail.com", "department":"ENI", "address":"prof ka ghar", "age":40, "gender":"male", "contact":"9660054658", "nationality":"indian"}
//		int status = adminOperation.addProfessor(password, prof);
//		if(status == 1)
//			return Response.status(200).entity("Professor added successfully.").build();
//		return Response.status(400).entity("Professor could not be added.").build();
//	}
	
	@POST
	@Path("/addProfessor")
	@Consumes("text/plain")
	@Produces(MediaType.APPLICATION_JSON)
//	public void addProfessor(Professor obj, @PathParam("password") String password)
	public void addProfessor(String str)
	{
		System.out.println(str);
		JSONObject obj = new JSONObject(str);
		
		System.out.println(obj.toString());
		System.out.println(obj.getString("userName"));
		String password = obj.getString("password");
		obj.remove("password");
		Gson gson = new Gson();
		Professor prof = gson.fromJson(obj.toString(), Professor.class);
		System.out.println(prof.getUserName());
		System.out.println(password);
		
		adminOperation.addProfessor(password, prof);
//		{"userId":115, "userName":"Prof Bhavya", "role":"Testprof", "email":"profbhavya@gmail.com", "department":"ENI", "address":"prof ka ghar", "age":40, "gender":"male", "contact":"9660054658", "nationality":"indian"}
//		int status = adminOperation.addProfessor(password, prof);
//		if(status == 1)
//			return Response.status(200).entity("Professor added successfully.").build();
//		return Response.status(400).entity("Professor could not be added.").build();
	}
	

	@PUT
	@Path("/openRegistration")
	@Produces(MediaType.APPLICATION_JSON)
	public Response openRegistration() {

		adminOperation.startRegistrationWindow();
		return Response.status(201).entity("Window Opened").build();

	}

	@PUT
	@Path("/closeRegistration")
	@Produces(MediaType.APPLICATION_JSON)
	public Response closeRegistration() {
		
		adminOperation.closeRegistrationWindow();
		return Response.status(201).entity("Window Closed").build();
	}
	

	@POST
	@Path("/addCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(Course course) {
		adminOperation.addCourse2(course);
		return Response.status(201).entity("Course Added").build();
	}
}
