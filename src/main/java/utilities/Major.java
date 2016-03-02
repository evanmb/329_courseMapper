package utilities;

import java.util.ArrayList;

import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

public class Major {
	private String objectID;
	private ArrayList<String> coreClasses;
	private String name;
	private String code;
	
	public Major(ParseObject object) {
		fillFromParseObject(object);
	}
	
	public Major(String objectID) {
		ParseQuery<ParseObject> findMajor = new ParseQuery<ParseObject>("Majors");
		try {
			fillFromParseObject(findMajor.get(objectID));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void fillFromParseObject(ParseObject parseMajor) {
		objectID = parseMajor.getObjectId();
		name = parseMajor.getString("name");
		code = parseMajor.getString("majorCode");

		coreClasses = new ArrayList<String>();
		for (Object o : parseMajor.getList("coreClasses"))
			coreClasses.add((String) o);
	}
	
	public ArrayList<Semester> makeSchedule(int maxCredits, int minCredits) {
		ArrayList<Semester> schedule = new ArrayList<Semester>();
		
		//TODO algorithm
		System.out.println("This program does nothing useful");
		return schedule;
	}
}
