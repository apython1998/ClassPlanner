package ithacacollege.comp345.group4.classPlanner;


import com.fasterxml.jackson.databind.ObjectMapper;
import ithacacollege.comp345.group4.classPlanner.controller.StudentAPI;
import ithacacollege.comp345.group4.classPlanner.model.*;
import ithacacollege.comp345.group4.classPlanner.ui.StudentUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassPlannerMain {

    /**
     * Main to run ClassPlanner
     * @param args
     */
    public static void main(String[] args) throws IOException {
//        /**
//         * Load the Courses from a JSON file using JSONUtil
//         */
//        List<Course> allCourses = JsonUtil.listFromJsonFile("src/main/resources/courseCatalog.json", Course.class);
//        Map<String, Course> courseCatalog = new HashMap<>();
//        for (Course course : allCourses) {
//            courseCatalog.put(course.getCourseNum(), course);
//        }
//        /**
//         * Load the Sections from a JSON file using JSON Util
//         */
//        List<Section> allSections = JsonUtil.listFromJsonFile("src/main/resources/sectionsCatalog.json", Section.class);
//        Map<String, List<Section>> sectionCatalog = new HashMap<>();
//        List<Section> list1 = allSections.subList(0, 2);
//        List<Section> list2 = new ArrayList<>();
//        Course course1 = courseCatalog.get("COMP11500");
//        for (Section s: list1) {
//            list2.add(s.setCourse(course1));
//        }
//        sectionCatalog.put("COMP11500", list2);
//
//        list1 = allSections.subList(2, 4);
//        List<Section> list3 = new ArrayList<>();
//        Course course2 = courseCatalog.get("MATH14400");
//        for (Section s: list1) {
//            list3.add(s.setCourse(course2));
//        }
//        sectionCatalog.put("MATH14400", list3);
//
//        list1 = allSections.subList(4, 6);
//        List<Section> list4 = new ArrayList<>();
//        Course course3 = courseCatalog.get("MATH11100");
//        for (Section s: list1) {
//            list3.add(s.setCourse(course3));
//        }
//        sectionCatalog.put("MATH11100", list4);
//
//
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
//        directory.setSectionCatalog(sectionCatalog);
        Directory directory = JsonUtil.fromJsonFile("src/main/resources/savedDirectory.json", Directory.class);
        StudentAPI studentAPI = new StudentAPI(directory);
        StudentUI studentUI = new StudentUI(studentAPI);
        studentUI.run();
        JsonUtil.toJsonFile("src/main/resources/savedDirectory.json", directory);
    }

}
