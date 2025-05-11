package com.atilio.procedural;

import java.awt.EventQueue;

import org.tinylog.Logger;

import com.atilio.procedural.entities.MainMatrix;
import com.atilio.procedural.generators.DiagonalLinePattern;
import com.atilio.procedural.generators.FillRandomPattern;
import com.atilio.procedural.generators.MainPattern;
import com.atilio.procedural.generators.BSPWithCorridorsPattern;
import com.atilio.procedural.generators.BinarySpacePartitioningPattern;
import com.atilio.procedural.panels.AppFrame;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Logger.info("Desplegando");
        int option = 4;
        int cellToUSe = 23;
        MainMatrix mainMatrix;
        try {
            mainMatrix = new MainMatrix(20,20);
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
                case 4:
                    pattern = new BSPWithCorridorsPattern(mainMatrix);
                    break;


                default:
                    pattern = new FillRandomPattern(mainMatrix, cellToUSe);
                    break;
            }
            pattern.process();
            mainMatrix.printMatrix();

        } catch (Exception e) {
            Logger.error(e);
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
