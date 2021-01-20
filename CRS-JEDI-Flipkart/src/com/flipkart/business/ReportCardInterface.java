package com.flipkart.business;
import com.flipkart.bean.Student;
import com.flipkart.bean.Admin;

public interface ReportCardInterface {

	void printGradeReport(int adminId, Student student);

	void emailGradeReport(int adminId, Student student);
}
