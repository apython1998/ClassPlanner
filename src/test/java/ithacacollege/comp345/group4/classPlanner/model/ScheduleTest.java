package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScheduleTest {

    @Test
    public void constructorTest() {
        Schedule schedule = new Schedule();
        assertNotNull(schedule);
    }

    @Test
    public void addSectionTest() {
        Schedule schedule = new Schedule();
    }
}
