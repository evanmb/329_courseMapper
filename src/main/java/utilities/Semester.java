package utilities;

import java.util.ArrayList;

public class Semester {
	private ArrayList<Course> courses;
	
	private int totalCredits;
	
	public Semester() {
		courses = new ArrayList<Course>();
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
	
	public String toString() {
		if (courses.size() == 0)
			return "Empty Semester";
		
		String retVal = courses.get(0).getCourseCode();
		for (int i=1; i<courses.size(); i++) {
			retVal += ", " + courses.get(i).getCourseCode();
		}
		
		return retVal;
	}
}
