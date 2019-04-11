package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    void viewCurrentCourses(){
        Directory directory = new Directory();
        directory.registerStudent("asdf", "asdf");

        Course course1 = new Course("Software Engineering", 3.0, "COMP345", null, null, null, null);

        directory.addCurrentCourse("asdf", course1);

        System.out.println(directory.viewCurrentCourses("asdf"));
    }

    @Test
    void genCoursePlanTest() throws IOException {
        Directory d = new Directory();
        List<Course> allCourses = JsonUtil.listFromJsonFile("src/main/resources/courseCatalog.json", Course.class);
        Map<String, Course> courseCatalog = new HashMap<>();
        for (Course course : allCourses) {
            courseCatalog.put(course.getCourseNum(), course);
        }

        d.setCourseCatalog(courseCatalog);
        Major fakeMajor = new Major();
        fakeMajor.setTitle("Computer Science");
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP17100"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP17200"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP11500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP22000"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP10500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP20500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP21000"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP37500"));
        fakeMajor.addCourse(d.getCourseCatalog().get("COMP11000"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH11100"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH11200"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH21100"));
        fakeMajor.addCourse(d.getCourseCatalog().get("MATH21600"));

        d.registerStudent("jon", "shmon");
        Student s = d.getStudents().get("jon");
        s.changeMajor(fakeMajor);
        HashMap<String, List<Course>> plan = d.genCoursePlan("jon", Semester.Fall, 2019, 15);
        String planStr = d.scheduleToStr(plan);
        System.out.println(planStr);

    }
}