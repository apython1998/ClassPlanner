package ithacacollege.comp345.group4.classPlanner.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TranscriptTest {

    @Test
    public void constructorTest() {
        Transcript myTranscript = new Transcript();
        assertNotNull(myTranscript);
        myTranscript = new Transcript("src/test/resources/exTranscript.json");
        assertEquals("Completed\tCOMP17100\tPrinciples of Comp Sci I\tA\t4.0\n" +
                "In Progress\tCOMP17200\tPrinciples of Comp Sci II\t\t4.0", myTranscript.toString());
    }
}
