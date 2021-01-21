package com.flipkart.client;

import org.apache.log4j.Logger;

public class MainCRSApplication {
	private static Logger logger = Logger.getLogger(MainCRSApplication.class);
	
	public static void main(String[] args) {
		logger.info("Welcome!!!");
		showMenu();
	}
	
	private static void showMenu() {
		System.out.println("Menu");
	}
}
