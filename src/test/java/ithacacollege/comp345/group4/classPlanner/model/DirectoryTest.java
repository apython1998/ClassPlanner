package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DirectoryTest {

    @Test
    void registerStudent() {
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
        assertThrows(InvalidArgumentException.class, ()-> directory.loginStudent(null, "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> directory.loginStudent("asdf", null));
        assertThrows(InvalidArgumentException.class, ()-> directory.loginStudent("", "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> directory.loginStudent("asdf", ""));
        assertThrows(InvalidArgumentException.class, ()-> directory.loginStudent(null, null));
    }

    @Test
    void registerFaculty() {
        Directory directory = new Directory();

        assertEquals(0, directory.getFaculty().size());
        assertTrue(directory.registerFaculty("faculty", "asdf"));

        //Check that faculty is added
        assertEquals(1, directory.getFaculty().size());

        //Make sure you cant add same person twice
        assertThrows(InvalidArgumentException.class, ()-> directory.registerFaculty(null, "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> directory.registerFaculty("asdf", null));
        assertThrows(InvalidArgumentException.class, ()-> directory.registerFaculty("", "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> directory.registerFaculty("asdf", ""));
        assertThrows(InvalidArgumentException.class, ()-> directory.registerFaculty(null, null));
    }

    @Test
    void loginFaculty() {
        Directory directory = new Directory();
        directory.registerFaculty("faculty", "asdf"); //Populate directory with a student

        assertNotNull(directory.loginFaculty("faculty", "asdf")); // Should login successfully
        assertNull(directory.loginFaculty("faculty", "badPassword")); // Should fail w/ incorrect pw
        assertNull(directory.loginFaculty("badUsername", "asdf")); // Should fail w/ incorrect user

        //Bad Input Checks
        assertThrows(InvalidArgumentException.class, ()-> directory.loginFaculty(null, "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> directory.loginFaculty("asdf", null));
        assertThrows(InvalidArgumentException.class, ()-> directory.loginFaculty("", "asdf"));
        assertThrows(InvalidArgumentException.class, ()-> directory.loginFaculty("asdf", ""));
        assertThrows(InvalidArgumentException.class, ()-> directory.loginFaculty(null, null));
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
        HashMap<String, List<Course>> plan = d.genCoursePlan("jon", Semester.Fall, 2019, 15, new ArrayList<Course>());
        String planStr = d.scheduleToStr(plan);
        System.out.println(planStr);

    }

    @Test
    public void searchMajorReqsTest() throws IOException {
        Directory d = JsonUtil.fromJsonFile("src/main/resources/savedDirectory.json", Directory.class);

        d.registerStudent("greg", "pegleg");
        Student s = d.getStudents().get("greg");
        s.changeMajor(d.getMajorDirectory().get("Computer Science Major BS"));
        s.addTakenCourses(d.getCourseCatalog().get("MATH11100"));
        s.addTakenCourses(d.getCourseCatalog().get("MATH11200"));
        s.addTakenCourses(d.getCourseCatalog().get("COMP22000"));
        List<Course> reqs = d.searchMajorReqs("greg", "Mathematics Major BS");
        System.out.println(reqs);
        boolean hasLinAlg = false;
        for (Course c : reqs) {
            assertNotEquals(c.getCourseNum(), "MATH11100");//A class they should have, in both majors
            assertNotEquals(c.getCourseNum(), "MATH11200");//Another class they should have in both majors
            assertNotEquals(c.getCourseNum(), "COMP22000");//A class they shouldn't need but have taken
            assertNotEquals(c.getCourseNum(), "COMP31100");//A class they shouldn't need and has not taken
            if (c.getCourseNum().equals("MATH23100"))//A class that should be in the new list of reqs
                hasLinAlg = true;
        }
        assertTrue(hasLinAlg);
    }

    void addFriendTest() {
        Directory d = new Directory();
        d.registerStudent("dmccaffrey", "asdf");
        d.registerStudent("apython", "asdf");

        d.addFriend("dmccaffrey", "apython");

        assertEquals("dmccaffrey", d.getStudents().get("apython").getFriendRequestList().get(0));

        assertThrows(NoSuchElementException.class, ()-> d.addFriend("dmccaffrey", "dshane"));
        assertThrows(NoSuchElementException.class, ()-> d.addFriend("dshane", "dmccaffrey"));

    }

    @Test
    void getFriendsScheduleTest() throws IOException {
        Directory d = new Directory();
        List<Course> allCourses = JsonUtil.listFromJsonFile("src/main/resources/courseCatalog.json", Course.class);
        Map<String, Course> courseMap = new HashMap<>();
        Map<String, List<Section>> sectionMap = new HashMap<>();

        String courseTimes1 = "MWF 9:00-9:50,TR 8:00-9:15";
        String courseTimes2 = "MWF 10:00-10:50,TR 9:25-10:40";
        String courseTimes3 = "MWF 11:00-11:50,TR 10:50-12:05";
        String courseTimes4 = "MWF 12:00-12:50,TR 2:35-3:50";
        for (Course c: allCourses) {
            List<Section> thisSectionList = new ArrayList<>();
            courseMap.put(c.getCourseNum(), c);
            Section s1 = new Section(c, 1, "24601", "F2019", courseTimes1);
            Section s2 = new Section(c, 2, "24601", "F2019", courseTimes2);
            Section s3 = new Section(c, 3, "24601", "F2019", courseTimes3);
            Section s4 = new Section(c, 4, "24601", "F2019", courseTimes4);

            thisSectionList.add(s1);
            thisSectionList.add(s2);
            thisSectionList.add(s3);
            thisSectionList.add(s4);

            sectionMap.put(c.getCourseNum(), thisSectionList);
        }

        List<Major> allMajors = JsonUtil.listFromJsonFile("src/main/resources/majorCatalogWithCourseObjects.json", Major.class);
        Map<String, Major> majorMap = new HashMap<>();
        for (Major m: allMajors) {
            majorMap.put(m.getTitle() + " " + m.getType(), m);
        }

        d.setCourseCatalog(courseMap);
        d.setMajorDirectory(majorMap);

        List<Course> choosesOne = new ArrayList<>();
        choosesOne.add(d.getCourseCatalog().get("MATH11100"));

        Student student1 = new Student("dmccaffrey", "asdf", d.getMajorDirectory().get("Computer Science Major BS"), null);
        Student student2 = new Student("apython", "asdf", d.getMajorDirectory().get("Computer Science Major BA"), null);

        d.getStudents().put("dmccaffrey", student1);

        //throws when student is not in directory
        assertThrows(NoSuchElementException.class, ()-> d.getFriendsSchedule("dmccaffrey", "apython"));

        d.getStudents().put("apython", student2);

        //throws when students aren't friends
        assertThrows(IllegalArgumentException.class, ()-> d.getFriendsSchedule("dmccaffrey", "apython"));

        d.addFriend("dmccaffrey", "apython");
        d.addFriend("apython", "dmccaffrey");
        d.setSectionCatalog(sectionMap);

        //throws when student hasn't yet created a schedule
        assertThrows(NullPointerException.class, ()-> d.getFriendsSchedule("dmccaffrey", "apython"));

        student1.changeMajor(d.getMajorDirectory().get("Computer Science Major BS"));
        student1.setPlan(d.genCoursePlan("dmccaffrey", Semester.Fall, 2019, 15, choosesOne));
        student1.setSchedule(d.genSchedule("dmccaffrey"));


        System.out.println(d.getFriendsSchedule("apython", "dmccaffrey"));
    }
}