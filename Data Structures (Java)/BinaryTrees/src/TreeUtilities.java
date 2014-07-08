// You don't need to read or understand any of the code in this file,
// it's just provided for ease of displaying/creating trees.
// You just need to be able to call the showTree and createRandomTree methods.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.util.*;

/** This class provides various tree-related facilities.
  *
  * @author Sharon Curtis
  */
public class TreeUtilities extends JDialog implements ActionListener {


/////////////////// Code for generating trees ////////////////////////


    private static Random rng = new Random(); // random number generator


/** Creates a random tree of between 5 and 9 small integers.
  *
  * @return     the random tree of integers
  */
    public static BinaryTree createRandomTree() {

        int numTreeVals = 5 + rng.nextInt(4); // how many integers in the tree?
        int[] a = getRandomTreeNumbers(numTreeVals); // get random integers for tree insertion

        BinaryTree t = new BinaryTree(a[0]);
        for (int i = 1; i<numTreeVals; i++) {
            insert(a[i],t); // inserting the values into tree t
        }
        return t;
    }


    private static void insert(int val, BinaryTree tree) {
    // Inserts a given value into the tree
    // at some randomly-chosen leaf position.

        BinaryTree t;
        boolean goLeft = (rng.nextInt(100)<50);

        if ((tree.left==null) && goLeft) {
            t = new BinaryTree(val);
            tree.left = t;
        }
        else if (goLeft) {
            insert(val,tree.left);
        }
        else if ((tree.right==null) && !goLeft) {
            t = new BinaryTree(val);
            tree.right = t;
        }
        else
           insert(val,tree.right);
    }



    private static int[] getRandomTreeNumbers(int numTreeVals) {
    // Creates an array of integers suitable for insertion into a tree.

        int[] a = new int[numTreeVals];
        int maxTreeVal = 98;  // suitable range of small integer values
        int minTreeVal = -9;  // suitable for putting in these binary trees
        int newNum,i;
        int nextEntry = 0;
        while (nextEntry != a.length) {
            newNum = rng.nextInt(maxTreeVal - minTreeVal) + minTreeVal;
            a[nextEntry] = newNum;
            i = 0;
            while (a[i]!=newNum)
                i++;
            if (i==nextEntry)
                nextEntry++;
        }
        return a;
    }




/////////////////// Code for creating images of trees ////////////////////////


    private static int nodeSpacing = 40;   // for display purposes
    private static int borderGap = 40;     // gap between parts of the tree (for display)
    private static Color nodeColour = new Color(181,234,141);
    private static int nodeRadius = 15;   // radius of circle used to draw node
    private static int letterWidthOffset = 5;   //
    private static int letterHeightOffset = 5;   //


