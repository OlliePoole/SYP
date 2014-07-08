// You don't need to read or understand any of the code in this file,
// it's just provided for ease of displaying and reading in graphs.

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.font.*;
import java.util.*;
import java.io.*;

/** This class provides various graph-related facilities, namely
  * displaying graphs and reading in graph data from files.
  *
  * @author Sharon Curtis
  */
public class GraphUtilities extends JDialog {

    private static Font preferredFont = new Font("Verdana", Font.BOLD, 14);


/////////////////// Code to read in a graph ////////////////////////

/** Allows the user to select a file containing a description of
  * a graph. If no file is selected, the program terminates.
  *
  * @param startFolder   the folder that the file chooser should start from
  * @return              the file that was selected
  */
    public static File getGraphFile(String startFolder) {

        File f;

        // setting up a little dialogue box,
        // to select a file containing a graph
        JFileChooser fc = new JFileChooser(startFolder);
        fc.setDialogTitle("Choose a file containing a graph");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int fcReturnValue = fc.showOpenDialog(null);

        // now, which file did the user select?
        if (fcReturnValue != JFileChooser.APPROVE_OPTION) {
            // user must have cancelled, or an error occurred
            System.out.println("No file selected. Program terminating.");
            System.exit(0);
            f = fc.getSelectedFile();
        }
        else { // user selected a file ok
            f = fc.getSelectedFile();
        }
        return f;
    }


/** Recreates a undirected graph from its textual description.
  * For simplicity, all graph nodes should have different
  * labels. The general format expected for a graph is this kind
  * of thing, where nodes are specified by their labels and
  * comma-separated coordinates, and edges are specified by two
  * nodes separated by <->. The labels are case-sensitive.
  * <pre>
  * David   100,300
  * Sharon  300,100
  * Ian     500,300
  * David <-> Ian
  * David <-> Sharon
  * Sharon <-> Ian
  * </pre>
  *
  * @param f    the file wherein the description of the graph is to be found
  * @return     the graph described by the file, if the file correctly describes
  *             a graph, otherwise it returns the empty graph
  */
   public static FriendsGraph readInGraph(File f) {

      ArrayList<String> lines = readTextFile(f);
      FriendsGraph g = new FriendsGraph(1);  // default in case of non-readable file
      String label;

      // first pass: counting the nodes and warning of any duplicate labels
      ArrayList<String> nodeLabels = new ArrayList<String>();
      for (String line: lines) {
          if (isNodeDescription(line)) {
              label = extractNodeLabel(line);
              if (nodeLabels.contains(label)) {
                  // oops there is a name called this already!
                  System.out.println("Warning: duplicate label \""+label
                                    +"\" found in graph description. Ignoring duplicate.");
              }
              else {
                  nodeLabels.add(label);
              }
          }
      }
      if (nodeLabels.size()>0) {
          // second pass: adding the nodes
          g = new FriendsGraph(nodeLabels.size());  // empty graph
          for (String line: lines) {
              if (isNodeDescription(line)) {
                  label = extractNodeLabel(line);
                  g.addNode(label,extractX(line),extractY(line));
              }
          }

          // third pass: adding the edges
          for (String line: lines) {
              if (!isNodeDescription(line)) {
                  if (isEdgeDescription(line)) {
                      String source = extractSource(line);
                      if (isValidNode(g,source)) {
                          String target = extractTarget(line);
                          if (isValidNode(g,target)) {
                              g.addEdge(source,target);
                              // System.out.println("Added edge \""+source+"\" <-> \""+target+"\".");
                          }
                          else {
                              System.out.println("Warning: did not recognise node \""+target
                                               +"\" found in graph description. Ignoring this edge.");
                          }
                      }
                      else {
                          System.out.println("Warning: did not recognise node \""+source
                                           +"\" found in graph description. Ignoring this edge.");
                      }
                  }
                  else {
                      System.out.println("Warning: could not parse line \""+line
                                        +"\" found in graph description. Ignoring this.");
                  }
              }
          }
      }
      return g;
    }

    private static boolean isNodeDescription(String line) {
    // returns true if this string is of the form   "David   100,300"

        boolean ok = true;
        int comma = line.lastIndexOf(",");
        if (comma<3)
            ok = false;
        else {
            String postComma = line.substring(comma+1).trim();
            String preComma = line.substring(0,comma).trim();
            int space = preComma.lastIndexOf(" ");
            String postSpace = preComma.substring(space+1).trim();
            String preSpace = preComma.substring(0,space).trim();
            // preSpace should have the name,
            // postSpace should have the x coordinate
            // postComma should have the y coordinate
            try {
                Integer.parseInt(postSpace);
                Integer.parseInt(postComma);
            }
            catch (Exception e) {
                ok = false;
            }
            ok = ok && preSpace.length()>0;
        }

        return ok;
    }

