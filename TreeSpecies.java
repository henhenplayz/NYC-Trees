package project2;

public class TreeSpecies {
    private String name, species;

    /** a constructor for a new TreeSpecies object
     * @param commonName a string that describes a TreeSpecies's common name
     * @param latinName a strign that describes a TreeSpecies's latin name
     */
    public TreeSpecies(String commonName, String latinName) {
        this.name = commonName;
        this.species = latinName;
        try {
            if (commonName == null || latinName == null) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            System.err.println("Common name and or latin name is null.");
            System.exit(1);
        }
    }

    /**
     * @return the called TreeSpecies's common name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name set the called TreeSpecies's common name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return  the called TreeSpecies's latin name
     */
    public String getSpecies() {
        return species;
    }

    /**
     * @param species set the called TreeSpecies's latin name
     */
    public void setSpecies(String species) {
        this.species = species;
    }

    // Overriding equals() to compare two TreeSpecies objects
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        // tests to see if object o is a tree, if it is not then return false;
        if (!(o instanceof TreeSpecies)) {
            return false;
        }

        // let o be a tree to access its data
        TreeSpecies compare = (TreeSpecies) o;

        if (this.getName() != null && this.getSpecies() != null) { // make sure the common names and latin names are valid 
            if (compare.getName() != null && compare.getSpecies() != null) {
                if ((this.getName().equalsIgnoreCase(compare.getName()))
                        && (this.getSpecies().equalsIgnoreCase(compare.getSpecies()))) {
                        return true;
                }
            }
        }
        return false;
    }
    

}
