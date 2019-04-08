package ithacacollege.comp345.group4.classPlanner.controller;


import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import ithacacollege.comp345.group4.classPlanner.model.Course;
import ithacacollege.comp345.group4.classPlanner.model.Schedule;
import ithacacollege.comp345.group4.classPlanner.model.Major;
import ithacacollege.comp345.group4.classPlanner.model.Student;

import ithacacollege.comp345.group4.classPlanner.model.Directory;
import ithacacollege.comp345.group4.classPlanner.model.requirements.Requirement;

import java.util.List;

public class StudentAPI {

    private Directory directory;

    public StudentAPI() {
        this.directory = new Directory();
    }

    public StudentAPI(Directory directory) {
        this.directory = directory;
    }

    /**
     * Takes information from UI and creates a student account
     * @param username
     * @param password
     */
    public boolean register(String username, String password) {
        return directory.registerStudent(username, password);
    }

    public List<Course> viewCurrentCourses(String name){
        return directory.viewCurrentCourses(name);
    }

    public void setStudentMajor(String student, String major){
        if(!directory.getStudents().containsKey(student))
            throw new InvalidArgumentException("Student does not exist.");
        else if(!directory.getMajorDirectory().containsKey(major))
            throw new InvalidArgumentException("Major does not exist.");
        else
            directory.getStudents().get(student).changeMajor(directory.getMajorDirectory().get(major));
    }

    public List<Requirement> viewMajorRequirements(String major){
        if(!directory.getMajorDirectory().containsKey(major))
            throw new InvalidArgumentException("Major does not exist.");
        else
            return directory.getMajorDirectory().get(major).requirements;
    }

    public boolean validateMajor(String major) {
        return directory.getMajorDirectory().containsKey(major);
    }

    public List<Course> viewTakenCourses(String name){
        return directory.viewTakenCourses(name);
    }

    public List<Course> viewPlannedCourses(String name){
        return directory.viewPlannedCourses(name);
    }

    public boolean addCurrentCourse(String name, Course course){
        return directory.addCurrentCourse(name, course);
    }
    /**
     * Takes a login attempt information and checks if its authenticated
     * @param username
     * @param password
     * @return Student if login is successful, otherwise null
     */
    public Student login(String username, String password) {
        return directory.loginStudent(username, password);
    }


    public boolean addPastCourse(String name, Course course){
        return directory.addPastCourse(name, course);
    }

    public boolean addFutureCourse(String name, Course course){
        return directory.addFutureCourse(name, course);
    }

    /****************************    GETTERS AND SETTERS     ****************************/
    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }
}
