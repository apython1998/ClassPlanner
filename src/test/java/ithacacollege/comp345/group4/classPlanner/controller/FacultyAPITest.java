package ithacacollege.comp345.group4.classPlanner.controller;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import ithacacollege.comp345.group4.classPlanner.model.Faculty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FacultyAPITest {

    @Test
    void registerTest() {
        FacultyAPI facultyAPI = new FacultyAPI();

        //Check that registration works correctly
        assertTrue(facultyAPI.register("asdf", "asdf"));

        //Check that you cant have two accounts with the same username
        assertFalse(facultyAPI.register("asdf", "asdf"));

        //Bad Input Checks
        assertThrows(InvalidArgumentException.class, ()-> facultyAPI.register(null, "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> facultyAPI.register("asdf", null));
        assertThrows(InvalidArgumentException.class, ()-> facultyAPI.register("", "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> facultyAPI.register("asdf", ""));
        assertThrows(InvalidArgumentException.class, ()-> facultyAPI.register(null, null));
    }

    @Test
    void loginTest() {
        FacultyAPI facultyAPI = new FacultyAPI();
        facultyAPI.register("username", "password"); // Create a user account

        Faculty loginAttempt = facultyAPI.login("username", "password");
        assertNotNull(loginAttempt); // Check that login returns correct object

        loginAttempt = facultyAPI.login("username", "badpassword"); // Check for failed login attempt with bad password
        assertNull(loginAttempt);

        loginAttempt = facultyAPI.login("badusername", "password"); // Check for failed login attempt with bad username
        assertNull(loginAttempt);

        //Bad Input Checks
        assertThrows(InvalidArgumentException.class, ()-> facultyAPI.login(null, "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> facultyAPI.login("asdf", null));
        assertThrows(InvalidArgumentException.class, ()-> facultyAPI.login("", "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> facultyAPI.login("asdf", ""));
        assertThrows(InvalidArgumentException.class, ()-> facultyAPI.login(null, null));
    }

}
