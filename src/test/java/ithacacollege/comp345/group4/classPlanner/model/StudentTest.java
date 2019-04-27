package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import javax.management.InstanceAlreadyExistsException;
import java.io.IOException;
import java.util.ArrayList;
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
    void addFriendsTest() {
        Student student1 = new Student("dmccaffrey", "123450", null, null);
        Student student2 = new Student("apython", "asdf", null, null);

        //student2 should get a friend request
        student1.addFriend(student2.getUsername());
        assertEquals("dmccaffrey", student2.getFriendRequestList().get(0));

        //throws when request is pending
        assertThrows(InvalidArgumentException.class, ()-> student1.addFriend(student2.getUsername()));

        //both students should be on each others friend list
        student2.addFriend(student1.getUsername());
        assertEquals("apython", student1.getFriendsList().get(0));
        assertEquals("dmccaffrey", student2.getFriendsList().get(0));

        //throws when students are already friends
        assertThrows(InstanceAlreadyExistsException.class, ()-> student2.addFriend(student1.getUsername()));

        //throws when student tries to friend themself
        assertThrows(IllegalArgumentException.class, ()-> student1.addFriend(student1.getUsername()));
    }

    @Test
    void acceptFriendRequestTest() {
        Student student1 = new Student("dmccaffrey", "123450", null, null);
        Student student2 = new Student("apython", "asdf", null, null);

        //both students should be friends
        student1.addFriend(student2.getUsername());
        student2.acceptFriendRequest(student1.getUsername(), true);
        assertEquals("dmccaffrey", student2.getFriendsList().get(0));
        assertEquals("apython", student1.getFriendsList().get(0));

        //student3 should be deleted from student1 friend request list
        Student student3 = new Student("dshane", "qwerty", null, null);
        student3.addFriend(student1.getUsername());
        student1.acceptFriendRequest(student3.getUsername(), false);
        assertTrue(student1.getFriendRequestList().isEmpty());

        //throws when student is not in friend request list
        assertThrows(InvalidArgumentException.class, ()-> student1.acceptFriendRequest("jcleveland", true));
    }

    @Test
    void friendRequestListToStringTest() {
        Student student1 = new Student("dmccaffrey", "123450", null, null);
        Student student2 = new Student("apython", "123450", null, null);
        Student student3 = new Student("dshane", "123450", null, null);
        Student student4 = new Student("jcleveland", "123450", null, null);

        student2.addFriend(student1.getUsername());
        student3.addFriend(student1.getUsername());
        student4.addFriend(student1.getUsername());

        assertEquals("apython\ndshane\njcleveland\n", student1.friendRequestListToString());
    }

    @Test
    void friendsListToString() {
        Student student1 = new Student("dmccaffrey", "123450", null, null);
        Student student2 = new Student("apython", "123450", null, null);
        Student student3 = new Student("dshane", "123450", null, null);
        Student student4 = new Student("jcleveland", "123450", null, null);

        student2.addFriend(student1.getUsername());
        student3.addFriend(student1.getUsername());
        student4.addFriend(student1.getUsername());

        student1.acceptFriendRequest(student2.getUsername(), true);
        student1.acceptFriendRequest(student3.getUsername(), true);
        student1.acceptFriendRequest(student4.getUsername(), true);

        assertEquals("apython\ndshane\njcleveland\n", student1.friendRequestListToString());
    }


}
