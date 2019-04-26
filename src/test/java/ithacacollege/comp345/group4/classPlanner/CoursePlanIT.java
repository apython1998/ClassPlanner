package ithacacollege.comp345.group4.classPlanner;

import ithacacollege.comp345.group4.classPlanner.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.*;

public class CoursePlanIT {

    @Test
    void checkPlanCreditsTest() throws IOException {
        Directory d = new Directory();
        List<Course> allCourses = JsonUtil.listFromJsonFile("src/main/resources/courseCatalog.json", Course.class);
        Map<String, Course> courseCatalog = new HashMap<>();
        for (Course course : allCourses) {
            courseCatalog.put(course.getCourseNum(), course);
        }

        d.setCourseCatalog(courseCatalog);
        Major fakeMajor = new Major();
        fakeMajor.setTitle("Computer Science");
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP17100"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP17200"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP11500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP22000"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP10500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP20500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP21000"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP37500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP11000"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH11100"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH11200"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH21100"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH21600"));

        d.registerStudent("jon", "shmon");
        Student s = d.getStudents().get("jon");
        s.changeMajor(fakeMajor);
        int maxCredits = 16;
        HashMap<String, List<Course>> plan = d.genCoursePlan("jon", Semester.Fall, 2019, maxCredits);
        String planStr = d.scheduleToStr(plan);
        System.out.println(planStr);

        Set<String> semesters = plan.keySet();

        for (String semester : semesters) {
            List<Course> courses = plan.get(semester);
            double credits = 0;
            for (int i = 0; i < courses.size(); i++) {
                credits += courses.get(i).getCredits();
            }
            assertTrue(credits <= maxCredits);
        }
    }

    @Test
    void planPreReqTest() throws IOException {
        Directory d = new Directory();
        List<Course> allCourses = JsonUtil.listFromJsonFile("src/main/resources/courseCatalog.json", Course.class);
        Map<String, Course> courseCatalog = new HashMap<>();
        for (Course course : allCourses) {
            courseCatalog.put(course.getCourseNum(), course);
        }

        d.setCourseCatalog(courseCatalog);
        Major fakeMajor = new Major();
        fakeMajor.setTitle("Computer Science");
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP17100"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP17200"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP11500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP22000"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP10500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP20500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP21000"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP37500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP11000"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH11100"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH11200"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH21100"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH21600"));

        d.registerStudent("jon", "shmon");
        Student s = d.getStudents().get("jon");
        s.changeMajor(fakeMajor);

        HashMap<String, List<Course>> plan = d.genCoursePlan("jon", Semester.Fall, 2019, 15);

        String[] semesters = new String[4];

        semesters[0] = "Spring2019";
        semesters[1] = "Fall2019";
        semesters[2] = "Spring2020";
        semesters[3] = "Fall2020";

        List<Course> plannedCourses = new ArrayList<>();
        for (int i = 0; i < semesters.length; i++){
            String semester = semesters[i];
            List<Course> courses = plan.get(semester);
            for (int j = 0; j < courses.size(); j++) {
                Course course = courses.get(j);
                plannedCourses.add(course);
                List<String> preReqsStr = course.getprereqs();
                for (int k = 0; k < preReqsStr.size(); k++) {
                    assertTrue(plannedCourses.contains(courseCatalog.get(preReqsStr.get(k))));
                }
            }
        }
    }


    @Test
    void differentCreditTest() throws IOException {
        Directory d = new Directory();
        List<Course> allCourses = JsonUtil.listFromJsonFile("src/main/resources/courseCatalog.json", Course.class);
        Map<String, Course> courseCatalog = new HashMap<>();
        for (Course course : allCourses) {
            courseCatalog.put(course.getCourseNum(), course);
        }

        d.setCourseCatalog(courseCatalog);
        Major fakeMajor = new Major();
        fakeMajor.setTitle("Computer Science");
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP17100"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP17200"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP11500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP22000"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP10500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP20500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP21000"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP37500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP11000"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH11100"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH11200"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH21100"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH21600"));

        d.registerStudent("jon", "shmon");
        Student s = d.getStudents().get("jon");
        s.changeMajor(fakeMajor);

        HashMap<String, List<Course>> plan = d.genCoursePlan("jon", Semester.Fall, 2019, 18);
        HashMap<String, List<Course>> plan2 =  d.genCoursePlan("jon", Semester.Fall, 2019, 12);

        assertNotEquals(plan, plan2);
    }

    @Test
    void checkMajorReqsTest() throws IOException{
        Directory d = new Directory();
        List<Course> allCourses = JsonUtil.listFromJsonFile("src/main/resources/courseCatalog.json", Course.class);
        Map<String, Course> courseCatalog = new HashMap<>();
        for (Course course : allCourses) {
            courseCatalog.put(course.getCourseNum(), course);
        }

        d.setCourseCatalog(courseCatalog);
        Major fakeMajor = new Major();
        fakeMajor.setTitle("Computer Science");
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP17100"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP17200"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP11500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP22000"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP10500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP20500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP21000"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP37500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP11000"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH11100"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH11200"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH21100"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH21600"));

        List<Course> coursesPreGeneration = fakeMajor.getRequirements();

        d.registerStudent("jon", "shmon");
        Student s = d.getStudents().get("jon");
        s.changeMajor(fakeMajor);

        HashMap<String, List<Course>> plan = d.genCoursePlan("jon", Semester.Fall, 2019, 18);
        assertEquals(coursesPreGeneration, fakeMajor.getRequirements());
    }
}
