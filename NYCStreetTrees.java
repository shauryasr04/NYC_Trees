package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * The main class. This class reads the data file (entered as an argument in the
 * console). Stores the values read in that data file
 * into a respective Tree and TreeSpecies object and also constructs the
 * respective TreeList and TreeSpecies Lists. It then prompts the user
 * to input a specific keyword in relation to the species they are searching
 * for, where the class then calls various methods from the TreeList and
 * TreeSpecies list classes to find and return the count of the specifc trees
 * that match the user's input. The display is divided based on borough
 * while also providing data for the entirety of NYC. It displays the matching
 * species', the number of Trees with that specifies found in the respective
 * borough (or NYC),
 * the total number of trees in that borough (or NYC), and the computed
 * percentage between the number of matching Trees and the total number of
 * trees.
 * 
 * @author Shaurya Srivastava
 */
public class NYCStreetTrees {
    /**
     * The main() method of this program.
     * 
     * @param args array of Strings provided on the command line when the program is
     *             started;
     *             the first string should be the name of the input file containing
     *             the list of the NYC Street Trees.
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        final DecimalFormat df = new DecimalFormat("0.00");
        /*
         * Following Code to read the file from the command line is taken from the
         * ColorConverter.java file credited to Joanna Klukowska
         */
        // verify that the command line argument exists
        if (args.length == 0) {
            System.err.println("Usage Error: the program expects file name as an argument.\n");
            System.exit(1);
        }

