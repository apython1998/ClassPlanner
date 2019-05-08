package ithacacollege.comp345.group4.classPlanner.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * creates a transcript entry from a JSON file
     * @param filename filepath
     */
    public TranscriptEntry(String filename) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(filename)){
            Object obj = jsonParser.parse(reader);

            JSONObject entry = (JSONObject) obj;
            JSONObject myCourse = (JSONObject) entry.get("course");
            course = parseCourse(myCourse);
            grade = (String) entry.get("grade");
            inProgress = (boolean) entry.get("inProgress");
            courseComplete = (boolean) entry.get("courseComplete");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return String with the course name, status and a grade
     */
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
                    course.getCourseNum() + //
                    "\t" + //
                    course.getName() + //
                    "\t" + //
                    grade + //
                    "\t" + //
                    course.getCredits();

        }
    }

    public static Course parseCourse(JSONObject myCourse) {
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
                coursePreReqs.add(parseCourse((JSONObject) o));
            }
        } else {
            coursePreReqs = null;
        }
        return new Course(courseName, credits, courseDiscAndNum,null, null, null, null);
    }

    public static TranscriptEntry parseEntry(JSONObject entry) {
            JSONObject myCourse = (JSONObject) entry.get("course");
            Course course = parseCourse(myCourse);
            String myGrade = (String) entry.get("grade");
            boolean myInProgress = (boolean) entry.get("inProgress");
            boolean myCourseComplete = (boolean) entry.get("courseComplete");
            return new TranscriptEntry(course, myGrade, myInProgress, myCourseComplete);
    }

    public Course getCourse() {
        return course;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public boolean isCourseComplete() {
        return courseComplete;
    }
}
