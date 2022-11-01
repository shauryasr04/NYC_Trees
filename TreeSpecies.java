package project2;

/**
 * TreeSpecies class is consisted of two fields that take in a common name and a
 * latin name and stores it into the TreeSpecies object.
 * This class also contains the equals method and has some basic setters and
 * getters and a toString().
 * 
 * @author Shaurya Srivastava
 */
public class TreeSpecies {
    private String cname;
    private String lName;

    /**
     * Tree Species Constructor
     * 
     * @param commonName - Common Name of a Species
     * @param latinName  - Latin Name of a Species
     * @throws IllegalArgumentException if either inputted common name or latin name
     *                                  is null
     */
    public TreeSpecies(String commonName, String latinName) throws IllegalArgumentException {
        if (commonName == null || latinName == null) {
            throw new IllegalArgumentException("Enter a proper String name or a blank string, entry CANNOT be null!");
        }
        cname = commonName;
        lName = latinName;
    }

    /**
     * Overriden equals method. Comparison is done by both the Common and Latin
     * name, case nsensitve. Common is checked first in this case.
     * 
     * @param other - Other TreeSpecies object to be compared
     * @return true if other is equal to the current TreeSpecies object, false
     *         otherwise
     * 
     */
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (!(other instanceof TreeSpecies))
            return false;
        TreeSpecies other2 = (TreeSpecies) other;
        if (this.cname.equalsIgnoreCase(other2.cname)) {
            if (this.lName.equalsIgnoreCase(other2.lName)) {
                return true;
            }
        }
        return false;

    }

    /*
     * @return cname (Common Name) of the given Tree Species
     */
    public String getCommonName() {
        return cname;
    }

    /*
     * @return lName (Latin Name) of the given Tree Species
     */
    public String getLatinName() {
        return lName;
    }

    /*
     * TreeSpecies toString() method
     * 
     * @return a String representation of the Tree Species by printing out both the
     * Common and the Latin Name
     */
    public String toString() {
        return cname + ", " + lName;
    }

}