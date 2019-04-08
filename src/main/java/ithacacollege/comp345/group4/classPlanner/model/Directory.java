package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Directory {

    private Map<String, Major> majorDirectory;
    private Map<String, Student> students;
    private Map<String, Course> courseCatalog;
    private Map<String, List<Section>> sectionCatalog;

    public Directory() {
        this.majorDirectory = new HashMap<>();
        this.students = new HashMap<>();
        this.courseCatalog = new HashMap<>();
        this.sectionCatalog = new HashMap<>();
    }

    public Directory(Map<String, Student> users) {
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

    /**
     * Authenticates a student's attempt to login if username and password exist in Student directory
     * @param username String of student's username
     * @param password String of student's attempted password entry
     * @return Student that is logged in if authenticated, else return null
     */
    public Student loginStudent(String username, String password) {
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidArgumentException("Invalid String Used for Username or Password");
        } else {
            Student userAttempt = students.get(username);
            if (userAttempt != null) {
                if (userAttempt.authenticate(password)) {
                    return userAttempt;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    public void uploadMajor(String file){
        JSONParser parser = new JSONParser();
        Major newMajor = new Major();
        try {
            JSONObject majorObject = (JSONObject)parser.parse(new FileReader(file));

            String title = (String) majorObject.get("title");
            newMajor.title = title;

            JSONArray courses = (JSONArray) majorObject.get("courses");
            Iterator<String> courseitr = courses.iterator();
            while (courseitr.hasNext()) {
                String courseTitle = courseitr.next();
                Course newCourse = new Course();
                newCourse.setCourseNum(courseTitle);
                newMajor.addCourse(newCourse);
            }

            JSONArray grouping = (JSONArray) majorObject.get("grouping");
            Iterator<JSONArray> groupingItr = grouping.iterator();
            while(groupingItr.hasNext()){
                JSONArray chooseOne = (JSONArray) groupingItr.next();
                Iterator<String> chooseCourseItr = chooseOne.iterator();
                List<Course> chooseCourseList = new ArrayList<>();
                while(chooseCourseItr.hasNext()) {
                    Course c = new Course();
                    c.setCourseNum(chooseCourseItr.next());
                    chooseCourseList.add(c);
                }
                newMajor.addChooseOne(chooseCourseList);
            }
        }
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        majorDirectory.put(newMajor.title, newMajor);
    }

    public List<Course> viewCurrentCourses(String name){
        if (!students.containsKey(name)){
            throw new InvalidArgumentException("There is no account associated with that name");
        }
        User student = students.get(name);
        return ((Student) student).getCurrentCourses();
    }

    public List<Course> viewTakenCourses(String name){
        if (!students.containsKey(name)){
            throw new InvalidArgumentException("There is no account associated with that name");
        }
        User student = students.get(name);
        return ((Student) student).getTakenCourses();
    }

    public List<Course> viewPlannedCourses(String name){
        if (!students.containsKey(name)){
            throw new InvalidArgumentException("There is no account associated with that name");
        }
        User student = students.get(name);
        return ((Student) student).getPlannedCourses();
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
        return ((Student) student).addTakenCourses(course);
    }

    public boolean addFutureCourse(String name, Course course){
        if (!students.containsKey(name)){
            throw new InvalidArgumentException("There is no account associated with that name");
        }
        User student = students.get(name);
        return ((Student) student).addPlannedCourses(course);
    }

    public void addToSectionCatalog(String name, String courseNum, int number, String classTimes, String crn, String year) {
        if (!sectionCatalog.containsKey(name)) {
            Course course = courseCatalog.get(courseNum);
            Section sectionToAdd = new Section(course, number, crn, year, classTimes);
        }
    }

    /**************************** GETTERS AND SETTERS     ****************************/
    public Map<String, Student> getStudents() {
        return students;
    }
    public Map<String, Major> getMajorDirectory() { return majorDirectory; }

    public Map<String, Course> getCourseCatalog() {
        return courseCatalog;
    }

    public void setCourseCatalog(Map<String, Course> courseCatalog) {
        this.courseCatalog = courseCatalog;
    }

    public void setStudents(Map<String, Student> users) {
        this.students = users;
    }


}
