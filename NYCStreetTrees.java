package project2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class NYCStreetTrees {
    /**
     * Opens, reads a data file, obtains user input, peform some datavalidation and 
     * handling all errors that may occur, and finnally prining all output to the standard output stream.
     * Handles any exception thrown by other classes and terminate gracefully.
     * @param args csv file to open, read, and parse
     * @throws Exception IllegalArgumentException is thrown when a value is given for a variable that is outside of its parameters.
     */
    public static void main(String[] args) throws Exception {

        // determine if the command line argument exists
        if (args.length == 0) {
            System.err.println("Usage Error: the program expects file name as an argument. \n");
            System.exit(1);
        }

        // determine if the command line arguemnt contains a viable file.
        File csvFile = new File(args[0]);
        if (!csvFile.exists()) {
            System.err.println("Error: the file " + csvFile.getAbsolutePath() + " cannot be opened. \n");
            System.exit(1);
        }
        if (!csvFile.canRead()) {
            System.err.println("Error: the file " + csvFile.getAbsolutePath() + " cannot be opened for reading. \n");
            System.exit(1);
        }

        // open the file for reading
        CSV csv = null;

        try {
            csv = new CSV(new Scanner(csvFile));
        } catch (FileNotFoundException e) {
            System.err.println("Error: the file " + csvFile.getAbsolutePath() +
                    " cannot be opened for reading.\n");
            System.exit(1);
        }
        TreeList list = new TreeList();
        int treeID;
        String status = null;
        String health = null;
        String spc_latin = null;
        String spc_common = null;
        int zipcode = 0;
        String boroname = null;
        double x_sp = 0.0;
        double y_sp = 0.0;
        Tree tree = null;
        TreeSpecies treeSpecies = null;
        TreeSpeciesList treeSpeciesList = new TreeSpeciesList();
        ArrayList<String> lines = new ArrayList<String>();

        // System.out.println("hi1");

        try {
            csv.getNextRow(); // skip the header
            int invalidData = 0;
            boolean valid = true;
            for (int i = 0; i < csv.getNumOfRows() - 1; i++) { //parse through the data, attempting to skip over invalid data entries
                lines = csv.getNextRow();
                if (!lines.get(0).equals("")) {
                    treeID = Integer.parseInt(lines.get(0));
                } else {
                    treeID = -1;
                    invalidData += 6;
                    valid = false;
                }
                if(lines.get(1).equals("")) {
                    invalidData +=6;
                }
                if(lines.get(2).equals("")) {
                    invalidData +=6;
                }
                if(lines.get(3).equals("")) {
                    invalidData +=6;
                }
                if(lines.get(4).equals("")) {
                    invalidData +=6;
                }
                if(lines.get(5).equals("")) {
                    invalidData +=6;
                }
                
                
                status = lines.get(6 + invalidData);
                if(lines.get(6).equals("")) {
                    invalidData +=6;
                }
                health = lines.get(7 + invalidData);
                if(lines.get(7).equals("")) {
                    invalidData +=6;
                }
                spc_latin = lines.get(8 + invalidData);
                if(lines.get(8).equals("")) {
                    invalidData +=6;
                }
                if (lines.get(9 + invalidData) == null) {
                    invalidData +=6;
                    valid = false;
                }
                spc_common = lines.get(9 + invalidData);
                zipcode = Integer.parseInt(lines.get(25 + invalidData));
                boroname = lines.get(29 + invalidData);
                x_sp = Double.parseDouble(lines.get(39 + invalidData));
                y_sp = Double.parseDouble(lines.get(40 + invalidData));

                if (valid == true) { // if the data is valid, create a tree
                    tree = new Tree(treeID, status,
                            health, spc_latin, spc_common,
                            zipcode, boroname,
                            x_sp, y_sp);

                    list.add(tree); // put the tree into a list

                    treeSpecies = new TreeSpecies(spc_common, spc_latin); //create a treeSpecies based on that data

                    treeSpeciesList.add(treeSpecies); //add that treeSpecies to a list
                }
                invalidData = 0;
                valid = true;
            }
        } catch (Exception e) {
            System.err.println("Invalid data format.");
        }

        Scanner inTree = new Scanner(System.in);
        String userValue = null;
        TreeSpeciesList commonTreeSpeciesList = new TreeSpeciesList();
        TreeSpeciesList outCommonTreeSpeciesList = new TreeSpeciesList();
        TreeSpeciesList outLatinTreeSpeciesList = new TreeSpeciesList();
        TreeSpeciesList latinTreeSpeciesList = new TreeSpeciesList();
        boolean inList = false;
        int numOfFlowersNYC = 0;
        int numOfFlowersManhattan = 0;
        int numOfFlowersBronx = 0;
        int numOfFlowersBrooklyn = 0;
        int numOfFlowersQueens = 0;
        int numOfFlowersStatenIsland = 0;
        int totalFlowersNYC = 0;
        int totalFlowersManhattan = 0;
        int totalFlowersBronx = 0;
        int totalFlowersBrooklyn = 0;
        int totalFlowersQueens = 0;
        int totalFlowersStatenIsland = 0;
        double percentageNYC = 0.0;
        double percentageManhattan = 0.0;
        double percentageBronx = 0.0;
        double percentageBrooklyn = 0.0;
        double percentageQueens = 0.0;
        double percentageStatenIsland = 0.0;
        do {
            System.out.println("Enter the tree species to learn more about it (\"quit\" to stop):");
            System.out.println();

            userValue = inTree.nextLine();
            if (!userValue.equalsIgnoreCase("quit")) {
                
                    commonTreeSpeciesList = treeSpeciesList.getByCommonName(userValue); // add all the common names to a list
                    latinTreeSpeciesList = treeSpeciesList.getByLatinName(userValue);// add all the latin names to a list

                if (!userValue.equalsIgnoreCase("quit") && commonTreeSpeciesList == null
                        && latinTreeSpeciesList == null) { // if user input does not equal quit, and both lists are empty
                    System.out.println("There are no records of " + userValue + " on NYC streets."); // print this
                    System.out.println();
                }
                if (commonTreeSpeciesList != null) {
                    outCommonTreeSpeciesList.add(commonTreeSpeciesList.get(0)); // outCommonTreeSpeciesList must have 1
                                                                                // element

                    for (int i = 1; i < commonTreeSpeciesList.size(); i++) {
                        for (int j = 0; j < outCommonTreeSpeciesList.size(); j++) {
                            if (outCommonTreeSpeciesList.get(j).equals(commonTreeSpeciesList.get(i))) {
                                inList = true;
                            }
                        }
                        if (inList == false) {
                            outCommonTreeSpeciesList.add(commonTreeSpeciesList.get(i));
                        }
                        inList = false;
                    }
                    System.out.println("All matching species: ");

                    for (int i = 0; i < outCommonTreeSpeciesList.size(); i++) {
                        System.out.println(outCommonTreeSpeciesList.get(i).getName());
                    }
                    System.out.println();
                }
                if (latinTreeSpeciesList != null) {
                    outLatinTreeSpeciesList.add(latinTreeSpeciesList.get(0)); // outLatinTreeSpeciesList must have 1 element

                    for (int i = 1; i < latinTreeSpeciesList.size(); i++) {
                        for (int j = 0; j < outLatinTreeSpeciesList.size(); j++) {
                            if (outLatinTreeSpeciesList.get(j).equals(latinTreeSpeciesList.get(i))) {
                                inList = true;
                            }
                        }
                        if (inList == false) {
                            outLatinTreeSpeciesList.add(latinTreeSpeciesList.get(i));

                        }
                        inList = false;
                    }
                    if (!(outCommonTreeSpeciesList.size() == 0)) {
                        System.out.println("All matching species: ");
                    }
                    for (int i = 0; i < outLatinTreeSpeciesList.size(); i++) {

                        System.out.println(outLatinTreeSpeciesList.get(i).getSpecies());
                    }
                    System.out.println();
                }
                if (commonTreeSpeciesList != null || latinTreeSpeciesList != null) {

                    totalFlowersNYC = list.getTotalNumberOfTrees();
                    totalFlowersManhattan = list.getCountByBorough("Manhattan");
                    totalFlowersBronx = list.getCountByBorough("Bronx");
                    totalFlowersBrooklyn = list.getCountByBorough("Brooklyn");
                    totalFlowersQueens = list.getCountByBorough("Queens");
                    totalFlowersStatenIsland = list.getCountByBorough("Staten Island");

                    for (int i = 0; i < outCommonTreeSpeciesList.size(); i++) {
                        numOfFlowersNYC += list.getCountByCommonName(outCommonTreeSpeciesList.get(i).getName());
                        numOfFlowersManhattan += list.getCountByCommonNameBorough(
                                outCommonTreeSpeciesList.get(i).getName(),
                                "Manhattan");
                        numOfFlowersBronx += list.getCountByCommonNameBorough(outCommonTreeSpeciesList.get(i).getName(),
                                "Bronx");
                        numOfFlowersBrooklyn += list.getCountByCommonNameBorough(
                                outCommonTreeSpeciesList.get(i).getName(),
                                "Brooklyn");
                        numOfFlowersQueens += list.getCountByCommonNameBorough(
                                outCommonTreeSpeciesList.get(i).getName(),
                                "Queens");
                        numOfFlowersStatenIsland += list
                                .getCountByCommonNameBorough(outCommonTreeSpeciesList.get(i).getName(),
                                        "Staten Island");
                    }

                    for (int i = 0; i < outLatinTreeSpeciesList.size(); i++) {
                        numOfFlowersNYC += list.getCountByLatinName(outLatinTreeSpeciesList.get(i).getSpecies());
                        numOfFlowersManhattan += list.getCountByLatinNameBorough(
                                outLatinTreeSpeciesList.get(i).getSpecies(),
                                "Manhattan");
                        numOfFlowersBronx += list.getCountByLatinNameBorough(
                                outLatinTreeSpeciesList.get(i).getSpecies(),
                                "Bronx");
                        numOfFlowersBrooklyn += list.getCountByLatinNameBorough(
                                outLatinTreeSpeciesList.get(i).getSpecies(),
                                "Brooklyn");
                        numOfFlowersQueens += list.getCountByLatinNameBorough(
                                outLatinTreeSpeciesList.get(i).getSpecies(),
                                "Queens");
                        numOfFlowersStatenIsland += list
                                .getCountByLatinNameBorough(outLatinTreeSpeciesList.get(i).getSpecies(),
                                        "Staten Island");
                    }

                    percentageNYC = (((double) numOfFlowersNYC / totalFlowersNYC) * 100);
                    if (totalFlowersNYC == 0) {
                        percentageNYC = 0.00;
                    }
                    percentageManhattan = (((double) numOfFlowersManhattan / totalFlowersManhattan) * 100);
                    if (totalFlowersManhattan == 0) {
                        percentageManhattan = 0.00;
                    }
                    percentageBronx = (((double) numOfFlowersBronx / totalFlowersBronx) * 100);
                    if (totalFlowersBronx == 0) {
                        percentageBronx = 0.00;
                    }
                    percentageBrooklyn = (((double) numOfFlowersBrooklyn / totalFlowersBrooklyn) * 100);
                    if (totalFlowersBrooklyn == 0) {
                        percentageBrooklyn = 0.00;
                    }
                    percentageQueens = (((double) numOfFlowersQueens / totalFlowersQueens) * 100);
                    if (totalFlowersQueens == 0) {
                        percentageQueens = 0.00;
                    }
                    percentageStatenIsland = (((double) numOfFlowersStatenIsland / totalFlowersStatenIsland) * 100);
                    if (totalFlowersStatenIsland == 0) {
                        percentageStatenIsland = 0.00;
                    }

                    System.out.println("Popularity in the city: ");
                    System.out.printf("%8s%12s%,17d(%,2d)%14.2f%%\n", "NYC", ":", numOfFlowersNYC, totalFlowersNYC,
                            percentageNYC);
                    System.out.printf("%14s%6s%,17d(%,1d)%15.2f%%\n", "Manhattan", ":", numOfFlowersManhattan,
                            totalFlowersManhattan, percentageManhattan);
                    System.out.printf("%10s%10s%,17d(%,1d)%15.2f%%\n", "Bronx", ":", numOfFlowersBronx,
                            totalFlowersBronx,
                            percentageBronx);
                    System.out.printf("%13s%7s%,17d(%,1d)%15.2f%%\n", "Brooklyn", ":", numOfFlowersBrooklyn,
                            totalFlowersBrooklyn, percentageBrooklyn);
                    System.out.printf("%11s%9s%,17d(%,1d)%15.2f%%\n", "Queens", ":", numOfFlowersQueens,
                            totalFlowersQueens,
                            percentageQueens);
                    System.out.printf("%18s%2s%,17d(%,1d)%15.2f%%\n", "Staten Island", ":", numOfFlowersStatenIsland,
                            totalFlowersStatenIsland, percentageStatenIsland);
                }
            }
            outCommonTreeSpeciesList.clear();
            outLatinTreeSpeciesList.clear();
            numOfFlowersNYC = 0;
            numOfFlowersManhattan = 0;
            numOfFlowersBronx = 0;
            numOfFlowersBrooklyn = 0;
            numOfFlowersQueens = 0;
            numOfFlowersStatenIsland = 0;
            totalFlowersNYC = 0;
            totalFlowersManhattan = 0;
            totalFlowersBronx = 0;
            totalFlowersBrooklyn = 0;
            totalFlowersQueens = 0;
            totalFlowersStatenIsland = 0;
            percentageNYC = 0.0;
            percentageManhattan = 0.0;
            percentageBronx = 0.0;
            percentageBrooklyn = 0.0;
            percentageQueens = 0.0;
            percentageStatenIsland = 0.0;
        } while (!userValue.equalsIgnoreCase("quit"));

        inTree.close();
    }
}
