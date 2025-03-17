package com.atilio.procedural.entities;

public class BinaryTree {
    private BinaryTreeNode<SubMatrix> root = null;


    private BinaryTreeNode<SubMatrix> addRecursive(BinaryTreeNode<SubMatrix> current, SubMatrix value) {
        if (current == null) {
            return new BinaryTreeNode<>(value);
        }
        CellCoordinates cur = current.getElement().getInitalCell();
        CellCoordinates val = value.getInitalCell();
        if (cur.equals(val)) {
            current.setNodeLeft(addRecursive(current.getNodeLeft(), value));
        } else {
            current.setNodeRight(addRecursive(current.getNodeRight(), value));
        }
        return current;
    }

    public void addNode(SubMatrix value){
        root = addRecursive(root, value);
    }

    public BinaryTreeNode<SubMatrix> getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode<SubMatrix> root) {
        this.root = root;
    }

}
