package com.atilio.procedural.mics;

import java.util.LinkedList;
import java.util.Queue;

import com.atilio.procedural.entities.BinaryTreeNode;

public class PrintToConsoleFunctions {
    private PrintToConsoleFunctions() {
    }

    public static <T> void printTreeBreadthFirst(BinaryTreeNode<T> tree, int elementsSize) {
        if (tree == null) {
            return;
        }
        Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
        BinaryTreeNode<T> current;
        queue.add(tree);
        int alt = CustomMathFunctions.getTreeHeight(elementsSize);
        int[] sizes = new int[alt];
        for (int j = 1; j <= alt; j++) {
            sizes[j - 1] = CustomMathFunctions.getTreeSize(j);
        }
        int i = 0;
        int nivel = 0;
        while (!queue.isEmpty()) {
            current = queue.remove();
            System.out.println("elem: " + current.getElement() + " hash: " + current.hashCode());
            if (current.getNodeLeft() != null) {
                queue.add(current.getNodeLeft());
            }
            if (current.getNodeRight() != null) {
                queue.add(current.getNodeRight());
            }
            if (isInArray(i, sizes)) {
                System.out.println("=======================> nivel " + nivel++);
            }
            i++;

        }
    }

    public static <T> void printTreePreOrder(BinaryTreeNode<T> tree) {
        if (tree == null) {
            return;
        }
        System.out.println(tree.getElement());
        BinaryTreeNode<T> left = tree.getNodeLeft();
        BinaryTreeNode<T> right = tree.getNodeRight();
        printTreePreOrder(left);
        printTreePreOrder(right);
        System.out.println("#########################################");
    }

    public static boolean isInArray(int value, int[] array) {
        if (value == 0) {
            return true;
        }
        for (int i = 0; i < array.length; i++) {
            if (value == (array[i]) - 1) {
                return true;
            }
        }
        return false;
    }
}
