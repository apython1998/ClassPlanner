package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.AlreadyFriendsException;
import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;

import javax.management.InstanceAlreadyExistsException;
import java.util.*;

public class Student extends User {
    private int ID;
    private List<Course> takenCourses;
    private List<Course> currentCourses;
    private List<Course> plannedCourses;

    private List<String> friendsList;
    private List<String> friendRequestList;

    private Schedule nextSemesterSchedule;

    private Semester semester;
    private int year;
    private Map<String, List<Course>> plan;

    private Major major;
    private List<Major> minors;

    private Transcript transcript;

    private List<Course> invitations;

    public Student() {
    }

    private void updateData() {
        for (TranscriptEntry t: transcript.getCourseList()) {
            if (t.isInProgress() && !currentCourses.contains(t.getCourse())) {
                currentCourses.add(t.getCourse());
            }
            if (t.isCourseComplete() && !takenCourses.contains(t.getCourse())) {
                takenCourses.add(t.getCourse());
            }
        }
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
        this.friendRequestList = new ArrayList<>();
        this.friendsList = new ArrayList<>();
        this.invitations = new ArrayList<>();
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
        //if (takenCourses.isEmpty()) {
        //    return null;
        //} !------------ This is causing null pointer exceptions. It should be okay to return an empty list.
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
        return plannedCourses;
    }

    public boolean addCurrentCourses(Course course) {
        if (course == null || currentCourses.contains(course)) {
            return false;
        }
        currentCourses.add(course);
        return true;

    }

    /**
     * takes a student and adds current student's username to their friendRequestList
     * @param friend student object
     * @return true if the student is added to other friend request list, false otherwise
     * @throws InvalidArgumentException if name is already in their friend request list
     * @throws AlreadyFriendsException if the students are already friends
     * @throws IllegalArgumentException if the user tries to pass themself as an argument
     */
    public boolean addFriend(Student friend) {
        if (friendRequestList.contains(friend.getUsername())) {
            friendsList.add(friend.getUsername());
            friend.friendsList.add(getUsername());
            friendRequestList.remove(friend.getUsername());
            return false;
        }
        if (friend.friendRequestList.contains(getUsername())) {
            throw new InvalidArgumentException("Request is pending");
        }
        if (friendsList.contains(friend.getUsername())) {
            throw new AlreadyFriendsException("You are already friends!");
        }
        if (friend == this) {
            throw new IllegalArgumentException("You can't friend yourself!");
        }
        friend.friendRequestList.add(getUsername());
        return true;
    }

    /**
     * adds the student to the friends list or removes from pending requests
     * @param friend student object
     * @param confirm true to accept, false to decline
     * @throws InvalidArgumentException if there isn't a friend request for the passed in student
     */
    public void acceptFriendRequest(Student friend, boolean confirm) {
        if (!friendRequestList.contains(friend.getUsername())) {
            throw new InvalidArgumentException("There is no friend request for this student");
        }
        friendRequestList.remove(friend.getUsername());
        if (confirm) {
            friendsList.add(friend.getUsername());
            friend.friendsList.add(getUsername());
        }
    }

    public List<Course> getCurrentCourses() {
        //if (currentCourses.isEmpty()) {
        //    return null;
        //}!------------ This is also causing null pointer exceptions.
        return currentCourses;
    }


    public boolean addToTranscript(Course course, String grade, boolean inProgress, boolean courseComplete){
        if (course == null || grade == null) {
            throw new InvalidArgumentException("Course or grade must not be null");
        }
        boolean found = false;
        for (TranscriptEntry e: transcript.getCourseList()) {
            if (e.getCourse().equals(course)) {
                found = true;
            }
        }
        plannedCourses.remove(course);
        if (!found) {
            return transcript.addEntry(course, grade, inProgress, courseComplete);
        } else {
            return false;
        }
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcriptIn) {
        transcript = transcriptIn;
        updateData();
    }

    public void clearPlannedCourses(){ plannedCourses.clear(); }

    public void setSchedule(Schedule schedule) {
        nextSemesterSchedule = schedule;
    }

    public Schedule getSchedule() {
        return nextSemesterSchedule;
    }

    public List<String> getFriendsList() {
        return friendsList;
    }

    public List<String> getFriendRequestList() {
        return friendRequestList;
    }

    public String friendRequestListToString() {
        StringBuilder sb = new StringBuilder();
        for (String s: friendRequestList) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    public String friendsListToString() {
        StringBuilder sb = new StringBuilder();
        for (String s: friendsList) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    public Map<String, List<Course>> getPlan() {
        return plan;
    }

    public void setPlan(Map<String, List<Course>> plan) {
        this.plan = plan;
    }

    public void addInvitation(Course c){
        invitations.add(c);
    }

    public void removeInvitation(Course c){
        invitations.remove(c);
    }

    public void setInvitations(List<Course> invitations) {
        this.invitations = invitations;
    }

    public List<Course> getInvitations(){
        return invitations;
    }
}