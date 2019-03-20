package ithacacollege.comp345.group4.classPlanner.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TranscriptTest {

    @Test
    public void constructorTest() {
        Transcript myTranscript = new Transcript();
        assertNotNull(myTranscript);
        myTranscript = new Transcript("");
    }
}
