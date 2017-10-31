// Author: Drew Rautenberg
// Class section: CS1110-560, Wednesday 2:30 to 4:20
// Program: Assignment 4, Population Report

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PopulationReport {
    public static void main(String[] args) throws IOException {
        String[] code = new String[239];
        String[] name = new String[239];
        String[] continent = new String[239];
        String[] region = new String[239];
        int[] landArea = new int[239];
        int[] population = new int[239];
        double[] lifeExpectancy = new double[239];

        //variables for storing
        String codeIn;
        String nameIn;
        String continentIn;
        String regionIn;
        int landAreaIn;
        int populationIn;
        double lifeExpIn;


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

            codeIn= fields[0].substring(29, 32);
            nameIn=fields[1].substring(1,fields[1].length()-1);
            continentIn = fields[2].substring(1, fields[2].length() - 1);
            regionIn = fields[3].substring(1, fields[3].length() - 1);
            landAreaIn = Integer.parseInt(fields[4]);
            populationIn = Integer.parseInt(fields[5]);
            lifeExpIn = Double.parseDouble(fields[6]);

            if (populationIn != 0 && lifeExpIn!=0 && !continentIn.equals("'Antarctica'")) {
                code[i]=codeIn;
                continent[i]=continentIn;
                landArea[i]=landAreaIn;
                population[i]=populationIn;
                lifeExpectancy[i]=lifeExpIn;
                if (nameIn.length() > 16) {
                    name[i] = nameIn.substring(0, 16);
                }
                else {
                    name[i]=nameIn;
                }
                if (regionIn.length() > 16) {
                    region[i] = regionIn.substring(0, 16);
                }
                else {
                    region[i]=regionIn;
                }
            }
        }
        inFile.close();

        System.out.println("POPULATION REPORT");

        doTable1(code,name,region,lifeExpectancy, continent);
    }

    static void doTable1(String[] code,String[] name,String[] region, double[] lifeExpectancy, String[] cont) {
        System.out.println("Table 1: Life Expectancy in Africa by Country");
        System.out.println("Code    Name    Region");
        for (int i=0;i<name.length;i++) {
            if (cont[i].equals("Africa")) {
                System.out.println(code[i] + " " + name[i] + " " + region[i] + " " + lifeExpectancy[i]);
            }
        }



    }

    static void doTable2() {

    }

    static void doTable3() {

    }
}
