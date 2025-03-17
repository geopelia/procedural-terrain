package com.atilio.procedural.generators;

import java.util.List;
import com.atilio.procedural.entities.BinaryTreeNode;
import com.atilio.procedural.entities.CellCoordinates;
import com.atilio.procedural.entities.MainMatrix;
import com.atilio.procedural.entities.SubMatrix;
import com.atilio.procedural.mics.BinaryTreeValidator;
import com.atilio.procedural.mics.PrintToConsoleFunctions;

import java.util.ArrayList;
import java.util.Random;

public class UnBalancedBinarySpacePartitioningPattern extends MainPattern {

    private Random myRandom;
    private static final int RECURSIVITY_LEVEL = 8; // max value 6 please

    public UnBalancedBinarySpacePartitioningPattern(MainMatrix matrix) {
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
        SubMatrix root = new SubMatrix(initialCell, matrixToUse.getRows(), matrixToUse.getCols());
        subMatrices.add(root);
        BinaryTreeNode<SubMatrix> tree = new BinaryTreeNode<>(root, null, null);
        splitSpace(subMatrices, root, recursionLevel, tree);
        PrintToConsoleFunctions.printTreeBreadthFirst(tree, subMatrices.size());
        drawInMainMatrix(subMatrices);
        if (BinaryTreeValidator.isCompleteTree(tree)) {
            System.out.println("es completo");
        } else {
            System.out.println("no hay completo");
        }
    }

    private void drawInMainMatrix(List<SubMatrix> subMatrices) {
        for (int i = 0; i < subMatrices.size(); i++) {
            if (i > 0) {
                CellCoordinates position = subMatrices.get(i).getInitalCell();
                for (int j = 0; j < subMatrices.get(i).getRows(); j++) {
                    for (int k = 0; k < subMatrices.get(i).getColumns(); k++) {
                        matrixToUse.setValue(position.getRow() + j, position.getColumn() + k, i);
                    }
                }
            }
        }
    }

    private void splitSpace(List<SubMatrix> matrices, SubMatrix root, int recursionLevel,
            BinaryTreeNode<SubMatrix> tree) {
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
            BinaryTreeNode<SubMatrix> lefTreeNode = new BinaryTreeNode<SubMatrix>(
                    leftSubMatrix, null, null);
            tree.setNodeLeft(lefTreeNode);
            splitSpace(matrices, leftSubMatrix, recursionLevel, lefTreeNode);
        }
        if (rightSubMatrix != null && !root.equals(rightSubMatrix)) {
            BinaryTreeNode<SubMatrix> righTreeNode = new BinaryTreeNode<SubMatrix>(
                    rightSubMatrix, null, null);
            tree.setNodeRight(righTreeNode);
            splitSpace(matrices, rightSubMatrix, recursionLevel, righTreeNode);
        }
    }

}
