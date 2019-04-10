package ithacacollege.comp345.group4.classPlanner;


import ithacacollege.comp345.group4.classPlanner.controller.StudentAPI;
import ithacacollege.comp345.group4.classPlanner.model.Course;
import ithacacollege.comp345.group4.classPlanner.model.Directory;
import ithacacollege.comp345.group4.classPlanner.model.JsonUtil;
import ithacacollege.comp345.group4.classPlanner.model.Major;
import ithacacollege.comp345.group4.classPlanner.ui.StudentUI;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassPlannerMain {

    /**
     * Main to run ClassPlanner
     * @param args
     */
    public static void main(String[] args) throws IOException {
        /**
         * Load the majors from a JSON file using JSONUtil
         */
        List<Course> allCourses = JsonUtil.listFromJsonFile("src/main/resources/courseCatalog.json", Course.class);
        Map<String, Course> courseCatalog = new HashMap<>();
        for (Course course : allCourses) {
            courseCatalog.put(course.getCourseNum(), course);
        }
        List<Major> allMajors = JsonUtil.listFromJsonFile("src/main/resources/majorCatalog.json", Major.class);
        Directory directory = new Directory();
        directory.setCourseCatalog(courseCatalog);
        //Preload major JSON:
        directory.uploadMajor("src/test/resources/TestMajorReqs.json");
        StudentAPI studentAPI = new StudentAPI(directory);
        StudentUI studentUI = new StudentUI(studentAPI);
        studentUI.run();
    }

}
