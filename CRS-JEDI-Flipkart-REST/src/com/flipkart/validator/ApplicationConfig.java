/**
 * 
 */
package com.flipkart.validator;

/**
 * @author parth
 *
 */
import org.glassfish.jersey.server.ResourceConfig;

import com.flipkart.RESTController.*;



public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {
		register(StudentRESTAPI.class);
		register(AdminRESTAPI.class);
		register(ProfessorRESTAPI.class);
	}
}

