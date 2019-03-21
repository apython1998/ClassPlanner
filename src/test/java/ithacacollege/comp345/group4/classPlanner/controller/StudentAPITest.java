package ithacacollege.comp345.group4.classPlanner.controller;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import ithacacollege.comp345.group4.classPlanner.model.Student;
import org.junit.jupiter.api.Test;

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

}
