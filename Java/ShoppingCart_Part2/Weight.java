/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcart;

/**
 * Weight Class
 * Stores information about a purchase of type weight
 * @author Ollie Poole
 */
public class Weight extends Purchase {

    private double weight;

    /**
     * Constructor
     * @param name      The name of the item
     * @param price     The price of the item
     * @param weight    The weight of the item
     */
    public Weight(String name, double price, double weight) {
        super(name, price);
        this.weight = weight;
    }

    /**
     * returns the weight
     * @return 
     */
    @Override
    public double getWeight() {
        return weight;
    }

}
