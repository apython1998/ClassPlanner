package ithacacollege.comp345.group4.classPlanner.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    void addPastCoursesTest(){
        Student student = new Student("test", "abc", null, null);

        Course course1 = new Course("test1", 3.0, "TEST123", null, null, null, null);
        Course course2 = new Course("test2", 3.0, "TEST321", null, null, null, null);


        assertTrue(student.addTakenCourses(course1)); //adding 1st course
        assertFalse(student.addTakenCourses(course1)); //adding the course again should not work
        //check equals
        //assertEquals(courseList, student.getTakenCourses());

        //check not equals
        //assertNotEquals(emptyList, student.getTakenCourses());
        assertTrue(student.addTakenCourses(course2));
        assertFalse(student.addTakenCourses(course2)); //adding the course again should not work

        //bad input
        //assertThrows(InvalidArgumentException.class, ()-> student.addCoursesTaken(null));

    }

    @Test
    void addCurrentCoursesTest(){
        Student student = new Student("test", "abc", null, null);
        List<Course> courseList = new ArrayList<>();
        Course course1 = new Course("Software Engineering", 3.0, "COMP345", null, null, null, null);
        Course course2 = new Course("Machine Learning", 3.0, "COMP490", null, null, null, null);

        assertTrue(student.addCurrentCourses(course1)); //adding 1st course
        assertFalse(student.addCurrentCourses(course1)); //adding the course again should not work

        assertTrue(student.addCurrentCourses(course2));
        assertFalse(student.addCurrentCourses(course2)); //adding the course again should not work

        //bad input
        //assertThrows(InvalidArgumentException.class, ()-> student.addCurrentCourses(null));
    }

    @Test
    void addPlannedCoursesTest(){
        Student student = new Student("test", "abc", null, null);
        List<Course> courseList = new ArrayList<>();
        Course course1 = new Course("test1", 3.0, "TEST123", null, null, null, null);
        Course course2 = new Course("test2", 3.0, "TEST321", null, null, null, null);

        assertTrue(student.addPlannedCourses(course1)); //adding 1st course
        assertFalse(student.addPlannedCourses(course1)); //adding the course again should not work
        //check equals
        assertEquals(courseList, student.getPlannedCourses());

        //check not equals
        //assertNotEquals(emptyList, student.getPlannedCourses());
        assertTrue(student.addPlannedCourses(course2));
        assertFalse(student.addPlannedCourses(course2)); //adding the course again should not work

        //bad input
        //assertThrows(InvalidArgumentException.class, ()-> student.addCoursesPlanned(null));
    }

    @Test
    void viewCoursesTest() {
        Student student = new Student("test", "abc", null, null);
        Course course1 = new Course("Software Engineering", 3.0, "COMP345", null, null, null, null);
        Course course2 = new Course("Machine Learning", 3.0, "COMP490", null, null, null, null);

        assertTrue(student.addCurrentCourses(course1)); //adding 1st course
        assertFalse(student.addCurrentCourses(course1)); //adding the course again should not work

        assertTrue(student.addCurrentCourses(course2));
        assertFalse(student.addCurrentCourses(course2)); //adding the course again should not work

        System.out.println(student.getCurrentCourses());

        //bad input
        //assertThrows(InvalidArgumentException.class, ()-> student.addCoursesPlanned(null));
    }

    @Test
    void genPlanTest(){
        //TODO
    }

}
