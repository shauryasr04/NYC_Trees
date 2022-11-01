package project2;

/**
 * This tree class passes a constructor that takes in a treeId and a TreeSpecies
 * object of its common and latin name. It also is composed of
 * other fields such as health, status, zipcode, etc, but the main ones are the
 * common name, latin name, borough, and the ID. It also has an
 * overriden compareTo method since this class implemetns the Comparable<Tree>
 * interface. This class is the basic outline for a tree class, with
 * setters and getters for each field as well as a toString() method that prints
 * out a String representation for each Tree object.
 * 
 * @author Shaurya Srivastava
 */
public class Tree implements Comparable<Tree> {
    // fields
    private int tree_ID;
    private String status;
    private String health;
    private String spc_latin;
    private String spc_common;
    private int zipcode;
    private String boroname;
    private double x_sp;
    private double y_sp;

    /*
     * Tree Constructor
     * 
     * @param treeID - the ID number for each unique tree
     * 
     * @param species - a TreeSpecies object of both the common name and the latin
     * name for the tree
     * 
     * @throws IllegalArgumentException if inputted species value is null
     * 
     * @throws IllegalArgumentException if inputted ID number is null
     */
    public Tree(int treeID, TreeSpecies species) {
        if (species == null) {
            throw new IllegalArgumentException(
                    "Species (Common and/or Latin) value entered CANNOT be null! Try again.");
        }
        if (treeID < 0) {
            throw new IllegalArgumentException("ID Number entered CANNOT be a negative number! Try again.");
        }
        tree_ID = treeID;
        spc_common = species.getCommonName();
        spc_latin = species.getLatinName();
    }

    /**
     * @Override compareTo method. Comparison is checked by Common Name, and then
     *           further checked by tree ID.
     * @param Tree o - user inputted tree
     * @return 0 if o is less than current tree, 1 if o is greater than current
     *         Tree, and 0 if trees are the same.
     */
    public int compareTo(Tree o) {
        if (this.spc_common.equalsIgnoreCase(o.spc_common)) {
            if (this.tree_ID == o.tree_ID) {
                return 0;
            } else if (this.tree_ID > o.tree_ID) {
                return 1;
            } else {
                return -1;
            }

        } else if (this.spc_common.compareToIgnoreCase(o.spc_common) < 0) {
            return -1;
        } else if (this.spc_common.compareToIgnoreCase(o.spc_common) > 0) {
            return 1;
        }
        return 0;
    }

