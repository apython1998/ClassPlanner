package ithacacollege.comp345.group4.classPlanner.model;

import java.util.ArrayList;
import java.util.List;

public class Major {
    private String title;
    private List<Course> requirements;

    public Major() {
        requirements = new ArrayList<Course>();
        title = "";
    }

    public List<Course> getRequirements() {
        return requirements;
    }

    public String getTitle() {
        return title;
    }

    public void addCourse(Course course){
        requirements.add(course);
    }

    public void addChooseOne(List<Course> courses){
        //TODO
    }

    public interface Requirement {
        boolean fulfillsRequirment(Course c);
    }

    public class SingleCourse implements Requirement {
        private Course course;
        public SingleCourse(Course c){
            course = c;
        }
        public boolean fulfillsRequirment(Course c){
            return c.getCourseDiscAndNum().equals(course.getCourseDiscAndNum());
        }
    }

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
}