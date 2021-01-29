/**
 * 
 */
package com.flipkart.RESTController;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
	
	
}
