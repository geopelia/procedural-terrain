package com.atilio.procedural.generators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.atilio.procedural.entities.MainMatrix;
import com.atilio.procedural.exceptions.AppException;

class DiagonalLinePatternTest {
    @Test
    void testProcess() {
        String result = "1, 0, 0\n0, 1, 0\n0, 0, 1";
        String result2 = "1, 0, 0\n0, 1, 0";
        String result3 = "1, 0, 0\n0, 1, 0\n0, 0, 1\n0, 0, 0\n0, 0, 0";
        try {
            MainMatrix mainMatrix = new MainMatrix(3, 3);
            mainMatrix.fillWithZero();
            DiagonalLinePattern pattern = new DiagonalLinePattern(mainMatrix);
            pattern.process();
            assertNotEquals("false", mainMatrix.printToExport());
            assertEquals(result, mainMatrix.printToExport());
            mainMatrix = new MainMatrix(2, 3);
            mainMatrix.fillWithZero();
            pattern = new DiagonalLinePattern(mainMatrix);
            pattern.process();
            assertEquals(result2, mainMatrix.printToExport());
            mainMatrix = new MainMatrix(5, 3);
            mainMatrix.fillWithZero();
            pattern = new DiagonalLinePattern(mainMatrix);
            pattern.process();
            assertEquals(result3, mainMatrix.printToExport());
        } catch (AppException e) {
            fail(e);

        }


    }

}
