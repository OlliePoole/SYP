/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shoppingcart;

/**
 * Data Item Class
 * Class to store information on potential purchases
 * @author olliepoole
 */
public class DataItem {
    
    private int m_ID;
    private String m_description;
    private double m_price;
    private int m_sort;
    
    /**
     * Constructor
     * @param IDNum         The ID number of the item
     * @param description   The Description of the item
     * @param price         The price of the item  
     * @param sort          The sort of the item (i.e. number or weight)
     */
    public DataItem(int IDNum, String description, double price, int sort) {
        m_ID = IDNum;
        m_description = description;
        m_price = price;
        m_sort = sort;
    }
    
    
    /**
     * @return the m_ID
     */
    public int getID() {
        return m_ID;
    }

    /**
     * @param ID the m_ID to set
     */
    public void setID(int ID) {
        this.m_ID = ID;
    }

    /**
     * @return the m_description
     */
    public String getDescription() {
        return m_description;
    }

    /**
     * @param description the m_description to set
     */
    public void setDescription(String description) {
        this.m_description = description;
    }

    /**
     * @return the m_price
     */
    public double getPrice() {
        return m_price;
    }

    /**
     * @param price the m_price to set
     */
    public void setPrice(double price) {
        this.m_price = price;
    }
    
        /**
     * @return the m_sort
     */
    public int getSort() {
        return m_sort;
    }

    /**
     * @param sort the m_sort to set
     */
    public void setSort(int sort) {
        this.m_sort = sort;
    }
    
}
