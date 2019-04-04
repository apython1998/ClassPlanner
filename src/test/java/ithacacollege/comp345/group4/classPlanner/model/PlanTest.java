package ithacacollege.comp345.group4.classPlanner.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class PlanTest {

    @Test
    public void planConstructorTest(){

    }

    @Test
    public void generateSemesterPlanTest(){

    }

    @Test
    public void findNewCourseInCatalogTest(){

    }

    @Test
    public void hasAllPreReqsTest(){
        Transcript exTranscript = new Transcript("src/test/resources/exTranscript.json");
        List<SemesterPlan> planList = new ArrayList<>();
        SemesterPlan exPrevSemPlan = null;
        SemesterPlan exCurrSemPlan = null;
        Course exCourse1 = new Course();
        try {
            exPrevSemPlan = JsonUtil.fromJsonFile("src/test/resources/exPrevSemPlan.json", SemesterPlan.class);
            planList.add(exPrevSemPlan);
            exCurrSemPlan = JsonUtil.fromJsonFile("src/test/resources/exCurrSemPlan.json", SemesterPlan.class);

        }catch(IOException e){ e.printStackTrace();}

        assertTrue(exCurrSemPlan.hasAllPreReqs(exCourse1, planList, exTranscript));

    }
}
