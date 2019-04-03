package ithacacollege.comp345.group4.classPlanner.model;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Plan {

    private List<SemesterPlan> semesterPlans;

    public Plan(int numSemesters, float creditsPerSemester, Semester startSemester, Year startYear){
        semesterPlans = new ArrayList<>();
        generatePlan(numSemesters, creditsPerSemester, startSemester, startYear);
    }

    /**
     * Fills out a sequence of semester plans.
     * This plan will be in accordance with the degree requirements and will not violate prerequisite rules.
     * Semesters alternate between Spring and Fall, and year increases every new spring semester.
     *
     * @param numSemesters Number of semester plans to create
     * @param creditsPerSemester Number of credits per semester
     * @param startSemester First semester to generate
     * @param startYear First year to generate
     */
    private void generatePlan(int numSemesters, float creditsPerSemester, Semester startSemester, Year startYear){

    }

}
