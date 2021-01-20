package com.flipkart.business;

import org.apache.log4j.Logger;

public interface UserInterface {
	default public void viewCatalog() {

		Logger logger = Logger.getLogger(UserInterface.class);

		CourseCatalogOperation catalogOperation = new CourseCatalogOperation();

		logger.info("Course Id\tCourse Name\tFees\tCourse Description");

	}
}
