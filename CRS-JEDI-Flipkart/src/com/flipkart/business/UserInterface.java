package com.flipkart.business;

import org.apache.log4j.Logger;

public interface UserInterface {
	default public void viewCatalog() {

		Logger logger = Logger.getLogger(UserInterface.class);

		CourseCatalogOperation catalogOperation = new CourseCatalogOperation();

//		List<Course> courses = catalogOperation.searchAndFetchDetails();
		logger.info("Course Id\tCourse Name\tFees\tCourse Description");

//		courses.forEach(course -> logger.info(course.getCourseId() + "\t\t" + course.getCourseName() + " \t\t"
//				+ course.getFees() + "\t" + course.getDescription()));
	}
}
