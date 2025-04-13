package com.atilio.procedural.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.atilio.procedural.entities.BinaryTree;
import com.atilio.procedural.entities.BinaryTreeNode;
import com.atilio.procedural.entities.CellCoordinates;
import com.atilio.procedural.entities.MainMatrix;
import com.atilio.procedural.entities.SubMatrix;
import com.atilio.procedural.mics.BinaryTreeValidator;
import com.atilio.procedural.mics.PrintToConsoleFunctions;

public class EnhancedBSPPattern  extends MainPattern{

    private Random myRandom;
    public EnhancedBSPPattern(MainMatrix matrix) {
        super(matrix);
        myRandom = new Random();
    }

    @Override
    public void process() {
        List<SubMatrix> subMatrices = new ArrayList<>();
        CellCoordinates initialCell = new CellCoordinates(0, 0);
        SubMatrix root = new SubMatrix(initialCell, matrixToUse.getRows(), matrixToUse.getCols());
        subMatrices.add(root);
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.addNode(root);
        int size = 1;
        while (!subMatrices.isEmpty()) {
            SubMatrix currenSubMatrix = subMatrices.remove(0);
            float chance = myRandom.nextFloat();
            int colsLeft = currenSubMatrix.getColumns();
            int colsRight = colsLeft;
            int rowsLeft = currenSubMatrix.getRows();
            int rowsRight = rowsLeft;
            CellCoordinates cellCoordinateRight = new CellCoordinates(root.getInitalCell());
            if (chance < 0.5) {
                rowsLeft = (int) Math.floor(currenSubMatrix.getRows() / 2d);
                rowsRight -= rowsLeft;
                cellCoordinateRight.setRow(rowsLeft);
            } else {
                colsLeft = (int) Math.floor(currenSubMatrix.getColumns() / 2d);
                colsRight -= colsLeft;
                cellCoordinateRight.setColumn(colsLeft);
            }
            int areaLeft = rowsLeft * colsLeft;
            int areaRight = rowsRight * colsRight;
            if (areaLeft < 3 || areaRight < 3) {
                continue;
            }
            SubMatrix subMatrixLeft = new SubMatrix(new CellCoordinates(currenSubMatrix.getInitalCell()), rowsLeft , colsLeft);
            subMatrices.add(subMatrixLeft);
            SubMatrix subMatrixRight = new SubMatrix(cellCoordinateRight, rowsRight , colsRight);
            subMatrices.add(subMatrixRight);
            binaryTree.addNode(subMatrixLeft);
            binaryTree.addNode(subMatrixRight);
            size = size +2;
        }
        System.out.println(binaryTree);
        BinaryTreeValidator.isFullTree(binaryTree.getRoot());


    }

}
