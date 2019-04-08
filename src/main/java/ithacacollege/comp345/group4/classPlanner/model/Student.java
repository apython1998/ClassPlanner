package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Student extends User {
    private int ID;
    private List<Course> takenCourses;
    private List<Course> currentCourses;
    private List<Course> plannedCourses;

    private Semester semester;
    private int year;

    private Major major;
    private List<Major> minors;

    private Transcript transcript;

    public Student() {
    }

    public Student(String username, String password, Major major, List<Major> minors) {
        super(username, password);
        this.ID = ID;
        this.takenCourses = new ArrayList<>();
        this.currentCourses = new ArrayList<>();
        this.plannedCourses = new ArrayList<>();
        this.major = major;
        this.minors = minors;
        /*this.semester = semester;
        this.year = year;*/
        this.transcript = new Transcript();
    }

    public int getID() {
        return ID;
    }

    public Major getMajor() {
        return major;
    }

    /**
     * Changes the major of a Student
     *
     * @param major - major the Student intends on completing
     */
    public void changeMajor(Major major) {
        this.major = major;
    }

    /**
     * Adds a minor to the student's account
     *
     * @param minor - minor the Student would like to add
     */
    public void addMinor(Major minor) {
        if (this.minors.contains(minor)) {
            return;
        }
        this.minors.add(minor);
    }

    /**
     * Removes a minor from the Student
     *
     * @param minor - minor intended to be removed
     * @return - boolean if the removal was successful (false if the minor wasn't there)
     */
    public boolean removeMinor(Major minor) {
        return minors.remove(minor);
    }

    /**
     * Adds the courses a Student has already taken
     *
     * @param courses - list of courses the student has already taken
     */
    public void addCoursesTaken(List<Course> courses) {
        if (courses != null) {
            for (Course c : courses) {
                if (c != null) {
                    if (!takenCourses.contains(c)) {
                        this.takenCourses.add(c);
                    }
                } else {
                    throw new InvalidArgumentException("Invalid Course");
                }
            }
        } else {
            throw new InvalidArgumentException("Invalid Course");
        }
    }

    public boolean addCoursesTaken(Course course) {
        if (course == null) {
            throw new InvalidArgumentException("Invalid course");
        }
        if (takenCourses.contains(course)) {
            return false;
        } else {
            takenCourses.add(course);
            return true;
        }
    }

    public boolean addTakenCourses(Course course) {
        if (course == null || takenCourses.contains(course)) {
            return false;
        }
        takenCourses.add(course);
        return true;
    }

    public List<Course> getTakenCourses() {
        if (takenCourses.isEmpty()) {
            return null;
        }
        return takenCourses;
    }

    /**
     * Adds the courses a Student plans on taking
     *
     * @param courses - list of courses the student intends on taking
     */
    public boolean addPlannedCourses(List<Course> courses) {
        if (courses != null) {
            for (Course c : courses) {
                if (c != null) {
                    if (!plannedCourses.contains(c)) {
                        this.plannedCourses.add(c);
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean addPlannedCourses(Course course) {
        if (course == null || plannedCourses.contains(course)) {
            return false;
        }
        plannedCourses.add(course);
        return true;
    }

    public List<Course> getPlannedCourses() {
        if (plannedCourses.isEmpty()) {
            return null;
        }
        return plannedCourses;
    }

    public boolean addCurrentCourses(Course course) {
        if (course == null || currentCourses.contains(course)) {
            return false;
        }
        currentCourses.add(course);
        return true;

    }

    public List<Course> getCurrentCourses() {
        if (currentCourses.isEmpty()) {
            return null;
        }
        return currentCourses;
    }


    public boolean addToTranscript(Course course, String grade, boolean inProgress, boolean courseComplete){
        return transcript.addEntry(course, grade, inProgress, courseComplete);
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcriptIn) {
        transcript = transcriptIn;
    }
}
