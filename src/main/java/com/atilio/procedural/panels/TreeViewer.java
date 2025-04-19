package com.atilio.procedural.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JPanel;

import com.atilio.procedural.entities.BinaryTreeNode;
import com.atilio.procedural.entities.SubMatrix;

public class TreeViewer<M extends Serializable> extends JPanel {
    private BinaryTreeNode<M> rootTreeNode;

    @SuppressWarnings("unchecked")
    public void setRootTreeNode(BinaryTreeNode<SubMatrix> rootTreeNode) {
        this.rootTreeNode = (BinaryTreeNode<M>) rootTreeNode;
    }

    public TreeViewer(){ }

    public TreeViewer(BinaryTreeNode<M> root) {
        this.rootTreeNode = root;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree(g, rootTreeNode, getWidth() / 2, 30, getWidth() / 4);
    }

    private void drawTree(Graphics g, BinaryTreeNode<M> node, int x, int y, int xOffset) {
        if (node == null)
            return;

        g.setColor(Color.BLACK);
        g.fillOval(x - 15, y - 15, 30, 30);
        g.setColor(Color.WHITE);
        g.drawString(node.getElement().toString().substring(0, 10), x - 5, y + 5);

        if (node.getNodeLeft() != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x, y, x - xOffset, y + 50);
            drawTree(g, node.getNodeLeft(), x - xOffset, y + 50, xOffset / 2);
        }

        if (node.getNodeRight() != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x, y, x + xOffset, y + 50);
            drawTree(g, node.getNodeRight(), x + xOffset, y + 50, xOffset / 2);
        }
    }

}
