package com.atilio.procedural.panels;

import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class AppFrame extends JFrame  {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    public AppFrame(int[][] mainMatrix) {
        Terrain terrain = new Terrain(mainMatrix);
        add(terrain);

        JButton refreshButton = new JButton("Refrescar");
        refreshButton.addActionListener(e -> terrain.repaint());
        refreshButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(refreshButton);

        JButton treeViewerButton = new JButton("Ver ultimo árbol");
        treeViewerButton.addActionListener(e -> new WindowFrame());
        treeViewerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(treeViewerButton);

        setTitle("Terreno Generado ");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}
