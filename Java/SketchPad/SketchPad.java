/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sketchpad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Sketch pad class
 * @author Ollie Poole
 */
public class SketchPad extends JPanel {

    private static final int DIAMETER = 16;
    private List<Point> points;

    /**
     * Constructor
     * @param points        The array list of coordinates 
     */
    public SketchPad(ArrayList<Point> points) {
        this.points = points;
    }
    
    /**
     * Paints the component
     * @param g         The graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);

        int size = points.size();
        int[] x = new int[size];
        int[] y = new int[size];
        int i = 0;
        for (Point p : points) {
            x[i] = p.x;
            y[i] = p.y;
            i++;
        }
        g.drawPolyline(x, y, i);
        if (size > 0) {
            g.fillOval(x[size - 1] - DIAMETER / 2, y[size - 1] - DIAMETER / 2,
                    DIAMETER, DIAMETER);
        }
    }

}
