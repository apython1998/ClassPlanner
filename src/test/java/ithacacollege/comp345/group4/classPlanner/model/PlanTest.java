package ithacacollege.comp345.group4.classPlanner.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlanTest {

    @Test
    public void planConstructorTest(){

    }

    /**
     * Depends on a working hasAllPreReqs
     */
    @Test
    public void semesterPlanConstructorTest(){
        Transcript exTranscript = new Transcript("src/test/resources/exTranscript.json");
        List<SemesterPlan> planList = new ArrayList<>();
        SemesterPlan exPrevSemPlan = null;
        Directory directory = new Directory();
        //Preload major JSON:
        directory.uploadMajor("src/test/resources/TestMajorReqs.json");
        Major csMajor = directory.getMajorDirectory().get("Computer Science");
        try {
            exPrevSemPlan = JsonUtil.fromJsonFile("src/test/resources/exPrevSemPlan.json", SemesterPlan.class);
            planList.add(exPrevSemPlan);
        }catch(IOException e){ e.printStackTrace();}

        SemesterPlan generatedPlan = new SemesterPlan(15, Semester.Fall, 2019, planList, directory.getMajorDirectory().get("Computer Science"), exTranscript);
        List<Course> courseList = generatedPlan.getCourses();

        double credSum = 0;
        boolean allFulfill = true;
        for(Course c : courseList) {
            credSum += c.getCredits();
            allFulfill = csMajor.fulfillsRequirement(c) && allFulfill;
            //assert that has every pre req
            assertTrue(generatedPlan.hasAllPreReqs(c, planList, exTranscript));
        }
        //Assert that is correct number of credits:
        assertEquals(credSum, 15d);
        //Assert that every course fulfills a requirement
        assertTrue(allFulfill);

    }

    @Test
    public void hasAllPreReqsTest(){
        Transcript exTranscript = new Transcript("src/test/resources/exTranscript.json");
        List<SemesterPlan> planList = new ArrayList<>();
        SemesterPlan exPrevSemPlan = null;
        SemesterPlan exCurrSemPlan = null;
        Course exCourse1 = new Course();
        exCourse1.setCourseNum("COMP17200");
        List<String> preReqs = new ArrayList<>();
        preReqs.add("COMP17100");
        exCourse1.setprereqs(preReqs);
        try {
            exPrevSemPlan = JsonUtil.fromJsonFile("src/test/resources/exPrevSemPlan.json", SemesterPlan.class);
            planList.add(exPrevSemPlan);

        }catch(IOException e){ e.printStackTrace();}

        assertTrue(exCurrSemPlan.hasAllPreReqs(exCourse1, planList, exTranscript));

    }
}
