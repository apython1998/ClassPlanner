package ithacacollege.comp345.group4.classPlanner.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TranscriptEntry {
    private Course course;
    private String grade;
    private boolean inProgress;
    private boolean courseComplete;

    public TranscriptEntry() {
        course = null;
        grade = "";
        inProgress = false;
        courseComplete = false;
    }

    public TranscriptEntry(Course courseIn, String gradeIn, boolean inProgressIn, boolean courseCompleteIn) {
        course = courseIn;
        grade = gradeIn;
        inProgress = inProgressIn;
        courseComplete = courseCompleteIn;
    }

    public TranscriptEntry(String filename) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(filename)){
            Object obj = jsonParser.parse(reader);

            JSONObject entry = (JSONObject) obj;
            JSONObject myCourse = (JSONObject) entry.get("course");
            JSONArray myPreReqs = (JSONArray) myCourse.get("preReqs") ;

            String courseName = (String) myCourse.get("name");
            int courseCRN = Integer.parseInt((String) myCourse.get("crn"));
            String courseDiscAndNum = (String) myCourse.get("courseDiscAndNum");
            double credits = (double) myCourse.get("credits");
            String courseSemester = (String) myCourse.get("semester");
            List<Course> coursePreReqs;
            if (myPreReqs != null) {
                coursePreReqs = new ArrayList<>();
                for (Object o : myPreReqs) {
                    coursePreReqs.add((Course) o);
                }
            } else {
                coursePreReqs = null;
            }
            course = new Course(courseName, courseCRN, credits, courseDiscAndNum, courseSemester, coursePreReqs);
            grade = (String) entry.get("grade");
            inProgress = (boolean) entry.get("inProgress");
            courseComplete = (boolean) entry.get("courseComplete");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        if (course == null) {
            return "";
        } else {
            String inProgressString = "";
            String courseCompleteString = "";
            if (inProgress)
                inProgressString = "In Progress";
            if (courseComplete)
                courseCompleteString = "Completed";
            return inProgressString + //
                    courseCompleteString + //
                    "\t" + //
                    course.getCourseDiscAndNum() + //
                    "\t" + //
                    course.getName() + //
                    "\t" + //
                    grade + //
                    "\t" + //
                    course.getCredits() + //
                    "\t" + //
                    course.getSemester();
        }
    }
}
