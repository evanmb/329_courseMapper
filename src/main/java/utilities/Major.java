package utilities;

import java.util.ArrayList;
import java.util.HashMap;

import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

public class Major {
	private ArrayList<Course> coreClasses;
	private String name;
	private String code;
	
	/**
	 * Creates a Major object from a ParseObject
	 * @param object
	 */
	public Major(ParseObject object) {
		fillFromParseObject(object);
	}
	
	/**
	 * Creates a Major object from the objectID of a ParseObject
	 * @param objectID
	 */
	public Major(String objectID) {
		ParseQuery<ParseObject> findMajor = new ParseQuery<ParseObject>("Majors");
		try {
			fillFromParseObject(findMajor.get(objectID));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fills the values of the object from a ParseObject
	 * 
	 * @param parseMajor
	 */
	private void fillFromParseObject(ParseObject parseMajor) {
		name = parseMajor.getString("name");
		
		code = parseMajor.getString("majorCode");

		coreClasses = new ArrayList<Course>();
		
		for (Object o : parseMajor.getList("coreClasses")) {
			coreClasses.add(new Course((String) o));
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getMajorCode() {
		return code;
	}
	
	/**
	 * Creates a semester for the given major
	 * @param maxCredits
	 * @param minCredits
	 * @return ArrayList of Semester objects
	 */
	public ArrayList<Semester> makeSchedule(int minCredits, int maxCredits) {
		ArrayList<Semester> schedule = new ArrayList<Semester>();
		
		HashMap<String, Boolean> scheduled = new HashMap<String, Boolean>();
		
		ArrayList<Course> toBeScheduled = (ArrayList<Course>) coreClasses.clone();
		
		int count = 0;
		
		while(toBeScheduled.size() > 0) {
			count++;
			//System.out.println("Round " + count);
			//System.out.println("To be scheduled: " + toBeScheduled);
			
			if (count > 15) {
				for (Course c : toBeScheduled) {
					System.out.println(c.getCourseCode());
				}
				return schedule;
			}
			
			Semester currentSemester = new Semester();
			
			for(Course course : toBeScheduled) {
				// If adding course will put over the top, next course
				if (course.getCredits() + currentSemester.getCredits() > maxCredits)
					continue;
				
				// Check if preReqs are met
				boolean needsPreReq = false;
				for (String preReq : course.getPreReqs()) {
					// Split in case requisite can be filled by multiple courses
					String[] splitReqs = preReq.split("\\|");
					
					// Check if at least one is completed
					boolean isMet = false;
					for (String req : splitReqs) {
						if (scheduled.containsKey(req)) {
							isMet = true;
						}
					}
					
					// If one is missing, can't take course
					if (!isMet) {
						needsPreReq = true;
						break;
					}
				}
				// if !needsPreReq and won't put over credits, add to semester
				if (!needsPreReq) {
					currentSemester.addCourse(course);
				}
			}
			
			// Update schedule
			//System.out.println("Current Semester: " + currentSemester);
			schedule.add(currentSemester);
			for(Course course : currentSemester.getCourses()) {
				scheduled.put(course.getCourseCode(), true);
				toBeScheduled.remove(course);
			}
		}
		return schedule;
	}
}
