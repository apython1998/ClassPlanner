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
            Student newUser = new Student(username, password, null, null);
            students.put(newUser.getUsername(), newUser);
            return true;
        }
    }

    public void uploadMajor(String file){

    }

    public List<Course> viewCurrentCourses(String name){
        if (!students.containsKey(name)){
            throw new InvalidArgumentException("There is no account associated with that name");
        }
        User student = students.get(name);
        return ((Student) student).getCurrentCourses();
    }

    public boolean addCurrentCourse(String name, Course course){
        if (!students.containsKey(name)){
            throw new InvalidArgumentException("There is no account associated with that name");
        }
        User student = students.get(name);
        return ((Student) student).addCurrentCourses(course);
    }

    public boolean addPastCourse(String name, Course course){
        if (!students.containsKey(name)){
            throw new InvalidArgumentException("There is no account associated with that name");
        }
        User student = students.get(name);
        return ((Student) student).addCoursesTaken(course);
    }

    public boolean addFutureCourse(String name, Course course){
        if (!students.containsKey(name)){
            throw new InvalidArgumentException("There is no account associated with that name");
        }
        User student = students.get(name);
        return ((Student) student).addCoursesPlanned(course);
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
