// Author: Drew Rautenberg
// Class section: CS1110-560, Wednesday 2:30 to 4:20
// Program: Assignment 4, Population Report

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PopulationReport {
    public static void main(String[] args) throws IOException {
        final int SIZE = 222;
        String[] code = new String[SIZE];
        String[] name = new String[SIZE];
        String[] continent = new String[SIZE];
        String[] region = new String[SIZE];
        int[] landArea = new int[SIZE];
        int[] population = new int[SIZE];
        double[] lifeExpectancy = new double[SIZE];

        //variables for storing
        String codeIn;
        String nameIn;
        String continentIn;
        String regionIn;
        int landAreaIn;
        int populationIn;
        double lifeExpIn;
        int index = 0;

        String[] fields;
        String line;
        String trash;

        File input = new File("CountryData8Col3Hdr.csv");
        Scanner inFile = new Scanner(input);

        //Skips first 3 lines of file
        for (int i = 0; i < 3; i++) {
            trash = inFile.nextLine();
        }

        for (int i = 0; i < 239; i++) {
            line = inFile.nextLine();
            fields = line.split(",");

            codeIn = fields[0].substring(29, 32);
            nameIn = fields[1].substring(1, fields[1].length() - 1);
            continentIn = fields[2].substring(1, fields[2].length() - 1);
            regionIn = fields[3].substring(1, fields[3].length() - 1);
            landAreaIn = Integer.parseInt(fields[4]);
            populationIn = Integer.parseInt(fields[5]);
            lifeExpIn = Double.parseDouble(fields[6]);

            if (populationIn != 0 && lifeExpIn != 0 && !continentIn.equals("'Antarctica'")) {
                code[index] = codeIn;
                continent[index] = continentIn;
                landArea[index] = landAreaIn;
                population[index] = populationIn;
                lifeExpectancy[index] = lifeExpIn;
                if (nameIn.length() > 16) {
                    name[index] = nameIn.substring(0, 16);
                }
                else {
                    name[index] = nameIn;
                }
                if (regionIn.length() > 16) {
                    region[index] = regionIn.substring(0, 16);
                }
                else {
                    region[index] = regionIn;
                }
                index++;
            }
        }
        inFile.close();

        System.out.println("POPULATION REPORT");

        doTable1(code, name, region, lifeExpectancy, continent);
        //doTable2(code, name, region, lifeExpectancy, continent);
    }

    static void doTable1(String[] code, String[] name, String[] region, double[] lifeExpectancy, String[] cont) {
        System.out.println("Table 1: Life Expectancy in Africa by Country");
        System.out.printf("\n%-5s %-17s %-17s %2s\n","Code","Name","Region","LifeExp");
        System.out.print("----  ----------------- ----------------- -------");
        for (int i = 0; i < name.length; i++) {
            if (cont[i].equals("Africa")) {
                System.out.printf("\n%-5s %-17s %-18s %2s", code[i], name[i], region[i], lifeExpectancy[i]);
                //System.out.println(code[i] + "  " + name[i] + " " + region[i] + " " + lifeExpectancy[i]);
            }
        }


    }

    static void doTable2(String[] code, String[] name, String[] region, double[] lifeExpectancy, String[] cont) {
        System.out.println("Table 2:  Europe Population & Size");
    }

    static void doTable3() {

    }
}
