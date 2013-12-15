/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcart;

/**
 * Number Class
 * Used to store purchases of type number
 * @author Ollie Poole
 */
public class Number extends Purchase {
    private int number;
    
    /**
     * Constructor
     * @param name          The name of the item
     * @param price         The price of the item
     * @param number        The number of items
     */
    public Number(String name, double price, int number) {
        super(name, price);
        this.number = number;
    }
    
    /**
     * Returns the quantity
     * @return          The quantity
     */
    @Override
    public int getQuantity() {
        return number;
    }

    
}
