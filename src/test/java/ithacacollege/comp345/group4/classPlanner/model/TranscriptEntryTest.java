package ithacacollege.comp345.group4.classPlanner.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TranscriptEntryTest {

    @Test
    public void constructorTest() {
        TranscriptEntry myEntry = new TranscriptEntry();
        assertNotNull(myEntry);
        try {
            myEntry = new TranscriptEntry("C:\\Users\\dcaff\\IdeaProjects\\comp345-classPlanner\\src\\test\\testResources\\exTranscriptEntry.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("In Progress\tCOMP17100\tPrinciples of Comp Sci I\t\t4.0\tF2019", myEntry.toString());
    }

    @Test
    public void toStringTest() {
        TranscriptEntry myEntry = new TranscriptEntry(new Course("Principles of Comp Sci I", 24850, 4.0, "COMP17100", "F2019", null),
                                                            "",
                                                            true,
                                                            false);
        //System.out.println(myEntry.toString());
        assertEquals("In Progress\tCOMP17100\tPrinciples of Comp Sci I\t\t4.0\tF2019", myEntry.toString());
        myEntry = new TranscriptEntry();
        assertEquals("", myEntry.toString());
    }
}
