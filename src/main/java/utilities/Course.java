package utilities;

import java.util.ArrayList;

public class Course {
	private int credits;
	
	private String courseCode;
	
	private ArrayList<Course> preReqs;
	
	public Course(String courseCode) {
		preReqs = new ArrayList<Course>();
		
		//PARSE MUMBO JUMBO
	}
	
	public int getCredits() {
		return credits;
	}
	
	public String getCourseCode() {
		return courseCode;
	}
	
	public ArrayList<Course> getPreReqs() {
		return preReqs;
	}
}
