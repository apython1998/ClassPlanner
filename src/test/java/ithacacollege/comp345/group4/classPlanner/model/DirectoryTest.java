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
    void loginStudentTest() {
        Directory directory = new Directory();
        directory.registerStudent("asdf", "asdf"); //Populate directory with a student

        assertNotNull(directory.loginStudent("asdf", "asdf")); // Should login successfully
        assertNull(directory.loginStudent("asdf", "badPassword")); // Should fail w/ incorrect pw
        assertNull(directory.loginStudent("badUsername", "asdf")); // Should fail w/ incorrect user

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
        d.uploadMajor("src/test/resources/TestMajorReqs.json");

        Major cs = d.getMajorDirectory().get("Computer Science");

        assertNotNull(cs);

        Course c = new Course();
        c.setCourseNum("COMP 11500");
        assertTrue(cs.requirements.get(0).fulfillsRequirment(c));
        c.setCourseNum("COMP 17100");
        assertTrue(cs.requirements.get(1).fulfillsRequirment(c));
        c.setCourseNum("COMP 32100");
        assertTrue(cs.requirements.get(2).fulfillsRequirment(c));
        c.setCourseNum("ITAL 10100");
        assertFalse(cs.requirements.get(2).fulfillsRequirment(c));
    }

    @Test
    void viewCurrentCourses(){
        Directory directory = new Directory();
        directory.registerStudent("asdf", "asdf");

        Course course1 = new Course("Software Engineering", 3.0, "COMP345", null, null, null, null);

        directory.addCurrentCourse("asdf", course1);

        System.out.println(directory.viewCurrentCourses("asdf"));
    }
}