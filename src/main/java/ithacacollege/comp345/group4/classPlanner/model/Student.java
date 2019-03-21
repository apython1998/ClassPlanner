package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private int ID;
    private List<Course> coursesTaken;
    private List<Course> currentCourses;
    private List<Course> coursesPlanned;

    private Major major;
    private List<Major> minors;

    private Transcript transcript;

    public Student() {
    }

    public Student(String username, String password, Major major, List<Major> minors) {
        super(username, password);
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

    /**
     * Removes a minor from the Student
     * @param minor - minor intended to be removed
     * @return - boolean if the removal was successful (false if the minor wasn't there)
     */
    public boolean removeMinor(Major minor){
        return minors.remove(minor);
    }

    /**
     * Adds the courses a Student has already taken
     * @param courses - list of courses the student has already taken
     */
    public void addCoursesTaken(List<Course> courses){
        if (courses != null) {
            for (Course c : courses) {
                if (c != null) {
                    if (!coursesTaken.contains(c)) {
                        this.coursesTaken.add(c);
                    }
                } else {
                    throw new InvalidArgumentException("Invalid Course");
                }
            }
        } else {
            throw new InvalidArgumentException("Invalid Course");
        }
    }

    public boolean addCoursesTaken(Course course){
        if (course == null){
            throw new InvalidArgumentException("Invalid course");
        }
        if (coursesTaken.contains(course)){
            return false;
        } else {
            coursesTaken.add(course);
            return true;
        }
    }

    public List<Course> getCoursesTaken() {
        if (coursesTaken.isEmpty()){
            throw new InvalidArgumentException("No courses are entered");
        }
        return coursesTaken;
    }

    /**
     * Adds the courses a Student plans on taking
     * @param courses - list of courses the student intends on taking
     */
    public void addCoursesPlanned(List<Course> courses){
        if (courses != null) {
            for (Course c : courses) {
                if (c != null) {
                    if (!coursesPlanned.contains(c)) {
                        this.coursesPlanned.add(c);
                    }
                } else {
                    throw new InvalidArgumentException("Invalid Course");
                }
            }
        }
        else {
            throw new InvalidArgumentException("Invalid Course");
        }
    }

    public boolean addCoursesPlanned(Course course){
        if (course == null){
            throw new InvalidArgumentException("Invalid course");
        }
        if (coursesPlanned.contains(course)){
            return false;
        } else {
            coursesPlanned.add(course);
            return true;
        }
    }

    public List<Course> getCoursesPlanned() {
        if (coursesPlanned.isEmpty()){
            throw new InvalidArgumentException("No courses are entered");
        }
        return coursesPlanned;
    }

    /**
     * Adds the courses a Student is currently registered for
     * @param courses - list of current courses the student is registered for
     */
    public void addCurrentCourses(List<Course> courses){
        if (courses != null) {
            for (Course c : courses) {
                if (c != null) {
                    if (!currentCourses.contains(c)) {
                        this.currentCourses.add(c);
                    }
                } else {
                    throw new InvalidArgumentException("Invalid Course");
                }
            }
        } else {
            throw new InvalidArgumentException("Invalid Course");
        }
    }

    public boolean addCurrentCourses(Course course){
        if (course == null){
            throw new InvalidArgumentException("Invalid course");
        }
        if (currentCourses.contains(course)){
            return false;
        } else {
            currentCourses.add(course);
            return true;
        }
    }

    public List<Course> getCurrentCourses() {
        if (currentCourses.isEmpty()){
            throw new InvalidArgumentException("No courses are entered");
        }
        return currentCourses;
    }
}
