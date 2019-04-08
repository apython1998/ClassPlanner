package ithacacollege.comp345.group4.classPlanner.model.requirements;

import ithacacollege.comp345.group4.classPlanner.model.Course;
import java.util.List;

public class ChooseOne implements Requirement {
    private List<Course> courses;
    public ChooseOne(List<Course> l){
        courses = l;
    }
    public boolean fulfillsRequirement(Course c){
        boolean found = false;
        for(Course lc : courses){
            if(lc.getCourseNum().equals(c.getCourseNum()))
                found = true;
        }
        return found;
    }

    public Course getCourse(){
        //FIX THIS
        return courses.get(0);
    }

    public String toString(){
        String ret = "Choose One:\n";
        for(Course c : courses)
            ret += c.toString() + "\n";
        return ret;
    }
}
