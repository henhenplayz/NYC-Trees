package project2;

public class Tree implements Comparable<Tree> {
    int treeID, zipcode;
    String status, health, spc_latin, spc_common, boroname;
    double x_sp, y_sp;

    /**
     * @param treeID     an integer containing a Tree Object's ID
     * @param status     a string of tree object's status
     * @param health     a string of tree object's health
     * @param spc_latin  a string of tree object's latin name
     * @param spc_common a string of tree object's common name
     * @param zipcode    an integer up to 5 places representing a tree object's
     *                   zipcode
     * @param boroname   a string of tree object's borough name
     * @param x_sp       a double value of tree object's x_sp value
     * @param y_sp       a double value of tree object's y_sp value
     */
    public Tree(int treeID, String status, String health, String spc_latin, String spc_common, int zipcode,
            String boroname, double x_sp, double y_sp) {
        // try to catch any illegal arguments for treeID and spc_latin
        try {
            if (treeID < 0 || spc_latin == null) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            System.err.println("Invalid treeID and/or species");
        }
        // try to catch any illegal arguments for the constructors of a tree object
        setTreeID(treeID);
        setStatus(status);
        setHealth(health);
        setSpc_common(spc_common);
        setSpc_latin(spc_latin);
        setZipcode(zipcode);
        setBoroname(boroname);
        setX_sp(x_sp);
        setY_sp(y_sp);

    }

    // two parameter constructor of treeID and species, the three most important
    // data points of a tree object
    public Tree(int treeID, TreeSpecies species) {

        if (treeID < 0 || species.getName() == null || species.getSpecies() == null) {
            throw new IllegalArgumentException();
        }

    }

    // Overriding equals() to compare two Tree objects
    @Override
    public boolean equals(Object o) {

        // tests to see if object o is a tree, if it is not then return false;
        if (!(o instanceof Tree)) {
            return false;
        }

        // let o be a tree to access its data
        Tree compare = (Tree) o;

        if (this.getSpc_common() != null && this.getSpc_latin() != null) {
            if (compare.getSpc_common() != null && compare.getSpc_latin() != null) {
                if ((this.getSpc_common().equalsIgnoreCase(compare.getSpc_common()))
                        && (this.getSpc_latin().equalsIgnoreCase(compare.getSpc_latin()))) {
                    if (this.getTreeID() == compare.getTreeID()) {
                        return true;
                    }
                }
            }
        }
        // uses compareTo to compare the called tree object and o tree
        if (this.compareTo(compare) == 0) {
            return true;
        }
        return false;

    }

    /**
     * @return the called tree's treeID
     */
    public int getTreeID() {
        return treeID;
    }

