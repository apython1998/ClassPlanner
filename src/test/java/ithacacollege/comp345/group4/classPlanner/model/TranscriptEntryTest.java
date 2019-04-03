package ithacacollege.comp345.group4.classPlanner.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;

import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

public class TranscriptEntryTest {

    @Test
    public void constructorTest() {
        TranscriptEntry myEntry = new TranscriptEntry();
        assertNotNull(myEntry);
        try {
            myEntry = new TranscriptEntry("src/test/resources/exTranscriptEntry.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("Completed\tCOMP17100\tPrinciples of Comp Sci I\tA\t4.0", myEntry.toString());
    }

    @Test
    public void toStringTest() {
        TranscriptEntry myEntry = new TranscriptEntry(new Course("Principles of Comp Sci I", 4.0, "COMP17100", null, null, null, null),
                                                            "",
                                                            true,
                                                            false);
        //System.out.println(myEntry.toString());
        assertEquals("In Progress\tCOMP17100\tPrinciples of Comp Sci I\t\t4.0", myEntry.toString());
        myEntry = new TranscriptEntry();
        assertEquals("", myEntry.toString());
    }

    @Test
    public void parseCourseTest() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("src/test/resources/exTranscriptEntry.json")) {
            Object obj = jsonParser.parse(reader);

            JSONObject entry = (JSONObject) obj;
            JSONObject myCourse = (JSONObject) entry.get("course");

            Course thisCourse = TranscriptEntry.parseCourse(myCourse);
            assertEquals("Principles of Comp Sci I", thisCourse.getName());
            assertEquals("COMP17100", thisCourse.getCourseNum());
            assertEquals(4.0, thisCourse.getCredits());
            assertNull(thisCourse.getprereqs());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
