package com.atilio.procedural.colors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.atilio.procedural.entities.CustomColor;
import com.atilio.procedural.exceptions.AppException;

public class ColorChooser {
    private List<CustomColor> availableColors;
    private Random random = new Random();
    private Map<Integer, CustomColor> colorsUsed;
    private int upperLimit;

    public ColorChooser(List<CustomColor> availableColors) {
        this.availableColors = availableColors;
        upperLimit = this.availableColors.size();
        colorsUsed = new HashMap<>(upperLimit);
    }

    public void addColorToColorsUsedMap(int pos) {
        if (!colorsUsed.containsKey(pos)) {
            CustomColor result = null;
            if (pos >= availableColors.size()) {
                result = getRandomAvailableColor();
            } else {
                result = availableColors.get(pos);
                if (colorsUsed.containsValue(result)) {
                    result = getRandomAvailableColor();
                }
            }
            colorsUsed.put(pos, result);
        }
    }

    private CustomColor getRandomAvailableColor() {
        boolean availablePos = false;
        int nextPos = -1;
        CustomColor tempColor = null;
        while (!availablePos) {
            nextPos = random.nextInt(upperLimit);
            tempColor = availableColors.get(nextPos);
            availablePos = !colorsUsed.containsKey(nextPos) && !colorsUsed.containsValue(tempColor);

        }
        return tempColor;
    }

    public Map<Integer, CustomColor> calculateColorsMap(int[][] matrix) throws AppException {
        int totalRows = matrix.length;
        int totalCols = matrix[0].length;
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < totalCols; i++) {
            for (int j = 0; j < totalRows; j++) {
                if (!values.contains(matrix[j][i])) {
                    values.add(matrix[j][i]);
                }
            }
        }
        if (values.size() > availableColors.size()) {
            throw new AppException("Hay mas valores distintos que colores disponibles, valores: " + values.size());
        }
        for (Integer value : values) {
            addColorToColorsUsedMap(value);
        }
        return colorsUsed;

    }
}
