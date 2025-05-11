package com.atilio.procedural.mics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import com.atilio.procedural.entities.BinaryTree;
import com.atilio.procedural.entities.BinaryTreeNode;
import com.atilio.procedural.entities.CellCoordinates;
import com.atilio.procedural.entities.SubMatrix;
import com.atilio.procedural.exceptions.AppException;

class NodeConnectionCreatorTest {
    @Test
    void testParentsOfLeaf() {
        BinaryTree binaryTree = createBinaryTree(15);
        BinaryTreeNode<SubMatrix> rootNode = binaryTree.getRoot();
        Map<BinaryTreeNode<SubMatrix>, Set<BinaryTreeNode<SubMatrix>>> leafAndParents = TreeLeafFinder
                .getLeafAndParents(rootNode);
        assertNotNull(leafAndParents);
        assertFalse(leafAndParents.isEmpty());
        assertEquals(4, leafAndParents.size());
        binaryTree = createBinaryTree(8);
        leafAndParents = TreeLeafFinder.getLeafAndParents(binaryTree.getRoot());
        assertEquals(3, leafAndParents.size());
        Set<BinaryTreeNode<SubMatrix>> parentsOfLeafs = TreeLeafFinder.getParentsOfLeafs(leafAndParents);
        assertEquals(3, parentsOfLeafs.size());
        for (BinaryTreeNode<SubMatrix> node : parentsOfLeafs) {
            assertTrue(node.getNodeLeft() != null || node.getNodeRight() != null);
        }
        Set<BinaryTreeNode<SubMatrix>> leaves = TreeLeafFinder.getLeaves(leafAndParents);
        assertEquals(4, leaves.size());
    }

    @Test
    void testParentsOfLeafOnlyRoot() {
        BinaryTreeNode<String> binaryTreeNodeD = new BinaryTreeNode<String>("d");
        Map<BinaryTreeNode<String>, Set<BinaryTreeNode<String>>> leafAndParents = TreeLeafFinder
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
        Map<BinaryTreeNode<String>, Set<BinaryTreeNode<String>>> leafAndParents = TreeLeafFinder
                .getLeafAndParents(root);
        assertNotNull(leafAndParents);
        assertFalse(leafAndParents.isEmpty());
        assertEquals(2, leafAndParents.size());
        Set<BinaryTreeNode<String>> parentsOfLeafs = TreeLeafFinder.getParentsOfLeafs(leafAndParents);
        assertEquals(2, parentsOfLeafs.size());
        assertTrue(parentsOfLeafs.contains(root));
        assertTrue(parentsOfLeafs.contains(binaryTreeNodeB));
        Set<BinaryTreeNode<String>> leaves = TreeLeafFinder.getLeaves(leafAndParents);
        assertTrue(leaves.contains(binaryTreeNodeC));
        assertTrue(leaves.contains(binaryTreeNodeD));
        assertTrue(leaves.contains(binaryTreeNodeF));
        Set<BinaryTreeNode<String>> leaves2 = TreeLeafFinder.getOnlyLeaves(root);
        assertNotNull(leaves2);
        assertFalse(leaves2.isEmpty());
        assertEquals(3, leaves2.size());
        assertTrue(leaves2.contains(binaryTreeNodeC));
        assertTrue(leaves2.contains(binaryTreeNodeD));
        assertTrue(leaves2.contains(binaryTreeNodeF));
        int size = 2;
        Set<BinaryTreeNode<String>> leaves3 = null;
        try {
            leaves3 = TreeLeafFinder.getRandomSubset(leaves2, size);
        } catch (AppException e) {
            fail(e);
        }
        final Set<BinaryTreeNode<String>> leaves4 = Set.copyOf(leaves3);
        assertNotNull(leaves3);
        assertFalse(leaves3.isEmpty());
        assertEquals(2, leaves3.size());
        Stream<BinaryTreeNode<String>> myStream = Stream.of(binaryTreeNodeC, binaryTreeNodeD, binaryTreeNodeF);
        assertEquals(2, myStream.filter(leaves4::contains).count());

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
        Map<BinaryTreeNode<String>, Set<BinaryTreeNode<String>>> leafAndParents = TreeLeafFinder
                .getLeafAndParents(root);
        Set<BinaryTreeNode<String>> parentsOfLeafs = TreeLeafFinder.getParentsOfLeafs(leafAndParents);
        assertEquals(2, parentsOfLeafs.size());
        assertTrue(parentsOfLeafs.contains(binaryTreeNodeC));
        assertTrue(parentsOfLeafs.contains(binaryTreeNodeB));
        Set<BinaryTreeNode<String>> leaves = TreeLeafFinder.getLeaves(leafAndParents);
        assertTrue(leaves.contains(binaryTreeNodeD));
        assertTrue(leaves.contains(binaryTreeNodeF));
        assertTrue(leaves.contains(binaryTreeNodeG));
        assertTrue(leaves.contains(binaryTreeNodeH));
        assertEquals(4, leaves.size());
        Set<BinaryTreeNode<String>> leaves2 = TreeLeafFinder.getOnlyLeaves(root);
        assertNotNull(leaves2);
        assertFalse(leaves2.isEmpty());
        assertEquals(4, leaves2.size());
        assertTrue(leaves2.contains(binaryTreeNodeD));
        assertTrue(leaves2.contains(binaryTreeNodeF));
        assertTrue(leaves2.contains(binaryTreeNodeG));
        assertTrue(leaves2.contains(binaryTreeNodeH));
        int size = 2;
        Set<BinaryTreeNode<String>> leaves3 = null;
        try {
            leaves3 = TreeLeafFinder.getRandomSubset(leaves2, size);
        } catch (AppException e) {
            fail();
        }
        assertNotNull(leaves3);
        assertFalse(leaves3.isEmpty());
        assertEquals(2, leaves3.size());
        size = 10;
        try {
            leaves3 = TreeLeafFinder.getRandomSubset(leaves2, size);
        } catch (AppException e) {
            fail();
        }
        assertEquals(4, leaves3.size());

    }

    @Test
    void otherLeafTest() {
        BinaryTreeNode<String> binaryTreeNodeD = new BinaryTreeNode<String>("d");
        BinaryTreeNode<String> binaryTreeNodeF = new BinaryTreeNode<String>("f");
        Set<BinaryTreeNode<String>> leaves = Set.of(binaryTreeNodeD, binaryTreeNodeF);
        assertThrows(AppException.class, () -> {
            TreeLeafFinder.getRandomSubset(leaves, 0);
        });
        assertThrows(AppException.class, () -> {
            TreeLeafFinder.getRandomSubset(leaves, -1);
        });
        Set<BinaryTreeNode<String>> leaves2 = Set.of();
        assertThrows(AppException.class, () -> {
            TreeLeafFinder.getRandomSubset(leaves2, 4);
        });

        Set<BinaryTreeNode<String>> leaves3 = null;
        assertThrows(AppException.class, () -> {
            TreeLeafFinder.getRandomSubset(leaves3, 4);
        });
    }

    @Test
    void testTransformToList() {
        BinaryTreeNode<String> binaryTreeNodeD = new BinaryTreeNode<String>("d");
        BinaryTreeNode<String> binaryTreeNodeF = new BinaryTreeNode<String>("f");
        Set<BinaryTreeNode<String>> leaves = Set.of(binaryTreeNodeD, binaryTreeNodeF);
        List<String> list = null;
        try {
            list = TreeLeafFinder.getListOfElements(leaves);
        } catch (AppException e) {
            fail(e);
        }
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(2, list.size());
        assertTrue(list.contains("d"));
        assertTrue(list.contains("f"));

    }

}
