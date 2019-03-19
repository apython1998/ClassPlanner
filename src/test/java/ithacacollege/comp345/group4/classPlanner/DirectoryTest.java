package ithacacollege.comp345.group4.classPlanner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DirectoryTest{

    @Test
    public void uploadMajorTest(){
        Directory d = new Directory();
        d.uploadMajor("resources/TestMajorReqs.json");

        Major cs = new Major();
        for(Major m : d.majorDirectory) {
            if (m.title.equals("Computer Science")) {
                cs = m;
            }
        }
        assertEquals(cs.title, "Computer Science");

        //Many of these needed but course constr. not yet implemented:
        assertTrue(cs.requirements.get(0).fulfillsRequirment(new Course()));
        //TODO add one of these assertions for each course req in the JSON file
    }
}