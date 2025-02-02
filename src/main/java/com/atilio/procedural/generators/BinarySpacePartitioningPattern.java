package com.atilio.procedural.generators;

import java.util.List;

import com.atilio.procedural.entities.CellCoordinates;
import com.atilio.procedural.entities.MainMatrix;
import com.atilio.procedural.entities.SubMatrix;
import com.atilio.procedural.exceptions.AppException;

import java.util.ArrayList;

import java.util.Random;

public class BinarySpacePartitioningPattern extends MainPattern {

    private Random myRandom;
    private static final int RECURSIVITY_LEVEL = 8; // max value 6 please

    public BinarySpacePartitioningPattern(MainMatrix matrix) {
        super(matrix);
        myRandom = new Random();
    }

    @Override
    public void process() {
        // Add a current matrix as pivot
        // Use while
        // You don't need a complete mainmatrix, just store pos, cols and row
        List<SubMatrix> subMatrices = new ArrayList<>();
        CellCoordinates initialCell = new CellCoordinates(0, 0);
        int recursionLevel = 0;
        // splitSpace(subMatrices, initialCell, matrixToUse.getRows(),
        // matrixToUse.getCols(), recursionLevel);
        SubMatrix root = new SubMatrix(initialCell, matrixToUse.getRows(), matrixToUse.getCols());
        subMatrices.add(root);
        splitSpace(subMatrices, root, recursionLevel);

        drawInMainMatrix(subMatrices);
    }

    private void drawInMainMatrix(List<SubMatrix> subMatrices) {
        for (int i = 0; i < subMatrices.size(); i++) {
            System.out.println(subMatrices.get(i).toString());
            if (i > 0) {
                CellCoordinates position = subMatrices.get(i).getInitalCell();
                for (int j = 0; j < subMatrices.get(i).getRows(); j++) {
                    for (int k = 0; k < subMatrices.get(i).getColumns(); k++) {
                        matrixToUse.setValue(position.getRow() + j, position.getColumn() + k, i);
                    }
                }
            }
            System.out.println("*----------------------*");
        }
    }

    private void splitSpace(List<SubMatrix> matrices, SubMatrix root, int recursionLevel) {
        System.out.println("voy por " + recursionLevel);
        if (recursionLevel > RECURSIVITY_LEVEL) {
            return;
        }
        recursionLevel++;
        SubMatrix leftSubMatrix = null;
        SubMatrix rightSubMatrix = null;
        float chance = myRandom.nextFloat();
        // left child
        int newColsLeft = root.getColumns();
        int newRowsLeft = root.getRows();
        if (chance < 0.5) {
            newRowsLeft = (int) Math.floor(root.getRows() / 2d);
        } else {
            newColsLeft = (int) Math.floor(root.getColumns() / 2d);
        }
        if ((newRowsLeft * newColsLeft) > 2) {
            leftSubMatrix = new SubMatrix(new CellCoordinates(root.getInitalCell()), newRowsLeft, newColsLeft);
            matrices.add(leftSubMatrix);
        }
        // right child
        int newColsRight = 0;
        int newRowsRight = 0;
        if (root.getColumns() == newColsLeft) {
            newColsRight = root.getColumns();
        } else if (root.getColumns() > newColsLeft) {
            newColsRight = root.getColumns() - newColsLeft;
        } else {
            System.out.println("wtf right cols!! ");
            return;
        }
        if (root.getRows() == newRowsLeft) {
            newRowsRight = root.getRows();
        } else if (root.getRows() > newRowsLeft) {
            newRowsRight = root.getRows() - newRowsLeft;
        } else {
            System.out.println("wtf right rows!");
            return;
        }

        CellCoordinates cellCoordinateRight = new CellCoordinates(root.getInitalCell());
        if (chance < 0.5) {
            cellCoordinateRight.setRow(newRowsLeft);
        } else {
            cellCoordinateRight.setColumn(newColsLeft);
        }
        if (root.isCellInMatrix(cellCoordinateRight) && ((newColsRight * newRowsRight) >= 2)) {
            rightSubMatrix = new SubMatrix(cellCoordinateRight, newRowsRight, newColsRight);
            matrices.add(rightSubMatrix);
        }
        if (leftSubMatrix != null && !root.equals(leftSubMatrix)) {

            splitSpace(matrices, leftSubMatrix, recursionLevel);
        }
        if (rightSubMatrix != null && !root.equals(rightSubMatrix)) {

            splitSpace(matrices, rightSubMatrix, recursionLevel);
        }
    }

    private void splitSpace(List<SubMatrix> matrices, CellCoordinates coordinates, int rows, int cols,
            int recursionLevel) {
        if (recursionLevel > RECURSIVITY_LEVEL) {
            return;
        }
        int newRecursionLevel = recursionLevel + 1;
        SubMatrix pivot = new SubMatrix(coordinates, rows, cols);
        matrices.add(pivot);
        float chance = myRandom.nextFloat();
        int newCols = cols;
        int newRows = rows;
        if (chance < 0.5) {
            newRows = (int) Math.floor(rows / 2d);
        } else {
            newCols = (int) Math.floor(cols / 2d);
        }
        if ((newRows * newCols) >= 2) {
            splitSpace(matrices, coordinates, newRows, newCols, newRecursionLevel);
        }
    }

}
