package ithacacollege.comp345.group4.classPlanner.controller;

import ithacacollege.comp345.group4.classPlanner.model.Course;
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

    public List<Course> viewCourses(String name){
        return directory.viewCourses(name);
    }

    /****************************    GETTERS AND SETTERS     ****************************/
    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }
}
