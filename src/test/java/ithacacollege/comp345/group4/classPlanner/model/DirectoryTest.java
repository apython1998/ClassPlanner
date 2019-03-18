package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DirectoryTest {

    @Test
    void register() {
        Directory directory = new Directory();

        //Add someone to the directory
        assertEquals(0, directory.getUsers().size());
        assertTrue(directory.register("asdf", "asdf"));
        //Check that user is added to user directory
        assertEquals(1, directory.getUsers().size());
        assertNotNull(directory.getUsers().get("asdf"));

        //Add someone that already exists in directory
        assertFalse(directory.register("asdf", "fdsa"));

        //Bad Input Checks
        assertThrows(InvalidArgumentException.class, ()-> directory.register(null, "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> directory.register("asdf", null));
        assertThrows(InvalidArgumentException.class, ()-> directory.register("", "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> directory.register("asdf", ""));
        assertThrows(InvalidArgumentException.class, ()-> directory.register(null, null));
    }
}