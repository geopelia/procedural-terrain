package com.atilio.procedural.panels;

import javax.swing.JPanel;

import com.atilio.procedural.entities.CustomColor;
import com.atilio.procedural.mics.ColorPalette;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.List;

public class Terrain extends JPanel {
    private final int[][] matrix;
    private static final Color DEFAULT_COLOR = Color.black;
    private transient List<CustomColor> availableColors;

    public Terrain(int[][] matrix) {
        this.matrix = matrix;
        availableColors = ColorPalette.loadPalette();
    }

    private void doDrawing(Graphics arg0) {
        Graphics2D graphics2d = (Graphics2D) arg0;
        graphics2d.setPaint(DEFAULT_COLOR);
        int totalRows = matrix.length;
        int totalCols = matrix[0].length;
        int cellWidth = (getWidth() - totalCols) / totalCols;
        int cellHeight = (getHeight() - totalRows) / totalRows;
        int marginX = (getWidth() - totalCols - (cellWidth * totalCols)) / 2;
        int marginY = (getHeight() - totalRows - (cellHeight * totalRows)) / 2;
        if (marginX < 0) {
            marginX = 0;
        }
        if (marginY < 0) {
            marginY = 0;
        }
        int positionX = marginX;
        int positionY;
        for (int i = 0; i < totalCols; i++) {
            positionY = marginY;
            for (int j = 0; j < totalRows; j++) {
                Rectangle rectangle = new Rectangle(positionX, positionY, cellWidth, cellHeight);
                graphics2d.draw(rectangle);
                Color color;
                if (matrix[j][i] != -1) {
                    CustomColor customColor = availableColors.get(matrix[j][i]);
                    color = new Color(customColor.getRed(), customColor.getGreen(), customColor.getBlue(), 255);
                } else {
                    color = Color.BLACK;
                }
                fillWithColor(graphics2d, rectangle, color);
                positionY += cellHeight + 1;
            }
            positionX += cellWidth + 1;
        }

    }

    private void fillWithColor(Graphics2D graphics2d, Rectangle rectangle, Color color) {
        graphics2d.setPaint(color);
        graphics2d.fill(rectangle);
        graphics2d.setPaint(DEFAULT_COLOR);
    }

    @Override
    protected void paintComponent(Graphics arg0) {
        super.paintComponent(arg0);
        doDrawing(arg0);
    }

}
