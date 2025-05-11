package com.atilio.procedural.mics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.atilio.procedural.entities.BinaryTree;
import com.atilio.procedural.entities.BinaryTreeNode;
import com.atilio.procedural.entities.CellCoordinates;
import com.atilio.procedural.entities.SubMatrix;

class NodeConnectionCreatorTest {
    @Test
    void testParentsOfLeaf() {
        BinaryTree binaryTree = createBinaryTree(15);
        BinaryTreeNode<SubMatrix> rootNode = binaryTree.getRoot();
        Map<BinaryTreeNode<SubMatrix>, Set<BinaryTreeNode<SubMatrix>>> leafAndParents = NodeConnectionCreator
                .getLeafAndParents(rootNode);
        assertNotNull(leafAndParents);
        assertFalse(leafAndParents.isEmpty());
        assertEquals(4, leafAndParents.size());
        binaryTree = createBinaryTree(8);
        leafAndParents = NodeConnectionCreator.getLeafAndParents(binaryTree.getRoot());
        assertEquals(3, leafAndParents.size());
        Set<BinaryTreeNode<SubMatrix>> parentsOfLeafs = NodeConnectionCreator.getParentsOfLeafs(leafAndParents);
        assertEquals(3, parentsOfLeafs.size());
        for (BinaryTreeNode<SubMatrix> node : parentsOfLeafs) {
            assertTrue(node.getNodeLeft() != null || node.getNodeRight() != null);
        }
        Set<BinaryTreeNode<SubMatrix>> leaves =NodeConnectionCreator.getLeafs(leafAndParents);
        assertEquals(4, leaves.size());
    }

    @Test
    void testParentsOfLeafOnlyRoot() {
        BinaryTreeNode<String> binaryTreeNodeD = new BinaryTreeNode<String>("d");
        Map<BinaryTreeNode<String>, Set<BinaryTreeNode<String>>> leafAndParents = NodeConnectionCreator
                .getLeafAndParents(binaryTreeNodeD);
        assertNotNull(leafAndParents);
        assertTrue(leafAndParents.isEmpty());
    }

    private BinaryTree createBinaryTree(int quantityElements) {
        BinaryTree binaryTree = new BinaryTree();
        for (int i = 0; i < quantityElements; i++) {
            CellCoordinates cellCoordinates = new CellCoordinates(0 + i, 2 * i);
            SubMatrix subMatrix = new SubMatrix(cellCoordinates, 2 + i, 2);
            binaryTree.addNode(subMatrix);
        }
        return binaryTree;
    }

    @Test
    void testIncompleteTree() {
        BinaryTreeNode<String> binaryTreeNodeD = new BinaryTreeNode<String>("d");
        BinaryTreeNode<String> binaryTreeNodeF = new BinaryTreeNode<String>("f");
        BinaryTreeNode<String> binaryTreeNodeB = new BinaryTreeNode<String>("b", binaryTreeNodeD, binaryTreeNodeF);
        BinaryTreeNode<String> binaryTreeNodeC = new BinaryTreeNode<String>("c");
        BinaryTreeNode<String> root = new BinaryTreeNode<String>("a", binaryTreeNodeB, binaryTreeNodeC);
        Map<BinaryTreeNode<String>, Set<BinaryTreeNode<String>>> leafAndParents = NodeConnectionCreator
                .getLeafAndParents(root);
        assertNotNull(leafAndParents);
        assertFalse(leafAndParents.isEmpty());
        assertEquals(2, leafAndParents.size());
        Set<BinaryTreeNode<String>> parentsOfLeafs = NodeConnectionCreator.getParentsOfLeafs(leafAndParents);
        assertEquals(2, parentsOfLeafs.size());
        assertTrue(parentsOfLeafs.contains(root));
        assertTrue(parentsOfLeafs.contains(binaryTreeNodeB));
        Set<BinaryTreeNode<String>> leaves = NodeConnectionCreator.getLeafs(leafAndParents);
        assertTrue(leaves.contains(binaryTreeNodeC));
        assertTrue(leaves.contains(binaryTreeNodeD));
        assertTrue(leaves.contains(binaryTreeNodeF));

    }

    @Test
    void testGetParents() {
        BinaryTreeNode<String> binaryTreeNodeD = new BinaryTreeNode<String>("d");
        BinaryTreeNode<String> binaryTreeNodeF = new BinaryTreeNode<String>("f");
        BinaryTreeNode<String> binaryTreeNodeG = new BinaryTreeNode<String>("g");
        BinaryTreeNode<String> binaryTreeNodeH = new BinaryTreeNode<String>("h");
        BinaryTreeNode<String> binaryTreeNodeB = new BinaryTreeNode<String>("b", binaryTreeNodeD, binaryTreeNodeF);
        BinaryTreeNode<String> binaryTreeNodeC = new BinaryTreeNode<String>("c", binaryTreeNodeG, binaryTreeNodeH);
        BinaryTreeNode<String> root = new BinaryTreeNode<String>("a", binaryTreeNodeB, binaryTreeNodeC);
        Map<BinaryTreeNode<String>, Set<BinaryTreeNode<String>>> leafAndParents = NodeConnectionCreator
                .getLeafAndParents(root);
        Set<BinaryTreeNode<String>> parentsOfLeafs = NodeConnectionCreator.getParentsOfLeafs(leafAndParents);
        assertEquals(2, parentsOfLeafs.size());
        assertTrue(parentsOfLeafs.contains(binaryTreeNodeC));
        assertTrue(parentsOfLeafs.contains(binaryTreeNodeB));
        Set<BinaryTreeNode<String>> leaves = NodeConnectionCreator.getLeafs(leafAndParents);
        assertTrue(leaves.contains(binaryTreeNodeD));
        assertTrue(leaves.contains(binaryTreeNodeF));
        assertTrue(leaves.contains(binaryTreeNodeG));
        assertTrue(leaves.contains(binaryTreeNodeH));
        assertEquals(4, leaves.size());
    }

}
