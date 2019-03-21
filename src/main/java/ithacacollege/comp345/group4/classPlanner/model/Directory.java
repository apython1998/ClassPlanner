package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Directory {

    private Map<String, User> students;
    private List<Major> majorDirectory;

    public Directory() {
        this.students = new HashMap<>();
        this.majorDirectory = new ArrayList<>();
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
                newCourse.setCourseDiscAndNum(courseTitle);
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
                    c.setCourseDiscAndNum(chooseCourseItr.next());
                    chooseCourseList.add(c);
                }
                newMajor.addChooseOne(chooseCourseList);
            }
        }
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        majorDirectory.add(newMajor);
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
