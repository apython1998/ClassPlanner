package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTimesTest {

    @Test
    public void constructorTest() {
        CourseTimes time = new CourseTimes("MWF", "9:00");
        assertNotNull(time);
        assertThrows(InvalidArgumentException.class, ()-> new CourseTimes("MWG", "9:00"));
        assertThrows(InvalidArgumentException.class, ()-> new CourseTimes("MWF", "9:99"));
        assertThrows(InvalidArgumentException.class, ()-> new CourseTimes("MWF", "25:59"));
        assertThrows(InvalidArgumentException.class, ()-> new CourseTimes("MWF", "23:590"));
        assertThrows(InvalidArgumentException.class, ()-> new CourseTimes("MWF", "2::59"));
    }

    @Test
    public void toStringTest() {
        CourseTimes time = new CourseTimes("MWF", "9:00");
        assertEquals(time.toString(), "MWF 9:00");
        time = new CourseTimes();
        assertEquals(time.toString(), " ");
    }

    @Test
    public void addDayTest() {
        CourseTimes time = new CourseTimes("MWF", "9:00");
        time.addDay('R');
        assertEquals("MWFR 9:00", time.toString());
        assertThrows(InvalidArgumentException.class, ()-> time.addDay('Y'));
        time.addDay('R');
        assertEquals("MWFR 9:00", time.toString());
    }

    @Test
    public void removeDayTest() {
//        CourseTimes time = new CourseTimes("MWF", "9:00");
//        time.removeDay('W');
//        assertEquals("MF 9:00", time.toString());
//        assertThrows(InvalidArgumentException.class, ()-> time.removeDay('Y'));
//        time.removeDay('W');
//        assertEquals("MF 9:00", time.toString());
    }
}
