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
import com.flipkart.client.StudentCRSMenu;
import com.flipkart.service.StudentOperation;

/**
 * @author JEDI04
 *
 */
@Path("/student")
public class StudentRESTAPI {

	StudentOperation studentOperation = StudentOperation.getInstance();
	Student student = null;
	
	
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
	
	
	
	@PUT
	@Path("/updateInfo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateInfo(@FormParam("studentID") Integer studentID, @FormParam("name") String name, @FormParam("age") Integer age, 
							   @FormParam("address") String address, @FormParam("contact") String contact, @FormParam("gender") String gender,
							   @FormParam("nationality") String nationality) {
		
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
