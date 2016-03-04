package utilities;

import java.util.ArrayList;

import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

public class Course {
	protected int credits;
	protected String courseCode;
	protected ArrayList<String> preReqs;
	
	public Course() {
		credits = 3;
		courseCode = "";
		preReqs = new ArrayList<String>();
	}
	
	public Course(String courseCode) {
		ParseQuery<ParseObject> findCourse = new ParseQuery<ParseObject>("Courses");
		
		findCourse.whereContains("courseCode", courseCode);
		
		try {
			fillFromParseObject(findCourse.find().get(0));
		}
		catch (ParseException e) {
			System.out.println(courseCode);
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			System.out.println(courseCode);
			e.printStackTrace();
		}
	}

	public Course(ParseObject parseCourse) {
		fillFromParseObject(parseCourse);
	}
	
	private void fillFromParseObject(ParseObject parseCourse) {
		preReqs = new ArrayList<String>();
		
		for (Object courseCode : parseCourse.getList("preReqs")) {
				if (((String)courseCode).contains("\\|")) {
					for (String curCourse : ((String)courseCode).split("\\|")) {
						System.out.println((String) curCourse);
						preReqs.add((String) curCourse);
					}
				}
				else {
					preReqs.add((String) courseCode);
				}
		}
		
		courseCode = parseCourse.getString("courseCode");
		credits = parseCourse.getInt("credits");
	}
	
	public int getCredits() {
		return credits;
	}
	
	public String getCourseCode() {
		return courseCode;
	}
	
	public ArrayList<String> getPreReqs() {
		return preReqs;
	}
	
	public boolean equals(Object other) {
		if (other == null) return false;
		
		if (!this.getClass().equals(other.getClass())) return false;
		
		Course o = (Course) other;
		
		return (courseCode.equals(o.courseCode) 
				&& credits == o.credits 
				&& preReqs.equals(o.preReqs));
	}
	
	public String toString() {
		return courseCode;
	}
}
