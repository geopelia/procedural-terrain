package com.atilio.procedural.mics;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import com.atilio.procedural.entities.BinaryTreeNode;

public class NodeConnectionCreator {
    private NodeConnectionCreator() {

    }

    public static <T extends Serializable> Map<BinaryTreeNode<T>, Set<BinaryTreeNode<T>>> getLeafAndParents(
            BinaryTreeNode<T> treeNode) {
        Map<BinaryTreeNode<T>, Set<BinaryTreeNode<T>>> parentsAndLeafs = new HashMap<>();
        Queue<BinaryTreeNode<T>> elementsToProcess = new LinkedList<>();
        BinaryTreeNode<T> current;
        elementsToProcess.add(treeNode);
        while (!elementsToProcess.isEmpty()) {
            Set<BinaryTreeNode<T>> children = new HashSet<>(2);
            current = elementsToProcess.remove();

            if (current.getNodeLeft() != null) {
                elementsToProcess.add(current.getNodeLeft());
                addToSet(current.getNodeLeft(), children);
            }
            if (current.getNodeRight() != null) {
                elementsToProcess.add(current.getNodeRight());

                addToSet(current.getNodeRight(), children);
            }
            if (!children.isEmpty()) {
                parentsAndLeafs.put(current, children);

            }

        }

        return parentsAndLeafs;
    }

    private static <T extends Serializable> void addToSet(BinaryTreeNode<T> node, Set<BinaryTreeNode<T>> set) {
        if (node.isLeaf()) {
            set.add(node);
        }
    }

    public static <T extends Serializable> Set<BinaryTreeNode<T>> getParentsOfLeafs(
            Map<BinaryTreeNode<T>, Set<BinaryTreeNode<T>>> leafsMap) {
        return Set.copyOf(leafsMap.keySet());
    }

    public static <T extends Serializable> Set<BinaryTreeNode<T>> getLeafs(
            Map<BinaryTreeNode<T>, Set<BinaryTreeNode<T>>> leavesMap) {
        return Set.copyOf(leavesMap.values().stream().flatMap(Set::stream).collect(Collectors.toSet()));
    }
}
