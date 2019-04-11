package ithacacollege.comp345.group4.classPlanner;


import ithacacollege.comp345.group4.classPlanner.controller.StudentAPI;
import ithacacollege.comp345.group4.classPlanner.model.Directory;
import ithacacollege.comp345.group4.classPlanner.model.JsonUtil;
import ithacacollege.comp345.group4.classPlanner.ui.StudentUI;

import java.util.concurrent.TimeUnit;

public class ClassPlannerMain {

    /**
     * Main to run ClassPlanner
     * @param args
     */
    public static void main(String[] args) throws Exception {
//        /**
//         * Load the Courses from a JSON file using JSONUtil
//         */
//        List<Course> allCourses = JsonUtil.listFromJsonFile("src/main/resources/courseCatalog.json", Course.class);
//        Map<String, Course> courseCatalog = new HashMap<>();
//        for (Course course : allCourses) {
//            courseCatalog.put(course.getCourseNum(), course);
//        }
//        /**
//         * Load the Majors from a JSON file using JSONUtil
//         */
//        List<Major> allMajors = JsonUtil.listFromJsonFile("src/main/resources/majorCatalogWithCourseObjects.json", Major.class);
//        Map<String, Major> majorCatalog = new HashMap<>();
//        for (Major major : allMajors) {
//            majorCatalog.put(major.getTitle() + " " + major.getType(), major);
//        }
//        Directory directory = new Directory();
//        directory.setCourseCatalog(courseCatalog);
//        directory.setMajorDirectory(majorCatalog);
//        Preload major JSON:
//        directory.uploadMajor("src/test/resources/TestMajorReqs.json");
        Directory directory = JsonUtil.fromJsonFile("src/main/resources/savedDirectory.json", Directory.class);
        StudentAPI studentAPI = new StudentAPI(directory);
        StudentUI studentUI = new StudentUI(studentAPI);
        studentUI.run();
        JsonUtil.toJsonFile("src/main/resources/savedDirectory.json", directory);
        System.out.println("Saving System....");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Goodbye!");
    }

}
