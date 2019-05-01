package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;

import java.util.*;

public class Schedule {
    private List<Section> courses;

    Schedule() {
        courses = new ArrayList<>();
    }

    Schedule(Map<String, List<Section>> sections, List<Course> courses) {
        if (sections == null) {
            throw new InvalidArgumentException("ERROR: Section catalog does not exist");
        }
        if (courses == null) {
            throw new InvalidArgumentException("ERROR: Please generate Plan before creating schedule");
        }
        this.courses = new ArrayList<>();
        for (Course c: courses) {
            String name = c.getCourseNum();
            if (!sections.containsKey(name)) {
                throw new NoSuchElementException("ERROR: Course not found in section catalog");
            }
            Section currSection = null;
            boolean availability = false;
            List<Section> sectionList = sections.get(name);
            Collections.shuffle(sectionList);
            for (Section s: sectionList) {
                currSection = s;
                if (checkAvailability(s)) {
                    this.courses.add(s);
                    availability = true;
                    break;
                }
            }
            if (currSection == null || !availability) {
                throw new IllegalArgumentException("ERROR: Could not find a free section for given course");
            }
//            int i = 0;
//            boolean available;
//            Section s;
//            do {
//                s = sections.get(name).get(i);
//                available = checkAvailability(s);
//                i++;
//            } while (!available || i < sections.values().size());
//            if (!checkAvailability(s)) {
//                throw new IllegalArgumentException("Could not find a free section for given course");
//            }
//            this.courses.add(s);
        }
    }


    /**
     * checks to see if a section to add conflicts with any section in the list of
     * already existing courses
     * @param courseToAdd
     * @return true if no time conflicts
     */
    public boolean checkAvailability(Section courseToAdd) {
        List<CourseTimes> timesList = courseToAdd.getTimes();
        //getting each section in list
        for (Section s: courses) {
            //index of section's course times
            int j = 0;
            for (CourseTimes t : timesList) {
                //iterate over each day in each course time
                for (int i = 0; i < s.getTimes().get(j).getDays().size(); i++) {
                    //stops i from going out of bounds
                    if (!(i > t.getDays().size() - 1)) {
                        char currDayToAdd = t.getDay(i);
                        char currDay = s.getTimes().get(j).getDay(i);
                        String currTimeToAdd = t.getTime();
                        String currTime = s.getTimes().get(j).getTime();
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
            for (int i = 0; i < s.getTimes().size(); i++) {
                CourseTimes currTime = s.getTimes().get(i);
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