    private static BufferedImage getTreeImage(BinaryTree t) {
    // Returns a BufferedImage displaying this tree.

        // First we need to figure out how big to make the image
        labelWithDepths(t);
        Dimension d = assignCoordinates(t);

        // then create the image
        BufferedImage bimage = new BufferedImage(d.width,d.height,BufferedImage.TYPE_INT_RGB);

        // set up the graphics context for the image
        Graphics2D g2d = bimage.createGraphics();
        g2d.setPaint(Color.white);
        g2d.fill(new Rectangle2D.Double(0.0,0.0,(double)(d.width),(double)(d.height)));
        g2d.setStroke (new BasicStroke (3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(new Font("Verdana", Font.BOLD, 14));

        // finally we draw the tree
        drawTree(g2d,t);
        return bimage;
    }

    private static void labelWithDepths(BinaryTree t) {
    // Labels each node with its depth/height from this node.
       if ((t.left==null) && (t.right==null))   // leaf node
          t.height = 1;
       else if ((t.left==null) && (t.right!=null)) { // right child only
          labelWithDepths(t.right);
          t.height = t.right.height + 1;
       }
       else if ((t.left!=null) && (t.right==null)) { // left child only
          labelWithDepths(t.left);
          t.height = t.left.height + 1;
       }
       else {
          labelWithDepths(t.right);
          labelWithDepths(t.left);
          if  (t.right.height > t.left.height)
             t.height = t.right.height + 1;
          else
             t.height = t.left.height + 1;
       }
    }


    private static Dimension assignCoordinates(BinaryTree t) {
    // Calculates suitable (x,y) display positions for this node (and recursively
    // down the tree too). Afterwards a dimension of the recommended
    // size of display image for this is returned.
        assignBoxes(nodeSpacing,t);
        int rootX = borderGap + t.nodeFromLeft;
        assignXCoordinates(rootX,t);
        assignYCoordinates(borderGap,nodeSpacing,t);
        Dimension d = new Dimension (t.boxWidth+2*borderGap,
                                    (t.height-1)*nodeSpacing + 2*borderGap);
        return d;
    }


    private static void assignBoxes(int gap, BinaryTree t) {
    // Calculates some values for display purposes:
    // boxWidth is the size of the box required to display this tree.
    // nodeFromLeft measures how far the root is from the left of the box.
    //
    //              <-      ->
    //              ........(5)......
    //              .      /   \    .
    //              .    (1)   (17) .
    //              .    /          .
    //              . (-9)          .
    //              .................
    //              <-   boxWidth  ->
    //
    // xdfp  is the horizontal displacement of a node relative to its parent,
    // and this method may set the xdfp of a child node.
       int d;
       if ((t.left==null) && (t.right==null)) {  // leaf node
          t.boxWidth = gap;
          t.nodeFromLeft = gap / 2;
       }
       else if ((t.left==null) && (t.right!=null)) {  // right child only
          assignBoxes(gap,t.right);
          t.right.xdfp = gap;
          if (t.right.nodeFromLeft > (3*gap / 2)) {
             t.boxWidth = t.right.boxWidth;
             t.nodeFromLeft = t.right.nodeFromLeft - gap;
          }
          else {
             t.boxWidth = (3*gap / 2) + t.right.boxWidth - t.right.nodeFromLeft;
             t.nodeFromLeft = (gap / 2);
         }
       }
       else if ((t.left!=null) && (t.right==null)) { // left child only
          assignBoxes(gap,t.left);
          t.left.xdfp = - gap;
          if (t.left.boxWidth - t.left.nodeFromLeft > (3*gap /2)) {
              t.boxWidth = t.left.boxWidth;
              t.nodeFromLeft = t.left.nodeFromLeft + gap;
          }
          else {
             t.boxWidth = (3*gap / 2) + t.left.nodeFromLeft;
             t.nodeFromLeft = t.left.nodeFromLeft + gap;
          }
       }
       else {
          assignBoxes(gap,t.left);
          assignBoxes(gap,t.right);
          d = (t.left.boxWidth + t.right.nodeFromLeft - t.left.nodeFromLeft);
          if (d < 2*gap) d = 2*gap;
          t.boxWidth =  t.left.nodeFromLeft + d
                              + t.right.boxWidth
                              - t.right.nodeFromLeft;
          t.nodeFromLeft = t.left.nodeFromLeft + (d / 2);
          t.left.xdfp = - (d / 2);
          t.right.xdfp = d / 2;
       }
       // System.out.println("At node :"+data+", boxWidth="+
       //                    boxWidth+", nodeFromLeft="+nodeFromLeft);
    }

    private static void assignXCoordinates(int thisX, BinaryTree t) {
    // calculates some x values for display purposes,
        t.x = thisX;
        // System.out.println("At node :"+data+", x="+x);
        if (t.left!=null)
             assignXCoordinates(thisX + t.left.xdfp,t.left);
        if (t.right!=null)
             assignXCoordinates(thisX + t.right.xdfp,t.right);

    }

    private static void assignYCoordinates(int rootYcoord, int gap, BinaryTree t) {
    // Calculates some y values for display purposes.
    // The gap parameter gives the gap between levels of the tree.
       t.y = rootYcoord;
       // System.out.println("At node :"+data+", y="+y);
       if (t.left!=null)
          assignYCoordinates(rootYcoord+gap,gap,t.left);
       if (t.right!=null)
          assignYCoordinates(rootYcoord+gap,gap,t.right);
    }




    private static void drawTree(Graphics2D g2d, BinaryTree t) {
    // Draws this tree on the given graphics context, using the given

        assignCoordinates(t);

        // draw lines first so that the nodes appear in front
        drawTreeLines(g2d,t);
        drawTreeNodes(g2d,t);
    }


    private static void drawTreeLines(Graphics2D g2d, BinaryTree t) {
    // Draws all the lines for this tree.
    // Should be done before drawing the nodes.

        g2d.setPaint(Color.black);
        if (t.left!=null) {
            Line2D aLine = new Line2D.Double(t.x,t.y,t.left.x,t.left.y);
            g2d.draw(aLine);
            drawTreeLines(g2d,t.left);
        }
        if (t.right!=null) {
            Line2D aLine = new Line2D.Double(t.x,t.y,t.right.x,t.right.y);
            g2d.draw(aLine);
            drawTreeLines(g2d,t.right);
        }
    }

    private static void drawTreeNodes(Graphics2D g2d, BinaryTree t) {
    // Draws all the nodes for this tree.
    // It assumes the nodeString is no more than two characters long.

         // System.out.println("drawTreeNodes: drawing node "+data);
       Ellipse2D.Float el = new Ellipse2D.Float(t.x-nodeRadius,t.y-nodeRadius,
                                              2.0f*nodeRadius,2.0f*nodeRadius);
       g2d.setPaint(nodeColour);
       g2d.fill(el);
       g2d.setPaint(Color.black);
       g2d.draw(el);
       g2d.setFont(new Font("Verdana", Font.BOLD, 14));
       if ((t.data>=0) && (t.data<10))
          g2d.drawString(t.data+"", t.x-letterWidthOffset,t.y+letterHeightOffset);
       else
          g2d.drawString(t.data+"", t.x-2*letterWidthOffset, t.y+letterHeightOffset);
       if (t.left!=null)
           drawTreeNodes(g2d,t.left);
       if (t.right!=null)
           drawTreeNodes(g2d,t.right);
    }




/////////////////// Code for displaying trees ////////////////////////


    private static TreeUtilities imageDialog;

    private static int xOffset = 0;  // (xOffSet,yOffSet) is the current
    private static int yOffset = 0;  // location of the image window

    private static int currentRowHeight = 0; // max image height on this row
    private static BufferedImage previousImage = null;
    private static int maxX = 600;


/** Displays an image of a tree.
  *
  * @param  im   the image of the tree to be displayed
  * @return      returns zero if this method is successfully completed
  */
    public static int showTree(BinaryTree t) {

        BufferedImage im = getTreeImage(t);

        try {
            UIManager.setLookAndFeel(
            "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Error in trying to set Windows \"look and feel\"."
                            +" Default will be used instead.");
        }
        if (previousImage!=null) { // second or later image
            JFrame prev = new JFrame("Tree Display");
            prev.getContentPane().add(new JLabel(new ImageIcon(previousImage)),
                                      BorderLayout.CENTER);
            prev.setLocation(xOffset,yOffset);
            prev.pack();
            prev.setVisible(true);

            xOffset = xOffset + previousImage.getWidth()+10;
            currentRowHeight = Math.max(currentRowHeight,
                                        previousImage.getHeight());
            if (xOffset>maxX) {
                xOffset = 0;
                yOffset = yOffset + currentRowHeight;
                currentRowHeight = 0;
            }

        }

        Frame frame = JOptionPane.getFrameForComponent(null);
        imageDialog = new TreeUtilities(frame,xOffset,yOffset,im);
        imageDialog.setVisible(true);

        // afterwards
        previousImage = im;
        return 0;
    }


    private TreeUtilities(Frame frame, int x, int y, BufferedImage im) {

        super(frame, "Tree Display", Dialog.ModalityType.APPLICATION_MODAL );

        // display area for the image
        JLabel imageArea = new JLabel(new ImageIcon(im));
        getContentPane().add(imageArea, BorderLayout.CENTER);

        // container panel for buttons
        JPanel buttonPane = new JPanel(new FlowLayout());
        JButton cButton = new JButton("Continue");
        cButton.setActionCommand("cont");
        cButton.addActionListener(this);

        JButton qButton = new JButton("Exit Program");
        qButton.setActionCommand("quit");
        qButton.addActionListener(this);

        buttonPane.add(cButton);
        buttonPane.add(qButton);
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        setLocation(x,y);
        //Initialize values.
        pack();

    }

    public void actionPerformed(ActionEvent e) {
        if ("cont".equals(e.getActionCommand())) {
           setVisible(false);
        }
        else if ("quit".equals(e.getActionCommand())) {
           System.exit(0);
        }
    }









}
