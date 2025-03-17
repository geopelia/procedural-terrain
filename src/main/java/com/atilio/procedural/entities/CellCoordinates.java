package com.atilio.procedural.entities;

public class CellCoordinates {
    private int row;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    private int column;

    public CellCoordinates(CellCoordinates target) {
        this.row = target.row;
        this.column = target.column;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public CellCoordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "CellCoordinates [row=" + row + ", column=" + column + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + row;
        result = prime * result + column;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CellCoordinates other = (CellCoordinates) obj;
        if (row != other.row)
            return false;
        if (column != other.column)
            return false;
        return true;
    }
}
