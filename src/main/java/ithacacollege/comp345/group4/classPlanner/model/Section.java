package ithacacollege.comp345.group4.classPlanner.model;

import java.util.ArrayList;
import java.util.List;

public class Section extends Course {
    private int num;
    private String crn;
    private String year;
    private List<CourseTimes> times;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<CourseTimes> getTimes() {
        return times;
    }

    public void setTimes(List<CourseTimes> times) {
        this.times = times;
    }

    Section() {
        super();
    }

    Section(String name, String crn, double credits, String courseDiscAndNum, List<SemestersOffered> semesters_offered, CourseFrequency frequency_offered, List<String> preReqs, List<List<String>> chooseOnes, int num, String times, String year) {
        super(name, credits, courseDiscAndNum, semesters_offered, frequency_offered, preReqs, chooseOnes);
        this.num = num;
        this.crn = crn;
        this.times = new ArrayList<>();
        String[] subString = times.split(",");
        for (String s: subString) {
            String[] currStringList = s.split(" ");
            this.times.add(new CourseTimes(currStringList[0], currStringList[1]));
        }
        this.year = year;
    }

    public Section(Course course, int num, String crn, String year, String courseTimes) {
        super(course.getName(), course.getCredits(), course.getCourseNum(), course.getSemestersOffered(), course.getFrequencyOffered(), course.getprereqs(), course.getChooseOnes());
        this.num = num;
        this.crn = crn;
        this.times = new ArrayList<>();
        String[] subString = courseTimes.split(",");
        for (String s: subString) {
            String[] currStringList = s.split(" ");
            this.times.add(new CourseTimes(currStringList[0], currStringList[1]));
        }
        this.year = year;
    }

//    Section(int num, String crn, String year, String courseTimes) {
//        super();
//        this.num = num;
//        this.crn = crn;
//        this.times = new ArrayList<>();
//        String[] subString = courseTimes.split(",");
//        for (String s: subString) {
//            String[] currStringList = s.split(" ");
//            this.times.add(new CourseTimes(currStringList[0], currStringList[1]));
//        }
//        this.year = year;
//    }

    Section(Course course, int num, String crn, String year, List<CourseTimes> courseTimes) {
        super(course.getName(), course.getCredits(), course.getCourseNum(), course.getSemestersOffered(), course.getFrequencyOffered(), course.getprereqs(), course.getChooseOnes());
        this.num = num;
        this.crn = crn;
        this.times = courseTimes;
        this.year = year;
    }

    Section(int num, String crn, String year, List<CourseTimes> times) {
        super();
        this.num = num;
        this.crn = crn;
        this.times = times;
        this.year = year;
    }

    public Section setCourse(Course course) {
        return new Section(course, num, crn, year, times);
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCourseNum() + " " + num + " ");
        for (CourseTimes c: times) {
            if (times.indexOf(c) != times.size() - 1) {
                sb.append(c.toString() + ", ");
            } else {
                sb.append(c.toString());
            }
        }
        return sb.toString();
    }
}
