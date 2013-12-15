package framework;


import java.awt.Color;
import java.util.Random;

/**
 * Class KShape
 * This acts as a superclass to the other shape classes
 * @author Ollie Poole
 **/
public class KShape {

    private Color color;
    private int x;
    private int y;
    private Random rand = new Random();

    /**
     * Constructor
     * @param x         The x coordinate
     * @param y         The y coordinate
     * @param color     The color of the shape
     */
    public KShape(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * return the data as null
     * @return      null
     **/
    public int[] getData() {
        return null;
    }
    
    /**
     * returns the color
     * @return      The color of the shape
     **/
    public Color getColor() {
        return color;
    }
    
    /**
     * returns the x coordinate
     * @return      The x coordinate
     **/
    public int getX() {
        return x;
    }
    
    /**
     * returns the y coordinate
     * @return      The y coordinate
     **/
    public int getY() {
        return y;
    }
    
    /**
     * Used to move the shapes around within the kaleidoscope
     * @param xmax          The max x value
     * @param ymax          The max y value
     * @param nudgeValue    The value to nudge by
     */
    public void nudge(int xmax, int ymax, int nudgeValue) {
        x = x - nudgeValue + rand.nextInt(2 * nudgeValue + 1);
        if (x < 0) {
            x = 0;
        }
        if (x > xmax) {
            x = xmax;
        }
        y = y - nudgeValue + rand.nextInt(2 * nudgeValue + 1);
        if (y < 0) {
            y = 0;
        }
        if (y > ymax) {
            y = ymax;
        }
    }
}
