/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcart;

/**
 * Number Class
 * Stores information about a purchase made of type number
 * @author 12022846
 */
public class Number extends Purchase {
    private int number;
    
    /**
     * Constructor
     * 
     * @param name      The name of the item
     * @param price     The price of the item
     * @param number    The number of items
     */
    public Number(String name, double price, int number) {
        super(name, price);
        this.number = number;
    }
    
    /**
     * returns the number of items
     * @return      The number of items
     */
    public int getNumber() {
        return number;
    }
    
    /**
     * Returns the object in string form
     * @return      The object as a string
     */
    @Override
    public String toString() {
        return super.toString() + " Quantity: " + getNumber() + '\n';
    }
}
