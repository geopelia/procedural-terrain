package com.atilio.procedural.entities;

import java.io.Serializable;

public class CustomColor  implements Serializable {
    private String commonName = "undefined";
    private int red;
    private int green;
    private int blue;

    public CustomColor(String name, int red, int green, int blue) {
        this.commonName = name;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public CustomColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public String getCommonName() {
        return commonName;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }


}
