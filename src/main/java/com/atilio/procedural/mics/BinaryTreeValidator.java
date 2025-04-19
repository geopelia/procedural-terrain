package com.atilio.procedural.mics;

import java.io.Serializable;

import com.atilio.procedural.entities.BinaryTreeNode;

public class BinaryTreeValidator {
    private BinaryTreeValidator() {

    }

    /**
     * check if tree is full, meaning it has either zero or two child nodes for each node.
     * @param <T> A generic param could be anything
     * @param binaryTreeNode My node that represent a binary tree.
     * @return If the tree is full or not.
     */
    public static <T extends Serializable> boolean isFullTree(BinaryTreeNode<T> binaryTreeNode) {
        boolean hasLeft = false;
        boolean hasRight = false;
        boolean result = false;
        if (binaryTreeNode == null) {
            return false;
        }
        // It's a leaf
        if (binaryTreeNode.getNodeLeft() == null && binaryTreeNode.getNodeRight() == null) {
            return true;
        }
        hasLeft = binaryTreeNode.getNodeLeft() != null;
        hasRight = binaryTreeNode.getNodeRight() != null;
        result = hasLeft && hasRight;
        result = result && isFullTree(binaryTreeNode.getNodeLeft());
        result = result && isFullTree(binaryTreeNode.getNodeRight());
        return result;

    }

    public static <T extends Serializable> boolean areChildrenSameHeight(BinaryTreeNode<T> node) {
        if (node == null) {
            return true;
        }
        return getNodeHeight(node.getNodeLeft()) == getNodeHeight(node.getNodeRight());
    }

    public static <T extends Serializable> boolean isCompleteNode(BinaryTreeNode<T> node) {
        return (node != null) && (node.getNodeLeft() != null) && (node.getNodeRight() != null);
    }

    public static <T extends Serializable> int getNodeHeight(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getNodeHeight(node.getNodeLeft()), getNodeHeight(node.getNodeRight())) + 1;

    }

    public static <T extends Serializable> boolean isAlmostPerfectTree(BinaryTreeNode<T> node) {
        return isFullTree(node) && areChildrenSameHeight(node);
    }

}
