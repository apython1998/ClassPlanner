package ithacacollege.comp345.group4.classPlanner.controller;


import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import ithacacollege.comp345.group4.classPlanner.model.*;
import ithacacollege.comp345.group4.classPlanner.model.Course;
import ithacacollege.comp345.group4.classPlanner.model.Student;
import ithacacollege.comp345.group4.classPlanner.model.Directory;

import java.util.HashMap;
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

    /**
     * Takes a login attempt information and checks if its authenticated
     * @param username
     * @param password
     * @return Student if login is successful, otherwise null
     */
    public Student login(String username, String password) {
        return directory.loginStudent(username, password);
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

    public List<Course> viewMajorRequirements(String major){
        if(!directory.getMajorDirectory().containsKey(major))
            throw new InvalidArgumentException("Major does not exist.");
        else
            return directory.getMajorDirectory().get(major).getRequirements();
    }

    public List<List<Course>> viewMajorChooseOnes(String major){
        if(!directory.getMajorDirectory().containsKey(major))
            throw new InvalidArgumentException("Major does not exist.");
        else
            return directory.getMajorDirectory().get(major).getChooseOnes();
    }

    public HashMap<String, List<Course>> generateCoursePlan(String student, int year, Semester semester, int numCredits, List<Course> chooseOnes) {
        return directory.genCoursePlan(student, semester, year, numCredits, chooseOnes);
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

    public void uploadTranscript(String studentName, String filename) {
        if (directory.getStudents().containsKey(studentName)) {
            Student student = directory.getStudents().get(studentName);
            try {
                student.setTranscript(new Transcript(filename));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new InvalidArgumentException("Student does not exist");
        }
    }

    public List<Course> searchMajorRequirements(String username, String major){
        return directory.searchMajorReqs(username, major);
    }

    public Schedule genSchedule(String name) { return directory.genSchedule(name); }

    public boolean addFriend(String studentName, String friendName) {
        return directory.addFriend(studentName, friendName);
    }

    public void acceptFriendRequest(String studentName, String friendName, boolean confirm) {
        directory.acceptFriendRequest(studentName, friendName, confirm);
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