    private static String extractNodeLabel(String line) {
    // assuming that isNodeDescription(line), this returns the name of the node
        int comma = line.lastIndexOf(",");
        String preComma = line.substring(0,comma).trim();
        int space = preComma.lastIndexOf(" ");
        String preSpace = preComma.substring(0,space).trim();
        return preSpace;
    }

    private static int extractX(String line) {
    // assuming that isNodeDescription(line), this returns the x-coordinate of the node
        int x = 100;
        int comma = line.lastIndexOf(",");
        String preComma = line.substring(0,comma).trim();
        int space = preComma.lastIndexOf(" ");
        String postSpace = preComma.substring(space+1).trim();
        try {
            x = Integer.parseInt(postSpace);
        }
        catch (Exception e) {
        }
        return x;
    }

    private static int extractY(String line) {
    // assuming that isNodeDescription(line), this returns the y-coordinate of the node
        int y = 100;
        int comma = line.lastIndexOf(",");
        String postComma = line.substring(comma+1).trim();
        try {
            y = Integer.parseInt(postComma);
        }
        catch (Exception e) {
        }
        return y;
    }


    private static boolean isValidNode(FriendsGraph g, String str) {
    // returns true when str is the name of a node in the graph
        int i=0;
        while (i<g.size && (!g.nodes[i].name.equals(str)))
            i++;
        return i<g.size;
    }

    private static boolean isEdgeDescription(String line) {
    // returns true if this string is of the form   "David <-> Sharon"

        int i = line.indexOf("<->");
        if (i<1) {
            return false;
        }
        else {
            String pre = line.substring(0,i).trim();
            String post = line.substring(i+3).trim();
            return (pre.length()>0 && post.length()>0);
        }
    }

    private static String extractSource(String line) {
    // extracts the source node of this edge (described by line),
    // assuming that this line does indeed describe an edge
        int i = line.indexOf("<->");
        String pre = line.substring(0,i).trim();
        return pre;
    }

    private static String extractTarget(String line) {
    // extracts the source node of this edge (described by line),
    // assuming that this line does indeed describe an edge
        int i = line.indexOf("<->");
        String post = line.substring(i+3).trim();
        return post;
    }


    private static ArrayList<String> readTextFile(File inFile) {
    // Returns the contents of a text file as a list of lines.
    // The file is assumed to be a text file. If there is a problem,
    // an error message will be put on the console and the result
    // will be an empty list. Only non-empty lines are put in the list.

       ArrayList<String> contents = new ArrayList<String>();
       try {
           BufferedReader ansf = new BufferedReader(new FileReader(inFile));
           String fileLine = "";
           while ((fileLine = ansf.readLine()) != null) {
               fileLine = fileLine.trim();
               if (fileLine.length()>0)
                   contents.add(fileLine);
           }
           ansf.close();
       }
       catch (Exception e) {
           System.err.println("Some kind of a problem with reading the file \""
                            + inFile.getPath() + "\\" + inFile.getName() +"\":"
                            + e.toString());
       }
       return contents;
   }


/////////////////// Code for the dialogue box ////////////////////////


    private static GraphUtilities imageDialog;

    private static int xOffset = 0;  // (xOffSet,yOffSet) is the current
    private static int yOffset = 0;  // location of the image window, used in
                                     // case of multiple image displays at once

