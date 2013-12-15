/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcart;

import java.util.ArrayList;

/**
 * ArrayListStructure Class
 * Class to house the functions on the data structure
 * @author olliepoole
 */
public class ArrayListStructure {

    final static int WEIGHT = 1;
    final static int QUANTITY = 0;
    private final ArrayList<DataItem> arrayList;

    /**
     * Constructor
     */
    public ArrayListStructure() {
        arrayList = new ArrayList<>();

    }

    /**
     * Checks if the item is present 
     * @param itemNumber        The item number to search for
     * @return          True/false dependent on result
     */
    public boolean IsKnownItemNumber(int itemNumber) {
        if (arrayList.isEmpty()) {
            return false;
        }
        for (int x = 0;x<arrayList.size();x++) {
            if (arrayList.get(x).getID()==itemNumber) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new item to the list
     * @param ID
     * @param description
     * @param unitPrice
     * @param sort 
     */
    public void recordItem(int ID, String description, double unitPrice, int sort) {
        if (!IsKnownItemNumber(ID)) {
            if (unitPrice > 0) {
                if (sort == WEIGHT || sort == QUANTITY) {
                    arrayList.add(new DataItem(ID, description, unitPrice, sort));
                }
            }
        }
    }

    /**
     * returns the description
     * @param itemNumber
     * @return          The description
     */
    public String getDescription(int itemNumber) {
        for (int x = 0;x<arrayList.size();x++) {
            if (arrayList.get(x).getID()==itemNumber) {
                return arrayList.get(x).getDescription();
            }
        }
        return "";
    }

    /**
     * returns the price
     * @param itemNumber
     * @return          The price
     */
    public double getUnitPrice(int itemNumber) {
        for (int x = 0;x<arrayList.size();x++) {
            if (arrayList.get(x).getID()==itemNumber) {
                return arrayList.get(x).getPrice();
            }
        }

        return 0;
    }

    /**
     * returns the sort
     * @param itemNumber
     * @return          The sort
     */
    public int getSort(int itemNumber) {
        for (int x = 0;x<arrayList.size();x++) {
            if (arrayList.get(x).getID()==itemNumber) {
                return arrayList.get(x).getSort();
            }
        }

        return -1;
    }
    
    /**
     * @return      The size of the array
     */
    public int size() {
        return arrayList.size();
    }
    
    /**
     * @param index
     * @return          Returns the item at the index
     */
    public DataItem get(int index) {
        return arrayList.get(index);
    }

}
