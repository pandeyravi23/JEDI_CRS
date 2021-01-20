package com.flipkart.service;
import org.apache.log4j.Logger;
import com.flipkart.bean.Student;
import com.flipkart.bean.Admin;

public class ReportCardOperation implements ReportCardInterface {
	private static Logger logger = Logger.getLogger(StudentOperation.class);
	
	public void printGradeReport(int adminId, Student student){
		logger.info("Inside printGradeReport Method");
	}

	public void emailGradeReport(int adminId, Student student){
		logger.info("Inside emailGradeReport Method");
	}
}
