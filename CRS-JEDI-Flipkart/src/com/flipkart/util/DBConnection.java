/**
 * 
 */
package com.flipkart.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

/**
 * @author jedi04
 *
 */
public class DBConnection {
	public static Connection connection = null;
	private static Logger logger = Logger.getLogger(DBConnection.class);

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/test";
	static final String USER = "root";

//	static final String PASS = "common2";
	static final String PASS = "bernabeu";


//	static final String PASS = "invictus@1999";

	//static final String PASS = "common2";



	public static Connection getConnection() {
		if (connection != null) {
			return connection;
		} else {
			try {
				Class.forName(JDBC_DRIVER);
				connection = DriverManager.getConnection(DB_URL, USER, PASS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	public static void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
				logger.info("Connection Closed");
			} catch(Exception e) {
				logger.warn(e.getMessage());
			}
		}
		else {
			logger.info("Connection already closed");
		}
	}
}
