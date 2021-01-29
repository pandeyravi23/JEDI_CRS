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
import com.flipkart.client.StudentCRSMenu;
import com.flipkart.service.StudentOperation;

/**
 * @author JEDI04
 *
 */
@Path("/StudentRESTAPI")
public class StudentRESTAPI {
	StudentOperation student = StudentOperation.getInstance();
	@GET
	@Path("/getAllCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Course> getAllCourses() { 
		ArrayList<Course> al = student.getAllCourses();
//		Grades g = new Grades();
//		g.setCourseID(1);
//		g.setCourseName("gg");
//		g.setGrade("ggg");
//		g.setStudentId(1);
		return al;
	}
}
