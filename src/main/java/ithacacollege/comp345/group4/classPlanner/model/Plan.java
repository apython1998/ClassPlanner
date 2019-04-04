package ithacacollege.comp345.group4.classPlanner.model;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Plan {

    private List<SemesterPlan> semesterPlans;

    public Plan(float creditsPerSemester, Semester startSemester, Year startYear, Transcript transcript, Major major){
        semesterPlans = new ArrayList<>();
        generatePlan(creditsPerSemester, startSemester, startYear, transcript, major);
    }

    /**
     * Fills out a sequence of semester plans.
     * This plan will be in accordance with the degree requirements and will not violate prerequisite rules.
     * Semesters alternate between Spring and Fall, and year increases every new spring semester.
     *
     * @param creditsPerSemester Number of credits per semester
     * @param startSemester First semester to generate
     * @param startYear First year to generate
     */
    private void generatePlan(float creditsPerSemester, Semester startSemester, Year startYear, Transcript transcript, Major major){
        //TODO
    }

}
