package ithacacollege.comp345.group4.classPlanner.controller;


import ithacacollege.comp345.group4.classPlanner.model.Course;

import ithacacollege.comp345.group4.classPlanner.model.Student;

import ithacacollege.comp345.group4.classPlanner.model.Directory;

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
