package ithacacollege.comp345.group4.classPlanner.controller;

import ithacacollege.comp345.group4.classPlanner.model.Directory;
import ithacacollege.comp345.group4.classPlanner.model.Faculty;

public class FacultyAPI {

    private Directory directory;

    public FacultyAPI() { this.directory = new Directory(); }

    public FacultyAPI(Directory directory) {
        this.directory = directory;
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
}
