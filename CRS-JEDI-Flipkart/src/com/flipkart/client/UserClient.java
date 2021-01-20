package com.flipkart.client;

import org.apache.log4j.Logger;

public class UserClient {
	private static Logger logger = Logger.getLogger(UserClient.class);
	
	public static void main(String[] args) {
		logger.info("Welcome!!!");
		showMenu();
	}
	
	private static void showMenu() {
		System.out.println("Menu");
	}
}
