package com.atilio.procedural.mics;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.atilio.procedural.entities.CustomColor;

public class ColorPalette {
    private ColorPalette() {

    }

    public static List<CustomColor> loadPalette() {
        List<CustomColor> myColors = new ArrayList<>();
        InputStream inputStream = ColorPalette.class.getResourceAsStream("/colores.csv");
        int column = 0;
        String colorName = "";
        String rgbNotation = "";
        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                column = 0;
                try (Scanner rowScanner = new Scanner(line)) {
                    rowScanner.useDelimiter(":");
                    while (rowScanner.hasNext()) {
                        if (column == 1) {
                            colorName = rowScanner.next();
                        } else if (column == 3) {
                            rgbNotation = rowScanner.next();
                        } else {
                            rowScanner.next();
                        }
                        column++;
                    }
                }
                String[] rgb = rgbNotation.replace("(", "").replace(")", "").trim().split(",");
                CustomColor myColor = new CustomColor(colorName, Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]),
                        Integer.parseInt(rgb[2]));
                myColors.add(myColor);
            }
        }
        return myColors;

    }
}
