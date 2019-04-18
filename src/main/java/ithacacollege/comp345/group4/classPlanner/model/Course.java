package ithacacollege.comp345.group4.classPlanner.model;

import java.util.ArrayList;
import java.util.List;

public class Course implements Comparable<Course> {
    private String courseNum;
    private String name;
    private double credits;
    private List<SemestersOffered> semestersOffered;
    private CourseFrequency frequencyOffered;
    private List<String> prereqs;
    private List<List<String>> chooseOnes;

    public Course() {
        semestersOffered = new ArrayList<>();
        prereqs = new ArrayList<>();
        chooseOnes = new ArrayList<>();
    }

    public Course(String name, double credits, String courseNum, List<SemestersOffered> semestersOffered, CourseFrequency frequencyOffered, List<String> prereqs, List<List<String>> chooseOnes) {
        this.name = name;
        this.credits = credits;
        this.courseNum = courseNum;
        this.semestersOffered = semestersOffered;
        this.frequencyOffered = frequencyOffered;
        this.prereqs = prereqs;
        this.chooseOnes = chooseOnes;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public List<SemestersOffered> getSemestersOffered() {
        return semestersOffered;
    }

    public void setSemestersOffered(List<SemestersOffered> semesters_offered) {
        this.semestersOffered = semesters_offered;
    }

    public CourseFrequency getFrequencyOffered() {
        return frequencyOffered;
    }

    public void setFrequencyOffered(CourseFrequency frequency_offered) {
        this.frequencyOffered = frequency_offered;
    }

    public List<String> getprereqs() {
        return prereqs;
    }

    public void setprereqs(List<String> preReqs) {
        this.prereqs = preReqs;
    }

    public List<List<String>> getChooseOnes() {
        return chooseOnes;
    }

    public void setChooseOnes(List<List<String>> chooseOnes) {
        this.chooseOnes = chooseOnes;
    }

//    @Override
//    public String toString() {
//        return "Course{" +
//                "courseNum='" + courseNum + '\'' +
//                ", name='" + name + '\'' +
//                ", credits=" + credits +
//                ", semestersOffered=" + semestersOffered +
//                ", frequencyOffered=" + frequencyOffered +
//                ", prereqs=" + prereqs +
//                ", chooseOnes=" + chooseOnes +
//                '}';
//    }

    @Override
    public String toString() {
        if (semestersOffered != null) {
            return courseNum + ": " + name + " (" + semestersOffered.toString() + ", " + credits + ")";
        } else {
            return courseNum + ": " + name + " (" + credits + ")";
        }

    }

    public int compareTo(Course b) {
        String aNumStr = this.getCourseNum();
        aNumStr = aNumStr.substring(4);
        int aNum = Integer.parseInt(aNumStr);

        String bNumStr = b.getCourseNum();
        bNumStr = bNumStr.substring(4);
        int bNum = Integer.parseInt(bNumStr);

        return aNum - bNum;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!Course.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Course other = (Course) obj;

        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }

        return true;
    }
}