        // verify that command line argument contains a name of an existing file
        File treeFile = new File(args[0]);
        if (!treeFile.exists()) {
            System.err.println("Error: the file " + treeFile.getAbsolutePath() + " does not exist.\n");
            System.exit(1);
        }
        // verifying that the command line file that was inputted can actually be used
        // for reading.
        if (!treeFile.canRead()) {
            System.err.println("Error: the file " + treeFile.getAbsolutePath() +
                    " cannot be opened for reading.\n");
            System.exit(1);
        }
        // creation of TreeList(), TreeSpeciesList(), and Scanner to read the file
        //
        TreeList treeList = new TreeList();
        TreeSpeciesList treeSpeciesList = new TreeSpeciesList();
        Scanner scan = null;
        try {
            scan = new Scanner(treeFile);
        } catch (FileNotFoundException e) {
            System.out.println("File was not Found!");
        }
        // Usage of the CSV class, creation of the CSV Object and reading the data set
        // row by row
        CSV dataset = new CSV(scan);
        ArrayList<String> row = new ArrayList<String>();
        for (int i = 0; i < dataset.getNumOfRows(); i++) {
            try {
                row = dataset.getNextRow();
                TreeSpecies treeSpeciesTEMP = new TreeSpecies(row.get(9), row.get(8));
                treeSpeciesList.add(treeSpeciesTEMP);
                Tree t = new Tree(Integer.parseInt(row.get(0)), treeSpeciesTEMP);
                t.setBoroname(row.get(29));
                treeList.add(t);
            } catch (NumberFormatException e) {
                System.out.println("");
            }

        }
        scan.close();
        // Opening new scanner for User input
        Scanner scan2 = new Scanner(System.in);
        System.out.println("Enter the tree species to learn more about it (quit to stop): ");
        String input = scan2.nextLine();
        while (!(input.equals("quit"))) {
            TreeSpeciesList output_Common_Name = treeSpeciesList.getByCommonName(input);
            TreeSpeciesList output_Latin_Name = treeSpeciesList.getByLatinName(input);
            if (output_Common_Name == null && output_Latin_Name == null) {
                System.out.println("There are no records of " + "'" + input + "'" + " on NYC Streets.");
            } else {
                System.out.println("All Matching Species: ");
                // defining of all numerical variables (Count total in NYC)
                int numofTreesManhattan = treeList.getCountByBorough("Manhattan");
                int numofTreesQueens = treeList.getCountByBorough("Queens");
                int numofTreesBrooklyn = treeList.getCountByBorough("Brooklyn");
                int numofTreesStatenIsland = treeList.getCountByBorough("Staten Island");
                int numofTreesBronx = treeList.getCountByBorough("Bronx");
                int numofTreesNyc = numofTreesManhattan + numofTreesQueens + numofTreesBrooklyn + numofTreesStatenIsland
                        + numofTreesBronx;
                int outputtedManhattanTrees = 0;
                int outputtedBronxTrees = 0;
                int outputtedBrooklynTrees = 0;
                int outputtedStatenIslandTrees = 0;
                int outputtedQueensTrees = 0;
                int outputtedNumofTreesNYC = 0;
                // creating an ArrayList of the combined results so that they can be
                // Alphabetically Ordered
                ArrayList<String> compiledList = new ArrayList<String>();
                // accessing each individual element from the common and latin outputted lists
                // to combine them
                if (output_Common_Name != null) {
                    for (int i = 0; i < output_Common_Name.size(); i++) {
                        String input2 = output_Common_Name.get(i).getCommonName();
                        compiledList.add(input2);

                    }
                }

                if (output_Latin_Name != null) {
                    for (int i = 0; i < output_Latin_Name.size(); i++) {
                        String input2 = output_Latin_Name.get(i).getLatinName();
                        compiledList.add(input2);

                    }
                }
                // sorts the list of all the names so that they can be in alphabetical order
                // using Collections.sort();
                Collections.sort(compiledList);
                /**
                 * The following code below is done so that you can avoid double counting the
                 * same TreeSpecies objects that may
                 * come in both the Latin and the Common Name outputted TreeList.
                 */
                TreeSpeciesList totalSpecList = output_Common_Name;
                if (output_Latin_Name == null) {

                } else if (output_Common_Name == null) {
                    totalSpecList = output_Latin_Name;
                } else {
                    for (TreeSpecies x : output_Latin_Name) {
                        if (!totalSpecList.contains(x))
                            totalSpecList.add(x);
                    }
                }
                for (TreeSpecies x : totalSpecList) {
                    outputtedManhattanTrees += treeList.getCountByCommonNameBorough(x.getCommonName(), "Manhattan");
                    outputtedBronxTrees += treeList.getCountByCommonNameBorough(x.getCommonName(), "Bronx");
                    outputtedBrooklynTrees += treeList.getCountByCommonNameBorough(x.getCommonName(), "Brooklyn");
                    outputtedQueensTrees += treeList.getCountByCommonNameBorough(x.getCommonName(), "Queens");
                    outputtedStatenIslandTrees += treeList.getCountByCommonNameBorough(x.getCommonName(),
                            "Staten Island");
                }
                // prints out the relvant species names (both common and latin) from the
                // compiled array.
                for (int i = 0; i < compiledList.size(); i++) {
                    System.out.println("\t" + compiledList.get(i).toLowerCase());
                }
                // creates the total NYC amount of trees variable based on the TreeSpecies
                // Objects that are found
                outputtedNumofTreesNYC = outputtedManhattanTrees + outputtedBronxTrees + outputtedBrooklynTrees
                        + outputtedStatenIslandTrees + outputtedQueensTrees;
                System.out.println();
                /*
                 * Following Code Below is for Formatting the string
                 * Using System.out.printf()
                 */
                String NYC = "NYC:";
                String Manhttan = "Manhattan:";
                String Bronx = "Bronx:";
                String Brooklyn = "Brooklyn:";
                String StatenIsland = "Staten Island:";
                String Queens = "Queens:";
                System.out.println("Popularity in the City: ");
                System.out.printf("\t" + "%-30s %-20s %-10s\n", NYC, outputtedNumofTreesNYC + "(" + numofTreesNyc + ")",
                        df.format((double) outputtedNumofTreesNYC / numofTreesNyc * 100) + "%");
                if (outputtedManhattanTrees == 0 && numofTreesManhattan == 0) {
                    System.out.printf("\t" + "%-30s %-20s %-10s\n", Manhttan,
                            outputtedManhattanTrees + "(" + numofTreesManhattan + ")",
                            df.format((double) 0 / 1 * 100) + "%");
                } else {
                    System.out.printf("\t" + "%-30s %-20s %-10s\n", Manhttan,
                            outputtedManhattanTrees + "(" + numofTreesManhattan + ")",
                            df.format((double) outputtedManhattanTrees / numofTreesManhattan * 100) + "%");
                }
                if (outputtedBronxTrees == 0 && numofTreesBronx == 0) {
                    System.out.printf("\t" + "%-30s %-20s %-10s\n", Bronx,
                            outputtedBronxTrees + "(" + numofTreesBronx + ")", df.format((double) 0 / 1 * 100) + "%");
                } else {
                    System.out.printf("\t" + "%-30s %-20s %-10s\n", Bronx,
                            outputtedBronxTrees + "(" + numofTreesBronx + ")",
                            df.format((double) outputtedBronxTrees / numofTreesBronx * 100) + "%");
                }
                if (outputtedBrooklynTrees == 0 && numofTreesBrooklyn == 0) {
                    System.out.printf("\t" + "%-30s %-20s %-10s\n", Brooklyn,
                            outputtedBrooklynTrees + "(" + numofTreesBrooklyn + ")",
                            df.format((double) 0 / 1 * 100) + "%");
                } else {
                    System.out.printf("\t" + "%-30s %-20s %-10s\n", Brooklyn,
                            outputtedBrooklynTrees + "(" + numofTreesBrooklyn + ")",
                            df.format((double) outputtedBrooklynTrees / numofTreesBrooklyn * 100) + "%");
                }
                if (outputtedQueensTrees == 0 && numofTreesQueens == 0) {
                    System.out.printf("\t" + "%-30s %-20s %-10s\n", Queens,
                            outputtedQueensTrees + "(" + numofTreesQueens + ")", df.format((double) 0 / 1 * 100) + "%");
                } else {
                    System.out.printf("\t" + "%-30s %-20s %-10s\n", Queens,
                            outputtedQueensTrees + "(" + numofTreesQueens + ")",
                            df.format((double) outputtedQueensTrees / numofTreesQueens * 100) + "%");
                }
                if (outputtedStatenIslandTrees == 0 && numofTreesStatenIsland == 0) {
                    System.out.printf("\t" + "%-30s %-20s %-10s\n", StatenIsland,
                            outputtedStatenIslandTrees + "(" + numofTreesStatenIsland + ")",
                            df.format((double) 0 / 1 * 100) + "%");
                } else {
                    System.out.printf("\t" + "%-30s %-20s %-10s\n", StatenIsland,
                            outputtedStatenIslandTrees + "(" + numofTreesStatenIsland + ")",
                            df.format((double) outputtedStatenIslandTrees / numofTreesStatenIsland * 100) + "%");
                }
            }
            // Reprints the User Input Prompt to keep the while loop running until the user
            // enters "quit"
            System.out.println("Enter the tree species to learn more about it (quit to stop): ");
            input = scan2.next();
        }
        scan2.close();
    }
}
