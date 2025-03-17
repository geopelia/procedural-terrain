package com.atilio.procedural.entities;

import java.util.Arrays;

import com.atilio.procedural.exceptions.AppException;

public class MainMatrix {

    public static final int X_UPPER_SIZE_LIMIT = 78;
    public static final int Y_UPPER_SIZE_LIMIT = 78;
    private final int[][] matrix;
    private final int rows;
    private final int cols;

    public MainMatrix(int rows, int cols) throws AppException {
        if (rows > X_UPPER_SIZE_LIMIT || cols > Y_UPPER_SIZE_LIMIT ||
        rows < 1 || cols < 1) {
            throw new AppException("Las dimensiones de la matriz son inválidas");
        }
        matrix = new int[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    public void fillWithZero() {
        for (int[] row : matrix) {
            Arrays.fill(row, 0);
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void setValue(int row, int col, int value) {
        matrix[row][col] = value;
    }

    public int getSize() {
        return rows * cols;
    }

    public void setValue(int positionOfElement, int value) throws AppException {
        if (positionOfElement > getSize() || positionOfElement < 1) {
            throw new AppException("Posición inválida");
        }
        CellCoordinates cellCoordinates = transformPositionInCellCoordinates(positionOfElement);
        matrix[cellCoordinates.getRow()][cellCoordinates.getColumn()] = value;
    }

    private CellCoordinates transformPositionInCellCoordinates(int positionOfElement) {
        CellCoordinates coordinates;
        if (positionOfElement <= cols) {
            coordinates = new CellCoordinates(0, positionOfElement - 1);
        } else {
            int row = -1;
            int col =  -1;
            if (positionOfElement % cols == 0) {
                row = (positionOfElement / cols) - 1;
                col = cols - 1;
            } else {
                row = positionOfElement / cols;
                col = (positionOfElement  % cols) - 1;
            }
            coordinates = new CellCoordinates(row, col);

        }
        return coordinates;
    }

    public void printToExport() {
        StringBuilder value =  new StringBuilder();
        for (int[] row : matrix) {
            value.append(Arrays.toString(row).replace("[", " ").replace("]", " "));
            value.append("\n");
        }
        System.out.println(value);
    }

}
