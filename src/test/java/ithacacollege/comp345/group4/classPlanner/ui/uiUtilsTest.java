package ithacacollege.comp345.group4.classPlanner.ui;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class uiUtilsTest {

    @Test
    void cleanMajorStringTest() {
        assertEquals("Computer Science Major BA", (uiUtils.cleanMajorString("computer science major ba")));
        assertEquals("Computer Science Major BA", (uiUtils.cleanMajorString("Computer science major ba")));
        assertEquals("Computer Science Major BA", (uiUtils.cleanMajorString("computer Science major ba")));
        assertEquals("Computer Science Major BA", (uiUtils.cleanMajorString("Computer Science major ba")));
        assertEquals("Computer Science Major BA", (uiUtils.cleanMajorString("computer science major Ba")));
        assertEquals("Computer Science Major BA", (uiUtils.cleanMajorString("Computer science major Ba")));
        assertEquals("Computer Science Major BA", (uiUtils.cleanMajorString("computer Science major Ba")));
        assertEquals("Computer Science Major BA", (uiUtils.cleanMajorString("Computer Science major Ba")));
        assertEquals("Computer Science Major BA", (uiUtils.cleanMajorString("computer science major bA")));
        assertEquals("Computer Science Major BA", (uiUtils.cleanMajorString("Computer science major bA")));
        assertEquals("Computer Science Major BA", (uiUtils.cleanMajorString("computer Science major bA")));
        assertEquals("Computer Science Major BA", (uiUtils.cleanMajorString("Computer Science major bA")));
        assertEquals("Computer Science Major BA", (uiUtils.cleanMajorString("computer science major BA")));
        assertEquals("Computer Science Major BA", (uiUtils.cleanMajorString("Computer science major BA")));
        assertEquals("Computer Science Major BA", (uiUtils.cleanMajorString("computer Science major BA")));
        assertEquals("Computer Science Major BA", (uiUtils.cleanMajorString("computEr SciEnce majOr BA")));

        assertThrows(InvalidArgumentException.class, ()->uiUtils.cleanMajorString(null));
        assertThrows(InvalidArgumentException.class, ()->uiUtils.cleanMajorString(" "));
    }
}
