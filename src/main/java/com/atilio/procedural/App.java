package com.atilio.procedural;

import java.awt.EventQueue;

import com.atilio.procedural.entities.MainMatrix;
import com.atilio.procedural.exceptions.AppException;
import com.atilio.procedural.generators.BinarySpacePartitioningPattern;
import com.atilio.procedural.generators.DiagonalLinePattern;
import com.atilio.procedural.generators.FillRandomPattern;
import com.atilio.procedural.generators.MainPattern;
import com.atilio.procedural.panels.AppFrame;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Deplegando!");
        int option = 3;
        int cellToUSe = 23;
        MainMatrix mainMatrix;
        try {
            mainMatrix = new MainMatrix(5,5);
            mainMatrix.fillWithZero();
            MainPattern pattern;
            switch (option) {
                case 1:
                    pattern = new DiagonalLinePattern(mainMatrix);
                    break;
                case 2:
                    pattern = new FillRandomPattern(mainMatrix, cellToUSe);
                    break;
                case 3:
                    pattern = new BinarySpacePartitioningPattern(mainMatrix);
                    break;

                default:
                    pattern = new FillRandomPattern(mainMatrix, cellToUSe);
                    break;
            }
            pattern.process();
            mainMatrix.printMatrix();

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Runnable app = () -> {
            AppFrame appFrame;
            appFrame = new AppFrame(mainMatrix.getMatrix());
            appFrame.setVisible(true);
        };
        EventQueue.invokeLater(app);
    }
}
