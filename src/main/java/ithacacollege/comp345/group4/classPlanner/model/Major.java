package ithacacollege.comp345.group4.classPlanner.model;

import java.util.ArrayList;
import java.util.List;

public class Major {
    private String title;
    private String type;
    private List<Course> requirements;
    private List<List<Course>> chooseOnes;

    public Major() {
        requirements = new ArrayList<>();
        chooseOnes = new ArrayList<List<Course>>();
    }

    public Major(String title, List<Course> requirements, List<List<Course>> chooseOnes) {
        this.requirements = requirements;
        this.chooseOnes = chooseOnes;
        this.title = title;
    }

    public void addCourse(Course course){
        requirements.add(course);
    }

    public void addChooseOne(List<Course> courses){
        chooseOnes.add(courses);
    }

    public boolean fulfillsRequirement(Course c){
        boolean fulfilled = requirements.contains(c);
        for(Course rc : requirements)
            fulfilled |= c.getCourseNum().equals(rc.getCourseNum());
        if(!fulfilled) {
            for (List<Course> l : chooseOnes)
                for(Course rc : l)
                    fulfilled |= c.getCourseNum().equals(rc.getCourseNum());
        }
        return fulfilled;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}