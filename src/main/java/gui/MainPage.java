package gui;


import java.util.ArrayList;

import org.parse4j.Parse;
import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

import utilities.Major;
import utilities.Semester;

/**
 * 
 * @author Evan Blackwell
 *
 */
public class MainPage {
//	private static ParseObject majorParse;
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Parse.initialize("ueGmIfXGhjfg5T31XCDEc0fkT5uEVZVBfIDGLxBp", 
//				"XXZyCO5GmBUAAm9mirRaxfW91WqmxWf3cn4HN3iu");
//		Major major = getMajor("Computer Engineering");
//		ArrayList<Semester> schedule = major.makeSchedule(12, 18);
//		
//		int count = 1;
//		for (Semester s : schedule) {
//			System.out.println("Semester " + count + ": " + s.toString());
//			count++;
//		}
//	}

	private static Major getMajor(String majorName) {
		ParseQuery<ParseObject> major = new ParseQuery<ParseObject>("Majors");
		major.whereContains("name", majorName);
		try {
			return new Major(major.find().get(0));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
//		major.findInBackground(new FindCallback<ParseObject>() {
//			@Override
//			public void done(List<ParseObject> arg0, ParseException arg1) {
//				if (arg1 == null) 
//					majorParse = arg0.get(0);
//				else
//					arg1.printStackTrace();
//			}
//		});
//		
//		return new Major(majorParse);
	}
}
