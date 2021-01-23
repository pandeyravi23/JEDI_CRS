package com.flipkart.service;

import org.apache.log4j.Logger;

// on student approval
// on successful payment
// course registration complete

public class NotificationSystemOperation {
	public static Logger logger = Logger.getLogger(NotificationSystemOperation.class);
	
	public void courseRegistrationComplete()
	{
		logger.info("Course registration successful. Please proceed to make payment.\n");
	}
	
	public void paymentSuccessful()
	{
		logger.info("Payment successful.");
	}
	
	public void studentApproved()
	{
		logger.info("Congratulations! You have been approved.");
	}
}
