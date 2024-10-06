package project2;

public class TreeList {
    private Node head;
    private Node tail;
    private int listCount = 0;

    public class Node {
        Tree data;

        Node next;

        Node(Tree d) {
            data = d;
            next = null;
        }

        /**
         * @return the next Node
         */
        public Node getNext() {
            return next;
        }

        /**
         * @param next set the next node to this.next
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /**
         * @param element a string for what the program wants to recieve
         * @return  common name of trees, latin names of trees, statuses of trees, health of trees, and borough name of trees
         */
        public String toString(String element) {
            if (element.equalsIgnoreCase("spc_common")) { // common name of trees
                return this.data.getSpc_common();
            }
            if (element.equalsIgnoreCase("spc_latin")) { // latin names of trees
                return this.data.getSpc_latin();
            }
            if (element.equalsIgnoreCase("status")) { // statuses of trees
                return this.data.getStatus();
            }
            if (element.equalsIgnoreCase("health")) { // health of trees
                return this.data.getHealth();
            }
            if (element.equalsIgnoreCase("boroName")) { // borough name of trees
                return this.data.getBoroname();
            }
            return element;

        }
    }

    /**
     * Creates a new TreeList, setting head and tail to null and listCount to 0 
     */
    public TreeList() {
        head = null;
        tail = null;
        listCount = 0;
    }

    /**
     * @param tree adds tree to the end of the TreeList
     */
    public void add(Tree tree) {
        // create a new node with given tree
        Node new_node = new Node(tree);
        try {
            // test if tree is null, throw IllegalArgumentException if true
            if (tree == null) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            System.err.println("Invalid tree input");
            System.exit(1);
        }
        if (head == null) { // first element of the list
            head = new_node;
            tail = head;
        } else {
            tail.setNext(new_node);
            tail = tail.getNext();
        }
        listCount++;
    }

    /** Return the total number of trees in a list
     * @return the total number of trees in the list
     */
    public int getTotalNumberOfTrees() {
        return listCount;
    }

    /**
     * @param speciesName a string for which elements in the list to be compared against 
     * @return a new list of those element's common names were equal to speciesName
     */
    public int getCountByCommonName(String speciesName) {
        int counter = 0;
        Node compare = head;
        while (compare != null) {
            if (compare == tail) {
                return counter;
            }
            if (speciesName.equalsIgnoreCase(compare.toString("spc_common"))) {
                counter++;
            }
            compare = compare.next;
        }
        return counter;
    }

    /**
     * @param speciesName a string for which elements in the list to be compared against
     * @returna new list of those element's latin names were equal to speciesName
     */
    public int getCountByLatinName(String speciesName) {
        int counter = 0;
        Node compare = head;
        while (compare != null) {
            if (compare == tail) {
                return counter;
            }
            if (speciesName.equalsIgnoreCase(compare.toString("spc_latin"))) {
                counter++;
            }
            compare = compare.next;

        }
        return counter;
    }

    /**
     * @param boroName a string describing a borough in which elements in the list to be compared against
     * @returnnew list of those element's borough were equal to boroName
     */
    public int getCountByBorough(String boroName) {
        int counter = 0;
        Node compare = head;
        while (compare != null) {
            if (compare == tail) {
                return counter;
            }
            if (boroName.equalsIgnoreCase(compare.toString("boroName"))) {
                counter++;
            }
            compare = compare.next;
            
        }
        return counter;
    }

    /**
     * @param speciesName a string for which elements in the list to be compared against
     * @param boroNamea a string describing a borough in which elements in the list to be compared against
     * @return list of those element's common names and borough were both equal to speciesName and boroName
     */
    public int getCountByCommonNameBorough(String speciesName, String boroName) {
        int counter = 0;
        Node compare = head;
        while (compare != null) {
            if (compare == tail) {
                return counter;
            }
            if (boroName.equalsIgnoreCase(compare.toString("boroName"))
                    && speciesName.equalsIgnoreCase(compare.toString("spc_common"))) {
                counter++;
            }
            compare = compare.next;

        }
        return counter;
    }

    /**
     * @param speciesName a string for which elements in the list to be compared against
     * @param boroName a string describing a borough in which elements in the list to be compared against
     * @return list of those element's latin names and borough were both equal to speciesName and boroName
     */
    public int getCountByLatinNameBorough(String speciesName, String boroName) {
        int counter = 0;
        Node compare = head;
        while (compare != null) {
            if (compare == tail) {
                return counter;
            }
            if (boroName.equalsIgnoreCase(compare.toString("boroName"))
                    && speciesName.equalsIgnoreCase(compare.toString("spc_latin"))) {
                counter++;
            }
            compare = compare.next;

        }
        return counter;
    }

}
