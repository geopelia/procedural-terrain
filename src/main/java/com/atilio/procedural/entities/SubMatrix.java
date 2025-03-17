package com.atilio.procedural.entities;

public class SubMatrix {
    private CellCoordinates initalCell;
    private int rows;
    private int columns;

    public SubMatrix(CellCoordinates initalCell, int rows, int columns) {
        this.initalCell = initalCell;
        this.rows = rows;
        this.columns = columns;
    }

    public CellCoordinates getInitalCell() {
        return initalCell;
    }

    public void setInitalCell(CellCoordinates initalCell) {
        this.initalCell = initalCell;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getSize() {
        return columns * rows;
    }

    public boolean isCellInMatrix(CellCoordinates cell) {
        if (cell.getRow() < initalCell.getRow() || cell.getRow() > (initalCell.getRow() + rows - 1)) {
            return false;
        }
        return !(cell.getColumn() < initalCell.getColumn() || cell.getColumn() > (initalCell.getColumn() + columns - 1));
    }

    @Override
    public String toString() {
        return "SubMatrix [initalCell=" + initalCell + ", rows=" + rows + ", columns=" + columns + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((initalCell == null) ? 0 : initalCell.hashCode());
        result = prime * result + rows;
        result = prime * result + columns;
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
        SubMatrix other = (SubMatrix) obj;
        if (initalCell == null) {
            if (other.initalCell != null)
                return false;
        } else if (!initalCell.equals(other.initalCell))
            return false;
        if (rows != other.rows)
            return false;
        if (columns != other.columns)
            return false;
        return true;
    }

}
