package ithacacollege.comp345.group4.classPlanner;

import ithacacollege.comp345.group4.classPlanner.model.Course;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    void addPastCoursesTest(){
        Student student = new Student(12345678, null, null);
        List<Course> courseList = new ArrayList<>();
        Course course1 = new Course("test1", 12345, 3.0, "TEST123", "FA17", null);
        Course course2 = new Course("test2", 13465, 3.0, "TEST321", "FA17", null);
        Course course3 = new Course("test1", 12345, 3.0, "TEST123", "FA17", null);
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);

        student.addCoursesTaken(courseList);

        List<Course> emptyList = new ArrayList<>();

        assertEquals(courseList, student.getCoursesTaken());
        assertNotEquals(emptyList, student.getCoursesTaken());
    }

    @Test
    void addCurrentCoursesTest(){
        Student student = new Student(12345678, null, null);
        List<Course> courseList = new ArrayList<>();
        Course course1 = new Course("test1", 12345, 3.0, "TEST123", "FA17", null);
        Course course2 = new Course("test2", 13465, 3.0, "TEST321", "FA17", null);
        Course course3 = new Course("test1", 12345, 3.0, "TEST123", "FA17", null);
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);

        student.addCurrentCourses(courseList);

        List<Course> emptyList = new ArrayList<>();

        assertEquals(courseList, student.getCurrentCourses());
        assertNotEquals(emptyList, student.getCurrentCourses());
    }

    @Test
    void addPlannedCoursesTest(){
        Student student = new Student(12345678, null, null);
        List<Course> courseList = new ArrayList<>();
        Course course1 = new Course("test1", 12345, 3.0, "TEST123", "FA17", null);
        Course course2 = new Course("test2", 13465, 3.0, "TEST321", "FA17", null);
        Course course3 = new Course("test1", 12345, 3.0, "TEST123", "FA17", null);
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);

        student.addCoursesPlanned(courseList);

        List<Course> emptyList = new ArrayList<>();

        assertEquals(courseList, student.getCoursesPlanned());
        assertNotEquals(emptyList, student.getCoursesPlanned());
    }

}
