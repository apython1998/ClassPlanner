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

    public void addCourse(Course course){
        requirements.add(course);
    }

    public void addChooseOne(List<Course> courses){
        //TODO
    }

    public boolean fulfillsRequirement(Course c){
        return requirements.contains(c);
    }

    @Override
    public boolean equals(Object m){
        return this.title.equals(((Major)m).title);
    }

    /** ------------ getters and setters ---------------- **/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Course> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<Course> requirements) {
        this.requirements = requirements;
    }

    public List<List<Course>> getChooseOnes() {
        return chooseOnes;
    }

    public void setChooseOnes(List<List<Course>> chooseOnes) {
        this.chooseOnes = chooseOnes;
    }
}