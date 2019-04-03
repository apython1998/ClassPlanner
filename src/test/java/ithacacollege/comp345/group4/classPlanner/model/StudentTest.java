package ithacacollege.comp345.group4.classPlanner.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StudentTest {

    @Test
    void addPastCoursesTest(){
        Student student = new Student("test", "abc", null, null);
        List<Course> courseList = new ArrayList<>();
        Course course1 = new Course("test1", 3.0, "TEST123", "FA17", null);
        Course course2 = new Course("test2", 3.0, "TEST321", "FA17", null);
        Course course3 = new Course("test1", 3.0, "TEST123", "FA17", null);
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);

        student.addCoursesTaken(courseList);

        List<Course> emptyList = new ArrayList<>();

        //check equals
        assertEquals(courseList, student.getCoursesTaken());

        //check not equals
        assertNotEquals(emptyList, student.getCoursesTaken());

        //bad input
        //assertThrows(InvalidArgumentException.class, ()-> student.addCoursesTaken(null));

    }

    @Test
    void addCurrentCoursesTest(){
        Student student = new Student("test", "abc", null, null);
        List<Course> courseList = new ArrayList<>();
        Course course1 = new Course("Software Engineering", 3.0, "COMP345", "FA17", null);
        Course course2 = new Course("Machine Learning", 3.0, "COMP490", "FA17", null);
        Course course3 = new Course("Probability", 3.0, "MATH316", "FA17", null);
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);

        student.addCurrentCourses(courseList);

        List<Course> emptyList = new ArrayList<>();

        //check equals
        assertEquals(courseList, student.getCurrentCourses());

        //check not equals
        assertNotEquals(emptyList, student.getCurrentCourses());

        //bad input
        //assertThrows(InvalidArgumentException.class, ()-> student.addCurrentCourses(null));
    }

    @Test
    void addPlannedCoursesTest(){
        Student student = new Student("test", "abc", null, null);
        List<Course> courseList = new ArrayList<>();
        Course course1 = new Course("test1", 3.0, "TEST123", "FA17", null);
        Course course2 = new Course("test2", 3.0, "TEST321", "FA17", null);
        Course course3 = new Course("test1", 3.0, "TEST123", "FA17", null);
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);

        student.addCoursesPlanned(courseList);

        List<Course> emptyList = new ArrayList<>();

        //check equals
        assertEquals(courseList, student.getCoursesPlanned());

        //check not equals
        assertNotEquals(emptyList, student.getCoursesPlanned());

        //bad input
        //assertThrows(InvalidArgumentException.class, ()-> student.addCoursesPlanned(null));
    }

    @Test
    void viewCoursesTest(){
        Student student = new Student("test", "abc", null, null);
        List<Course> courseList = new ArrayList<>();
        Course course1 = new Course("Software Engineering", 3.0, "COMP345", "FA17", null);
        Course course2 = new Course("Machine Learning", 3.0, "COMP490", "FA17", null);
        Course course3 = new Course("Probability", 3.0, "MATH316", "FA17", null);
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);

        student.addCurrentCourses(courseList);

        List<Course> emptyList = new ArrayList<>();

        System.out.println(student.getCurrentCourses());

        //check equals
        assertEquals(courseList, student.getCurrentCourses());

        //check not equals
        assertNotEquals(emptyList, student.getCurrentCourses());

        //bad input
        //assertThrows(InvalidArgumentException.class, ()-> student.addCoursesPlanned(null));
    }

}
