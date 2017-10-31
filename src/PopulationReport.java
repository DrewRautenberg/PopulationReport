// Author: Drew Rautenberg
// Class section: CS1110-560, Wednesday 2:30 to 4:20
// Program: Assignment 4, Population Report

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

        File input = new File("CountryData8Col3Hdr.csv");
        Scanner inFile = new Scanner(input);

        PrintWriter outFile = new PrintWriter("PopulationReport.txt");

        //Skips first 3 lines of file
        for (int i = 0; i < 3; i++) {
            inFile.nextLine();
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

        outFile.printf("%15s%s\n\n", " ", "POPULATION REPORT");

        doTable1(code, name, region, lifeExpectancy, continent, outFile);
        doTable2(code, name, continent, population, landArea, outFile);
        doTable3(continent, lifeExpectancy, outFile);

        outFile.printf("\n\n%15s%s", " ", "<< END OF REPORT >>");

        outFile.close();
    }

    private static void doTable1(String[] code, String[] name, String[] region, double[] lifeExpectancy,
                                 String[] cont, PrintWriter outFile) throws IOException {
        outFile.println("\nTable 1: Life Expectancy in Africa by Country");
        outFile.printf("\n%-5s %-17s %-17s %2s\n", "Code", "Name", "Region", "LifeExp");
        outFile.print("----  ----------------- ----------------- -------");
        for (int i = 0; i < name.length; i++) {
            if (cont[i].equals("Africa")) {
                outFile.printf("\n%-5s %-17s %-18s %2s", code[i], name[i], region[i], lifeExpectancy[i]);
            }
        }
    }

    private static void doTable2(String[] code, String[] name, String[] cont, int[] population, int[] landArea,
                                 PrintWriter outFile) throws IOException {
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
        outFile.println("\n\n");
        outFile.println("Table 2:  Europe Population & Size");
        outFile.println("\nPopulation:");
        outFile.printf("\t%-9s %-10s %-16s (%s)",
                "Largest:", population[popMaxIndex], name[popMaxIndex], code[popMaxIndex]);
        outFile.printf("\n\t%s %-10s %-16s (%s)",
                "Smallest:", population[popMinIndex], name[popMinIndex], code[popMinIndex]);
        outFile.printf("\n\t%-9s %-10s", "Average:", popAvg);
        outFile.printf("\n\t%-9s %-10s", "Total:", popTotal);

        outFile.println("\nLand size (in sq km):");
        outFile.printf("\t%-9s %-10s %-16s (%s)",
                "Largest:", landArea[landMaxIndex], name[landMaxIndex], code[landMaxIndex]);
        outFile.printf("\n\t%s %-10s %-16s (%s)",
                "Smallest:", landArea[landMinIndex], name[landMinIndex], code[landMinIndex]);
        outFile.printf("\n\t%-9s %-10s", "Average:", landAvg);
        outFile.printf("\n\t%-9s %-10s", "Total:", landTotal);
    }

    private static void doTable3(String[] cont, double[] lifeExp, PrintWriter outFile) throws IOException {
        double africaMax = Double.MIN_VALUE;
        double africaMin = Double.MAX_VALUE;
        double africaTot = 0;
        double africaAvg;
        int africaCt = 0;

        double asiaMax = Double.MIN_VALUE;
        double asiaMin = Double.MAX_VALUE;
        double asiaTot = 0;
        double asiaAvg;
        int asiaCt = 0;

        double europeMax = Double.MIN_VALUE;
        double europeMin = Double.MAX_VALUE;
        double europeTot = 0;
        double europeAvg;
        int europeCt = 0;

        double naMax = Double.MIN_VALUE;
        double naMin = Double.MAX_VALUE;
        double naTot = 0;
        double naAvg;
        int naCt = 0;

        double ocMax = Double.MIN_VALUE;
        double ocMin = Double.MAX_VALUE;
        double ocTot = 0;
        double ocAvg;
        int ocCt = 0;

        double saMax = Double.MIN_VALUE;
        double saMin = Double.MAX_VALUE;
        double saTot = 0;
        double saAvg;
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

        outFile.println("\n\n");
        outFile.println("TABLE 3:  Life Expectancy by Continent");
        outFile.printf("\n\t%-14s %-7s %-6s %s", "Continent", "Highest", "Lowest", "Average");
        outFile.println("\n\t-------------- ------- ------ -------");
        outFile.printf("\t%-15s %-7s %-6s %.1f", "Africa", africaMax, africaMin, africaAvg);
        outFile.printf("\n\t%-15s %-7s %-6s %.1f", "Asia", asiaMax, asiaMin, asiaAvg);
        outFile.printf("\n\t%-15s %-7s %-6s %.1f", "Europe", europeMax, europeMin, europeAvg);
        outFile.printf("\n\t%-15s %-7s %-6s %.1f", "North America", naMax, naMin, naAvg);
        outFile.printf("\n\t%-15s %-7s %-6s %.1f", "Oceania", ocMax, ocMin, ocAvg);
        outFile.printf("\n\t%-15s %-7s %-6s %.1f", "South America", saMax, saMin, saAvg);
    }
}
