package ithacacollege.comp345.group4.classPlanner.model.requirements;

import ithacacollege.comp345.group4.classPlanner.model.Course;

public interface Requirement {
    boolean fulfillsRequirment(Course c);
}
