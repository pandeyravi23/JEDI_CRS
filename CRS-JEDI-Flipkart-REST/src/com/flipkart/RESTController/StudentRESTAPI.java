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
	public Student getStudentDetails(@PathParam("id") int id) {
		Student student = studentOperation.getStudentByID(id);
		return student;
	}
	
	@GET
	@Path("/allCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Course> getAllCourses(@QueryParam("id") int id) { 
		ArrayList<Course> allCourses = studentOperation.getAllCourses();
		return allCourses;
	}
	
	@GET
	@Path("/registeredCourses/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Course> getRegisteredCourses(@PathParam("id") int id){
		student = studentOperation.getStudentByID(id);
		ArrayList<Course> registeredCourses = studentOperation.getRegisteredCourses(student);
		return registeredCourses;
	}
	
	@GET
	@Path("/grades/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Grades> getGrades(@PathParam("id") int id){
		ArrayList<Grades> grades = studentOperation.viewGrades(id);
		return grades;
	}
	
	@POST
	@Path("/addCourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(@FormParam("courseID") int courseID,@FormParam("studentID") int studentID) {
		student = studentOperation.getStudentByID(studentID);
		studentOperation.addCourse(student, courseID);
		String message = "Course with ID " + courseID + " succesfully added";
		return Response.status(201).entity(message).build();
	}
	
<<<<<<< HEAD
=======
	@POST
	@Path("/deleteCourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCourse(@FormParam("courseID") int courseID, @FormParam("studentID") int studentID) {
		student = studentOperation.getStudentByID(studentID);
		studentOperation.deleteCourse(student, courseID);
		String message = "Course with ID " + courseID + " succesfully deleted";
		return Response.status(201).entity(message).build();
	}
	
	@POST
	@Path("/registerCourses/{studentID}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerCourses(ArrayList<Integer> courseCart, @PathParam("studentID") int studentID) {
		student = studentOperation.getStudentByID(studentID);
		studentOperation.registerCourses(courseCart, student);
		return Response.status(201).entity("Success").build();
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
		
		studentOperation.updateInfo(student);
		return Response.status(200).entity("Success").build();
	}
>>>>>>> d3d6460367399824f81384f64073a80cadc06d54
	
	@PUT
	@Path("/updateInfo")
	@Produces(MediaType.APPLICATION_JSON)
<<<<<<< HEAD
	public Response updateInfo(@FormParam("studentID") Integer studentID, @FormParam("name") String name, @FormParam("age") Integer age, 
							   @FormParam("address") String address, @FormParam("contact") String contact, @FormParam("gender") String gender,
							   @FormParam("nationality") String nationality) {
=======
	public Response updateInfo(
			@FormParam("studentID") Integer studentID, @FormParam("name") String name, @FormParam("age") Integer age, 
			@FormParam("address") String address, @FormParam("contact") String contact, @FormParam("gender") String gender,
			@FormParam("nationality") String nationality) {
>>>>>>> d3d6460367399824f81384f64073a80cadc06d54
		
		student = studentOperation.getStudentByID(studentID);
		student.setUserName(name);
		student.setAge(age);
		student.setAddress(address);
		student.setContact(contact);
		student.setGender(gender);
		student.setNationality(nationality);
		
		studentOperation.updateInfo(student);
		return Response.status(200).entity("Success").build();
	}
}
