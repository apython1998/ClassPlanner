package ithacacollege.comp345.group4.classPlanner.model.requirements;

import ithacacollege.comp345.group4.classPlanner.model.Course;

public class SingleCourse implements Requirement {
    private Course course;
    public SingleCourse(Course c){
        course = c;
    }

    public Course getCourse() {
        return course;
    }

    public boolean fulfillsRequirement(Course c){
        return c.getCourseNum().equals(course.getCourseNum());
    }
    public String toString(){
        return "Single Course:\n" + course.toString();
    }
}