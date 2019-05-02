package ithacacollege.comp345.group4.classPlanner;


import ithacacollege.comp345.group4.classPlanner.model.Directory;
import ithacacollege.comp345.group4.classPlanner.model.JsonUtil;
import ithacacollege.comp345.group4.classPlanner.ui.CombinedUI;

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
////        Map<String, List<Section>> sectionCatalog = new HashMap<>();
////        String courseTimes1 = "MWF 9:00-9:50,TR 8:00-9:15";
////        String courseTimes2 = "MWF 10:00-10:50,TR 9:25-10:40";
////        String courseTimes3 = "MWF 11:00-11:50,TR 10:50-12:05";
////        String courseTimes4 = "MWF 12:00-12:50,TR 2:35-3:50";
////        for (Course course : allCourses) {
////            List<Section> thisSectionList = new ArrayList<>();
////            courseCatalog.put(course.getCourseNum(), course);
////            Section s1 = new Section(course, 1, "24601", "F2019", courseTimes1);
////            Section s2 = new Section(course, 2, "24601", "F2019", courseTimes2);
////            Section s3 = new Section(course, 3, "24601", "F2019", courseTimes3);
////            Section s4 = new Section(course, 4, "24601", "F2019", courseTimes4);
////
////            thisSectionList.add(s1);
////            thisSectionList.add(s2);
////            thisSectionList.add(s3);
////            thisSectionList.add(s4);
////
////            sectionCatalog.put(course.getCourseNum(), thisSectionList);
////        }
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
        CombinedUI combinedUI = new CombinedUI(directory);
        combinedUI.run();
        JsonUtil.toJsonFile("src/main/resources/savedDirectory.json", directory);
        System.out.println("Saving System....");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Goodbye!");
    }

}
