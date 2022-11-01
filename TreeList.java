package project2;

/**
 * TreeList class is composed of Nodes (that are implemented in their own
 * private class) that hold a Tree object as data and a "next"
 * reference to the next Node. This class also has methods that further work
 * with the created TreeList. It is composed of methods that return
 * the number of Trees in a specific borough, number of Trees in general, and
 * the number of Trees that contain a specific species name in a
 * specific borough, just to name a few.
 * 
 * @author Shaurya Srivastava
 */
public class TreeList {
    /**
     * Private Node Class which takes in a Tree Object as the data. Created a
     * constructor as well.
     */
    private class Node {
        Tree data;
        Node next;

        private Node(Tree x) {
            data = x;
            next = null;
        }
    }

    /*
     * TreeList fields. A reference to the head pointing to the front of the list,
     * lail pointing to the back of the list, and a size
     * field to keep track of how many nodes are in the list.
     */
    Node head;
    Node tail;
    int size;

    /**
     * TreeList Constructor
     * Initializes head and tail to null, signifies the creation of a new, empty
     * list
     * Sets size to 0 since the newly created list is empty
     */
    public TreeList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * TreeList add method. Given a paramter, it will create a node with that
     * parameter and add to the TreeList.
     * Always adds the node to the END of the TreeList
     * 
     * @param tr - Tree object to be added to the TreeList
     * @throws IllegalArgumentException if tr is null.
     */
    public void add(Tree tr) throws IllegalArgumentException {
        Node x = new Node(tr);
        if (tr == null) {
            throw new IllegalArgumentException("Entered value CANNOT be null.. enter a valid Tree");
        }
        if (head == null) {
            head = x;
            tail = x;
            size++;
        } else {
            tail.next = x;
            tail = x;
            size++;
        }
    }

    /**
     * Method returns the total number of trees, returns the size field in this case
     * 
     * @return size, the total number of nodes in the TreeList
     */
    public int getTotalNumberOfTrees() {
        return size;
    }

    /**
     * Iterates through the TreeList and checks for a specific inputted speciesName
     * (common name) to determine the total count
     * of that species name (common name) in the Tree List.
     * 
     * @param speciesName - a keyword speciesName (specifically common name)
     * @return the number of speacies with the latin name'speciesName' in the
     *         TreeList
     */
    public int getCountByCommonName(String speciesName) {
        speciesName = speciesName.toLowerCase();
        Node current = head;
        int count = 0;
        while (current != null) {
            if (current.data.getCommon().toLowerCase().equals(speciesName)) {
                count++;
            }
            current = current.next;
        }
        return count;
    }

    /**
     * Iterates through the TreeList and checks for a specific inputted speciesName
     * (latin) to determine the total count
     * of that species name (in latin) in the Tree List.
     * 
     * @param speciesName - user inputted species Name (Latin Name)
     * @return - the number of speacies with the latin name'speciesName' in the
     *         TreeList
     */

    public int getCountByLatinName(String speciesName) {
        speciesName = speciesName.toLowerCase();
        Node current = head;
        int count = 0;
        while (current != null) {
            if (current.data.getLatin().toLowerCase().equals(speciesName)) {
                count++;
            }
            current = current.next;
        }
        return count;
    }

    /**
     * Iterates through the TreeList and checks for a specific borough to determine
     * a total number of Trees in the Borough
     * 
     * @param boroName - a borough name given through user input
     * @return the number of trees in a specific borough given as user input
     */
    public int getCountByBorough(String boroName) {
        boroName = boroName.toLowerCase();
        Node current = head;
        int count = 0;
        while (current != null) {
            if (current.data.getBoroname().toLowerCase().equals(boroName)) {
                count++;
            }
            current = current.next;
        }
        return count;

    }

    /**
     * This method returns the Number of a specific species (by common name) in a
     * given borough
     * Iterates through the linked list and checks based on a given species name and
     * borough how many corresponding trees there are
     * 
     * @param speciesName - the species name in LATIN
     * @param boroName    - the name of the borough in NYC
     * @return the number of a specific species (by latin name) in a given borough
     */
    public int getCountByCommonNameBorough(String speciesName, String boroName) {
        speciesName = speciesName.toLowerCase();
        boroName = boroName.toLowerCase();
        Node current = head;
        int count = 0;
        while (current != null) {
            if (current.data.getCommon().toLowerCase().equals(speciesName)) {
                if (current.data.getBoroname().toLowerCase().equals(boroName)) {
                    count++;
                }
            }
            current = current.next;
        }
        return count;
    }

    /*
     * This method returns the Number of a specific species (by latin name) in a
     * given borough
     * Iterates through the linked list and checks based on a given species name and
     * borough how many corresponding trees there are
     * 
     * @param speciesName - the species name in LATIN
     * 
     * @param boroName - the name of the borough in NYC
     * 
     * @return the number of a specific species (by latin name) in a given borough
     */
    public int getCountByLatinNameBorough(String speciesName, String boroName) {
        speciesName = speciesName.toLowerCase();
        boroName = boroName.toLowerCase();
        Node current = head;
        int count = 0;
        while (current != null) {
            if (current.data.getLatin().toLowerCase().equals(speciesName)) {
                if (current.data.getBoroname().toLowerCase().equals(boroName)) {
                    count++;
                }
            }
            current = current.next;
        }
        return count;
    }

    /**
     * ToString representation of the TreeList
     * 
     * @return the String that contains all of the data that is stored in each
     *         individual Tree.
     */
    public String toString() {
        Node current = head;
        String outputted = "";
        System.out.println("------List of Trees-------------");
        while (current != null) {
            outputted += current.data + ", ";
            current = current.next;
        }
        return outputted;
    }

}
