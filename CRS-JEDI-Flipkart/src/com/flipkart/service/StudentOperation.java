package com.flipkart.service;
import com.flipkart.bean.*;
import org.apache.log4j.Logger;

import com.flipkart.bean.Student;
import com.flipkart.dao.CoursesDAOOperation;
import com.flipkart.dao.StudentDAOOperation;
import com.flipkart.bean.Course;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentOperation implements StudentInterface {
    private static Logger logger = Logger.getLogger(StudentOperation.class);
	CoursesDAOOperation coursesDaoOperation = new CoursesDAOOperation();
    StudentDAOOperation studentDaoOperation = new StudentDAOOperation();
    
    public int getNumberOfEnrolledCourses(Student student) {
    	int count = 0;
    	try {
    		count = studentDaoOperation.getNoOfCourses(student);
    	}
    	catch (Exception e) {
    		 e.printStackTrace();
    	}
    	return count;
    }

	// operation to show available courses in course catalog
    public void showCourses(){

        try{
            ArrayList<Course> courses = coursesDaoOperation.getAllCourses();
            logger.info("================AVAILABLE COURSES================\n");
            logger.info("Course ID\tCourse Name\tCredits");
            for (Course course : courses) {
                logger.info(course.getCourseID() + "\t" + course.getCourseName() + "\t" + course.getCredits());
            }
            logger.info("=================================================\n");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    // operation to view grades assigned by professor
    public void viewGrades(int studentId){
        try{
            ArrayList<Grades> grades = studentDaoOperation.getGrades(studentId);
            logger.info("======================GRADES===================\n");
            logger.info("Coure ID\tCourse Name\t\tGrade");
            for(Grades grade : grades){
                logger.info(grade.getCourseId() + "\t\t" + grade.getCourseName() + "\t\t" + grade.getGrade());
            }
            logger.info("=================================================\n");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    // operation to make payment
    public void makePayment(Student student){
        try {
        	studentDaoOperation.setPaymentStatus(student);
        	student.setPaymentStatus(true);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
    }

    // operation to update student info
    public boolean updateInfo(Student student){

        try {
            studentDaoOperation.updateInfo(student);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    // operation to add course to registered courses
    public boolean addCourse(Student student, int courseId){
        try{
            studentDaoOperation.addCourse(student,courseId);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    // operation to delete course from registered courses
    public boolean deleteCourse(Student student, int courseId){

        try{
            studentDaoOperation.dropCourse(student,courseId);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    // operation to register for courses
    public boolean registerCourses(Student student){
    	if(student.getIsRegistered()){
    	    logger.info("You have already registered.\n");
    	    return true;
        }

    	int courseCounter = 0;
    	ArrayList<Integer> courseCart = new ArrayList<>();
        logger.info("================COURSE REGISTRATION================\n");
        while(true) {
            logger.info("Enter 1 to view available courses.");
            logger.info("Enter 2 to add course.");
            logger.info("Enter 3 to delete course.");
            logger.info("Enter 4 to view course cart.");
            logger.info("Enter 5 to finish registration process.");
            logger.info("Enter 6 to cancel registration process.");
            Scanner input = new Scanner(System.in);
            int operation = input.nextInt();

            if(operation==1){
                showCourses();
            }
            else if (operation == 2) {
                logger.info("Enter course ID to be added: ");
                int courseID = input.nextInt();
                //addCourse(student,courseID);
                if(courseCart.contains(courseID)){
                    logger.info("Course " + courseID + " already in course cart\n");
                }
                else if(coursesDaoOperation.noOfEnrolledStudents(courseID)>=10){
                    logger.info("Course " + courseID + " is full. Please add some other course.\n");
                }
                else{
                    courseCart.add(courseID);
                    courseCounter++;
                    logger.info("Course " + courseID + " added to Course Cart.\n");
                }
            }
            else if (operation == 3) {
                logger.info("Enter course ID to be dropped: ");
                int courseID = input.nextInt();
                //deleteCourse(student,courseID);
                if(!courseCart.contains(courseID)){
                    logger.info("Course " + courseID + " not in cart\n");
                }
                else {
                    courseCart.remove(Integer.valueOf(courseID));
                    courseCounter--;
                    logger.info("Course " + courseID + " deleted to Course Cart.\n");
                }
            }
            else if (operation == 4){
                logger.info("============Course Cart============\n");
                logger.info("Course IDs:");
                for(Integer courseId : courseCart){
                    logger.info(courseId);
                }
                logger.info("====================================\n");
            }
            else if (operation == 5) {
                if (courseCounter >= 4 && courseCounter <= 6) {
                    for (Integer courseID : courseCart) {
                        studentDaoOperation.addCourse(student, courseID);
                    }
                    logger.info("Proceed to make payment\n");
                    setRegistrationStatus(student);
                    student.setIsRegistered(true);
                    break;
                } else if (courseCounter < 4) {
                    logger.info("Less than 4 courses registered. Add more courses.\n");
                } else if (courseCounter > 6) {
                    logger.info("More than 6 courses registered. Drop few courses.\n");
                }
            }
            else if(operation == 6){
                logger.info("......... Exiting from Registration Process ...........\n");
                break;
            }
        }
        logger.info("==============================================\n");
        return false;
    }


    // operation to show registered courses
    public void viewRegisteredCourses(Student student){

        try{
            if(!student.getIsRegistered()){
                logger.info("You have not registered any courses yet. Please register courses.\n");
            }
            else {
                ArrayList<Course> enrolledCourses = studentDaoOperation.getEnrolledCourses(student);
                logger.info("================REGISTERED COURSES================\n");
                logger.info("Course ID\tCourse Name");
                for (Course course : enrolledCourses) {
                    logger.info(course.getCourseID() + "\t\t" + course.getCourseName());
                }
                logger.info("==================================================\n");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    
    public Student getStudentByEmail(String email) {
    	StudentDAOOperation studentOperation = new StudentDAOOperation();
    	Student st = studentOperation.getStudentByEmail(email);
    	return st;
    }


    public void setRegistrationStatus(Student student){
        try{
            studentDaoOperation.setRegistrationStatus(student);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}



