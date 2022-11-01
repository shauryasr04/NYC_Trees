package project2;

import java.util.ArrayList;

/**
 * This Class creates a TreeSpeciesList, which is an extension of
 * ArrayList<TreeSpecies>. This holds values of TreeSpecies objects,
 * which contain a common and a latin name. The class features two methods that
 * iterate through the created TreeSpecies list to either return
 * a new List of TreeSpecies that contain a given keyword in either their
 * commmon name or their latin name.
 * 
 * @author Shaurya Srivastava
 */
public class TreeSpeciesList extends ArrayList<TreeSpecies> {

    public TreeSpeciesList() {
    }

    /**
     * Iterates through the TreeSpeciesList to return the number of TreeSpecies that
     * have a corresponding keyword in its common name
     * 
     * @param keyword - a user inputted String
     * @return a TreeSpeciesList of all TreeSpecies that have the corresponding
     *         Keyword in their Common Name
     */
    public TreeSpeciesList getByCommonName(String keyword) {
        if (keyword == null) {
            throw new IllegalArgumentException("Input Cannot be Null! Enter a valid name!");
        }
        keyword = keyword.toLowerCase();
        TreeSpeciesList added = new TreeSpeciesList();
        for (TreeSpecies t : this) {
            String iterated = t.getCommonName();
            iterated = iterated.toLowerCase();
            if (iterated.contains(keyword)) {
                if (!(added.contains(t))) {
                    if (!(iterated.equals("spc_common"))) {
                        added.add(t);
                    }
                }
            }
        }
        if (added.size() > 0) {
            return added;
        }
        return null;
    }

    /**
     * Iterates through the TreeSpeciesList to return the number of TreeSpecies that
     * have a corresponding keyword in its latin name
     * 
     * @param keyword - a user inputted String
     * @return a TreeSpeciesList of all TreeSpecies that have the corresponding
     *         Keyword in their Latin Name
     */
    public TreeSpeciesList getByLatinName(String keyword) {

        if (keyword == null) {
            throw new IllegalArgumentException("Input Cannot be Null! Enter a valid name!");
        }
        keyword = keyword.toLowerCase();
        TreeSpeciesList addedl = new TreeSpeciesList();
        for (TreeSpecies t : this) {
            String iterated = t.getLatinName();
            iterated = iterated.toLowerCase();
            if (iterated.contains(keyword)) {
                if (!(addedl.contains(t))) {
                    if (!(iterated.equals("spc_latin"))) {
                        addedl.add(t);
                    }
                }
            }
        }
        if (addedl.size() > 0) {
            return addedl;
        }
        return null;
    }

}
