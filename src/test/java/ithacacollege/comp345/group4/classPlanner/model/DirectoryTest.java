package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DirectoryTest {

    @Test
    void register() {
        Directory directory = new Directory();

        //Add someone to the directory
        assertEquals(0, directory.getStudents().size());
        assertTrue(directory.registerStudent("asdf", "asdf"));
        //Check that user is added to user directory
        assertEquals(1, directory.getStudents().size());
        assertNotNull(directory.getStudents().get("asdf"));

        //Add someone that already exists in directory
        assertFalse(directory.registerStudent("asdf", "fdsa"));

        //Bad Input Checks
        assertThrows(InvalidArgumentException.class, ()-> directory.registerStudent(null, "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> directory.registerStudent("asdf", null));
        assertThrows(InvalidArgumentException.class, ()-> directory.registerStudent("", "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> directory.registerStudent("asdf", ""));
        assertThrows(InvalidArgumentException.class, ()-> directory.registerStudent(null, null));
    }

    @Test
    void login() {
        Directory directory = new Directory();
        directory.registerStudent("asdf", "asdf"); //Populate directory with a student

        assertTrue(directory.loginStudent("asdf", "asdf")); // Should login successfully
        assertFalse(directory.loginStudent("asdf", "badPassword")); // Should fail w/ incorrect pw
        assertFalse(directory.loginStudent("badUsername", "asdf")); // Should fail w/ incorrect user

        //Bad Input Checks
        assertThrows(InvalidArgumentException.class, ()-> directory.registerStudent(null, "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> directory.registerStudent("asdf", null));
        assertThrows(InvalidArgumentException.class, ()-> directory.registerStudent("", "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> directory.registerStudent("asdf", ""));
        assertThrows(InvalidArgumentException.class, ()-> directory.registerStudent(null, null));
    }



    @Test
    public void uploadMajorTest(){
        Directory d = new Directory();
        d.uploadMajor("resources/TestMajorReqs.json");

        Major cs = new Major();
        for(Major m : d.getMajorDirectory()) {
            if (m.title.equals("Computer Science")) {
                cs = m;
            }
        }
        assertEquals(cs.title, "Computer Science");

        //Many of these needed but course constr. not yet implemented:
        assertTrue(cs.requirements.get(0).fulfillsRequirment(new Course()));
        //TODO add one of these assertions for each course req in the JSON file
    }
}