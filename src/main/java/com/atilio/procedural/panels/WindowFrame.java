package com.atilio.procedural.panels;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.atilio.procedural.entities.BinaryTreeNode;
import com.atilio.procedural.entities.SubMatrix;
import com.atilio.procedural.mics.TreeToStream;

public class WindowFrame  extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public WindowFrame() {
        BinaryTreeNode<SubMatrix> treeNode = TreeToStream.loadFromStream(null);
        TreeViewer<BinaryTreeNode<SubMatrix>> viewer = new TreeViewer<>();
        viewer.setRootTreeNode(treeNode);
        add(viewer);
        setTitle("Visor de arbol");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }
}
