package com.atilio.procedural.mics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CustomMathFunctionsTest {
    @Test
    void testLog2() {
        assertEquals(0, CustomMathFunctions.log2(1));
        assertEquals(1, CustomMathFunctions.log2(2));
        assertEquals(3, CustomMathFunctions.log2(8));
        assertEquals(10, CustomMathFunctions.log2(1024));
        assertThrows(Exception.class, () -> {
            CustomMathFunctions.log2(0);
        });
    }

    @Test
    void testGetTreeSize() {
        assertEquals(1, CustomMathFunctions.getTreeSize(1));
        assertEquals(3, CustomMathFunctions.getTreeSize(2));
        assertEquals(7, CustomMathFunctions.getTreeSize(3));
        assertEquals(63, CustomMathFunctions.getTreeSize(6));
    }

    @Test
    void testGetTreeHeight() {
        assertEquals(1, CustomMathFunctions.getTreeHeight(1));
        assertEquals(2, CustomMathFunctions.getTreeHeight(3));
        assertEquals(3, CustomMathFunctions.getTreeHeight(7));
        assertEquals(5, CustomMathFunctions.getTreeHeight(31));
        assertThrows(Exception.class, () -> {
            CustomMathFunctions.getTreeHeight(0);
        });

    }
}
