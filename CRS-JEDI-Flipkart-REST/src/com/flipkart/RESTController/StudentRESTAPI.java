/**
 * 
 */
package com.flipkart.RESTController;

import java.util.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.*;
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
	public Response getStudentDetails(
			@NotNull
			@DecimalMin(value = "100", message = "studentID value has to be of 3 digits")
			@Digits(fraction = 0, integer = 3)
			@PathParam("id") int id) throws ValidationException {
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
		return ResponseHelpers.success(allCourses, "Success Updated");
	}
	
	@GET
	@Path("/registeredCourses/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRegisteredCourses(
			@NotNull
			@DecimalMin(value = "100", message = "studentID value has to be of 3 digits")
			@Digits(fraction = 0, integer = 3)
			@PathParam("id") int id) throws ValidationException {
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
	public Response getGrades(
			@NotNull
			@DecimalMin(value = "100", message = "studentID value has to be of 3 digits")
			@Digits(fraction = 0, integer = 3)
			@PathParam("id") int id) throws ValidationException {
		ArrayList<Grades> grades = studentOperation.viewGrades(id);
		if(grades == null) {
			return ResponseHelpers.badRequest(null, "Student has not registered courses yet.");
		}
		return ResponseHelpers.success(grades, "Success");
	}
	
	
	@POST
	@Path("/addCourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(
			@NotNull
			@DecimalMin(value = "100", message = "courseID value has to be of 3 digits")
			@Digits(fraction = 0, integer = 3)
			@FormParam("courseID") int courseID,
			
			@NotNull
			@DecimalMin(value = "100", message = "studentID value has to be of 3 digits")
			@Digits(fraction = 0, integer = 3)
			@FormParam("studentID") int studentID) throws ValidationException {
		student = studentOperation.getStudentByID(studentID);
		if(student == null) {
			return ResponseHelpers.badRequest(null, "Student with id: " + studentID + " doesn't exist");
		}
		studentOperation.addCourse(student, courseID);
		return ResponseHelpers.successPost(courseID, "Course with ID " + courseID + " succesfully added");
	}
	
	@DELETE
	@Path("/deleteCourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCourse(
			@NotNull
			@DecimalMin(value = "100", message = "courseID value has to be of 3 digits")
			@Digits(fraction = 0, integer = 3)
			@FormParam("courseID") int courseID, 
			
			@NotNull
			@DecimalMin(value = "100", message = "studentID value has to be of 3 digits")
			@Digits(fraction = 0, integer = 3)
			@FormParam("studentID") int studentID) throws ValidationException {
		student = studentOperation.getStudentByID(studentID);
		if(student == null) {
			return ResponseHelpers.badRequest(null, "Studentt with id: " + studentID + " doesn't exist");
		}
		studentOperation.deleteCourse(student, courseID);
		return ResponseHelpers.success(courseID, "Course with ID " + courseID + " succesfully deleted");
	}
	
	
	
	// TODO: Make sure validation with ArrayList works
	@POST
	@Path("/registerCourses/{studentID}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerCourses(
			@NotNull
			@DecimalMin(value = "100", message = "courseId value in courseCart has to be of 3 digits")
			@Digits(fraction = 0, integer = 3)
			ArrayList<Integer> courseCart,
			
			@NotNull
			@DecimalMin(value = "100", message = "studentID value has to be of 3 digits")
			@Digits(fraction = 0, integer = 3)
			@PathParam("studentID") int studentID) throws ValidationException {
		student = studentOperation.getStudentByID(studentID);
		if(student == null) {
			return ResponseHelpers.badRequest(null, "Studentt with id: " + studentID + " doesn't exist");
		}
		studentOperation.registerCourses(courseCart, student);
		return ResponseHelpers.successPost(courseCart, "Successfully registered for courses");
	}
	
	
	@POST
	@Path("/registerStudent")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerStudent(
			@Pattern(message = "Invalid Email Address->" + "Valid emails:user@gmail.com or my.user@domain.com etc.",
		            regexp = "^[a-zA-Z0-9_!#$%&*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
			@Size(min = 5, max = 30, message = "The length of email should be between 5 to 30")
			@FormParam("email") String email, 
			
			@Size(min = 5, max = 30, message = "The length of password should be between 5 to 30")
			@FormParam("password") String password,
			
			@Size(min = 5, max = 30, message = "The length of name should be between 5 to 30")
			@FormParam("name") String name, 
			
			@DecimalMin(value = "15", message = "Student shall be minimum of age 15 yr")
		    @DecimalMax(value = "30", message = "Student can not have age more than 30 yr")
			@FormParam("age") Integer age, 
			
			@Size(min = 5, max = 30, message = "The length of address should be between 5 to 30")
			@FormParam("address") String address, 
			
			@Size(min = 5, max = 30, message = "The length of contact should be between 5 to 30")
			@FormParam("contact") String contact, 
			
			@Pattern(message = "Please either enter male/female",
            regexp = "^male$|^female$")
			@FormParam("gender") String gender,
			
			@Size(min = 5, max = 30, message = "The length of nationality should be between 5 to 30")
			@FormParam("nationality") String nationality, 
			
			@Size(min = 5, max = 30, message = "The length of branch should be between 5 to 30")
			@FormParam("branch") String branch, 
			
			@NotNull
			@DecimalMin(value = "1000", message = "Roll value has to be of 4 digits")
			@Digits(fraction = 0, integer = 4)
			@FormParam("rollno") int rollno) throws ValidationException {
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
		return ResponseHelpers.successPost(student, "Successfully registered");
	}

	
	@PUT
	@Path("/updateInfo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateInfo(
			@NotNull
			@DecimalMin(value = "100", message = "studentID value has to be of 3 digits")
			@Digits(fraction = 0, integer = 3)
			@FormParam("studentID") Integer studentID, 
			
			@Size(min = 5, max = 30, message = "The length of name should be between 5 to 30")
			@FormParam("name") String name, 
			
			@DecimalMin(value = "15", message = "Student shall be minimum of age 15 yr")
		    @DecimalMax(value = "30", message = "Student can not have age more than 30 yr")
			@FormParam("age") Integer age,
			
			@Size(min = 5, max = 30, message = "The length of address should be between 5 to 30")
			@FormParam("address") String address,
			
			@Size(min = 5, max = 30, message = "The length of contact should be between 5 to 30")
			@FormParam("contact") String contact,
			
			@Pattern(message = "Please either enter male/female",
            regexp = "^male$|^female$")
			@FormParam("gender") String gender,
			
			@Size(min = 5, max = 30, message = "The length of nationality should be between 5 to 30")
			@FormParam("nationality") String nationality) throws ValidationException {
		
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
	public Response makePayment(
			@NotNull
			@DecimalMin(value = "100", message = "studentID value has to be of 3 digits")
			@Digits(fraction = 0, integer = 3)
			@PathParam("id") int studentID, 
			
			@Size(min = 5, max = 30, message = "The length of method of payment should be between 5 to 30")
			@FormParam("method") String method) {
		student = studentOperation.getStudentByID(studentID);
		if(student == null) {
			return ResponseHelpers.badRequest(null, "Studentt with id: " + studentID + " doesn't exist");
		}
		studentOperation.makePayment(student,method);
		return ResponseHelpers.success(studentID, "Payment Successful");
	}
		
}
