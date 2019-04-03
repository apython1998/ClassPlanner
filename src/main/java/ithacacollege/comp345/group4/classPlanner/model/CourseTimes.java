package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CourseTimes {
    private static final String validDays = "MTWRF";
    private List<Character> days;
    private String time;

    public CourseTimes() {
        days = new ArrayList<>();
        time = "";
    }

    public CourseTimes(String days, String time) {
        this.days = new ArrayList<>();
        for (int i = 0; i < days.length(); i++) {
            char currDay = days.charAt(i);
            addDay(currDay);
        }
        if (isTimeValid(time)) {
            this.time = time;
        } else {
            throw new InvalidArgumentException("Time is invalid");
        }
    }

    public void addDay(char dayIn) {
        if (isDayValid(dayIn)) {
            if (!this.days.contains(dayIn)) {
                this.days.add(dayIn);
            }
        } else {
            throw new InvalidArgumentException("Days must be M, T, W, R, or F");
        }
    }

    public void removeDay(char dayIn) {
        if (isDayValid(dayIn)) {
            if (this.days.contains(dayIn)) {
                this.days.remove(dayIn);
            }
        } else {
            throw new InvalidArgumentException("Days must be M, T, W, R, or F");
        }
    }

    public void setTime(String timeIn) {
        time = timeIn;
    }

    public List<Character> getDays() {
        return days;
    }

    public char getDay(int index) {
        return days.get(index);
    }

    public String getTime() {
        return time;
    }

    public static boolean isDayValid(char currDay) {
        return validDays.contains(Character.toString(currDay));
    }

    public static boolean isTimeValid(String time) {
        if (time.length() > 5 || time.length() < 4 || time.indexOf(':') == -1) {
            return false;
        } else {
            String[] subStringList = time.split(":");
            if (subStringList.length > 2) {
                return false;
            }
            int hours = Integer.parseInt(subStringList[0]);
            int minutes = Integer.parseInt(subStringList[1]);
            return !(hours < 0 || hours > 23 || minutes < 0 || minutes > 59);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < days.size(); i++) {
            sb.append(days.get(i));
        }
        sb.append(" " + time);
        return sb.toString();
    }


}
