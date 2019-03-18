package ithacacollege.comp345.group4.classPlanner;

import java.util.List;

public class Major {
    public String title;
    private List<Requirement> requirements;

    public void addCourse(Course course){
        Requirement r = new SingleCourse(course);
        requirements.add(r);
    }

    public void addChooseOne(List<Course> courses){
        Requirement r = new ChooseOne(courses);
        requirements.add(r);
    }

    private interface Requirement {
        boolean fulfillsRequirment(Course c);
    }

    private class SingleCourse implements Requirement {
        private Course course;
        public SingleCourse(Course c){
            course = c;
        }
        public boolean fulfillsRequirment(Course c){
            return c.equals(course);
        }
    }

    private class ChooseOne implements Requirement {
        private List<Course> courses;
        public ChooseOne(List<Course> l){
            courses = l;
        }
        public boolean fulfillsRequirment(Course c){
            boolean found = false;
            for(Course lc : courses){
                if(lc.equals(c))
                    found = true;
            }
            return true;
        }
    }
}