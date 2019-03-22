package ithacacollege.comp345.group4.classPlanner.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;
    private int crn;
    private double credits;
    private String courseDiscAndNum;
    private String semester;
    private List<Course> preReqs;

    public Course() {
        name = "";
        crn = 0;
        credits = 0.0;
        courseDiscAndNum = "";
        semester = "";
        preReqs = new ArrayList<Course>(0);
    }

    public Course(String name, int crn, double credits, String courseDiscAndNum, String semester, List<Course> preReqs) {
        this.name = name;
        this.crn = crn;
        this.credits = credits;
        this.courseDiscAndNum = courseDiscAndNum;
        this.semester = semester;
        this.preReqs = preReqs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCrn() {
        return crn;
    }

    public void setCrn(int crn) {
        this.crn = crn;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public String getCourseDiscAndNum() {
        return courseDiscAndNum;
    }

    public void setCourseDiscAndNum(String courseDiscAndNum) {
        this.courseDiscAndNum = courseDiscAndNum;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public List<Course> getPreReqs() {
        return preReqs;
    }

    public void setPreReqs(List<Course> preReqs) {
        this.preReqs = preReqs;
    }

    public String toString() {
        return this.courseDiscAndNum + ": " + this.name + " " + this.credits + " credits";
    }
}
