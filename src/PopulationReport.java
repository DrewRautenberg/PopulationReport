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

        //System.out.println(getSpacer(20)+"POPULATION REPORT");
        System.out.printf("%15s%s\n\n", " ", "POPULATION REPORT");


        doTable1(code, name, region, lifeExpectancy, continent);
        doTable2(code, name, continent, population, landArea);
        doTable3(continent, lifeExpectancy);
    }

    static void doTable1(String[] code, String[] name, String[] region, double[] lifeExpectancy, String[] cont) {
        System.out.println("\nTable 1: Life Expectancy in Africa by Country");
        System.out.printf("\n%-5s %-17s %-17s %2s\n", "Code", "Name", "Region", "LifeExp");
        System.out.print("----  ----------------- ----------------- -------");
        for (int i = 0; i < name.length; i++) {
            if (cont[i].equals("Africa")) {
                System.out.printf("\n%-5s %-17s %-18s %2s", code[i], name[i], region[i], lifeExpectancy[i]);
            }
        }


    }

    static void doTable2(String[] code, String[] name, String[] cont, int[] population, int[] landArea) {
        int popMax = Integer.MIN_VALUE;
        int popMin = Integer.MAX_VALUE;
        int popMaxIndex = -1;
        int popMinIndex = -1;
        int popTotal = 0;
        int popAvg;
        int count = 0;
        int landMax = Integer.MIN_VALUE;
        int landMin = Integer.MAX_VALUE;
        int landMaxIndex = -1;
        int landMinIndex = -1;
        int landTotal = 0;
        int landAvg;

        for (int i = 0; i < name.length; i++) {
            if (cont[i].equals("Europe")) {
                if (population[i] > popMax) {
                    popMax = population[i];
                    popMaxIndex = i;
                }
                if (population[i] < popMin) {
                    popMin = population[i];
                    popMinIndex = i;
                }
                popTotal += population[i];
                if (landArea[i] > landMax) {
                    landMax = landArea[i];
                    landMaxIndex = i;
                }
                if (landArea[i] < landMin) {
                    landMin = landArea[i];
                    landMinIndex = i;
                }
                landTotal += landArea[i];
                count++;
            }
        }
        popAvg = popTotal / count;
        landAvg = landTotal / count;
        System.out.println("\n\n");
        System.out.println("Table 2:  Europe Population & Size");
        System.out.println("\nPopulation:");
        System.out.printf("\t%-9s %-10s %-16s (%s)",
                "Largest:", population[popMaxIndex], name[popMaxIndex], code[popMaxIndex]);
        System.out.printf("\n\t%s %-10s %-16s (%s)",
                "Smallest:", population[popMinIndex], name[popMinIndex], code[popMinIndex]);
        System.out.printf("\n\t%-9s %-10s", "Average:", popAvg);
        System.out.printf("\n\t%-9s %-10s", "Total:", popTotal);

        System.out.println("\nLand size (in sq km):");
        System.out.printf("\t%-9s %-10s %-16s (%s)",
                "Largest:", landArea[landMaxIndex], name[landMaxIndex], code[landMaxIndex]);
        System.out.printf("\n\t%s %-10s %-16s (%s)",
                "Smallest:", landArea[landMinIndex], name[landMinIndex], code[landMinIndex]);
        System.out.printf("\n\t%-9s %-10s", "Average:", landAvg);
        System.out.printf("\n\t%-9s %-10s", "Total:", landTotal);

    }

    static void doTable3(String[] cont, double[] lifeExp) {
        double africaMax = Double.MIN_VALUE;
        double africaMin = Double.MAX_VALUE;
        double africaTot = 0;
        double africaAvg = 0;
        int africaCt = 0;

        double asiaMax = Double.MIN_VALUE;
        double asiaMin = Double.MAX_VALUE;
        double asiaTot = 0;
        double asiaAvg = 0;
        int asiaCt = 0;

        double europeMax = Double.MIN_VALUE;
        double europeMin = Double.MAX_VALUE;
        double europeTot = 0;
        double europeAvg = 0;
        int europeCt = 0;

        double naMax = Double.MIN_VALUE;
        double naMin = Double.MAX_VALUE;
        double naTot = 0;
        double naAvg = 0;
        int naCt = 0;

        double ocMax = Double.MIN_VALUE;
        double ocMin = Double.MAX_VALUE;
        double ocTot = 0;
        double ocAvg = 0;
        int ocCt = 0;

        double saMax = Double.MIN_VALUE;
        double saMin = Double.MAX_VALUE;
        double saTot = 0;
        double saAvg = 0;
        int saCt = 0;

        for (int i = 0; i < cont.length; i++) {
            switch (cont[i]) {
                case "Africa":
                    if (lifeExp[i] > africaMax) {
                        africaMax = lifeExp[i];
                    }
                    if (lifeExp[i] < africaMin) {
                        africaMin = lifeExp[i];
                    }
                    africaTot += lifeExp[i];
                    africaCt++;
                    break;
                case "Asia":
                    if (lifeExp[i] > asiaMax) {
                        asiaMax = lifeExp[i];
                    }
                    if (lifeExp[i] < asiaMin) {
                        asiaMin = lifeExp[i];
                    }
                    asiaTot += lifeExp[i];
                    asiaCt++;
                    break;
                case "Europe":
                    if (lifeExp[i] > europeMax) {
                        europeMax = lifeExp[i];
                    }
                    if (lifeExp[i] < europeMin) {
                        europeMin = lifeExp[i];
                    }
                    europeTot += lifeExp[i];
                    europeCt++;
                    break;
                case "North America":
                    if (lifeExp[i] > naMax) {
                        naMax = lifeExp[i];
                    }
                    if (lifeExp[i] < naMin) {
                        naMin = lifeExp[i];
                    }
                    naTot += lifeExp[i];
                    naCt++;
                    break;
                case "Oceania":
                    if (lifeExp[i] > ocMax) {
                        ocMax = lifeExp[i];
                    }
                    if (lifeExp[i] < ocMin) {
                        ocMin = lifeExp[i];
                    }
                    ocTot += lifeExp[i];
                    ocCt++;
                    break;
                case "South America":
                    if (lifeExp[i] > saMax) {
                        saMax = lifeExp[i];
                    }
                    if (lifeExp[i] < saMin) {
                        saMin = lifeExp[i];
                    }
                    saTot += lifeExp[i];
                    saCt++;
                    break;
            }
        }

        africaAvg = africaTot / africaCt;
        asiaAvg = asiaTot / asiaCt;
        europeAvg = europeTot / europeCt;
        naAvg = naTot / naCt;
        ocAvg = ocTot / ocCt;
        saAvg = saTot / saCt;

        System.out.println("\n\n");
        System.out.println("TABLE 3:  Life Expectancy by Continent");
        System.out.printf("\n\t%-14s %-7s %-6s %s", "Continent", "Highest", "Lowest", "Average");
        System.out.println("\n\t-------------- ------- ------ -------");
        System.out.printf("\t%-15s %-7s %-6s %.1f", "Africa", africaMax, africaMin, africaAvg);
        System.out.printf("\n\t%-15s %-7s %-6s %.1f", "Asia", asiaMax, asiaMin, asiaAvg);
        System.out.printf("\n\t%-15s %-7s %-6s %.1f", "Europe", europeMax, europeMin, europeAvg);
        System.out.printf("\n\t%-15s %-7s %-6s %.1f", "North America", naMax, naMin, naAvg);
        System.out.printf("\n\t%-15s %-7s %-6s %.1f", "Oceania", ocMax, ocMin, ocAvg);
        System.out.printf("\n\t%-15s %-7s %-6s %.1f", "South America", saMax, saMin, saAvg);


    }
}
