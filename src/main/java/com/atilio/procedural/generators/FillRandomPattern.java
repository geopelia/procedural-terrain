package com.atilio.procedural.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.atilio.procedural.entities.MainMatrix;
import com.atilio.procedural.exceptions.AppException;

import org.tinylog.Logger;
public class FillRandomPattern extends MainPattern {
    private Random myRandom;
    private int rowsToUse;

    public FillRandomPattern(MainMatrix matrix, int rowsToUse) throws AppException {
        super(matrix);
        if (rowsToUse < 0 || rowsToUse > matrix.getSize()) {
            throw new AppException("numero invalido de celdas a pintar");
        }
        this.rowsToUse = rowsToUse;
        myRandom = new Random();
    }

    @Override
    public void process() {
        int position = 0;
        List<Integer> positionsUsed = new ArrayList<>();
        for (int i = 0; i < rowsToUse; i++) {
            position = generateUniqueRandomPosition(positionsUsed);
            try {
                matrixToUse.setValue(position, 1);
            } catch (AppException e) {
                Logger.error(e);
                return;
            }

        }

    }

    private int generateUniqueRandomPosition(List<Integer> positionsUsed) {
        int position = myRandom.nextInt(matrixToUse.getSize());
        while (positionsUsed.contains(position) || position == 0) {
            position = myRandom.nextInt(matrixToUse.getSize());
        }
        positionsUsed.add(position);
        return position;
    }

}
