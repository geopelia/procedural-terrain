package com.atilio.procedural.generators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.atilio.procedural.entities.MainMatrix;
import com.atilio.procedural.exceptions.AppException;

class FillRandomPatternTest {

    @Test
    void testProcess() {
        assertThrowsExactly(AppException.class, () -> {
            MainMatrix myMatrix = new MainMatrix(5, 3);
            new FillRandomPattern(myMatrix, 50);
        });

        try {
            MainMatrix matrix = new MainMatrix(5, 5);
            int cells = 10;
            FillRandomPattern pattern = new FillRandomPattern(matrix, cells);
            pattern.process();
            int result = countCells(matrix);
            assertEquals(cells, result);
        } catch (Exception e) {
            fail(e);
        }
    }

    private int countCells(MainMatrix matrix) {
        int cellsWithValue = 0;
        int[][] array = matrix.getMatrix();
        for (int[] rows : array) {
            for (int cell : rows) {
                if (cell != 0) {
                    cellsWithValue++;
                }
            }
        }
        return cellsWithValue;
    }

}
