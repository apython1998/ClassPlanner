package ithacacollege.comp345.group4.classPlanner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectoryTest{

    @Test
    public void uploadMajorTest(){
        Directory d = new Directory();
        d.uploadMajor("resources/TestMajorReqs.json");

        Major cs = new Major();
        for(Major m : d.majorDirectory)
            if(m.title.equals("ComputerScience")) {
                cs = m;
            }

        assertEquals(cs.title, "Computer Science");
    }
}