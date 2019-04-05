package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.model.requirements.ChooseOne;
import ithacacollege.comp345.group4.classPlanner.model.requirements.Requirement;
import ithacacollege.comp345.group4.classPlanner.model.requirements.SingleCourse;

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

    @Override
    public boolean equals(Object m){
        return this.title.equals(((Major)m).title);
    }
}