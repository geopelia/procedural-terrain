package com.atilio.procedural.entities;

public class BinaryTreeNode<T> {
    private T element;
    private BinaryTreeNode<T> nodeLeft;
    private BinaryTreeNode<T> nodeRight;

    public BinaryTreeNode(T element, BinaryTreeNode<T> nodeLeft, BinaryTreeNode<T> nodeRight) {
        this.element = element;
        this.nodeLeft = nodeLeft;
        this.nodeRight = nodeRight;
    }

    public BinaryTreeNode(T element) {
        this.element = element;
        this.nodeLeft = null;
        this.nodeRight = null;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public BinaryTreeNode<T> getNodeLeft() {
        return nodeLeft;
    }

    public void setNodeLeft(BinaryTreeNode<T> nodeLeft) {
        this.nodeLeft = nodeLeft;
    }

    public BinaryTreeNode<T> getNodeRight() {
        return nodeRight;
    }

    public void setNodeRight(BinaryTreeNode<T> nodeRight) {
        this.nodeRight = nodeRight;
    }

}
