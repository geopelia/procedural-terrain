package com.atilio.procedural.generators;

import com.atilio.procedural.entities.MainMatrix;

public class DiagonalLinePattern extends MainPattern {

    public DiagonalLinePattern(MainMatrix matrix) {
        super(matrix);
    }

    @Override
    public void process() {
        int x = 0;
        int y=0;
        while (x < matrixToUse.getRows() && y < matrixToUse.getCols()) {
            matrixToUse.setValue(y++, x++, 1);
        }
    }

}
