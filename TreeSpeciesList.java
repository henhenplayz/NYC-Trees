package project2;

import java.util.ArrayList;

public class TreeSpeciesList extends ArrayList<TreeSpecies> {

    /**
     * Creates a new empty arraylist object of TreeSpecies
     * 
     */
    public TreeSpeciesList() {
        new ArrayList<TreeSpecies>();
    }

    /** creates a TreeSpeciesList comparing keyword to every common name in the list to create another list
     * @param keyword a string to compare with
     * @return another TreeSpeciesList that only has keyword in its element's common name
     */
    public TreeSpeciesList getByCommonName(String keyword) {
        TreeSpeciesList commonName = new TreeSpeciesList(); //create new TreeSpeciesList

        if (keyword == null) {
            throw new IllegalArgumentException(); // if keyword == null, throw IllegalArgumentException().
        }

        for (int i = 0; i < this.size(); i++) { // iterate through this list
            if (this.get(i).getName().toLowerCase().contains(keyword.toLowerCase())) { // if the latin name of a tree of this list contains keyword
                commonName.add(this.get(i)); // add this tree to the return list
            }
        }
        if (commonName.size() == 0) { // if there were no matches, return null
            return null;
        }
        return commonName; // return the list with all of the matches

    }

    /** creates a TreeSpeciesList comparing keyword to every latin name in the list to create another list
     * @param keyword a string to compare with
     * @return another TreeSpeciesList that only has keyword in its element's latin name
     */
    public TreeSpeciesList getByLatinName(String keyword) {
        TreeSpeciesList latinName = new TreeSpeciesList(); //create new TreeSpeciesList

        if (keyword == null) {
            throw new IllegalArgumentException(); // if keyword == null, throw IllegalArgumentException().
        }

        for (int i = 0; i < this.size(); i++) { // iterate through this list
            if (this.get(i).getSpecies().toLowerCase().contains(keyword.toLowerCase())) {  // if the latin name of a tree of this list contains keyword
                latinName.add(this.get(i)); // add this tree to the return list
            }
        }
        if (latinName.size() == 0) { // if there were no matches, return null
            return null;   
        }
        return latinName; // return the list with all of the matches

    }
}
