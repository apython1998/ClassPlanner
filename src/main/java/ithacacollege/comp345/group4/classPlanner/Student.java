package ithacacollege.comp345.group4.classPlanner;

import ithacacollege.comp345.group4.classPlanner.model.Course;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int ID;
    private List<Course> coursesTaken;
    private List<Course> currentCourses;
    private List<Course> coursesPlanned;

    private Major major;
    private List<Major> minors;

    Transcript transcript;

    public Student(int ID, Major major, List<Major> minors){
        this.ID = ID;
        this.coursesTaken = new ArrayList<>();
        this.currentCourses = new ArrayList<>();
        this.coursesPlanned = new ArrayList<>();
        this.major = major;
        this.minors = minors;
        this.transcript = new Transcript();
    }

    public int getID() { return ID; }

    public Major getMajor() { return major; }

    /**
     * Changes the major of a Student
     * @param major - major the Student intends on completing
     */
    public void changeMajor(Major major){ this.major = major; }

    /**
     * Adds a minor to the student's account
     * @param minor - minor the Student would like to add
     */
    public void addMinor(Major minor){
        if (this.minors.contains(minor)){
            return;
        }
        this.minors.add(minor);
    }

    public boolean removeMinor(Major minor){
        return minors.remove(minor);
    }

    public void addCoursesTaken(List<Course> courses){
        for (Course c: courses) {
            this.coursesTaken.add(c);
        }
    }

    public List<Course> getCoursesTaken() { return coursesTaken; }

    public void addCoursesPlanned(List<Course> courses){
        for (Course c: courses) {
            this.coursesPlanned.add(c);
        }
    }

    public List<Course> getCoursesPlanned() { return coursesPlanned; }

    public void addCurrentCourses(List<Course> courses){
        for (Course c: courses) {
            this.currentCourses.add(c);
        }
    }

    public List<Course> getCurrentCourses() { return currentCourses; }
}
