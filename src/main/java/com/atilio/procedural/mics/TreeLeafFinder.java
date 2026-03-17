package com.atilio.procedural.mics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import com.atilio.procedural.entities.BinaryTreeNode;
import com.atilio.procedural.exceptions.AppException;

public class TreeLeafFinder {
    private TreeLeafFinder() {

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

    public static <T extends Serializable> Set<BinaryTreeNode<T>> getLeaves(
            Map<BinaryTreeNode<T>, Set<BinaryTreeNode<T>>> leavesMap) {
        return Set.copyOf(leavesMap.values().stream().flatMap(Set::stream).collect(Collectors.toSet()));
    }

    public static <T extends Serializable> Set<BinaryTreeNode<T>> getOnlyLeaves(BinaryTreeNode<T> treeNode) {
        Set<BinaryTreeNode<T>> leaves = new HashSet<>();
        Queue<BinaryTreeNode<T>> elementsToProcess = new LinkedList<>();
        BinaryTreeNode<T> current;
        elementsToProcess.add(treeNode);
        while (!elementsToProcess.isEmpty()) {
            current = elementsToProcess.remove();
            if (current.isLeaf()) {
                leaves.add(current);
            }

            if (current.getNodeLeft() != null) {
                elementsToProcess.add(current.getNodeLeft());
            }
            if (current.getNodeRight() != null) {
                elementsToProcess.add(current.getNodeRight());
            }
        }
        return leaves;
    }

    public static <T extends Serializable> Set<BinaryTreeNode<T>> getRandomSubset(Set<BinaryTreeNode<T>> allLeaves,
            int subsetSize) throws AppException {
        if (subsetSize < 1) {
            throw new AppException("tamaño de subset inválido");
        }
        if (allLeaves == null || allLeaves.isEmpty()) {
            throw new AppException("El set esta vacio o nulo");
        }
        if (subsetSize >= allLeaves.size()) {
            return Set.copyOf(allLeaves);
        }
        List<BinaryTreeNode<T>> list = new ArrayList<>(allLeaves);
        Collections.shuffle(list);
        return new HashSet<>(list.subList(0, subsetSize));
    }

    public static <T extends Serializable> List<T> getListOfElements(Set<BinaryTreeNode<T>> leaves)
            throws AppException {
        if (leaves == null || leaves.isEmpty()) {
            throw new AppException("El set esta vacio o nulo");
        }
        return leaves.stream().map(e -> e.getElement()).toList();

    }

    public static <T extends Serializable> BinaryTreeNode<T> getParentOfNode(
            Map<BinaryTreeNode<T>, Set<BinaryTreeNode<T>>> parentsAndChildrenNodes, BinaryTreeNode<T> value)
            throws AppException {
        Optional<BinaryTreeNode<T>> optionalParent = parentsAndChildrenNodes.entrySet().stream()
                .filter(entry -> entry.getValue().contains(value))
                .map(Entry::getKey).findFirst();
        if (optionalParent.isPresent()) {
            return optionalParent.get();
        } else {
            throw new AppException("El nodo indicado no tiene padre :( ");
        }

    }

    public static <T extends Serializable> Set<BinaryTreeNode<T>> getRandomSubsetWithoutSharingParents(
            Map<BinaryTreeNode<T>, Set<BinaryTreeNode<T>>> parentsAndChildrenNodes, Set<BinaryTreeNode<T>> allLeaves,
            int subsetSize) throws AppException {
        List<BinaryTreeNode<T>> randomSubset = new ArrayList<>();
        Set<BinaryTreeNode<T>> childsToRemove;
        int pivotSize = subsetSize;
        do {
            randomSubset.addAll(getRandomSubset(allLeaves, pivotSize));
            childsToRemove = getSiblingsToDelete(parentsAndChildrenNodes, randomSubset);
            allLeaves.removeAll(randomSubset);
            randomSubset.removeAll(childsToRemove);

            pivotSize = allLeaves.size() - childsToRemove.size();
            if (randomSubset.size() == subsetSize || pivotSize <= 0) {
                break;
            }

        } while (!childsToRemove.isEmpty() || !allLeaves.isEmpty() || pivotSize >= 0);
        while (randomSubset.size() > subsetSize) {
            randomSubset.remove(0);
        }
        return new HashSet<>(randomSubset);
    }

    private static <T extends Serializable> Set<BinaryTreeNode<T>> getSiblingsToDelete(
            Map<BinaryTreeNode<T>, Set<BinaryTreeNode<T>>> parentsAndChildrenNodes,
            List<BinaryTreeNode<T>> randomSubset)
            throws AppException {
        Set<BinaryTreeNode<T>> parents = new HashSet<>();
        Set<BinaryTreeNode<T>> childsToRemove = new HashSet<>();
        for (BinaryTreeNode<T> currentBinaryTreeNode : randomSubset) {
            BinaryTreeNode<T> currentParent = getParentOfNode(parentsAndChildrenNodes, currentBinaryTreeNode);
            if (parents.contains(currentParent)) {
                childsToRemove.add(currentBinaryTreeNode);
            } else {
                parents.add(currentParent);
            }
        }
        return childsToRemove;
    }

}
