package com.adilyakut;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class UserInterface implements  Runnable{

    @Override
    public void run() {

        int colNumber = CsvRead.getWholeSheet().get(0).size();
        

        String[][] myArray = new String[CsvRead.getWholeSheet().toArray().length][colNumber];
        for (int i = 0; i < colNumber; i++) {
            String names;
            names = CsvRead.getWholeSheet().get(0).get(i);
            myArray[0][i] = names;
        }
        for (int i = 1; i < CsvRead.getWholeSheet().toArray().length; i++) {
            ArrayList<String> row;
            row = CsvRead.getWholeSheet().get(i);
            myArray[i - 1] = row.toArray(new String[colNumber]);
        }

        JFrame frame = new JFrame("Objektivering");
        JTable table = new JTable(new DefaultTableModel(myArray,CsvRead.getWholeSheet().get(0).toArray()));
        table.setBounds(30,30,90,100);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.setPreferredSize(new Dimension(1100,550));
        frame.add(scrollPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);






    }
}
