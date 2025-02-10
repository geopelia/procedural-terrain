package com.atilio.procedural.mics;

public class CustomMathFunctions {
    private CustomMathFunctions() {}

    public static int log2(int number) {
        return (int) (Math.log(number)/Math.log(2));
    }

    /**
     * Get the numbers of node needed for a binary tree of certain height.
     * @param height The height of our desired binary tree.
     * @return Number of elements that the binary tree should have.
     */
    public static int getTreeSize(int height) {
        return (int) (Math.pow(2d, (height + 1)) - 1);
    }

    /**
     * Get the aproximate height of a binary tree based on the number of elements.
     * @param totalElements The total of elements that have a binary tree.
     * @return Aproximate height of the binary tree based on number of elements.
     */
    public static int getTreeHeight(int totalElements) {
        return (int) Math.ceil(CustomMathFunctions.log2(totalElements + 1) - 1d);

    }
}
