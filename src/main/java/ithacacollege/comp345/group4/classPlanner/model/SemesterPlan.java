package ithacacollege.comp345.group4.classPlanner.model;

import java.sql.Time;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class SemesterPlan {
    private Semester semester;
    private int year;
    private List<Course> courses;

    public SemesterPlan(Semester semester, int year, List<SemesterPlan> previousSemesters, Major major, Transcript transcript) {
        courses = new ArrayList<>();
        this.semester = semester;
        this.year = year;
    }

    /**
     * Generate a list of courses for current semester plan
     * in accordance with preReqs and degree reqs
     * @param previousSemesters List of previous semester plans in order to check preReqs
     * @param major Major to check degree requirements
     * @param transcript Transcript, also to check for preReqs
     */
    public void generateSemesterPlan(List<SemesterPlan> previousSemesters, Major major, Transcript transcript){
        //TODO
    }

    /**
     * Finds from the catalog a course not yet present in the schedule, previous schedules, or transcript
     * And which has all preReqs
     * @param previousSemesters List of all previous plans
     * @param major The major, for degree requirments
     * @param transcript The student's transcript
     * @return Course to be added to plan
     */
    public Course findNewCourseInCatalog(List<SemesterPlan> previousSemesters, Major major, Transcript transcript){
        return null;//TODO
    }

    /**
     * Determines if student has all preReqs for given course
     * @param previousSemesters To check if preReqs may be present in previous plans
     * @param transcript To check if preReqs may be present in transcript
     * @return True if all preReqs are found
     */
    public boolean hasAllPreReqs(Course course, List<SemesterPlan> previousSemesters, Transcript transcript){
        return false;//TODO
    }


    /* ----- Getters and Setters ------ */
    public List<Course> getCourses() { return courses; }
}
