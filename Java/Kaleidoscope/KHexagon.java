/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.student;

import framework.KShape;
import java.awt.Color;

/**
 * Hexagon Class
 * This defines the characteristics of the hexagon
 * @author Ollie Poole
 **/
public class KHexagon extends KShape {

    private int s;
    
    /**
     * Constructor
     * @param x       The x coordinate
     * @param y       The y coordinate
     * @param s       The width
     * @param color     The colour of the shape
     **/
    public KHexagon(int x, int y, int s, Color color) {
        super(x, y, color);
        this.s = s;
    }
    
    /**
     * returns the width of the shape
     * @return      The width of the shape
     **/
    public int getWidth() {
        return s;
    }
    
    /**
     * returns the updated data unique to a hexagon
     * @return      The data as an int array
     **/
    @Override
    public int[] getData() {
        int[] data = new int[15];
        double angle = 2 * Math.PI / 6;
        data[0] = getColor().getRed();
        data[1] = getColor().getGreen();
        data[2] = getColor().getBlue();
        for (int i = 0; i < 6; i++) {
            data[3 + 2 * i] = (int) (getX() + s * Math.cos(angle * i));
            data[4 + 2 * i] = (int) (getY() + s * Math.sin(angle * i));
        }
        return data;

    }
}
