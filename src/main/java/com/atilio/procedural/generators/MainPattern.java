package com.atilio.procedural.generators;

import com.atilio.procedural.entities.MainMatrix;

public abstract class MainPattern {
    protected MainMatrix matrixToUse;

    protected MainPattern(MainMatrix matrix){
        this.matrixToUse = matrix;
    }

    public abstract void process();
}
