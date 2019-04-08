package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.model.requirements.ChooseOne;
import ithacacollege.comp345.group4.classPlanner.model.requirements.Requirement;
import ithacacollege.comp345.group4.classPlanner.model.requirements.SingleCourse;

import java.util.ArrayList;
import java.util.List;

public class Major {
    private String title;
    private List<Course> requirements;
    private List<List<Course>> chooseOnes;

    public Major() { }

    public Major(String title, List<Course> requirements, List<List<Course>> chooseOnes) {
        this.requirements = requirements;
        this.chooseOnes = chooseOnes;
        this.title = title;
    }

    public List<Course> getCourses() {
        return requirements;
    }

    public void setTitle(String title) {
        this.title = title;
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

    /*public boolean fulfillsRequirement(Course c){
        boolean foundFulfillment = false;
        for(Course r : requirements)
            foundFulfillment = r.fulfillsRequirement(c) || foundFulfillment;
        return foundFulfillment;
    }*/

    @Override
    public boolean equals(Object m){
        return this.title.equals(((Major)m).title);
    }
}