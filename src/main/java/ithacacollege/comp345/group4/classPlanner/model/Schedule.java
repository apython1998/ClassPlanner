package ithacacollege.comp345.group4.classPlanner.model;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private List<Section> courses;
    private SemesterPlan suggestedCourses;

    Schedule() {
        courses = new ArrayList<>();
    }

    Schedule(SemesterPlan suggestedCourses) {
//        courses = new ArrayList();
//        for (Course c: suggestedCourses.getCourses()) {
//
//        }
    }

    public boolean checkAvailability(Section courseToAdd) {
        List<CourseTimes> timesList = courseToAdd.getCourseTimes();
        for (Section s: courses) {
            int j = 0;
            for (CourseTimes t : timesList) {
                for (int i = 0; i < t.getDays().size(); i++) {
                    if (s.getCourseTimes().get(j).getDay(i) == t.getDay(i)) {
                        return false;
                    }
                }
            }
            j++;
        }
        return true;
    }

    public void addCourse(Section courseToAdd) {
        if (checkAvailability(courseToAdd)) {
            courses.add(courseToAdd);
        }
    }

    public String display() {
        String result = "";
        return result;
    }
}