    private static int currentRowHeight = 0; // max image height on this row
    private static BufferedImage previousImage = null;
    private static int maxX = 600;


/** Displays an image of a graph.
  *
  * @param  g    the graph to be displayed
  * @return      returns zero if this method is successfully completed
  */
    public static int showGraph(FriendsGraph g) {

        BufferedImage im = getGraphImage(g);

        try {
            UIManager.setLookAndFeel(
            "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Unable to use Windows \"look and feel\"."
                            +" Default \"look and feel\" will be used instead.");
        }
        if (previousImage!=null) { // second or later image
                                   // so display not overlapping

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
        imageDialog = new GraphUtilities(frame,xOffset,yOffset,im);
        imageDialog.setVisible(true);

        // afterwards
        previousImage = im;
        return 0;
    }


    private GraphUtilities(Frame frame, int x, int y, BufferedImage im) {
    // creates a dialog box which displays the given image im,
    // positioned at location (x,y) on the screen

        super(frame, "Graph Display", Dialog.ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // makes a display area for the image
        JLabel imageArea = new JLabel(new ImageIcon(im));
        getContentPane().add(imageArea, BorderLayout.CENTER);

        setLocation(x,y);
        //Initialize values.
        pack();

    }


/////////////////// Code to create an image of a graph ////////////////////////

   static float gap = 5.0f;
   static float edgeGap = 30.0f;

   private static BufferedImage getGraphImage(FriendsGraph g) {
   // returns an image displaying the given graph

       // first a dummy image
	   BufferedImage im = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
       // first we need to figure out how big to make the image
       Dimension d = assignCoordinates(g,im.createGraphics());

       // then create the image
	   im = new BufferedImage(d.width,d.height,BufferedImage.TYPE_INT_RGB);

	   // set up the graphics context for the image
	   Graphics2D g2d = im.createGraphics();
	   g2d.setPaint(getBackgroundColour(g));
	   g2d.fill(new Rectangle2D.Double(0.0,0.0,(double)(d.width),(double)(d.height)));

	   g2d.setStroke (new BasicStroke (2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                        RenderingHints.VALUE_ANTIALIAS_ON);
       g2d.setFont(preferredFont);

       // finally we draw the graph:
       // draw lines first so that the nodes appear in front
       drawEdgeLines(g2d,g);
       drawNodeLabels(g2d,g);
       return im;
   }

    private static Dimension assignCoordinates(FriendsGraph g, Graphics2D g2d) {
    // calculates how big to display this graph

       // first to find the max x/y values
       float maxXvalue = 100.0f;
       float maxYvalue = 100.0f;

       float x,y;

       for (Node n: g.nodes) {

           GlyphVector glyph
              = preferredFont.createGlyphVector(g2d.getFontRenderContext(),
                                                n.name);
           Rectangle2D boundsRect = glyph.getLogicalBounds();

           x = n.x + (float)(boundsRect.getWidth())+gap+3.0f;
           y = n.y + (float)(boundsRect.getHeight())+gap+3.0f;
           if (x>maxXvalue)
               maxXvalue = x;
           if (y>maxYvalue)
               maxYvalue = y;
       }
       return new Dimension((int)(maxXvalue+edgeGap),(int)(maxYvalue+edgeGap));

    }

    private static Color getBackgroundColour(FriendsGraph g) {
        // start with a particular blue colour in HSB colourspace
        float col[] = Color.RGBtoHSB(99,194,255, null);

        // now to get a seed for the random number generator,
        // based on the contents of the graph itself
        int hash = 0;
        for (Node n: g.nodes) {
            String label = n.name;
            for (int i=0; i<label.length(); i++) {
                hash = hash + (int)(n.x*n.y*label.codePointAt(i));
            }
        }
        long seed = (long)hash;
        Random rng = new Random(seed);

        // now to change to a colour of half the saturation,
        // same brightness, but a random hue
        float newHue = rng.nextFloat();
        return Color.getHSBColor(newHue, col[1]/2.0f, col[2]);
    }

    private static void drawEdgeLines(Graphics2D g2d, FriendsGraph g) {
    // Draws all the lines for this graph
    // (should be done before drawing the nodes)

        g2d.setPaint(Color.black);
        for (int n=0; n<g.size; n++)
           for (int m=n; m<g.size; m++) {
               if ((m!=n) && g.edgeFrom(m,n)) {
                   Line2D aLine = new Line2D.Float(g.nodes[n].x,g.nodes[n].y,
                                                   g.nodes[m].x,g.nodes[m].y);
                   g2d.draw(aLine);
               }
               else if ((m==n) && g.edgeFrom(m,m)) {
                   Ellipse2D el = new Ellipse2D.Float(g.nodes[m].x-20.0f,
                                                      g.nodes[m].y,
                                                      40.0f,40.0f);
                   g2d.draw(el);
               }
           }
    }


    private static void drawNodeLabels(Graphics2D g2d, FriendsGraph g) {

       RoundRectangle2D.Float nodeBox;


       for (int n=0; n<g.size; n++) {

           GlyphVector glyph
              = preferredFont.createGlyphVector(g2d.getFontRenderContext(),
                                                g.nodes[n].name);
           Rectangle2D boundsRect = glyph.getLogicalBounds();
           nodeBox = new RoundRectangle2D.Float(
                         g.nodes[n].x - (float)(boundsRect.getWidth()/2.0f+gap),
                         g.nodes[n].y - (float)(boundsRect.getHeight()/2.0f+gap),
                         (float)(boundsRect.getWidth()+2*gap),
                         (float)(boundsRect.getHeight()+2*gap),
                         8.0f,8.0f);


           AffineTransform oldT = g2d.getTransform();
           Composite oldC = g2d.getComposite();
           AffineTransform at = new AffineTransform();
           at.translate(3.0,3.0);
           g2d.setTransform(at);
           g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
           g2d.setPaint(new Color(100,100,100));
           g2d.fill(nodeBox);
           g2d.setTransform(oldT);
           g2d.setComposite(oldC);

           g2d.setPaint(Color.white);
           g2d.fill(nodeBox);
           g2d.setPaint(Color.black);
           g2d.draw(nodeBox);
           g2d.drawString(g.nodes[n].name,
                          g.nodes[n].x - (float)(boundsRect.getWidth()/2.0f),
                          g.nodes[n].y + (float)(boundsRect.getHeight()/4.0f));
       }
    }
}
