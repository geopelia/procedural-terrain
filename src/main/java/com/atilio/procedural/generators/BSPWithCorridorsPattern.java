package com.atilio.procedural.generators;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
    private static final int SIBLING_COLOR = 25;

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
        Map<BinaryTreeNode<SubMatrix>, Set<BinaryTreeNode<SubMatrix>>> leafAndParents = TreeLeafFinder
                .getLeafAndParents(tree);

        int newSize = leaves.size() / 7;
        if (newSize == 0) {
            newSize = 1;
        }
        Set<BinaryTreeNode<SubMatrix>> leavesSelected;

        List<SubMatrix> rooms = new ArrayList<>();
        try {

            leavesSelected = TreeLeafFinder.getRandomSubsetWithoutSharingParents(leafAndParents, leaves, newSize);

            rooms = TreeLeafFinder.getListOfElements(leavesSelected);
        } catch (Exception e) {
            Logger.error("Error obteniendo las salas en la matrix");
            Logger.error(e);
            return;

        }
        for (int i = 0; i < subMatrices.size(); i++) {
            boolean isRoom = rooms.contains(subMatrices.get(i));
            CellCoordinates position = subMatrices.get(i).getInitalCell();
            for (int j = 0; j < subMatrices.get(i).getRows(); j++) {
                for (int k = 0; k < subMatrices.get(i).getColumns(); k++) {
                    int value = BACKGROUND_COLOR;
                    if (isRoom) {
                        value = ROOM_COLOR;
                    }
                    matrixToUse.setValue(position.getRow() + j, position.getColumn() + k,
                            value);
                }
            }

        }

    }

    /**
     *
     * @Deprecated(since = "now", forRemoval = true)
     * @param subMatrices
     * @param tree
     */
    private void drawInMainMatrixOLd(List<SubMatrix> subMatrices, BinaryTreeNode<SubMatrix> tree) {
        Set<BinaryTreeNode<SubMatrix>> leaves = TreeLeafFinder.getOnlyLeaves(tree);
        int newSize = leaves.size() / 4;
        if (newSize == 0) {
            newSize = 1;
        }
        Set<BinaryTreeNode<SubMatrix>> leavesSelected;
        List<SubMatrix> rooms = new ArrayList<>();
        List<SubMatrix> siblings = new ArrayList<>();
        Set<BinaryTreeNode<SubMatrix>> parents = new HashSet<>();
        try {
            subMatrices.retainAll(TreeLeafFinder.getListOfElements(leaves));
            Map<BinaryTreeNode<SubMatrix>, Set<BinaryTreeNode<SubMatrix>>> leafAndParents = TreeLeafFinder
                    .getLeafAndParents(tree);
            leavesSelected = TreeLeafFinder.getRandomSubsetWithoutSharingParents(leafAndParents, leaves, newSize);
            for (BinaryTreeNode<SubMatrix> binaryTreeNode : leavesSelected) {
                BinaryTreeNode<SubMatrix> parent = TreeLeafFinder.getParentOfNode(leafAndParents, binaryTreeNode);
                parents.add(parent);
            }
            for (BinaryTreeNode<SubMatrix> binaryTreeNode : parents) {
                if (leavesSelected.contains(binaryTreeNode.getNodeLeft())
                        && leavesSelected.contains(binaryTreeNode.getNodeRight())) {
                    siblings.add(binaryTreeNode.getNodeLeft().getElement());
                    siblings.add(binaryTreeNode.getNodeRight().getElement());
                }
            }
            rooms = TreeLeafFinder.getListOfElements(leavesSelected);
            if (!rooms.containsAll(siblings)) {
                throw new AppException("agregando vainas que no son");
            }
        } catch (AppException e) {
            Logger.error("Error obteniendo las salas en la matrix");
            Logger.error(e);
            return;
        }
        for (int i = 0; i < subMatrices.size(); i++) {
            boolean isSibling = siblings.contains(subMatrices.get(i));
            boolean isRoom = rooms.contains(subMatrices.get(i));
            CellCoordinates position = subMatrices.get(i).getInitalCell();
            for (int j = 0; j < subMatrices.get(i).getRows(); j++) {
                for (int k = 0; k < subMatrices.get(i).getColumns(); k++) {
                    int value = BACKGROUND_COLOR;
                    if (isSibling) {
                        value = SIBLING_COLOR;
                    } else if (isRoom) {
                        value = ROOM_COLOR;
                    }
                    matrixToUse.setValue(position.getRow() + j, position.getColumn() + k,
                            value);
                }
            }

        }
    }

}
