package ithacacollege.comp345.group4.classPlanner;

import ithacacollege.comp345.group4.classPlanner.controller.StudentAPI;
import ithacacollege.comp345.group4.classPlanner.model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class SystemStartupIT {

    @Test
    void loadCourseCatalogTest() throws IOException {
        List<Course> allCourses = JsonUtil.listFromJsonFile("src/main/resources/courseCatalog.json", Course.class);
        Map<String, Course> courseCatalog = new HashMap<>();
        for (Course course : allCourses) {
            courseCatalog.put(course.getCourseNum(), course);
        }
        // Make sure that they are the same size
        assertEquals(allCourses.size(), courseCatalog.size());

        Directory directory = new Directory();
        directory.setCourseCatalog(courseCatalog);

        // Check that Random 10% of the courses in the random list exist in the directory
        Random rand = new Random();
        for (int i=0; i<(allCourses.size()/10); i++) {
            Course randomCourse = allCourses.get(rand.nextInt(allCourses.size()));
            assertNotNull(directory.getCourseCatalog().get(randomCourse.getCourseNum()));
        }
    }

    @Test
    void loadMajorCatalogTest() throws IOException {
        List<Major> allMajors = JsonUtil.listFromJsonFile("src/main/resources/majorCatalogWithCourseObjects.json", Major.class);
        Map<String, Major> majorCatalog = new HashMap<>();
        int repeats = 0;
        for (Major major : allMajors) {
            if (majorCatalog.containsKey(major.getTitle() + " " + major.getType())) {
                repeats++;
            }
            majorCatalog.put(major.getTitle() + " " + major.getType(), major);
        }
        // They are the same size but minus repeats which are document
        assertEquals(allMajors.size()-repeats, majorCatalog.size());

        Directory directory = new Directory();
        directory.setMajorDirectory(majorCatalog);

        // Check that Random 10% of the courses in the random list exist in the directory
        Random rand = new Random();
        for (int i=0; i<(allMajors.size()/10); i++) {
            Major randomMajor = allMajors.get(rand.nextInt(allMajors.size()));
            assertNotNull(directory.getMajorDirectory().get(randomMajor.getTitle() + " " + randomMajor.getType()));
        }
    }

    @Test
    void registerAndAddMajorTest() throws IOException {
        List<Major> allMajors = JsonUtil.listFromJsonFile("src/main/resources/majorCatalogWithCourseObjects.json", Major.class);
        Map<String, Major> majorCatalog = new HashMap<>();
        for (Major major : allMajors) {
            majorCatalog.put(major.getTitle() + " " + major.getType(), major);
        }

        Directory directory = new Directory();
        directory.setMajorDirectory(majorCatalog);
        StudentAPI studentAPI = new StudentAPI(directory);
        studentAPI.register("asdf", "asdf");
        Student student = studentAPI.login("asdf", "asdf");

        // Student didn't add major yet
        assertNull(student.getMajor());

        // Student tries to add a bad major
        assertThrows(InvalidArgumentException.class, ()-> studentAPI.setStudentMajor(student.getUsername(), "Not a Major BA"));

        // Add a good major (the first in the catalog)
        studentAPI.setStudentMajor(student.getUsername(), majorCatalog.keySet().toArray()[0].toString());
        assertNotNull(student.getMajor());
    }

}
