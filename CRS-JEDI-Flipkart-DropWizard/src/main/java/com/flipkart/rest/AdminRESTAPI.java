package com.flipkart.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.flipkart.dao.AdminDAOOperation;
import com.flipkart.helper.AddAdminHelper;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.service.AdminOperation;
import com.google.gson.Gson;
import com.flipkart.util.ResponseHelpers;
import com.flipkart.util.ValidationOperation;

/**
 * Handles Admin related functionality 
 * through rest services
 * 
 * @author JEDI04
 * 
 */
@Path("/admin")
public class AdminRESTAPI {
	AdminOperation adminOperation = AdminOperation.getInstance();
	
	/**
	 * 
	 * @param id Student ID
	 * @return Response object containing report card
	 * @throws ValidationException
	 */
	@GET
	@Path("/getReportCard")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReportCard(
			@NotNull
			@DecimalMin(value = "100", message = "Student ID has to be of 3 digits")
			@Digits(fraction = 0, integer = 3)
			@QueryParam("id") Integer id) throws ValidationException
	{
		ArrayList<JSONObject> reportCard = adminOperation.generateReportCard(id);
		
		if(reportCard.size() == 0)
			return ResponseHelpers.badRequest(reportCard, "Unable to generate report card for id : "  + id);
		return ResponseHelpers.success(reportCard, "Report Card for " + id + "successfully generated.");
		
		
	}
	
	/**
	 * Returns list of registered students
	 * @return Response object containing list of registered students
	 */
	@GET
	@Path("/getRegisteredStudents")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRegisteredStudents()
	{
		ArrayList<JSONObject> students =  adminOperation.getRegisteredStudents();
		if(students.size() == 0)
		{
			return ResponseHelpers.badRequest(students, "No students found.");
		}
		return ResponseHelpers.success(students, "Success");
	}
	
	/**
	 * Functionality to add professor
	 * @param str Details of professor to be added
	 * @return Response object containing status
	 */
	@POST
	@Path("/addProfessor")
	@Consumes("text/plain")
	@Produces(MediaType.APPLICATION_JSON)
//	public void addProfessor(Professor obj, @PathParam("password") String password)
	public Response addProfessor(String str)
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
		
		int status = adminOperation.addProfessor(password, prof);
		
		if(status == 0)
		{
			return ResponseHelpers.badRequest(status, "Professor entry " + prof.getEmail() + " already exists in database.");
		}
		
		return ResponseHelpers.success(status, "Prof. " + prof.getUserName() + " added.");
		
	}
	
	/**
	 * Functionality to add admin
	 * @param helper Admin helper object containing deatils of admin
	 * @return Response object containing status
	 * @throws ValidationException
	 */
	@POST
	@Path("/addAdmin")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAdmin(@Valid AddAdminHelper helper) throws ValidationException
	{
		Admin admin = helper.getAdmin();
		String password = helper.getPassword();
		
		System.out.println(admin.toString());
		System.out.println(password);
		
		int status = adminOperation.addAdmin(admin, password);
		
		if(status == 0)
		{
			return ResponseHelpers.badRequest(status, "Admin entry " + admin.getEmail() + " already exists in database.");
		}
		
		return ResponseHelpers.success(status, "Admin " + admin.getUserName() + " added.");
	}
	

	/**
	 * Opens the Registration Window for course registration
	 * @return Response
	 */
	
	@PUT
	@Path("/openRegistration")
	@Produces(MediaType.APPLICATION_JSON)
	public Response openRegistration() {
		boolean res = adminOperation.startRegistrationWindow();
		if(res){
			return ResponseHelpers.success(null, "Registration Opened");
		}
		return ResponseHelpers.badRequest(null, "Request to open registration failed");

	}

	
	/**
	 * Closes the Registration Window for course registration
	 * @return Response
	 */
	
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
	

	
	/**
	 * Adds new course in course catalog
	 * @param course
	 * @return Response
	 * @throws ValidationException
	 */
	
	@POST
	@Path("/addCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(@Valid Course course) throws ValidationException{
		boolean res = adminOperation.addCourse2(course);
		if(res){
			return ResponseHelpers.successPost(course, "Course Added Successfully");
		}
		return ResponseHelpers.badRequest(null, "Course Add Failed");
	}
	
	
	/**
	 * Deletes course from course catalog and course table
	 * @param courseID
	 * @return Response
	 * @throws ValidationException
	 */
	
	
	@DELETE
	@Path("/deleteCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCourse(
			@NotNull
			@DecimalMin(value = "100", message = "courseID value has to be of 3 digits")
			@Digits(fraction = 0, integer = 3)
			@QueryParam("courseID") Integer courseID) throws ValidationException{
		boolean res = adminOperation.deleteCourse(courseID);
		if(res==false) {
			return ResponseHelpers.badRequest(null, "Failed to delete Course ");
		}
		return ResponseHelpers.success(null, "Course Deleted Successfully");
	}
	
	
	
	/**
	 * Allot course to the professor
	 * @param courseID
	 * @param professorID
	 * @return Response
	 * @throws ValidationException
	 */
	
	
	@PUT 
	@Path("/allotProfessor")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response allotProfessor(
			@NotNull
			@DecimalMin(value = "100", message = "courseID value has to be of 3 digits")
			@Digits(fraction = 0, integer = 3)
			@QueryParam("courseID") Integer courseID, 
			
			@NotNull
			@DecimalMin(value = "100", message = "ProfessorID value has to be of 3 digits")
			@Digits(fraction = 0, integer = 3)
			@QueryParam("professorID") Integer professorID) throws ValidationException{
		boolean res = adminOperation.allotCourse(courseID,professorID);
		if(res==false) {
			return ResponseHelpers.badRequest(null, "Failed to Allocate Course");
		}
		return ResponseHelpers.success(null, "Course Alloted Successfully");
	}
	
	
	/**
	 * Approves New Student's Registration by entering studentID
	 *
	 * @param studentID
	 * @return Response
	 * @throws ValidationException
	 */
	
	@PUT
	@Path("/approveStudent")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response approveStudent(
			@NotNull
			@DecimalMin(value = "100", message = "studentID value has to be of 3 digits")
			@Digits(fraction = 0, integer = 3)
			@QueryParam("studentID") Integer studentID) throws ValidationException{
		boolean res = adminOperation.approveStudents(studentID);
		JSONObject msg = new JSONObject();
		if(res==false) {
			return ResponseHelpers.badRequest(null, "Some Error Occured");
		}
		return ResponseHelpers.success(null, "Student Approved!!");
	}
}
