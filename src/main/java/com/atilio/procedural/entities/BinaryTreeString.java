package com.atilio.procedural.entities;

import com.atilio.procedural.mics.BinaryTreeValidator;

public class BinaryTreeString {
    private BinaryTreeNode<String> root = null;

    private BinaryTreeNode<String> addRecursive(BinaryTreeNode<String> current, String value) {
        if (current == null) {
            return new BinaryTreeNode<>(value);
        }
        if (current.getNodeLeft() == null) {
            current.setNodeLeft(addRecursive(current.getNodeLeft(), value));
        } else if (current.getNodeRight() == null) {
            current.setNodeRight(addRecursive(current.getNodeRight(), value));
        } else {
            if (BinaryTreeValidator.getNodeHeight(current.getNodeLeft()) <= BinaryTreeValidator
                    .getNodeHeight(current.getNodeRight())) {
                if (BinaryTreeValidator.isAlmostPerfectTree(current.getNodeRight())) {

                    addRecursive(current.getNodeLeft(), value);
                } else {
                    addRecursive(current.getNodeRight(), value);
                }
            } else {
                if (BinaryTreeValidator.isAlmostPerfectTree(current.getNodeLeft())) {
                    addRecursive(current.getNodeRight(), value);
                } else {
                    addRecursive(current.getNodeLeft(), value);
                }
            }
        }
        return current;
    }

    public void addNode(String value) {
        root = addRecursive(root, value);
    }

    public BinaryTreeNode<String> getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode<String> root) {
        this.root = root;
    }

}
