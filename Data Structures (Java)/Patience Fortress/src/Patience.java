// This is the main program class.
// Do not change the code in this file, unless you need to alter
// the window dimensions to suit your monitor resolution.
//
// You can safely ignore the code in this file.


import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Patience extends JFrame {

    /* The variables windowWidth and windowHeight specify the default *
     * width and height of the patience game window. These may be     *
     * altered to suit your monitor size/resolution if you prefer.    */
    public static final int windowWidth = 800;
    public static final int windowHeight = 700;

    /* The variable cardWidth specifies the width of a displayed card in *
     * pixels. The width may be altered if needed, to suit your screen   *
     * resolution so that you can see the suit of a card easily.         */
    public static final int cardWidth = 60;


    public static void main(String[] args) {
	   Patience window = new Patience();  // creates the window in which
	                                      // to display the game
    }


    private Controller controlArea; // this object controls the response
                                    // to mouse clicks and organises the
                                    // displaying of graphics

    private Rules dealingAndRules;  // this object is in charge of dealing
                                    // cards and enforcing the rules

/** This constructor method for the Patience game sets up the window
  * for the game and also the display and game controllers.
  */
    public Patience() {

        super(); // calls the JFrame constructor to set up the window

        setTitle("Patience");  // displayed on the window's title bar
        setSize(windowWidth,windowHeight);
	    setIconImage(Controller.getLogo()); // a little spades picture
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setting up the game controller
        dealingAndRules = new Rules();

        // setting up the display controller
        controlArea = new Controller(cardWidth,dealingAndRules);
        JScrollPane scrollArea = new JScrollPane(controlArea);
  	    getContentPane().add(scrollArea); // makes the playing area visible

        // now to make the whole window visible on the screen
        setVisible(true);
    }

}
