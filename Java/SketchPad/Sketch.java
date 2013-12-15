/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sketchpad;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * GUI Class Sketch
 * @author olliepoole
 */
public class Sketch extends JFrame implements MouseListener, ActionListener {

    private static final Dimension SKETCH_SIZE = new Dimension(360, 360);
    ArrayList<Point> points = new ArrayList<>();
    SketchPad sketchPanel = new SketchPad(points);
    JButton deleteButton = new JButton("Delete");
    JTextField xCoodTF = new JTextField();
    JTextField yCoodTF = new JTextField();
    JPanel pane1 = new JPanel();

    /**
     * Constructor
     * Builds the GUI components
     */
    @SuppressWarnings("empty-statement")
    public Sketch() {
        super("Sketch");

        Container contentPane = getContentPane();
        pane1.setLayout(new BoxLayout(pane1, BoxLayout.X_AXIS));

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        xCoodTF.setEditable(true);
        yCoodTF.setEditable(true);

        pane1.add(xCoodTF);
        pane1.add(yCoodTF);
        pane1.add(deleteButton);

        sketchPanel.addMouseListener(this);
        sketchPanel.setPreferredSize(SKETCH_SIZE);
        contentPane.add(sketchPanel);
        contentPane.add(pane1);

        deleteButton.addActionListener(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);
        
        Cursor cursor;
        cursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
        sketchPanel.setCursor(cursor);


        sketchPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                xCoodTF.setText(Integer.toString(e.getX()));
                yCoodTF.setText(Integer.toString(e.getY()));

            }
        });
        {
        };

    }

    /**
     * Adds a point on mouse click
     * @param e         The location of the click
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        points.add(e.getPoint());
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public static void main(String[] args) {
        new Sketch();
    }
    
    /**
     * Actions performed when remove button clicked
     * @param ae        The source of the click
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        points.remove(points.size() - 1);
        repaint();
    }
}
