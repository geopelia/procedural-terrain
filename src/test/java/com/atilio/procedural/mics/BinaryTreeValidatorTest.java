package com.atilio.procedural.mics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.atilio.procedural.entities.BinaryTreeNode;

class BinaryTreeValidatorTest {
    @Test
    void testHeight() {
        BinaryTreeNode<Integer> tree = new BinaryTreeNode<Integer>(52);
        assertEquals(1, BinaryTreeValidator.getNodeHeight(tree));
        BinaryTreeNode<Integer> node2 = new BinaryTreeNode<Integer>(44);
        BinaryTreeNode<Integer> node3 = new BinaryTreeNode<Integer>(4);
        tree.setNodeLeft(node3);
        tree.setNodeRight(node2);
        assertEquals(2, BinaryTreeValidator.getNodeHeight(tree));
        tree = createCompleteTree(10);
        assertEquals(10, BinaryTreeValidator.getNodeHeight(tree));
    }

    @Test
    void testIsCompleteNode() {
        BinaryTreeNode<Integer> tree = new BinaryTreeNode<Integer>(52);
        assertFalse(BinaryTreeValidator.isCompleteNode(tree));
        BinaryTreeNode<Integer> node2 = new BinaryTreeNode<Integer>(44);
        tree.setNodeRight(node2);
        assertFalse(BinaryTreeValidator.isCompleteNode(tree));
        BinaryTreeNode<Integer> node3 = new BinaryTreeNode<Integer>(4);
        BinaryTreeNode<Integer> node4 = new BinaryTreeNode<Integer>(48);
        node2.setNodeLeft(node4);
        node2.setNodeRight(node3);
        assertTrue(BinaryTreeValidator.isCompleteNode(node2));

    }

    @Test
    void testIsCompleteTree() {

        BinaryTreeNode<Integer> tree = new BinaryTreeNode<Integer>(52);
        assertFalse(BinaryTreeValidator.isFullTree(null));
        assertTrue(BinaryTreeValidator.isFullTree(tree));
        BinaryTreeNode<Integer> node2 = new BinaryTreeNode<Integer>(44);
        BinaryTreeNode<Integer> node3 = new BinaryTreeNode<Integer>(4);
        BinaryTreeNode<Integer> node4 = new BinaryTreeNode<Integer>(48);
        node2.setNodeLeft(node4);
        node2.setNodeRight(node3);
        assertTrue(BinaryTreeValidator.isFullTree(node2));
        BinaryTreeNode<Integer> node5 = new BinaryTreeNode<Integer>(48);
        node3.setNodeRight(node5);
        assertFalse(BinaryTreeValidator.isFullTree(node2));
        tree = createCompleteTree(4);
        assertTrue(BinaryTreeValidator.isFullTree(tree));
        tree.getNodeLeft().getNodeLeft().setNodeRight(null);
        assertFalse(BinaryTreeValidator.isFullTree(tree));
    }

    @Test
    void testAreChildrenSameHeight() {
        BinaryTreeNode<Integer> tree = new BinaryTreeNode<Integer>(52);
        BinaryTreeNode<Integer> node2 = new BinaryTreeNode<Integer>(44);
        BinaryTreeNode<Integer> node3 = new BinaryTreeNode<Integer>(4);
        tree.setNodeLeft(node3);
        tree.setNodeRight(node2);
        assertTrue(BinaryTreeValidator.areChildrenSameHeight(tree));
        BinaryTreeNode<Integer> node4 = new BinaryTreeNode<Integer>(54);
        BinaryTreeNode<Integer> node5 = new BinaryTreeNode<Integer>(584);
        node3.setNodeLeft(node5);
        node3.setNodeRight(node4);
        assertFalse(BinaryTreeValidator.areChildrenSameHeight(tree));
        BinaryTreeNode<Integer> node6 = new BinaryTreeNode<Integer>(444);
        BinaryTreeNode<Integer> node7 = new BinaryTreeNode<Integer>(43);
        node2.setNodeLeft(node7);
        node2.setNodeRight(node6);
        assertTrue(BinaryTreeValidator.areChildrenSameHeight(tree));
        BinaryTreeNode<Integer> node8 = new BinaryTreeNode<Integer>(84);
        BinaryTreeNode<Integer> node9 = new BinaryTreeNode<Integer>(412);
        node5.setNodeLeft(node9);
        node5.setNodeRight(node8);
        assertFalse(BinaryTreeValidator.areChildrenSameHeight(tree));
    }

    private BinaryTreeNode<Integer> createCompleteTree(int height) {
        int totalElements = CustomMathFunctions.getTreeSize(height);
        List<BinaryTreeNode<Integer>> list = new ArrayList<>(totalElements);
        for (int i = 0; i < totalElements; i++) {
            list.add(new BinaryTreeNode<Integer>(i * 2));
        }
        int index = 0;
        while ((2 * index + 2) <= totalElements) {
            list.get(index).setNodeLeft(list.get((2 * index + 1)));
            list.get(index).setNodeRight(list.get((2 * index + 2)));
            index++;
        }
        return list.get(0);

    }
}