    /**
     * @param newID set the called tree's treeID
     */
    public void setTreeID(int newID) {
        try {
            if (newID >= 0) { // parameters of a new treeID
                this.treeID = newID;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            System.err.println("Illegal treeID.");
        }
    }

    /**
     * @return the called tree's status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * @param newStatus set the called tree's status
     */
    public void setStatus(String newStatus) {

        if (newStatus.equalsIgnoreCase("Alive") || newStatus.equalsIgnoreCase("Dead")
                || newStatus.equalsIgnoreCase("Stump") || "".equals(newStatus) || newStatus == null) { // parameters of a new status
            this.status = newStatus;
        } else {
            throw new IllegalArgumentException(); // throw IllegalArgumentException if parameters are not met
        }

        System.err.println("Illegal status."); // error output illegal status
    }

    /**
     * @return the called tree's health
     */
    public String getHealth() {
        return this.health;
    }

    /**
     * @param newHealth set the called tree's health
     */
    public void setHealth(String newHealth) {

        if (newHealth.equalsIgnoreCase("Good") || newHealth.equalsIgnoreCase("Fair")
                || newHealth.equalsIgnoreCase("Poor") || "".equals(newHealth) || newHealth == null) { // parameters of new health status
            this.health = newHealth;
        } else {
            throw new IllegalArgumentException(); // throw IllegalArgumentException if parameters are not met
        }

    }

    /**
     * @return the called tree's latin name
     */
    public String getSpc_latin() {
        return this.spc_latin;
    }

    // set the called tree's latin name
    public void setSpc_latin(String newSpc_latin) {
        try {
            if (!(newSpc_latin == null)) { // parameter of new tree's latin name
                this.spc_latin = newSpc_latin;
            } else {
                throw new IllegalArgumentException(); // throw IllegalArgumentException if parameter is not met
            }
        } catch (Exception e) {
            System.err.println("Illegal latin name."); // error output illegal health status
        }
    }

    /**
     * @return the called tree's common name
     */
    public String getSpc_common() {
        return this.spc_common;
    }

    /**
     * @param newSpc_common set the called tree's common name
     */
    public void setSpc_common(String newSpc_common) {
        try {
            if (!(newSpc_common == null)) { // parameter of new tree's common name
                this.spc_common = newSpc_common;
            } else {
                throw new IllegalArgumentException(); // throw IllegalArgumentException if parameter is not met
            }
        } catch (Exception e) {
            System.err.println("Illegal common name."); // error output illegal common name
        }

    }

    /**
     * @return the called tree's zipcode
     */
    public int getZipcode() {
        return this.zipcode;
    }

    /**
     * @param newZipcode set the called tree's zipcode
     */
    public void setZipcode(int newZipcode) {

        if (0 < newZipcode && newZipcode < 99999) { // parameters of new tree's zipcode
            this.zipcode = newZipcode;
        } else {
            throw new IllegalArgumentException(); // throw IllegalArgumentException if parameters are not met
        }
    }

    /**
     * @return the called tree's borough name
     */
    public String getBoroname() {
        return this.boroname;
    }

    /**
     * @param boroname set the called tree's borough name
     */
    public void setBoroname(String boroname) {
        if (boroname.equalsIgnoreCase("Manhattan") || boroname.equalsIgnoreCase("Bronx")
                || boroname.equalsIgnoreCase("Brooklyn") || boroname.equalsIgnoreCase("Queens")
                || boroname.equalsIgnoreCase("Staten Island")) { // parameters of new tree's borough name
            this.boroname = boroname;
        } else {
            throw new IllegalArgumentException(); // throw IllegalArgumentException if parameters are not met
        }

    }

    /**
     * @return the called tree's x_sp value
     */
    public double getX_sp() {
        return x_sp;
    }

    /**
     * @param x_sp set the called tree's x_sp value
     */
    public void setX_sp(double x_sp) {
        this.x_sp = x_sp;
    }

    /**
     * @return the called tree's y_sp value
     */
    public double getY_sp() {
        return y_sp;
    }

    /**
     * @param y_sp set the called tree's y_sp value
     */
    public void setY_sp(double y_sp) {
        this.y_sp = y_sp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * implements Comparable<Tree>'s compareTo method to compare two Tree objects
     */
    public int compareTo(Tree o) {

        // confirm that this object and o object have valid data for comparison
        if (this.getSpc_common() != null && o.getSpc_common() != null) {
            if ((this.getSpc_common().compareToIgnoreCase(o.getSpc_common()) != 0)) { // if the common name for this object does not equal the common name for o, then...
                return this.getSpc_common().compareToIgnoreCase(o.getSpc_common()); // return a positive or negative integer depending on the lexicographically of the two objects.
            }
            if ((this.getSpc_common().compareToIgnoreCase(o.getSpc_common()) == 0)) { // check to see if the common name for this object is the same as o.
                if (this.getTreeID() == o.getTreeID()) { // if so, check if the treeID's of this and o are ==, if they are return 0.
                    return 0;
                }

                if (this.getTreeID() != o.getTreeID()) { // else, return the treeID of this minus the treeID of o.
                    return this.getTreeID() - o.getTreeID();
                }
            }

        }

        return -1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     * returns a string consisting of Common name, treeID, and status
     */
    public String toString() {
        return this.getSpc_common() + ", " + this.getTreeID() + ", " + this.getStatus();
    }

}
