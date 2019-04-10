package ithacacollege.comp345.group4.classPlanner;

import ithacacollege.comp345.group4.classPlanner.model.Course;
import ithacacollege.comp345.group4.classPlanner.model.Directory;
import ithacacollege.comp345.group4.classPlanner.model.JsonUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SystemStartupIT {

    @Test
    void loadCourseCatalogTest() throws IOException {
        List<Course> allCourses = JsonUtil.listFromJsonFile("src/main/resources/courseCatalog.json", Course.class);
        Map<String, Course> courseCatalog = new HashMap<>();
        for (Course course : allCourses) {
            courseCatalog.put(course.getCourseNum(), course);
        }
        assertEquals(allCourses.size(), courseCatalog.size());  // Make sure that they are the same size

        Directory directory = new Directory();
        directory.setCourseCatalog(courseCatalog);

        // Check that 10% of the courses in the random list exist in the directory
        for (int i=0; i<(allCourses.size()/10); i++) {
            Random rand = new Random();
            Course randomCourse = allCourses.get(rand.nextInt(allCourses.size()));
            assertNotNull(directory.getCourseCatalog().get(randomCourse.getCourseNum()));
        }


    }

}
