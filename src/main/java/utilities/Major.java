package utilities;

import java.util.ArrayList;

import org.parse4j.ParseObject;

public class Major {
	private String objectID;
	
	public Major(ParseObject object) {
		objectID = object.getObjectId();
	}
	
	public ArrayList<Semester> makeSchedule(int maxCredits, int minCredits) {
		ArrayList<Semester> schedule = new ArrayList<Semester>();
		
		//TODO algorithm
		System.out.println("This program does nothing useful");
		return schedule;
	}
}
