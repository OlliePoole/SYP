/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.student;

import framework.KShape;
import java.awt.Color;

/**
 * Triangle Class
 * Used to store characteristics about a triangle
 * @author Ollie Poole
 */
public class KTriangle extends KShape {

    private int d;
   
     /**
      * Constructor
      * @param x        The x coordinate
      * @param y        The y coordinate
      * @param d        The distance between the edge and the center
      * @param color    The color of the shape
      */
    public KTriangle(int x, int y, int d, Color color) {
        super(x,y,color);
        this.d = d;
        
    }

     /**
     * returns the distance between the edge and the center
     * @return      The distance
     **/
    public int getD() {
        return d;
    }

    /**
     * Sets the location information for placing the shape on the screen
     * @return      The data int array with the information in
     **/
    @Override
    public int[] getData() {
        int[] data = new int[9];

        double cosTheta = Math.cos(Math.PI / 6);
        double sinTheta = Math.sin(Math.PI / 6);

        data[0] = getColor().getRed();
        data[1] = getColor().getGreen();
        data[2] = getColor().getBlue();
        data[3] = getX();
        data[4] = getY() - getD();
        data[5] = (int) (getX() + getD() * cosTheta);
        data[6] = (int) (getY() + getD() * sinTheta);
        data[7] = (int) (getX() - getD() * cosTheta);
        data[8] = (int) (getY() + getD() * sinTheta);

        return data;
    }
}
