package com.atilio.procedural.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BinaryTreeNodeTest {
    @Test
    void testInsert() {
        String[] elements = new String[] { "a", "b", "c", "d", "f", "g", "h" };
        BinaryTreeNode<String> rootNode = createTreeNodes();

        assertEquals(elements[0], rootNode.getElement());
        assertEquals(elements[1], rootNode.getNodeLeft().getElement());
        assertEquals(elements[2], rootNode.getNodeRight().getElement());
        assertEquals(elements[3], rootNode.getNodeLeft().getNodeLeft().getElement());
        assertEquals(elements[4], rootNode.getNodeLeft().getNodeRight().getElement());
        assertEquals(elements[5], rootNode.getNodeRight().getNodeLeft().getElement());
        assertEquals(elements[6], rootNode.getNodeRight().getNodeRight().getElement());

    }

    private BinaryTreeNode<String> createTreeNodes() {
        BinaryTreeNode<String> binaryTreeNodeD = new BinaryTreeNode<String>("d");
        BinaryTreeNode<String> binaryTreeNodeF = new BinaryTreeNode<String>("f");
        BinaryTreeNode<String> binaryTreeNodeG = new BinaryTreeNode<String>("g");
        BinaryTreeNode<String> binaryTreeNodeH = new BinaryTreeNode<String>("h");
        BinaryTreeNode<String> binaryTreeNodeB = new BinaryTreeNode<String>("b", binaryTreeNodeD, binaryTreeNodeF);
        BinaryTreeNode<String> binaryTreeNodeC = new BinaryTreeNode<String>("c", binaryTreeNodeG, binaryTreeNodeH);
        return new BinaryTreeNode<String>("a", binaryTreeNodeB, binaryTreeNodeC);
    }

    @Test
    void testLeafs() {
        BinaryTreeNode<String> rootNode = createTreeNodes();
        assertFalse(rootNode.isLeaf());
        assertFalse(rootNode.getNodeLeft().isLeaf());
        assertFalse(rootNode.getNodeRight().isLeaf());
        assertTrue(rootNode.getNodeLeft().getNodeLeft().isLeaf());
        assertTrue(rootNode.getNodeLeft().getNodeRight().isLeaf());
        assertTrue(rootNode.getNodeRight().getNodeLeft().isLeaf());
        assertTrue(rootNode.getNodeRight().getNodeRight().isLeaf());
    }

}
