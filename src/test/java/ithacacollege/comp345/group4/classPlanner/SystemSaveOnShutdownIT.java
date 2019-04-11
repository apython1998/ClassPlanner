package ithacacollege.comp345.group4.classPlanner;

import ithacacollege.comp345.group4.classPlanner.controller.StudentAPI;
import ithacacollege.comp345.group4.classPlanner.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SystemSaveOnShutdownIT {

    Map<String, Course> courseCatalog;
    Map<String, Major> majorCatalog;

    @BeforeEach
    void setup() throws IOException {
        List<Course> allCourses = JsonUtil.listFromJsonFile("src/main/resources/courseCatalog.json", Course.class);
        courseCatalog = new HashMap<>();
        for (Course course : allCourses) {
            courseCatalog.put(course.getCourseNum(), course);
        }
        /**
         * Load the Majors from a JSON file using JSONUtil
         */
        List<Major> allMajors = JsonUtil.listFromJsonFile("src/main/resources/majorCatalogWithCourseObjects.json", Major.class);
        majorCatalog = new HashMap<>();
        for (Major major : allMajors) {
            majorCatalog.put(major.getTitle() + " " + major.getType(), major);
        }
    }


    @Test
    void directorySaveOnShutdownTest() throws IOException {
        String username = "asdf";
        String password = "asdf";
        String majorString = "Computer Science Major BA";
        // MOCK STARTUP
        Directory directory = new Directory();
        directory.setCourseCatalog(courseCatalog);
        directory.setMajorDirectory(majorCatalog);
        StudentAPI studentAPI = new StudentAPI(directory);
        studentAPI.register(username, password);                     // Register a student
        Student newStudent = studentAPI.login(username, password);   // Hold on to that student
        studentAPI.setStudentMajor(newStudent.getUsername(), majorString);
        assertNotNull(newStudent.getMajor());                                       // Make sure that student has major now
        // MOCK SHUTDOWN
        JsonUtil.toJsonFile("src/test/resources/testSavedDirectory.json", directory);
        // MOCK STARTUP
        Directory savedDirectory = JsonUtil.fromJsonFile("src/test/resources/testSavedDirectory.json", Directory.class);
        StudentAPI savedStudentAPI = new StudentAPI(savedDirectory);
        Student savedStudent = savedStudentAPI.login(username, password);
        assertEquals(directory.getCourseCatalog().size(), savedDirectory.getCourseCatalog().size());
        assertEquals(directory.getMajorDirectory().size(), savedDirectory.getMajorDirectory().size());
        assertEquals(directory.getStudents().size(), savedDirectory.getStudents().size());
        assertEquals(directory.getSectionCatalog().size(), savedDirectory.getSectionCatalog().size());

        assertNotNull(savedStudent);
        assertNotNull(savedStudent.getMajor());
        assertEquals(majorString, savedStudent.getMajor().getTitle() + " " + savedStudent.getMajor().getType());

        // TEST BAD INPUT ON THE SAVED SYSTEM
        assertNull(savedStudentAPI.login("badUsername", "asdf"));
    }

}
