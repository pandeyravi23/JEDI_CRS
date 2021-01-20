package com.flipkart.business;
import org.apache.log4j.Logger;
import com.flipkart.bean.Student;
import com.flipkart.bean.Admin;

public class ReportCardOperation implements ReportCardInterface {

	void printGradeReport(int adminId, Student student){
		logger.info("Inside printGradeReport Method");
	}

	void emailGradeReport(int adminId, Student student){
		logger.info("Inside emailGradeReport Method");
	}
}
