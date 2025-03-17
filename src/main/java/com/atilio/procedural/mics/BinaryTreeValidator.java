package com.atilio.procedural.mics;

import java.util.List;

import com.atilio.procedural.entities.BinaryTree;
import com.atilio.procedural.entities.BinaryTreeNode;
import com.atilio.procedural.entities.SubMatrix;

public class BinaryTreeValidator {
    private BinaryTreeValidator() {

    }

    public static <T> boolean isCompleteTree(BinaryTreeNode<T> binaryTreeNode) {
        boolean hasLeft = false;
        boolean hasRight = false;
        boolean result = false;
        if (binaryTreeNode == null) {
            return false;
        }
        hasLeft = binaryTreeNode.getNodeLeft() != null;
        hasRight = binaryTreeNode.getNodeRight() != null;
        result = hasLeft && hasRight;
        result = result && isCompleteTree(binaryTreeNode.getNodeLeft());
        result = result && isCompleteTree(binaryTreeNode.getNodeRight());
        return result;

    }
}
