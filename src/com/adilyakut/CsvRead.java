package com.adilyakut;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvRead {

    private static final String COMMA_DELIMITER = ",";
    private static ArrayList<ArrayList<String>> wholeSheet = new ArrayList<>();
    static String myString = "";
    static String container = "";
    static boolean receive = false;

    public static ArrayList<ArrayList<String>> getWholeSheet() {
        if (wholeSheet.isEmpty()) {
            loadCSV();
        }
        return wholeSheet;
    }

    public static void loadCSV() {
        try (Scanner scanner = new Scanner(new File("resource/sample.csv"))) {
            while (scanner.hasNext()) {
                wholeSheet.add(getRows(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Can´t find the source .csv file!");
            e.printStackTrace();
        }
    }

    private static ArrayList<String> getRows(String row) {
        ArrayList<String> rowValues = new ArrayList<>();
        try (Scanner rowScan = new Scanner(row)) {
            rowScan.useDelimiter(COMMA_DELIMITER);

            while (rowScan.hasNext()) {
                myString = rowScan.next();
                container += myString;
                if (!myString.isEmpty()) {
                    if (myString.charAt(0) == '"') {
                        receive = true;
                        continue;
                    }

                }
                if (!container.isEmpty()) {
                    if (container.charAt((container.length() - 1)) == '"') {
                        receive = false;
                    }
                }
                rowValues.add(container);

                if (!receive) {
                    container = "";
                }
            }
            return rowValues;
        }

    }

}



