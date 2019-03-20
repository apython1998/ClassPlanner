package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Directory {

    private Map<String, User> students;
    private List<Major> majorDirectory;

    public Directory() {
        this.students = new HashMap<>();
    }

    public Directory(Map<String, User> users) {
        this.students = users;
    }

    /**
     * Registers a new user to the directory
     * @param username
     * @param password
     * @return true if registration successful, false if username already exists
     */
    public boolean registerStudent(String username, String password) throws InvalidArgumentException {
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidArgumentException("Invalid String Used");
        } else if (students.containsKey(username)) {
            return false;
        } else {
            Student newUser = new Student(username, password);
            students.put(newUser.getUsername(), newUser);
            return true;
        }
    }

    /**
     * Authenticates a student's attempt to login if username and password exist in Student directory
     * @param username String of student's username
     * @param password String of student's attempted password entry
     * @return
     */
    public boolean loginStudent(String username, String password) {
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidArgumentException("Invalid String Used for Username or Password");
        } else {
            User userAttempt = students.get(username);
            if (userAttempt != null) {
                return userAttempt.authenticate(password);
            } else {
                return false;
            }
        }
    }

    public void uploadMajor(String file){

    }



    /**************************** GETTERS AND SETTERS     ****************************/
    public Map<String, User> getStudents() {
        return students;
    }
    public List<Major> getMajorDirectory() { return majorDirectory; }

    public void setStudents(Map<String, User> users) {
        this.students = users;
    }


}
