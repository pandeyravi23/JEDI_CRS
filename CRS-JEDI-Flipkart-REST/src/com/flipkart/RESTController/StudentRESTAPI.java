/**
 * 
 */
package com.flipkart.RESTController;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grades;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.client.StudentCRSMenu;
import com.flipkart.service.AuthCredentialSystemOperations;
import com.flipkart.service.StudentOperation;
import com.flipkart.util.ResponseHelpers;

/**
 * @author JEDI04
 *
 */
@Path("/student")
public class StudentRESTAPI {

	private StudentOperation studentOperation = StudentOperation.getInstance();
	private AuthCredentialSystemOperations authentication = AuthCredentialSystemOperations.getInstance();
	private Student student = null;
	private User user = null;
	
	
	@GET
	@Path("/details/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudentDetails(@PathParam("id") int id) {
		student = studentOperation.getStudentByID(id);
		if(student == null) {
			return ResponseHelpers.badRequest(null, "Student with id: " + id + " doesn't exist");
		}
		return ResponseHelpers.success(student, "Success");
	}
	
	
	@GET
	@Path("/allCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCourses() { 
		ArrayList<Course> allCourses = studentOperation.getAllCourses();
		if(allCourses == null) {
			return ResponseHelpers.badRequest(null, "No courses available");
		}
		return ResponseHelpers.success(allCourses, "Success");
	}
	
	
	@GET
	@Path("/registeredCourses/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRegisteredCourses(@PathParam("id") int id){
		student = studentOperation.getStudentByID(id);
		if(student == null) {
			return ResponseHelpers.badRequest(null, "Studentt with id: " + id + " doesn't exist");
		}
		else if(student.getIsRegistered() == false) {
			return ResponseHelpers.badRequest(null, "Student is not yet registered");
		}
		ArrayList<Course> registeredCourses = studentOperation.getRegisteredCourses(student);
		if(registeredCourses == null) {
			return ResponseHelpers.badRequest(null, "Student has no registered courses");
		}
		return ResponseHelpers.success(registeredCourses, "Success");
	}
	
	
	@GET
	@Path("/grades/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGrades(@PathParam("id") int id){
		ArrayList<Grades> grades = studentOperation.viewGrades(id);
		if(grades == null) {
			return ResponseHelpers.badRequest(null, "Student has not registered courses yet.");
		}
		return ResponseHelpers.success(grades, "Success");
	}
	
	
	@POST
	@Path("/addCourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(@FormParam("courseID") int courseID,@FormParam("studentID") int studentID) {
		student = studentOperation.getStudentByID(studentID);
		if(student == null) {
			return ResponseHelpers.badRequest(null, "Studentt with id: " + studentID + " doesn't exist");
		}
		studentOperation.addCourse(student, courseID);
		return ResponseHelpers.success(courseID, "Course with ID " + courseID + " succesfully added");
	}
	
	
	@POST
	@Path("/deleteCourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCourse(@FormParam("courseID") int courseID, @FormParam("studentID") int studentID) {
		student = studentOperation.getStudentByID(studentID);
		if(student == null) {
			return ResponseHelpers.badRequest(null, "Studentt with id: " + studentID + " doesn't exist");
		}
		studentOperation.deleteCourse(student, courseID);
		return ResponseHelpers.success(courseID, "Course with ID " + courseID + " succesfully deleted");
	}
	
	
	@POST
	@Path("/registerCourses/{studentID}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerCourses(ArrayList<Integer> courseCart, @PathParam("studentID") int studentID) {
		student = studentOperation.getStudentByID(studentID);
		if(student == null) {
			return ResponseHelpers.badRequest(null, "Studentt with id: " + studentID + " doesn't exist");
		}
		studentOperation.registerCourses(courseCart, student);
		return ResponseHelpers.success(courseCart, "Successfully registered for courses");
	}
	
	
	@POST
	@Path("/registerStudent")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerStudent(
			@FormParam("email") String email, @FormParam("password") String password,
			@FormParam("name") String name, @FormParam("age") Integer age, 
			@FormParam("address") String address, @FormParam("contact") String contact, @FormParam("gender") String gender,
			@FormParam("nationality") String nationality, @FormParam("branch") String branch, @FormParam("rollno") int rollno) {
		student = new Student();
		user = new User();
		
		user.setEmail(email);
		student.setEmail(email);
		
		user.setUserName(name);
		student.setUserName(name);
		
		user.setAddress(address);
		student.setAddress(address);
		
		user.setAge(age);
		student.setAge(age);
		
		user.setGender(gender);
		student.setGender(gender);
		
		user.setContact(contact);
		student.setContact(contact);
		
		user.setNationality(nationality);
		student.setNationality(nationality);
		
		student.setBranch(branch);
		
		student.setRollNo(rollno);
		
		user.setApproved(false);
		user.setRole("1");
		student.setIsRegistered(false);
		student.setPaymentStatus(false);
		
		authentication.registerStudent(user, student, password);
		return ResponseHelpers.success(student, "Successfully registered");
	}

	
	@PUT
	@Path("/updateInfo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateInfo(@FormParam("studentID") Integer studentID, @FormParam("name") String name, @FormParam("age") Integer age, 
							   @FormParam("address") String address, @FormParam("contact") String contact, @FormParam("gender") String gender,
							   @FormParam("nationality") String nationality) {
		
		student = studentOperation.getStudentByID(studentID);
		if(student == null) {
			return ResponseHelpers.badRequest(null, "Studentt with id: " + studentID + " doesn't exist");
		}
		
		student.setUserName(name);
		student.setAge(age);
		student.setAddress(address);
		student.setContact(contact);
		student.setGender(gender);
		student.setNationality(nationality);
		
		studentOperation.updateInfo(student);
		return ResponseHelpers.success(student, "Successfully updated info");
	}	
	
	
	@PUT
	@Path("/payment/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response makePayment(@PathParam("id") int studentID, @FormParam("method") String method) {
		student = studentOperation.getStudentByID(studentID);
		if(student == null) {
			return ResponseHelpers.badRequest(null, "Studentt with id: " + studentID + " doesn't exist");
		}
		studentOperation.makePayment(student,method);
		return ResponseHelpers.success(studentID, "Payment Successful");
	}
		
}
