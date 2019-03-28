package ithacacollege.comp345.group4.classPlanner.model;

import java.util.ArrayList;
import java.util.List;

public class Major {
    public String title;
    public List<Requirement> requirements;

    public Major() {
        requirements = new ArrayList<Requirement>();
        title = "";
    }

    public void addCourse(Course course){
        Requirement r = new SingleCourse(course);
        requirements.add(r);
    }

    public void addChooseOne(List<Course> courses){
        Requirement r = new ChooseOne(courses);
        requirements.add(r);
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

        public String toString(){
            return "Required Course: " + course.getCourseDiscAndNum();
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

        public String toString(){
            String s = "Choose One: \n";
            int index = 1;
            for(Course c : courses){
                s += index + " - " + c.getCourseDiscAndNum() + "\n";
                index++;
            }
            return s;
        }
    }
}