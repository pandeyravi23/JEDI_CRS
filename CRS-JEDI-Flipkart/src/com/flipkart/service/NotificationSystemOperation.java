package com.flipkart.service;

import org.apache.log4j.Logger;

// on student approval
// on successful payment
// course registration complete

public class NotificationSystemOperation {
	public static Logger logger = Logger.getLogger(NotificationSystemOperation.class);
	
	public static void courseRegistrationComplete()
	{
		logger.info("Notification: Course registration successful. Please proceed to make payment.\n");
	}
	
	public static void paymentSuccessful()
	{
		logger.info("Notification: Payment successful.");
	}
	
	public static void studentApproved()
	{
		logger.info("Notification: Congratulations! You have been approved.");
	}
}
