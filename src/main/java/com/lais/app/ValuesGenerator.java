package com.lais.app;

import com.lais.util.CSVUtils;
import com.lais.valuegenerators.ExpanentialGenerator;
import com.lais.valuegenerators.GaussGenerator;

import java.io.FileWriter;
import java.util.Arrays;

/**
 * Created by DocDVZ on 24.04.2017.
 */
public class ValuesGenerator {
    // Gauss distribution parameters
    private static double gaussBase = 15d;
    private static double gaussSigma = 2d;

    // Expanential distribution parameters
    private static double expanentLambda = 0.33d;

    public static void main(String[] args) {
        GaussGenerator gg = new GaussGenerator(gaussBase, gaussSigma);
        ExpanentialGenerator eg = new ExpanentialGenerator(expanentLambda);

//        System.out.println(String.format("%-15s %-15s", "NEXT GAUSS", "NEXT EXP"));
        String csvFile =  "./src/main/resources/values.csv";
        try {
            FileWriter writer = new FileWriter(csvFile);
            CSVUtils.writeLine(writer, Arrays.asList("NEXT GAUSS", "NEXT EXP"),';');
            for (int i=0; i<500; i++) {
                CSVUtils.writeLine(writer, Arrays.asList(String.valueOf(gg.generateValue()), String.valueOf(eg.generateValue())),';');
            }
            writer.flush();
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
