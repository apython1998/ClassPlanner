package ithacacollege.comp345.group4.classPlanner.model;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class SemesterPlan {
    Semester semester;
    Year year;
    private List<Course> courses;

    public SemesterPlan(Semester semester, Year year, List<SemesterPlan> previousSemesters, Major major, Transcript transcript) {
        courses = new ArrayList<>();
        this.semester = semester;
        this.year = year;
        generateSemesterPlan(previousSemesters, major, transcript);
    }

    /**
     * Generate a list of courses for current semester plan
     * in accordance with preReqs and degree reqs
     * @param previousSemesters List of previous semester plans in order to check preReqs
     * @param major Major to check degree requirements
     * @param transcript Transcript, also to check for preReqs
     */
    private void generateSemesterPlan(List<SemesterPlan> previousSemesters, Major major, Transcript transcript){
        //TODO
    }

    private Course findCourseInCatalog(){
        return null;//TODO
    }

    private boolean hasAllPreReqs(List<SemesterPlan> previousSemesters, Transcript transcript){
        return false;//TODO
    }


    /* ----- Getters and Setters ------ */
    public List<Course> getCourses() { return courses; }
}
