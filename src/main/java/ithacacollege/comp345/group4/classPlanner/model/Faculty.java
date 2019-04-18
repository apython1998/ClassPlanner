package ithacacollege.comp345.group4.classPlanner.model;

import java.util.ArrayList;
import java.util.List;

public class Faculty extends User {

    private int ID;
    private List<Course> coursesTeaching;

    public Faculty() {
    }

    public Faculty(String username, String password) {
        super(username, password);
        this.coursesTeaching = new ArrayList<>();
    }

    public Faculty(String username, String password, List<Course> coursesTeaching) {
        super(username, password);
        this.coursesTeaching = coursesTeaching;
    }



    /**********************************   GETTERS & SETTERS *****************************/
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public List<Course> getCoursesTeaching() {
        return coursesTeaching;
    }

    public void setCoursesTeaching(List<Course> coursesTeaching) {
        this.coursesTeaching = coursesTeaching;
    }
}
