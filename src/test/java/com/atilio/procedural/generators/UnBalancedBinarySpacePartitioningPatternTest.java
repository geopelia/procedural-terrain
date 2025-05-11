package com.atilio.procedural.generators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.atilio.procedural.entities.BinaryTreeNode;
import com.atilio.procedural.entities.CellCoordinates;
import com.atilio.procedural.entities.MainMatrix;
import com.atilio.procedural.entities.SubMatrix;
import com.atilio.procedural.exceptions.AppException;
import com.atilio.procedural.mics.PrintToConsoleFunctions;

class UnBalancedBinarySpacePartitioningPatternTest {
    @Test
    void testSmallTree() {
        try {
            MainMatrix matrix = new MainMatrix(2, 2);
            BinarySpacePartitioningPattern pattern = new BinarySpacePartitioningPattern(matrix);
            pattern.process();
            Map<Integer, Integer> result = listColorsInMatrix(matrix);
            assertEquals(2, result.keySet().size(), matrix.printToExport());
            for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
                assertEquals(2, entry.getValue());
            }
            matrix = new MainMatrix(2, 1);
            pattern = new BinarySpacePartitioningPattern(matrix);
            pattern.process();
            result = listColorsInMatrix(matrix);
            assertEquals(1, result.size());
            matrix = new MainMatrix(1, 3);
            pattern = new BinarySpacePartitioningPattern(matrix);
            pattern.process();
            result = listColorsInMatrix(matrix);
            assertEquals(1, result.keySet().size(), matrix.printToExport());

            matrix = new MainMatrix(1, 17);
            pattern = new BinarySpacePartitioningPattern(matrix);
            pattern.process();
            result = listColorsInMatrix(matrix);
            for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
                assertTrue(entry.getValue() >= 2, matrix.printToExport());
            }
            matrix = new MainMatrix(31, 47);
            pattern = new BinarySpacePartitioningPattern(matrix);
            pattern.process();
            result = listColorsInMatrix(matrix);
            for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
                assertTrue(entry.getValue() >= 2, matrix.printToExport());
            }
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void testSplitSpace() {
        List<SubMatrix> subMatrices = new ArrayList<>();
        CellCoordinates initialCell = new CellCoordinates(0, 0);
        int recursionLevel = 0;
        MainMatrix matrix;
        try {
            matrix = new MainMatrix(1, 3);
            SubMatrix root = new SubMatrix(initialCell, matrix.getRows(), matrix.getCols());
            subMatrices.add(root);
            BinaryTreeNode<SubMatrix> tree = new BinaryTreeNode<>(root, null, null);
            BinarySpacePartitioningPattern pattern = new BinarySpacePartitioningPattern(matrix);
            pattern.splitSpace(subMatrices, root, recursionLevel, tree);
            assertEquals(1, subMatrices.size(), PrintToConsoleFunctions.printTreeBreadthFirst(tree, subMatrices.size()));
        } catch (AppException e) {
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
