package ithacacollege.comp345.group4.classPlanner.controller;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
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

}
