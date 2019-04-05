package ithacacollege.comp345.group4.classPlanner.model;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private List<Section> courses;
    private SemesterPlan suggestedCourses;

    Schedule() {
        courses = new ArrayList<>();
        suggestedCourses = new SemesterPlan();
    }

    Schedule(SemesterPlan suggestedCourses) {
//        courses = new ArrayList();
//        for (Course c: suggestedCourses.getCourses()) {
//
//        }
    }


    /**
     * checks to see if a section to add conflicts with any section in the list of
     * already existing courses
     * @param courseToAdd
     * @return true if no time conflicts
     */
    public boolean checkAvailability(Section courseToAdd) {
        List<CourseTimes> timesList = courseToAdd.getCourseTimes();
        //getting each section in list
        for (Section s: courses) {
            //index of section's course times
            int j = 0;
            for (CourseTimes t : timesList) {
                //iterate over each day in each course time
                for (int i = 0; i < s.getCourseTimes().get(j).getDays().size(); i++) {
                    //stops i from going out of bounds
                    if (!(i > t.getDays().size() - 1)) {
                        char currDayToAdd = t.getDay(i);
                        char currDay = s.getCourseTimes().get(j).getDay(i);
                        String currTimeToAdd = t.getTime();
                        String currTime = s.getCourseTimes().get(j).getTime();
                        if (currDay == currDayToAdd && currTime.equals(currTimeToAdd)) {
                            return false;
                        }
                    }
                }
                //increment course times index
                j++;
            }
        }
        return true;
    }

    /**
     * adds a course to the list of courses given no time conflicts
     * @throws IllegalArgumentException if there is a time conflict
     * @param courseToAdd
     */
    public void addCourse(Section courseToAdd) {
        if (checkAvailability(courseToAdd)) {
            courses.add(courseToAdd);
        } else {
            throw new IllegalArgumentException("Time conflict with entered course");
        }
    }

    public List<Section> getCourses() {
        return courses;
    }

    /**
     * displays courses for each day of the week
     * @return string of the schedule
     */
    public String display() {
        StringBuilder sb = new StringBuilder();
        String mondayString = "Monday: \n";
        String tuesdayString = "Tuesday: \n";
        String wednesdayString = "Wednesday: \n";
        String thursdayString = "Thursday: \n";
        String fridayString = "Friday: \n";
        for (Section s: courses) {
            for (int i = 0; i < s.getCourseTimes().size(); i++) {
                CourseTimes currTime = s.getCourseTimes().get(i);
                if (currTime.getDays().contains('M')) {
                    mondayString += currTime.getTime() + " " + s.getCourseNum() + "\n";
                }
                if (currTime.getDays().contains('T')) {
                    tuesdayString += currTime.getTime() + " " + s.getCourseNum() + "\n";
                }
                if (currTime.getDays().contains('W')) {
                    wednesdayString += currTime.getTime() + " " + s.getCourseNum() + "\n";
                }
                if (currTime.getDays().contains('R')) {
                    thursdayString += currTime.getTime() + " " + s.getCourseNum() + "\n";
                }
                if (currTime.getDays().contains('F')) {
                    fridayString += currTime.getTime() +  " " + s.getCourseNum() + "\n";
                }
            }
        }
        sb.append(mondayString + tuesdayString + wednesdayString + thursdayString + fridayString);
        return sb.toString();
    }
}
