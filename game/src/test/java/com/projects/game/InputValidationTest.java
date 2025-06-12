package com.projects.game;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputValidationTest {
	@Test
    void testGetGridSizeRejectsInvalidAndOutOfRangeInput() {
        String test0 = "0\n"; // test for lower bound
        String test10 = "10\n"; // test for upper bound
        String testAlphabet = "a\n"; // test for non-numeric
        String testValidValue = "4\n"; // test for valid value
        
        String simulatedInput = test0 + test10 + testAlphabet + testValidValue;
        
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        MineSweeper mineSweeper = new MineSweeper();

        int gridSize = mineSweeper.getGridSize(scanner);

        assertEquals(4, gridSize);
    }

    @Test
    void testGetNoOfMinesRejectsInvalidAndOutOfRangeInput() {
    	String test0 = "0\n"; // test for lower bound
        String test10 = "6\n"; // test for upper bound
    	String testAlphabet = "a\n"; // test for non-numeric
    	String testValidValue = "3\n"; // test for valid value
    	
    	String simulatedInput = test0 + test10 + testAlphabet + testValidValue;
    			
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        MineSweeper mineSweeper = new MineSweeper();

        int mines = mineSweeper.getNoOfMines(scanner, 5);

        assertEquals(3, mines);
    }
}
