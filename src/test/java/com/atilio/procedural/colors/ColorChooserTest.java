package com.atilio.procedural.colors;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import com.atilio.procedural.entities.CustomColor;
import com.atilio.procedural.exceptions.AppException;

class ColorChooserTest {
    @Test
    void testAddColorToColorsUsedMap() {

        ColorChooser chooser = new ColorChooser(getCustomColorList());
     Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {  //FAIL asap timeout occurs

        chooser.addColorToColorsUsedMap(154);
  });

    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testCalculateColorsMap() {
        ColorChooser chooser = new ColorChooser(getCustomColorList());

        int[] posiciones = {5,7,8,11,12,14,15,19,21,22,25,26,28,29,32,34,35,38,39,41,42,47,49,53,56,61,62,64,65,50,54,57,68,69,71,72,76,78,79,88,89,91,92,81,83,84,95,96,98,99,105,109,110,112,117,118,124,125,106,113,120,121,127,128,132,134,135,138,139,146,149,153,154,141,142,147,150,156,157,162,164,165,174,175,181,182,167,169,170,177,178,184,185,189,191,192,195,196,198,199,202,204,205,208,209,211,212};

        for (int i : posiciones) {
            chooser.addColorToColorsUsedMap(i);
        }
        int[][] empty = {{}};
        try {
            Map<Integer, CustomColor> calculateColorsMap = chooser.calculateColorsMap(empty);

            assertEquals( posiciones.length, calculateColorsMap.size());
            List<CustomColor> temp = new ArrayList<>();
            for (CustomColor customColor : calculateColorsMap.values()) {
                if (temp.contains(customColor)) {
                    fail("Esta repetido un color");
                } else {
                    temp.add(customColor);
                }
            }
        } catch (AppException e) {
            fail(e);
        }
    }

    private List<CustomColor> getCustomColorList() {
        int size = 140;
        List<CustomColor> result = new ArrayList<>(size);
        for (int i=0;i<size;i++) {
            result.add(new CustomColor(i, size, i));

        }
        return result;

    }
}
