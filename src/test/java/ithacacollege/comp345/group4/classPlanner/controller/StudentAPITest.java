package ithacacollege.comp345.group4.classPlanner.controller;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import ithacacollege.comp345.group4.classPlanner.model.Course;
import ithacacollege.comp345.group4.classPlanner.model.Directory;
import ithacacollege.comp345.group4.classPlanner.model.Student;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class StudentAPITest {

    @Test
    void registerTest() {
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
    void viewCourses() {
        StudentAPI studentAPI = new StudentAPI();

        studentAPI.register("asdf", "asdf");

        Course course1 = new Course("Software Engineering", 3.0, "COMP345", null, null, null, null);

        studentAPI.addCurrentCourse("asdf", course1);

        System.out.println(studentAPI.viewCurrentCourses("asdf"));
    }

    void loginTest() {
        StudentAPI studentAPI = new StudentAPI();
        studentAPI.register("username", "password"); // Create a user account

        Student loginAttempt = studentAPI.login("username", "password");
        assertNotNull(loginAttempt); // Check that login returns correct object

        loginAttempt = studentAPI.login("username", "badpassword"); // Check for failed login attempt with bad password
        assertNull(loginAttempt);

        loginAttempt = studentAPI.login("badusername", "password"); // Check for failed login attempt with bad username
        assertNull(loginAttempt);

        //Bad Input Checks
        assertThrows(InvalidArgumentException.class, ()-> studentAPI.login(null, "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> studentAPI.login("asdf", null));
        assertThrows(InvalidArgumentException.class, ()-> studentAPI.login("", "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> studentAPI.login("asdf", ""));
        assertThrows(InvalidArgumentException.class, ()-> studentAPI.login(null, null));
    }

    @Test
    void setStudentMajorTest(){
        Directory d = new Directory();
        d.uploadMajor("src/test/resources/TestMajorReqs.json");
        StudentAPI studentAPI = new StudentAPI(d);
        studentAPI.register("username", "password");

        studentAPI.setStudentMajor("username", "Computer Science");
        assertEquals(d.getStudents().get("username").getMajor(), d.getMajorDirectory().get("Computer Science"));

        assertThrows(InvalidArgumentException.class, ()-> studentAPI.setStudentMajor("username", "Pole Vaulting"));
        assertThrows(InvalidArgumentException.class, ()-> studentAPI.setStudentMajor("wrong name", "Computer Science"));
    }

    @Test
    void viewMajorRequirementsTest(){
        Directory d = new Directory();
        d.uploadMajor("src/test/resources/TestMajorReqs.json");
        StudentAPI studentAPI = new StudentAPI(d);
        studentAPI.register("username", "password");
        studentAPI.setStudentMajor("username", "Computer Science");


        assertEquals(studentAPI.viewMajorRequirements("Computer Science"), d.getMajorDirectory().get("Computer Science").getRequirements());
        assertThrows(InvalidArgumentException.class, ()-> studentAPI.viewMajorRequirements("Gator Wrangling"));
    }

    @Test
    void validateMajorTest(){
        Directory d = new Directory();
        d.uploadMajor("src/test/resources/TestMajorReqs.json");
        StudentAPI studentAPI = new StudentAPI(d);

        assertTrue(studentAPI.validateMajor("Computer Science"));
        assertFalse(studentAPI.validateMajor("Wine Tasting"));
    }

    @Test
    void uploadTranscript() {
        Directory d = new Directory();
        d.registerStudent("dmccaffrey", "abcdef");
        StudentAPI api = new StudentAPI(d);

        api.uploadTranscript("dmccaffrey", "src/test/resources/exTranscript");
        assertEquals("Completed\tCOMP17100\tPrinciples of Comp Sci I\tA\t4.0\nIn Progress\tCOMP17200\tPrinciples of Comp Sci II\t\t4.0", d.getStudents().get("dmccaffrey").getTranscript().toString());
        assertThrows(InvalidArgumentException.class, ()-> api.uploadTranscript("apython", "src/test/resources/exTranscript"));
    }

}
