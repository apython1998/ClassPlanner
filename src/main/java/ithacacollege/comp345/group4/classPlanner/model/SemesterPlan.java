package ithacacollege.comp345.group4.classPlanner.model;

import java.sql.Time;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class SemesterPlan {
    private Semester semester;
    private int year;
    private List<Course> courses;

    public SemesterPlan() {}
    public SemesterPlan(double numCredits, Semester semester, int year, List<SemesterPlan> previousSemesters, Major major, Transcript transcript) {
        courses = new ArrayList<>();
        this.semester = semester;
        this.year = year;
        generateSemesterPlan(numCredits, previousSemesters, major, transcript);
    }

    /**
     * Generate a list of courses for current semester plan
     * in accordance with preReqs and degree reqs
     * @param numCredits
     * @param previousSemesters List of previous semester plans in order to check preReqs
     * @param major Major to check degree requirements
     * @param transcript Transcript, also to check for preReqs
     */
    private void generateSemesterPlan(double numCredits, List<SemesterPlan> previousSemesters, Major major, Transcript transcript){
        //TODO
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


    /** ----- Getters and Setters ------ **/
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
