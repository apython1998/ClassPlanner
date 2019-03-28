package ithacacollege.comp345.group4.classPlanner.model;

import java.util.List;

public class CourseTimes {
    List<Character> days;
    List<String> times;

    public CourseTimes(List<Character> day, List<String> time) {
        this.days = day;
        this.times = time;
    }

    public void addDay(char dayIn) {
        days.add(dayIn);
    }

    public void addTime(String timeIn) {
        times.add(timeIn);
    }

    public void delDay(char dayIn) {
        days.remove(dayIn);
    }

    public void delTime(String timeIn) {
        times.remove(timeIn);
    }

    public void setDay(List<Character> day) {
        this.days = day;
    }

    public void setTime(List<String> time) {
        this.times = time;
    }

    public List<Character> getDays() {
        return days;
    }

    public List<String> getTimes() {
        return times;
    }


}
