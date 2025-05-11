package com.atilio.procedural.generators;

import java.util.List;
import com.atilio.procedural.entities.BinaryTreeNode;
import com.atilio.procedural.entities.CellCoordinates;
import com.atilio.procedural.entities.MainMatrix;
import com.atilio.procedural.entities.SubMatrix;
import com.atilio.procedural.mics.BinaryTreeValidator;
import com.atilio.procedural.mics.PrintToConsoleFunctions;
import com.atilio.procedural.mics.TreeToStream;

import java.util.ArrayList;
import java.util.Random;

import org.tinylog.Logger;

public class BinarySpacePartitioningPattern extends MainPattern {

    private Random myRandom;
    private static final int RECURSIVITY_LEVEL = 8; // max value 6 please

    public BinarySpacePartitioningPattern(MainMatrix matrix) {
        super(matrix);
        myRandom = new Random();
    }

    @Override
    public void process() {
        List<SubMatrix> subMatrices = new ArrayList<>();
        CellCoordinates initialCell = new CellCoordinates(0, 0);
        int recursionLevel = 0;
        SubMatrix root = new SubMatrix(initialCell, matrixToUse.getRows(), matrixToUse.getCols());
        subMatrices.add(root);
        BinaryTreeNode<SubMatrix> tree = new BinaryTreeNode<>(root, null, null);
        splitSpace(subMatrices, root, recursionLevel, tree);
        Logger.debug(PrintToConsoleFunctions.printTreeBreadthFirst(tree, subMatrices.size()));
        drawInMainMatrix(subMatrices);
        if (BinaryTreeValidator.isFullTree(tree)) {
            Logger.info("es completo");
        } else {
            Logger.warn("no hay completo");
        }
        String filename = "tree_" + matrixToUse.getRows() + "x" + matrixToUse.getCols() + "_";
        TreeToStream.saveToStream(tree, filename);
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

    private float getBetterSplitChance(SubMatrix matrix) {
        int newRow = 0;
        int newColumn = 0;
        float chance = myRandom.nextFloat();
        if (chance < 0.5) {
            newRow = (int) Math.floor(matrix.getRows() / 2d);
            if (newRow < 1) {
                chance = 0.6f;
            }
        } else {
            newColumn = (int) Math.floor(matrix.getColumns() / 2d);
            if (newColumn < 1) {
                chance = 0.1f;
            }
        }
        return chance;
    }

    public void splitSpace(List<SubMatrix> matrices, SubMatrix root, int recursionLevel,
            BinaryTreeNode<SubMatrix> tree) {
        Logger.debug("voy por " + recursionLevel);
        if (recursionLevel > RECURSIVITY_LEVEL) {
            Logger.warn("superada recursividad");
            return;
        }
        if ((root.getSize() / 2) < 2) {
            Logger.warn("no se puede dividir mas");
            Logger.debug(root);
            return;
        }
        recursionLevel++;
        float chance = getBetterSplitChance(root);

        SubMatrix leftSubMatrix = createLeftSubMatrix(root, chance);
        if (leftSubMatrix == null) {
            return;
        }
        if (leftSubMatrix.getRows() > root.getRows() || leftSubMatrix.getColumns() > root.getColumns()) {
            Logger.error("ERROR calculating value of submatrix ");
            Logger.debug("patent", root);
            Logger.debug("left", leftSubMatrix);
            return;
        }
        SubMatrix rightSubMatrix = createRightSubMatrix(root, leftSubMatrix, chance);
        if (!root.equals(leftSubMatrix)) {
            matrices.add(leftSubMatrix);
            BinaryTreeNode<SubMatrix> lefTreeNode = new BinaryTreeNode<>(
                    leftSubMatrix, null, null);
            tree.setNodeLeft(lefTreeNode);
            splitSpace(matrices, leftSubMatrix, recursionLevel, lefTreeNode);
        } else {
            Logger.debug("parent and left child are equals?", root, leftSubMatrix);
        }
        if (rightSubMatrix != null && !root.equals(rightSubMatrix)) {
            matrices.add(rightSubMatrix);
            BinaryTreeNode<SubMatrix> righTreeNode = new BinaryTreeNode<>(
                    rightSubMatrix, null, null);
            tree.setNodeRight(righTreeNode);
            splitSpace(matrices, rightSubMatrix, recursionLevel, righTreeNode);
        } else {
            Logger.debug("parent and right child are equals?", root, rightSubMatrix);
        }
    }

    private SubMatrix createLeftSubMatrix(SubMatrix parent, float chance) {
        int newColsLeft = parent.getColumns();
        int newRowsLeft = parent.getRows();
        if (chance < 0.5) {
            newRowsLeft = (int) Math.floor(parent.getRows() / 2d);
        } else {
            newColsLeft = (int) Math.floor(parent.getColumns() / 2d);
        }
        if ((newRowsLeft * newColsLeft) >= 2) {
            return new SubMatrix(new CellCoordinates(parent.getInitalCell()), newRowsLeft, newColsLeft);
        } else {
            Logger.debug("Don't have space to create left submatrix");
            return null;
        }
    }

    private SubMatrix createRightSubMatrix(SubMatrix parent, SubMatrix leftSubMatrix, float chance) {
        int newColsRight = calculateNewSize(parent.getColumns(), leftSubMatrix.getColumns());
        int newRowsRight = calculateNewSize(parent.getRows(), leftSubMatrix.getRows());
        CellCoordinates cellCoordinateRight = new CellCoordinates(parent.getInitalCell());
        if (chance < 0.5) {
            cellCoordinateRight.setRow(parent.getInitalCell().getRow() + leftSubMatrix.getRows());
        } else {
            cellCoordinateRight.setColumn(parent.getInitalCell().getColumn() + leftSubMatrix.getColumns());
        }
        if (parent.isCellInMatrix(cellCoordinateRight) && ((newColsRight * newRowsRight) >= 2)) {
            return new SubMatrix(cellCoordinateRight, newRowsRight, newColsRight);
        } else {
            Logger.debug("failed to generate right submatrix");
            Logger.debug("coordinates ok?", parent.isCellInMatrix(cellCoordinateRight));
            Logger.debug("new size =" + (newColsRight * newRowsRight));
            return null;

        }
    }

    private int calculateNewSize(int parentValue, int adjacentValue) {
        if (parentValue == adjacentValue) {
            // value didn't change
            return parentValue;
        } else {
            // return the value left
            return parentValue - adjacentValue;
        }
    }

}
