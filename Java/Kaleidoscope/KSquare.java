/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.student;

import framework.KShape;
import java.awt.Color;

/**
 * Square class
 * Used to store characteristics of a square object
 * @author Ollie Poole
 */
public class KSquare extends KShape {

    private int width;
    
    /**
     * Constructor
     * @param x         The x coordinate
     * @param y         The y coordinate
     * @param width     The width of the shape
     * @param color     The color of the shape
     */
    public KSquare(int x, int y, int width, Color color) {
        super(x, y, color);
        this.width = width;
    }
    
    /**
     * returns the width of the shape
     * @return      The width
     **/
    public int getWidth() {
        return width;
    }
    
    /**
     * Sets the location information for placing the shape on the screen
     * @return      The data int array with the information in
     **/
    @Override
    public int[] getData() {
        int[] data = new int[11];

        data[0] = getColor().getRed();
        data[1] = getColor().getGreen();
        data[2] = getColor().getBlue();
        data[3] = getX() - getWidth() / 2;
        data[4] = getY() - getWidth() / 2;
        data[5] = getX() + getWidth() / 2;
        data[6] = getY() - getWidth() / 2;
        data[7] = getX() + getWidth() / 2;
        data[8] = getY() + getWidth() / 2;
        data[9] = getX() - getWidth() / 2;
        data[10] = getY() + getWidth() / 2;

        return data;
    }
}
