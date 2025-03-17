package com.atilio.procedural.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class BinaryTreeTest {
    @Test
    public void testAdd() {
        List<SubMatrix> matrices = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CellCoordinates cellCoordinates = new CellCoordinates(0, 0);
            SubMatrix subMatrix = new SubMatrix(cellCoordinates, 2, 2);
            matrices.add(subMatrix);
        }
        BinaryTree binaryTree = new BinaryTree();
        for (SubMatrix subMatrix2 : matrices) {
            binaryTree.addNode(subMatrix2);
        }
        assertEquals(binaryTree.getRoot().getElement(), matrices.get(0));
        assertEquals(binaryTree.getRoot().getNodeLeft().getElement(), matrices.get(1));
        // assertEquals(binaryTree.getRoot().getNodeRight().getElement(), matrices.get(2));
    }
}
