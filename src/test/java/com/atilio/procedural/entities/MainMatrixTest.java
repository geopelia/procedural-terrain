package com.atilio.procedural.entities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.atilio.procedural.exceptions.AppException;

class MainMatrixTest {

    @Test
    void testConstructor() {
        assertThrows(AppException.class, () -> {
            new MainMatrix(-1, 10);
        });
        assertThrows(AppException.class, () -> {
            new MainMatrix(85, 100);
        });
        assertThrows(AppException.class, () -> {
            new MainMatrix(85, 10);
        });
        assertThrows(AppException.class, () -> {
            new MainMatrix(0, 0);
        });
        assertDoesNotThrow(() -> {
            new MainMatrix(5, 10);
        });
        assertDoesNotThrow(() -> {
            new MainMatrix(25, 25);
        });
    }

    @Test
    void testSetValue() {
        MainMatrix matrix = null;
        try {
            matrix = new MainMatrix(2, 4);
        } catch (Exception e) {
            fail(e);
        }
        matrix.fillWithZero();
        try {
            matrix.setValue(1, 20);
            matrix.setValue(4, 5);
            matrix.setValue(5, 15);
            matrix.setValue(8, 10);
            matrix.setValue(7, 30);
            matrix.setValue(2, 25);
        } catch (Exception e) {
            fail(e);
        }
        int[][] array = matrix.getMatrix();
        assertEquals(10, array[1][3]);
        assertEquals(20, array[0][0]);
        assertEquals(5, array[0][3]);
        assertEquals(15, array[1][0]);
        assertEquals(30, array[1][2]);
        assertEquals(25, array[0][1]);
        try {
            matrix = new MainMatrix(3, 3);
            matrix.fillWithZero();
            matrix.setValue(1, 5);
            matrix.setValue(3, 10);
            matrix.setValue(5, 15);
            matrix.setValue(7, 20);
            matrix.setValue(9, 25);
            matrix.setValue(6, 30);
        } catch (Exception e) {
            fail(e);
        }
        array = matrix.getMatrix();
        assertEquals(5, array[0][0]);
        assertEquals(10, array[0][2]);
        assertEquals(15, array[1][1]);
        assertEquals(20, array[2][0]);
        assertEquals(25, array[2][2]);
        assertEquals(30, array[1][2]);
        try {
            matrix = new MainMatrix(5, 6);
            matrix.fillWithZero();
            matrix.setValue(1, 5);
            matrix.setValue(6, 10);
            matrix.setValue(18, 15);
            matrix.setValue(25, 20);
            matrix.setValue(30, 25);
            matrix.setValue(15, 30);
        } catch (Exception e) {
            fail(e);
        }
        array = matrix.getMatrix();
        assertEquals(5, array[0][0]);
        assertEquals(10, array[0][5]);
        assertEquals(15, array[2][5]);
        assertEquals(20, array[4][0]);
        assertEquals(25, array[4][5]);
        assertEquals(30, array[2][2]);

    }

    @Test
    void testSetValueError() {
        assertThrowsExactly(AppException.class, () -> {
            MainMatrix matrix2 = new MainMatrix(2, 4);
            matrix2.setValue(10, 10);
        });

    }
}
