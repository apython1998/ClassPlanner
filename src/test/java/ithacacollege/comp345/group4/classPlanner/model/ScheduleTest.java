package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduleTest {

    @Test
    public void constructorTest() {
        Schedule schedule = new Schedule();
        assertNotNull(schedule);
    }

    @Test
    public void addSectionTest() {
        try {
            List<Course> allCourses = JsonUtil.listFromJsonFile("src/main/resources/courseCatalog.json", Course.class);
            Map<String, Course> courseMap = new HashMap<>();
            for (Course c: allCourses) {
                courseMap.put(c.getCourseNum(), c);
            }
            Schedule schedule = new Schedule();

            //no courses, should just add
            Section section = new Section(courseMap.get("COMP34500"), 1, "36546", "F2019", "MWF 9:00-9:50,R 9:25-10:40");
            assertEquals("COMP34500 1 MWF 9:00-9:50, R 9:25-10:40", section.toString());
            schedule.addCourse(section);

            //course with complete conflicts
            final Section conflictSection = new Section(courseMap.get("COMP31100"), 1, "36546", "F2019", "MWF 9:00-9:50,R 9:25-10:40");
            assertThrows(IllegalArgumentException.class, ()-> schedule.addCourse(conflictSection));

            //course with one day conflict
            final Section conflictSection2 = new Section(courseMap.get("COMP31100"), 2, "36546", "F2019", "MWF 10:00-10:50,R 9:25-10:40");
            assertThrows(IllegalArgumentException.class, ()-> schedule.addCourse(conflictSection2));

            //another course with no conflicts
            section = new Section(courseMap.get("COMP31100"), 2, "36546", "F2019", "MWF 1:00-1:50,R 2:35-3:50");
            assertEquals("COMP31100 2 MWF 1:00-1:50, R 2:35-3:50", section.toString());
            schedule.addCourse(section);

            //making sure all courses are accurately put in
            for (Section s: schedule.getCourses()) {
                System.out.println(s.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void displayTest() {
        try {
            List<Course> allCourses = JsonUtil.listFromJsonFile("src/main/resources/courseCatalog.json", Course.class);
            Map<String, Course> courseMap = new HashMap<>();
            for (Course c : allCourses) {
                courseMap.put(c.getCourseNum(), c);
            }
            Schedule schedule = new Schedule();

            Section section = new Section(courseMap.get("COMP34500"), 1, "36546", "S2019", "MWF 9:00-9:50,R 9:25-10:40");
            schedule.addCourse(section);

            section = new Section(courseMap.get("COMP31100"), 1, "36546", "S2019", "MWF 1:00-1:50,R 2:35-3:50");
            schedule.addCourse(section);

            section = new Section(courseMap.get("JAZZ20100"), 1, "46354", "S2019", "TR 10:50-11:40");
            schedule.addCourse(section);

            section = new Section(courseMap.get("MUEN11100"), 1, "66284", "S2019", "MWF 2:00-3:50");
            schedule.addCourse(section);

            section = new Section(courseMap.get("PFMJ19900"), 37, "24960", "S2019", "W 5:00-5:50");
            schedule.addCourse(section);

            System.out.println(schedule.display());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