    /**
     * Tree equals method. Comparison is done based on Latin and Common Name first,
     * and ID number if both latin and common name are the same.
     * 
     * @param Object o - user inputted object
     * @return true if both objects are equal, false if they aren't
     */
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof Tree))
            return false;
        Tree x = (Tree) o;
        if (this.spc_latin.equalsIgnoreCase(x.spc_latin)) {
            if (this.spc_common.equalsIgnoreCase(x.spc_common)) {
                if (this.tree_ID == x.tree_ID) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Tree toString() method
     * 
     * @return a String representation of the Tree Object
     */
    public String toString() {
        return "Tree " + tree_ID + ": Status: " + status + ", Health: " + health + ", Latin Name: " + spc_latin + ", Common Name: " + spc_common + ", Zipcode: " + zipcode + ", Borough Name: " + boroname + ", X_sp: "+ x_sp + ", Y_sp: " + y_sp;
    }

    /**
     * Sets the ID Value for a given tree
     * 
     * @param tID - user inputted integer value
     * @throws IllegalArgumentException if user inputs a number less than zero
     */
    public void setTreeID(int tID) {
        if (tID < 0) {
            throw new IllegalArgumentException(
                    "Enter a positive integer for the ID! Negative Numbers are not allowed!");
        }
        tree_ID = tID;
    }

    /**
     * Sets the status for a given tree
     * 
     * @param s - user inputted String
     * @throws IllegalArgumentException if "s" is not "Alive", "Dead", "Stump", " ",
     *                                  or null.
     */
    public void setStatus(String s) {
        if (s.equalsIgnoreCase("Alive") || s.equalsIgnoreCase("Dead") || s.equalsIgnoreCase("Stump")
                || s.equalsIgnoreCase("") || s == null) {
            status = s;
        } else {
            throw new IllegalArgumentException("Enter a valid status description!");
        }
    }

    /**
     * Sets the health for a given tree
     * 
     * @param x - a user inputted String value
     * @throws IllegalArgumentException if "x" is not "Good", "Fair", "Poor", " ",
     *                                  or null.
     */
    public void setHealth(String x) {
        if (x.equalsIgnoreCase("Good") || x.equalsIgnoreCase("Fair") || x.equalsIgnoreCase("Poor")
                || x.equalsIgnoreCase("") || x == null) {
            health = x;
        } else {
            throw new IllegalArgumentException("Enter a valid health description!");
        }
    }

    /**
     * Sets the Latin name for a given tree
     * 
     * @param lat - a user inputted latin name
     * @throws IllegalArgumentException if inputted value is null
     */
    public void setLatin(String lat) {
        if (lat == null) {
            throw new IllegalArgumentException(
                    "Enter a proper string integer for the Latin Name! Null isn't allowed!");
        }
        spc_latin = lat;
    }

    /**
     * Sets the Common name for a given tree
     * 
     * @param com - a user inputted common name
     * @throws IllegalArgumentException if inputted value is null
     */
    public void setCommon(String com) {
        if (com == null) {
            throw new IllegalArgumentException("Enter a name for the Common Name! Null Values are not allowed!");
        }
        spc_common = com;
    }

    /**
     * Sets the zipcode for a given tree
     * 
     * @param tID - inputted zipcode
     * @throws IllegalArgumentException if entered zipcode is out of the given range
     *                                  of 0-99999
     */
    public void setZipcode(int zip) {
        if (zip < 0 || zip > 99999) {
            throw new IllegalArgumentException("Enter a valid zipcode number!");
        }
        zipcode = zip;
    }

    /**
     * Sets the borough name for a given tree
     * 
     * @param x - inputted borough name
     * @throws IllegalArgumentException if inputted String isn't one of the five NYC
     *                                  boroughs or a blank string
     */
    public void setBoroname(String x) {
        if (!(x.equalsIgnoreCase("Manhattan") || x.equalsIgnoreCase("Bronx") || x.equalsIgnoreCase("Brooklyn")
                || x.equalsIgnoreCase("Queens") || x.equalsIgnoreCase("Staten Island") || x.equalsIgnoreCase(""))) {
            throw new IllegalArgumentException("Enter a valid borough name! : " + x + " does not exist");
        }
        boroname = x;
    }

    /**
     * Sets the X_sp for a given tree
     * 
     * @param x - inputted x_sp value
     */
    public void setxp(Double x) {
        x_sp = x;
    }

    /**
     * Sets the Y_sp for a given tree
     * 
     * @param y - inputted y_sp value
     */
    public void setyp(Double y) {
        y_sp = y;
    }

    /*
     * Tree ID get method
     * 
     * @return tree_ID - ID value of a tree
     */
    public int getTreeID() {
        return tree_ID;
    }

    /*
     * Status get method
     * 
     * @return status - status of a tree
     */
    public String getStatus() {
        return status;
    }

    /*
     * health get method
     * 
     * @return health - health of a tree
     */
    public String getHealth() {
        return health;
    }

    /*
     * Latin species name get method
     * 
     * @return spc_latin - Latin name of a tree
     */
    public String getLatin() {
        return spc_latin;
    }

    /*
     * Common species name get method
     * 
     * @return spc_common - Common name of a tree
     */
    public String getCommon() {
        return spc_common;
    }

    /*
     * Zipcode get method
     * 
     * @return zipcode - Zipcode of a tree
     */
    public int getZipcode() {
        return zipcode;
    }

    /*
     * Borough name get method
     * 
     * @return boroname - borough location of a tree
     */
    public String getBoroname() {
        return boroname;
    }

    /*
     * X_sp get method
     * 
     * @return x_sp - X_sp of a tree
     */
    public double getX_sp() {
        return x_sp;
    }

    /*
     * Y_sp get method
     * 
     * @return y_sp - Y_sp of a tree
     */
    public double getY_sp() {
        return y_sp;
    }
}
