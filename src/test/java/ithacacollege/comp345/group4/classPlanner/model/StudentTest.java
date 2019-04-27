package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

        courseList.add(course1);

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
    void updateDataTest() {
        Student student = new Student("test", "abc", null, null);
        try {
            Transcript transcript = new Transcript("src/test/resources/exTranscript.json");

            student.setTranscript(transcript);

            assertEquals("COMP17100: Principles of Comp Sci I (4.0)", student.getTakenCourses().get(0).toString());
            assertEquals("COMP17200: Principles of Comp Sci II (4.0)", student.getCurrentCourses().get(0).toString());

            student.setTranscript(transcript);

            assertEquals("COMP17100: Principles of Comp Sci I (4.0)", student.getTakenCourses().get(0).toString());
            assertEquals("COMP17200: Principles of Comp Sci II (4.0)", student.getCurrentCourses().get(0).toString());

            assertThrows(IndexOutOfBoundsException.class, ()-> student.getCurrentCourses().get(1));
            assertThrows(IndexOutOfBoundsException.class, ()-> student.getTakenCourses().get(1));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void addToTranscriptTest() throws IOException {
        Directory d = JsonUtil.fromJsonFile("src/main/resources/savedDirectory.json", Directory.class);

        Student student = new Student("test", "abc", null, null);
        Transcript transcript = new Transcript("src/test/resources/exTranscript.json");

        student.setTranscript(transcript);

        Course course1 = d.getCourseCatalog().get("COMP34500");
        Course course2 = d.getCourseCatalog().get("COMP31100");

        student.addPlannedCourses(course1);
        student.addPlannedCourses(course2);

        student.addToTranscript(course1, "", true, false);
        student.addToTranscript(course2, "", true, false);
        assertFalse(student.addToTranscript(course1, "", true, false));

        for (Course c: student.getPlannedCourses()) {
            assertTrue(!c.equals(course1));
            assertTrue(!c.equals(course2));
        }

        assertThrows(InvalidArgumentException.class, ()-> student.addToTranscript(null, "", true, false));
        assertThrows(InvalidArgumentException.class, ()-> student.addToTranscript(course1, null, true, false));
        assertThrows(InvalidArgumentException.class, ()-> student.addToTranscript(null, null, true, false));
    }

    @Test
    void changeMajorTest() throws IOException  {
        Student student = new Student("test", "abc", null, null);
        List<Major> allMajors = JsonUtil.listFromJsonFile("src/main/resources/majorCatalogWithCourseObjects.json", Major.class);
        HashMap<String, Major> majorCatalog = new HashMap<>();
        for (Major major : allMajors) {
            majorCatalog.put(major.getTitle() + " " + major.getType(), major);
        }

        Major cs = majorCatalog.get("Computer Science Major BS");
        Major physics = majorCatalog.get("Physics Major BS");

        student.changeMajor(cs);

        assertEquals(student.getMajor(), cs);

        student.changeMajor(physics);

        assertNotEquals(student.getMajor(), cs);

        assertEquals(student.getMajor(), physics);
    }


}
