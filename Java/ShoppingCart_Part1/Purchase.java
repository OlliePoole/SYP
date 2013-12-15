/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcart;

import java.text.DecimalFormat;

/**
 * Purchase Class
 * The general purchase information
 * @author Ollie Poole
 */
public class Purchase {
    private String name;
    private double price;
    
    /**
     * Constructor
     * @param name      The name of the item
     * @param price     The price of the item
     */
    public Purchase(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    /**
     * returns the name of the item
     * @return      The name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * returns the integer price
     * @return      the price as an integer
     */
    public int getIntPrice() {

        return (int)(price*100);
        
    }
    
    /**
     * returns the decimal price
     * @return      the price as a decimal
     */
    public String getDecimalPrice() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(price);
    }
    
    /**
     * returns the object in string form
     * @return      The object as a string
     */
    @Override
    public String toString() {
        return ("Name: " + getName() + " Price: " + getDecimalPrice());
    }
}
