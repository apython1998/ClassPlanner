package ithacacollege.comp345.group4.classPlanner.model.requirements;

import ithacacollege.comp345.group4.classPlanner.model.Course;
import java.util.List;

public class ChooseOne implements Requirement {
    private List<Course> courses;
    public ChooseOne(List<Course> l){
        courses = l;
    }
    public boolean fulfillsRequirment(Course c){
        boolean found = false;
        for(Course lc : courses){
            if(lc.getCourseDiscAndNum().equals(c.getCourseDiscAndNum()))
                found = true;
        }
        return found;
    }
}