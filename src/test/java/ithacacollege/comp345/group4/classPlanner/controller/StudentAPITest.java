package ithacacollege.comp345.group4.classPlanner.controller;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import ithacacollege.comp345.group4.classPlanner.model.Course;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentAPITest {

    @Test
    void register() {
        StudentAPI studentAPI = new StudentAPI();

        //Check that registration works correctly
        assertTrue(studentAPI.register("asdf", "asdf"));

        //Check that you cant have two accounts with the same username
        assertFalse(studentAPI.register("asdf", "asdf"));

        //Bad Input Checks
        assertThrows(InvalidArgumentException.class, ()-> studentAPI.register(null, "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> studentAPI.register("asdf", null));
        assertThrows(InvalidArgumentException.class, ()-> studentAPI.register("", "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> studentAPI.register("asdf", ""));
        assertThrows(InvalidArgumentException.class, ()-> studentAPI.register(null, null));
    }

    @Test
    void viewCourses(){
        StudentAPI studentAPI = new StudentAPI();

        studentAPI.register("asdf", "asdf");

        Course course1 = new Course("Software Engineering", 12345, 3.0, "COMP345", "FA17", null);

        studentAPI.addCurrentCourse("asdf", course1);

        System.out.println(studentAPI.viewCurrentCourses("asdf"));
    }

}
