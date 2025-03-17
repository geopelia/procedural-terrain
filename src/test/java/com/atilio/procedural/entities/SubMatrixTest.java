package com.atilio.procedural.entities;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class SubMatrixTest {

    @Test
    void testInCell() {
        CellCoordinates coordinates = new CellCoordinates(4, 4);
        SubMatrix subMatrix = new SubMatrix(coordinates, 5, 5);
        //valid
        CellCoordinates pointA = new CellCoordinates(5,8);
        CellCoordinates pointB = new CellCoordinates(4,4);
        CellCoordinates pointC = new CellCoordinates(4,8);
        CellCoordinates pointD = new CellCoordinates(8,4);
        CellCoordinates pointF = new CellCoordinates(8,8);
        CellCoordinates pointG = new CellCoordinates(6,6);
        //invalid
        CellCoordinates pointH = new CellCoordinates(5,10);
        CellCoordinates pointI = new CellCoordinates(4,15);
        CellCoordinates pointJ = new CellCoordinates(6,3);
        CellCoordinates pointK = new CellCoordinates(5,9);

        assertTrue(subMatrix.isCellInMatrix(pointA));
        assertTrue(subMatrix.isCellInMatrix(pointB));
        assertTrue(subMatrix.isCellInMatrix(pointC));
        assertTrue(subMatrix.isCellInMatrix(pointD));
        assertTrue(subMatrix.isCellInMatrix(pointF));
        assertTrue(subMatrix.isCellInMatrix(pointG));

        assertFalse(subMatrix.isCellInMatrix(pointK));
        assertFalse(subMatrix.isCellInMatrix(pointJ));
        assertFalse(subMatrix.isCellInMatrix(pointH));
        assertFalse(subMatrix.isCellInMatrix(pointI));
    }
}
