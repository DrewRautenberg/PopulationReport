// Author: Drew Rautenberg
// Class section: CS1110-560, Wednesday 2:30 to 4:20
// Program: Assignment 4, Population Report

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PopulationReport {
    public static void main(String[] args) throws IOException {
        String[] code;
        String[] name;
        String[] continent;
        String[] region;
        long[] landArea;
        long[] population;
        int[] lifeExpectancy;
        String[] shortCode;

        String codeRaw;
        String nameRaw;
        String continentRaw;
        String regionRaw;
        long landAreaRaw;
        long populationRaw;
        int lifeExpectancyRaw;
        String shortCodeRaw;
        String[] fields= new String[8];
        String line;

        File input = new File("CountryData8Col3Hdr.csv");
        Scanner inFile = new Scanner(input);

        for (int i=0;i<241;i++){
            line=inFile.nextLine();
            fields=line.split(",");

        }

        inFile.close();
    }

    static void doTable1() {

    }

    static void doTable2() {

    }

    static void doTable3() {

    }
}
