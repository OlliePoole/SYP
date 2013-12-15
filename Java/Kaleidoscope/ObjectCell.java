package student;

import framework.KShape;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import src.student.KHexagon;
import src.student.KSquare;
import src.student.KTriangle;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 * Object Cell Class
 * Generates the shapes to be outputted to the screen
 * @author Ollie Poole
 */
public class ObjectCell {

    private int width;
    private int height;
    private ArrayList<KShape> shapes;
    private int nbrSquares;
    private int nbrTriangles;
    private int nbrHexagons;
    private Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW};
    private static final int nudgeValue = 5;
    
    /**
     * Constructor
     * 
     * @param width
     * @param height 
     */
    public ObjectCell(int width, int height) {
        this.width = width;
        this.height = height;
        shapes = new ArrayList<>();
        nbrSquares = 300;
        nbrTriangles = 300;
        nbrHexagons = 300;

        reset();

    }

    /**
     * returns all the shape data for the shapes to be outputted to the screen
     * @return      An arrayList of integers
     **/
    public ArrayList<int[]> getShapeData() {
        ArrayList<int[]> result = new ArrayList<int[]>();

        for (KShape s : shapes) {
            result.add(s.getData());
        }

        return result;
    }
    
    /**
     * Rebuilds the shapes on the screen
     **/
    public void reset() {
        shapes = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < nbrSquares; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            Color c = colors[rand.nextInt(colors.length)];
            int w = rand.nextInt(30);

            shapes.add(new KSquare(x, y, w, c));
        }

        for (int i = 0; i < nbrTriangles; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            Color c = colors[rand.nextInt(colors.length)];
            int d = rand.nextInt(20);

            shapes.add(new KTriangle(x, y, d, c));
        }

        for (int i = 0; i < nbrHexagons; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            Color c = colors[rand.nextInt(colors.length)];
            int s = rand.nextInt(30);

            shapes.add(new KHexagon(x, y, s, c));
        }
    }
    
    /**
     * Adjusts the locations of the shpaes of the screen
     **/
    public void nudge() {
        for (KShape s : shapes) {
            s.nudge(width, width, nudgeValue);
        }

    }

    /**
     * Returns a data list of the red and black squares
     * @return      The int arraylist of the data
     **/
    private ArrayList<int[]> getRedBlackSquares() {

        ArrayList<int[]> result = new ArrayList<int[]>();
        int squareWidth = Math.min(width, height) / 50;
        int sep = squareWidth * 3 / 2;
        boolean black = true;
        for (int i = 0;
                i < width;
                i += sep) {
            for (int j = 0; j < height; j += sep) {
                int[] data = new int[11];
                data[0] = black ? 0 : 255;
                data[1] = 0;
                data[2] = 0;
                data[3] = i;
                data[4] = j;
                data[5] = i + squareWidth;
                data[6] = j;
                data[7] = i + squareWidth;
                data[8] = j + squareWidth;
                data[9] = i;
                data[10] = j + squareWidth;
                result.add(data);
                black = !black;
            }
        }
        return result;
    }
}
