package ithacacollege.comp345.group4.classPlanner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HelloWorldTest {

    @Test
    void addTwoNumsTest() {
        assertEquals(0, HelloWorld.addTwoNums(0,0));
        assertEquals(5, HelloWorld.addTwoNums(2,3));
    }

}
