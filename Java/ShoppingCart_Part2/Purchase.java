/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcart;

import java.text.DecimalFormat;

/**
 * Class for storing purchases
 * @author Ollie Poole
 */
public abstract class Purchase {
    private String name;
    private double price;
    
    /**
     * Constructor
     * @param name          The name of the item
     * @param price         The price of the item
     */
    public Purchase(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    /**
     * Returns the name
     * @return      The name of the item
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns the price as an integer
     * @return          The price as an integer
     */
    public int getIntPrice() {

        return (int)(price*100);
        
    }
    
    /**
     * Returns the price as a decimal
     * @return          The price as a decimal
     */
    public String getDecimalPrice() {
        
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(price);
    }
    
}
