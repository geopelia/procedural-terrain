package com.atilio.procedural.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.tinylog.Logger;

import com.atilio.procedural.entities.BinaryTreeNode;
import com.atilio.procedural.entities.CellCoordinates;
import com.atilio.procedural.entities.MainMatrix;
import com.atilio.procedural.entities.SubMatrix;
import com.atilio.procedural.exceptions.AppException;
import com.atilio.procedural.mics.TreeLeafFinder;
import com.atilio.procedural.mics.PrintToConsoleFunctions;
import com.atilio.procedural.mics.TreeToStream;

public class BSPWithCorridorsPattern extends BinarySpacePartitioningPattern {
    private static final int ROOM_COLOR = -1;
    private static final int BACKGROUND_COLOR = 40;

    public BSPWithCorridorsPattern(MainMatrix matrix) {
        super(matrix);
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
        drawInMainMatrix(subMatrices, tree);
        String filename = "tree_" + matrixToUse.getRows() + "x" + matrixToUse.getCols() + "_";
        TreeToStream.saveToStream(tree, filename);
    }

    private void drawInMainMatrix(List<SubMatrix> subMatrices, BinaryTreeNode<SubMatrix> tree) {
        Set<BinaryTreeNode<SubMatrix>> leaves = TreeLeafFinder.getOnlyLeaves(tree);
        int newSize = leaves.size() / 4;
        if (newSize == 0) {
            newSize = 1;
        }
        Set<BinaryTreeNode<SubMatrix>> leavesSelected;
        List<SubMatrix> rooms = new ArrayList<>();
        try {
            subMatrices.retainAll(TreeLeafFinder.getListOfElements(leaves));
            leavesSelected = TreeLeafFinder.getRandomSubset(leaves, newSize);
            rooms = TreeLeafFinder.getListOfElements(leavesSelected);
        } catch (AppException e) {
            Logger.error("Error obteniendo las salas en la matrix");
            Logger.error(e);
            return;
        }
        for (int i = 0; i < subMatrices.size(); i++) {
            boolean isRoom = rooms.contains(subMatrices.get(i));
            CellCoordinates position = subMatrices.get(i).getInitalCell();
            for (int j = 0; j < subMatrices.get(i).getRows(); j++) {
                for (int k = 0; k < subMatrices.get(i).getColumns(); k++) {
                    matrixToUse.setValue(position.getRow() + j, position.getColumn() + k,
                            isRoom ? ROOM_COLOR : BACKGROUND_COLOR);
                }
            }

        }
    }

}
