package utilities;

import java.util.ArrayList;

public class Semester {
	private ArrayList<Course> courses;
	
	private int totalCredits;
	
	public Semester() {
		totalCredits = 0;
	}
	
	public void addCourse(Course courseToAdd) {
		courses.add(courseToAdd);
		totalCredits += courseToAdd.getCredits();
	}
	
	public ArrayList<Course> getCourses() {
		return courses;
	}
	
	public int getCredits() {
		return totalCredits;
	}
}
