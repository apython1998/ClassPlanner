package ithacacollege.comp345.group4.classPlanner.model;

import java.util.List;

public class Section extends Course {
    private int num;
    private String year;
    private List<CourseTimes> times;


    Section() {
        super();
        num = 0;
        times = null;
        year = "";
    }

    Section(String name, int crn, double credits, String courseDiscAndNum, String semester, List<Course> preReqs, int num, CourseTimes times, String year) {
        super(name, crn, credits, courseDiscAndNum, semester, preReqs);
        this.num = num;
//        this.times = times;
        this.year = year;
    }

    public void addCourseTimes(String courseTimes) {
        CourseTimes newTime = new CourseTimes();
        String[] subStringList = courseTimes.split(" ");
        String dayString = subStringList[0];
        String timeString = subStringList[1];
        for (int i = 0; i < dayString.length(); i++) {
            newTime.addDay(dayString.charAt(i));
        }
        newTime.setTime(timeString);
        times.add(newTime);
    }

    public void delCourseTimes(String courseTimes) {
//        String[] subStringList = courseTimes.split(",");
//        String dayString = subStringList[0];
//        for (int i = 0; i < dayString.length(); i++) {
////            times.delDay(dayString.charAt(0));
//        }
    }

    public List<CourseTimes> getCourseTimes() {
        return times;
    }
}
