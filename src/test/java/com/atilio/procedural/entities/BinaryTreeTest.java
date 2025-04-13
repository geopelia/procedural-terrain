package com.atilio.procedural.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class BinaryTreeTest {
    @Test
    void testAdd() {
        List<SubMatrix> matrices = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            CellCoordinates cellCoordinates = new CellCoordinates(0 + i, 2 * i);
            SubMatrix subMatrix = new SubMatrix(cellCoordinates, 2 + i, 2);
            matrices.add(subMatrix);
        }
        BinaryTree binaryTree = new BinaryTree();
        for (int i = 0; i < matrices.size(); i++) {
            binaryTree.addNode(matrices.get(i));
        }
        assertEquals(matrices.get(0), binaryTree.getRoot().getElement());
        assertEquals(matrices.get(1), binaryTree.getRoot().getNodeLeft().getElement());
        assertEquals(matrices.get(2), binaryTree.getRoot().getNodeRight().getElement());
        assertEquals(matrices.get(3), binaryTree.getRoot().getNodeLeft().getNodeLeft().getElement());
        assertEquals(matrices.get(4), binaryTree.getRoot().getNodeLeft().getNodeRight().getElement());
        assertEquals(matrices.get(5), binaryTree.getRoot().getNodeRight().getNodeLeft().getElement());
        assertEquals(matrices.get(6), binaryTree.getRoot().getNodeRight().getNodeRight().getElement());
        assertEquals(matrices.get(7), binaryTree.getRoot().getNodeLeft().getNodeLeft().getNodeLeft().getElement());
        assertEquals(matrices.get(8), binaryTree.getRoot().getNodeLeft().getNodeLeft().getNodeRight().getElement());
        assertEquals(matrices.get(9), binaryTree.getRoot().getNodeLeft().getNodeRight().getNodeLeft().getElement());
        assertEquals(matrices.get(10), binaryTree.getRoot().getNodeLeft().getNodeRight().getNodeRight().getElement());
        assertEquals(matrices.get(11), binaryTree.getRoot().getNodeRight().getNodeLeft().getNodeLeft().getElement());
        assertEquals(matrices.get(12), binaryTree.getRoot().getNodeRight().getNodeLeft().getNodeRight().getElement());
        assertEquals(matrices.get(13), binaryTree.getRoot().getNodeRight().getNodeRight().getNodeLeft().getElement());
        assertEquals(matrices.get(14), binaryTree.getRoot().getNodeRight().getNodeRight().getNodeRight().getElement());
    }

    @Test
    void testAdd2() {
        String[] elements = new String[15];
        BinaryTreeString binaryTree = new BinaryTreeString();
        for (int i = 0; i < elements.length; i++) {
            elements[i] = String.valueOf(i + 1);
            binaryTree.addNode(elements[i]);
        }
        assertEquals(elements[0], binaryTree.getRoot().getElement());
        assertEquals(elements[1], binaryTree.getRoot().getNodeLeft().getElement());
        assertEquals(elements[2], binaryTree.getRoot().getNodeRight().getElement());
        assertEquals(elements[3], binaryTree.getRoot().getNodeLeft().getNodeLeft().getElement());
        assertEquals(elements[4], binaryTree.getRoot().getNodeLeft().getNodeRight().getElement());
        assertEquals(elements[5], binaryTree.getRoot().getNodeRight().getNodeLeft().getElement());
        assertEquals(elements[6], binaryTree.getRoot().getNodeRight().getNodeRight().getElement());
        assertEquals(elements[7], binaryTree.getRoot().getNodeLeft().getNodeLeft().getNodeLeft().getElement());
        assertEquals(elements[8], binaryTree.getRoot().getNodeLeft().getNodeLeft().getNodeRight().getElement());
        assertEquals(elements[9], binaryTree.getRoot().getNodeLeft().getNodeRight().getNodeLeft().getElement());
        assertEquals(elements[10], binaryTree.getRoot().getNodeLeft().getNodeRight().getNodeRight().getElement());
        assertEquals(elements[11], binaryTree.getRoot().getNodeRight().getNodeLeft().getNodeLeft().getElement());
        assertEquals(elements[12], binaryTree.getRoot().getNodeRight().getNodeLeft().getNodeRight().getElement());
        assertEquals(elements[13], binaryTree.getRoot().getNodeRight().getNodeRight().getNodeLeft().getElement());
        assertEquals(elements[14], binaryTree.getRoot().getNodeRight().getNodeRight().getNodeRight().getElement());

    }

}
