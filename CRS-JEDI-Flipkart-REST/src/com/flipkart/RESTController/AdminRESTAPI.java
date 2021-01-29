/**
 * 
 */
package com.flipkart.RESTController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.flipkart.dao.AdminDAOOperation;

/**
 * @author JEDI04
 *
 */

@Path("/admin")
public class AdminRESTAPI {
	
	
	@GET
	@Path("/getReportCard")
	@Produces(MediaType.APPLICATION_JSON)
	public void getReportCard(@QueryParam("id") Integer id)
	{
		AdminDAOOperation adminDAO = AdminDAOOperation.getInstance();
		adminDAO.printGrades(id);
		
	}
}
