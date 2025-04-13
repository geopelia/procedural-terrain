package com.atilio.procedural.generators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.atilio.procedural.entities.MainMatrix;

class UnBalancedBinarySpacePartitioningPatternTest {
    @Test
    void testSmallTree() {
        try {
            MainMatrix matrix = new MainMatrix(2, 2);
            UnBalancedBinarySpacePartitioningPattern pattern = new UnBalancedBinarySpacePartitioningPattern(matrix);
            pattern.process();
            Map<Integer, Integer> result = listColorsInMatrix(matrix);
            assertEquals(2, result.keySet().size());
            for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
                assertEquals(2, entry.getValue());
            }
            matrix = new MainMatrix(2, 1);
            pattern = new UnBalancedBinarySpacePartitioningPattern(matrix);
            pattern.process();
            result = listColorsInMatrix(matrix);
            assertEquals(1, result.size());
            matrix = new MainMatrix(1, 3);
            pattern = new UnBalancedBinarySpacePartitioningPattern(matrix);
            pattern.process();
            result = listColorsInMatrix(matrix);
            assertEquals(1, result.keySet().size(), matrix.printToExport());

        } catch (Exception e) {
            fail(e);
        }
    }

    private Map<Integer, Integer> listColorsInMatrix(MainMatrix matrix) {
        Map<Integer, Integer> colorsOcurrence = new HashMap<>();
        int[][] array = matrix.getMatrix();
        for (int[] rows : array) {
            for (int cell : rows) {
                if (colorsOcurrence.containsKey(cell)) {
                    int counter = colorsOcurrence.get(cell).intValue() + 1;
                    colorsOcurrence.put(cell, counter);
                } else {
                    colorsOcurrence.put(cell, 1);
                }
            }
        }
        return colorsOcurrence;

    }

}
