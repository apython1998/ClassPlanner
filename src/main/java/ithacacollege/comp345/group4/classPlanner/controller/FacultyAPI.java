package ithacacollege.comp345.group4.classPlanner.controller;

import ithacacollege.comp345.group4.classPlanner.model.Course;
import ithacacollege.comp345.group4.classPlanner.model.Directory;
import ithacacollege.comp345.group4.classPlanner.model.Faculty;
import ithacacollege.comp345.group4.classPlanner.model.Student;

public class FacultyAPI {

    private Directory directory;

    public FacultyAPI() { this.directory = new Directory(); }

    public FacultyAPI(Directory directory) {
        this.directory = directory;
    }

    public void inviteStudentToCourse(String username, Student student, Course course){
        Faculty f = directory.getFaculty().get(username);
        f.inviteStudent(student, course);
    }

    public int viewCourseInterest(Course course){
        return directory.courseIsPlannedCount(course);
    }
    /**
     * Takes info from UI and creates a faculty account
     * @param username
     * @param password
     * @return
     */
    public boolean register(String username, String password) { return directory.registerFaculty(username, password); }

    /**
     * Takes a login attempt and checks if user is authenticated
     * @param username
     * @param password
     * @return Faculty object if login is successful, otherwise null
     */
    public Faculty login(String username, String password) { return directory.loginFaculty(username, password); }

    public Directory getDirectory() {
        return directory;
    }
}
