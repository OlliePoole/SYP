package framework;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author p0073862
 */
public class KPanel extends JPanel {

    private final int radius;
    private final int width;
    private static final Color OUTSIDE_COLOR = Color.GRAY;
    private static final Color INSIDE_COLOR = Color.WHITE;
    private KModel model;
    private java.awt.Shape clipShape;

    public KPanel(KModel model, int radius) {
        this.radius = radius;
        this.width = 2 * radius;
        this.model = model;
        setPreferredSize(new Dimension(width, width));


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int nbrSegments = model.getNbrSegments();
        
        clipShape = new Arc2D.Double(0, 0, width, width, 90, 360.0 / nbrSegments,
                Arc2D.PIE);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(OUTSIDE_COLOR);
        g2.fillRect(0, 0, width, width);
        g2.setColor(INSIDE_COLOR);
        g2.fillOval(0, 0, width, width);
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(3));
        g2.drawOval(0, 0, width, width);

        Ellipse2D ellipse = new Ellipse2D.Float(0, 0, width, width);
        double angle = 2 * Math.PI / nbrSegments;

        boolean toggleMirror = true;
        AffineTransform vertMirror = GUIUtils.getHorizontalFlip(radius);
        AffineTransform angleMirror = GUIUtils.getReflection(radius, radius,
                -angle);

        for (int i = 1; i <= nbrSegments; i++) {
            g2.setClip(clipShape);
            drawSegment(g2);
            AffineTransform transform = g2.getTransform();
            transform.concatenate(toggleMirror ? vertMirror : angleMirror);
            toggleMirror = !toggleMirror;
            g2.setTransform(transform);
        }
    }

    private void drawSegment(Graphics2D g2) {
        for (int[] shape : model.getShapes()) {
            int nbrVertices = (shape.length - 3) / 2;
            int[] x = new int[nbrVertices];
            int[] y = new int[nbrVertices];
            for (int i = 0; i < nbrVertices; i++) {
                x[i] = shape[3 + 2 * i];
                y[i] = shape[4 + 2 * i];
            }
            java.awt.Polygon s = new java.awt.Polygon(x, y, nbrVertices);
            g2.setColor(new Color(shape[0], shape[1], shape[2], 200));
            g2.fillPolygon(s);
        }
    }
}
