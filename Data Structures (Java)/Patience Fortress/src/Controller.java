// You do NOT need to understand any of the code in this file,
// but you do need to know one thing: when it calls a method which is
// one of the ones you are to implement, it prints a message about its
// method call to the standard output. This means you can see
// exactly what method is being called when, with which parameters.



// Do NOT change the code in this file.
// Instead, please ignore the code in this file entirely.
//
// (If you are curious about what this file does, it provides
// the control facilities for the game, responding to mouse clicks
// and organising the graphics drawing.)

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.font.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class Controller extends JPanel implements MouseListener {

    public Rules cardsAndRules; // a reference to the object that deals cards
                                // and implements the rules, so that this
                                // controller can access it to find out details
                                // of which cards are where

    private GameDisplay gameSpecifics; // contains the game-specific display information


/** This constructor method initialises the Controller,
  * setting window display size and colours. It also calculates
  * various measurements that are used to display cards correctly.
  */
    public Controller(int cardWidth, Rules cardsAndRules) {

        super(new BorderLayout());   // calls JPanel constructor

        gameSpecifics = new GameDisplay(this); // for obtaining game-specific info

        this.cardWidth = (float)cardWidth; // sets the size of a displayed card
        initializeCardSizes();  // calculates various measurements relative to the
                            // card size, e.g. the height of a displayed card
        initializeFonts();  // sets up some suitable fonts for displaying suit symbols
        initializeCardImages();


        Dimension area = gameSpecifics.getPlayingAreaDimensions(cardWidth,cardHeight);
        // now to match the displayed size to that of the window
        setSize((int)area.getWidth(),(int)area.getHeight());
        setPreferredSize(area);
        setBorder(BorderFactory.createLineBorder(Color.black)); // thin border

        setBackground(gameSpecifics.baize);  // sets background colour


        this.cardsAndRules = cardsAndRules; // a reference to the object
                                            // dealing cards and enforcing rules

        // now to work out all the positions of the piles & columns of cards
        gameSpecifics.setCardDisplayParameters();

        cardSelected = false; // initially the user hasn't selected a card

        addMouseListener(this); // can listen out for mouse clicks
        repaint();
    }


////// Various parameters for drawing things (mostly for cards) //////

    private RoundRectangle2D.Float buttonBox; // shape of the redeal button,
                                              // needed for when we click on it

    public float cardWidth;  // width of a single playing card, from which
                              // other measurements are calculated,
                              // to make the card suitably proportioned
    public float cardHeight;  // height of a card (in proportion to its width)

    private float cardCornerRadius; // specifies how rounded the card corners are

    private float patternWidth; // specifies the width of a rectangle inside the outline,
                              // used for drawing the pattern on the backs of the cards

    private float borderWidth; // the width of the white border on the back of a card

    private Font f, mf, lf;  // normal-sized font, medium and large fonts
                             // for assorted card-drawing purposes

    private BufferedImage[] cardImages; // the card images are pre-loaded so we
                                        // don't have to go to the effort of redrawing
                                        // each card every time the display is refreshed

    private BasicStroke highlight = new BasicStroke(3.0f); // for a highlighted card

    private float[] dashes = {5.0f};     // dashed lines are for drawing empty piles of cards
    private  BasicStroke dashedLine
       = new BasicStroke(1.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,
	                     1.0f,dashes,0.0f);


////// Various methods for drawing things (mostly playing cards) //////


/** Returns a small 16x16 image with the symbol for the spades suit.  */
    public static BufferedImage getLogo() {

        // choose a font that hopefully has a spade suit symbol
        Font f = new Font("Trebuchet", Font.BOLD, 14);
        if (!f.canDisplay('\u2660')) { // can't display spades
           f = new Font("MS Serif", Font.BOLD, 14);
        }
        if (!f.canDisplay('\u2660')) {
           f = new Font("Lucida Sans Unicode", Font.BOLD, 14);
        } // give up if that one doesn't work either!

        // set up the 16x16 image
        BufferedImage bi = new BufferedImage(16,16,BufferedImage.TYPE_INT_RGB);
        Graphics2D gsr = (Graphics2D)bi.createGraphics();

        // paint a white 16x16 background on it
        gsr.setPaint(Color.white);
        Rectangle2D.Double bg = new Rectangle2D.Double(0.0,0.0,16.0,16.0);
        gsr.fill(bg);

        // paint a black spade symbol on it
        gsr.setFont(f);
        gsr.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        gsr.setPaint(Color.black);
        gsr.drawString(""+'\u2660',1,13); // spades suit symbol

        return bi;
    }

/** Called whenever the display is repainted.
  * Draws the coloured background and all the cards on top of it.
  */
    public void paintComponent(Graphics g){

        super.paintComponent(g);  // calls the superclass method,
                                  // which will get the background painted.

        Graphics2D g2 = (Graphics2D)g;
        // now turn anti-aliasing on, to produce smooth drawing not jagged edges
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        // draw the redeal button
        drawRedealButton(g2);

        // displays all the cards, using the information in the Rules object
        displayCards(g2);
    }

    private void drawRedealButton(Graphics2D g2) {
    // Draws the redeal button at the top-left
    // (the one that restarts the Patience game, when pressed)

       g2.setFont(mf);
       GlyphVector redealGlyph
          = mf.createGlyphVector(g2.getFontRenderContext(),"Redeal");
       Rectangle2D boundsRectRedeal = redealGlyph.getLogicalBounds();

       // how wide is an R?
       Rectangle boundsR = redealGlyph.getGlyphLogicalBounds(0).getBounds();

       buttonBox = new RoundRectangle2D.Float(
                          10.0f,
                          10.0f,
                          (float)(boundsRectRedeal.getWidth()+2*boundsR.getWidth()),
                          (float)(boundsRectRedeal.getHeight()+boundsR.getWidth()),
                          cardCornerRadius,
                          cardCornerRadius
                       );
       g2.setPaint(new Color(190,190,190));
       g2.fill(buttonBox);
       g2.setPaint(Color.black);
       g2.draw(buttonBox);
       float posX = (float)(10.f+boundsR.getWidth());
       float posY = (float)(10.0f+boundsRectRedeal.getHeight());
//       g2.setPaint(new Color(170,170,170));
//       g2.drawString("Redeal",posX+1.5f,posY+1.5f);
       g2.setPaint(Color.black);
       g2.drawString("Redeal",posX,posY);

    }

    private void initializeCardSizes() {
    // calculates various measurements relative to the
    // card size, e.g. the height of a displayed card

        cardHeight = 1.4f*cardWidth; // height of a card (to make cards suitably proportioned)
        cardCornerRadius = cardWidth/8.0f; // calculates a sensible size for the rounded corners

        borderWidth = cardWidth/12.0f; // the width of the white border
        patternWidth = cardWidth-2.0f*borderWidth; // width of the pattern on the back of a card
    }

    private void initializeFonts(){
    // sets up some suitable fonts for displaying suit symbols

        // size of font in proportion to size of playing card
        int fontSize = (int)(cardWidth/6.0f);

        // choose a font, hopefully with heart etc. symbols
        String fontFace = "Trebuchet";
        f = new Font(fontFace, Font.BOLD, fontSize);
        if (!f.canDisplay('\u2665')) { // can't display suits
           fontFace = "MS Serif"; // try this font instead
           f = new Font(fontFace, Font.BOLD, fontSize);
        }
        if (!f.canDisplay('\u2665')) {
           fontFace = "Lucida Sans Unicode";
           f = new Font(fontFace, Font.BOLD, fontSize);
        }
        if (!f.canDisplay('\u2665')) {
           fontFace = "Impact";
           f = new Font(fontFace, Font.BOLD, fontSize);
        } // give up if that one doesn't work either!

        int largerFontSize = (int)(2.5*fontSize);
        int mediumFontSize = (int)(1.4*fontSize);
        lf = new Font(fontFace, Font.BOLD, largerFontSize);
        mf = new Font(fontFace, Font.BOLD, mediumFontSize);

    }


    private void initializeCardImages() {
    // Puts all the images of the cards into the array cardImages
    // Note:
    // different orientations for playing cards not implemented as yet
    // although the drawCard method does cope with that

        cardImages = new BufferedImage[53];
        Card c;
        Graphics2D gCard;

        // the back of a playing card
        c = new Card(1,1,false); // suit/rank irrelevant with a face-down card
        cardImages[52] = new BufferedImage((int)(cardWidth+1.5f),
                                           (int)(cardHeight+1.5f),
                                            BufferedImage.TYPE_INT_ARGB);
        gCard = (Graphics2D)cardImages[52].createGraphics();
        Rectangle transparent = new Rectangle(0,0,cardImages[52].getWidth(),cardImages[52].getHeight());
        gCard.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                               RenderingHints.VALUE_ANTIALIAS_ON);
        gCard.setPaint(new Color(1,1,1,0));
        gCard.fill(transparent);
        drawCard(c,gCard,(int)((cardWidth+0.5f)/2.0f),(int)((cardHeight+0.5f)/2.0f));

        // and all the fronts
        for (int i=0; i<52; i++) {
            c = new Card(1+i/4,i%4);  // (rank,suit,face-up)
                                      // note: different with face-down cards
            cardImages[i] = new BufferedImage((int)(cardWidth+1.5f),
                                              (int)(cardHeight+1.5f),
                                              BufferedImage.TYPE_INT_ARGB);
            gCard = (Graphics2D)cardImages[i].createGraphics();
            gCard.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                   RenderingHints.VALUE_ANTIALIAS_ON);
            gCard.setPaint(new Color(0,0,0,0));
            gCard.fill(transparent);
            drawCard(c,gCard,(int)((cardWidth+0.5f)/2.0f),(int)((cardHeight+0.5f)/2.0f));
        }


    }


    public void drawCard(Card c, Graphics2D g, float x, float y) {
        drawCard(c,g,x,y,0.0);
    }

    public void drawCard(Card c, Graphics2D g, float x, float y, double orientation) {
    // draws the card c centred on the point (x,y)
    // inclined to given orientation/angle

        // remember current setting so we can reset g later
        AffineTransform prevTransform = g.getTransform();

        AffineTransform rotate = AffineTransform.getRotateInstance(orientation,(double)x,(double)y);
        AffineTransform rotatePlus180 = AffineTransform.getRotateInstance(orientation+Math.PI,(double)x,(double)y);
        g.setTransform(rotate);

        // drawing the outline of the playing card
        RoundRectangle2D.Float outline = getCardOutline(x,y);
        g.setPaint(Color.white);
        g.fill(outline);
        g.setPaint(Color.black);
        g.draw(outline);

        if (c.isFaceUp()) { // note: different with face-down cards

            // setting the right drawing colour (red or black)
            if ((c.getSuit()==c.CLUBS)
                || (c.getSuit()==c.SPADES) )
                g.setPaint(Color.black);
            else
                g.setPaint(new Color(250,0,0)); // red for hearts and diamonds

            // now to draw all the symbols


            g.setFont(f); // normal size font for corner symbols
            Point2D.Float boundingBox = boxSuitAndRank(c,g);


            // the corner suits and ranks
            drawSuitAndRank(c,g,(float)(x - cardWidth/2.0f+(borderWidth+1.0f)/2.0f ),
                                y-cardHeight/2.0f+(borderWidth+1.0f)/2.0f);
            drawSuitAndRank(c,g,(float)(x+cardWidth/2.0f-boundingBox.getX()-(borderWidth+1.0f)/2.0f),
                                y-cardHeight/2.0f+(borderWidth+1.0f)/2.0f);

            g.setTransform(rotatePlus180);

            // the corner suits and ranks, upside down now
            g.setFont(f); // normal size font for corner symbols
            drawSuitAndRank(c,g,(float)(x+(borderWidth+1.0f)/2.0f - cardWidth/2.0f),
                                y-cardHeight/2.0f+(borderWidth+1.0f)/2.0f);
            drawSuitAndRank(c,g,(float)(x+cardWidth/2.0f-boundingBox.getX()-(borderWidth+1.0f)/2.0f),
                                y-cardHeight/2.0f+(borderWidth+1.0f)/2.0f);

            g.setTransform(rotate);
            // drawing the pips or picture
            drawPips(c,g,x,y,boundingBox,rotate,rotatePlus180);

        }
        else // card is face-down, so draw the back of it
            drawReverse(c,g,x,y);

        // now put the orientation back again
        g.setTransform(prevTransform);

    }

    private RoundRectangle2D.Float getCardOutline(float x, float y) {
    // Returns the rounded rectangle that is the outline of a playing card
    // centred at (x,y), with no rotation.
    // (Outlines of playing cards are used quite a bit, for drawing
    // playing cards, hit detection and drawing outlines of empty stacks.
        return new RoundRectangle2D.Float(x-cardWidth/2.0f, y-cardHeight/2.0f,
                                      cardWidth, cardHeight, cardCornerRadius, cardCornerRadius);
    }

    private RoundRectangle2D.Float getCardOutline(float x, float y, int angle) {
        if (angle==0)
            return getCardOutline(x,y);
        else
            return new RoundRectangle2D.Float(x-cardHeight/2.0f,
                                              y-cardWidth/2.0f,
                                              cardHeight, cardWidth,
                                              cardCornerRadius, cardCornerRadius);

    }

    private RoundRectangle2D.Float getHighlightOutline(float x, float y) {
    // Returns the rounded rectangle that is the outline of a playing card
    // centred at (x,y), with no rotation.
    // (Outlines of playing cards are used quite a bit, for drawing
    // playing cards, hit detection and drawing outlines of empty stacks.
        return new RoundRectangle2D.Float(x+1-cardWidth/2.0f, y+1-cardHeight/2.0f,
                                      cardWidth-2, cardHeight-2, cardCornerRadius, cardCornerRadius);
    }

    private RoundRectangle2D.Float getHighlightOutline(float x, float y, int angle) {
        if (angle==0)
            return getHighlightOutline(x,y);
        else
            return new RoundRectangle2D.Float(x+1-cardHeight/2.0f,
                                              y+1-cardWidth/2.0f,
                                              cardHeight-2, cardWidth-2,
                                              cardCornerRadius, cardCornerRadius);


    }

    private Point2D.Float boxSuitAndRank(Card c, Graphics2D g) {
    // Works out how big the rank and suit symbols take up, together.
    // Also sets up some values for the positions of the symbols.

        String rank = getRankString(c);
        // first figure out how much room the rank of the card takes up
        GlyphVector rankGlyphs = f.createGlyphVector(g.getFontRenderContext(),
                                                     rank);
        Rectangle2D rankRect = rankGlyphs.getVisualBounds();
        Rectangle2D rankRect2 = rankGlyphs.getLogicalBounds();

        float widthRank = (float)rankRect2.getWidth(); // logical bounds give better centring
        float heightRank = (float)rankRect.getHeight(); // visual bounds give actual height

        String suit = getSuitString(c);
        // now figure out how big the suit symbol shape is
        GlyphVector suitGlyph = f.createGlyphVector(g.getFontRenderContext(),
                                                    suit);
        Rectangle2D suitRect = suitGlyph.getVisualBounds();
        Rectangle2D suitRect2 = suitGlyph.getLogicalBounds();
        float widthSuit = (float)suitRect2.getWidth(); // logical bounds give better centring
        float heightSuit = (float)suitRect.getHeight(); // visual bounds give actual height
        // however the actual glyph isn't positioned where you'd think,
        // it's a bit further to the right and down a bit, so to make the
        // | height of the image big enough, we use the
        // V logical height
        float heightSuit2 = (float)suitRect2.getHeight();


        // a rank of 10 is wide, so are heart symbols, so we'll use the
        // wider of the symbols to position both symbols:
        float maxWidth = (float)Math.max(widthRank,widthSuit);

        rposX = (maxWidth-widthRank)/2.0f;
        rposY = heightRank;
        sposX = (maxWidth-widthSuit)/2.0f;
        sposY = heightRank+heightSuit+((float)borderWidth-0.5f)/2.0f;

        return new Point2D.Float(maxWidth,heightRank+heightSuit2+(borderWidth+1.0f)/2.0f);

    }

    float rposX,rposY,sposX,sposY;  // for positioning rank and suit relative to their bounding box

    private void drawSuitAndRank(Card c, Graphics2D g, float cornerX, float cornerY) {
    // draws the rank and suit of the card drawn,
    // in vertical alignment with the rank on top

        g.drawString(getRankString(c),  // A,2,3,...,J,Q,K above
                     cornerX+rposX, cornerY+rposY);

        g.drawString(getSuitString(c), // suit below
                     cornerX+sposX, cornerY+sposY);

    }

    private void drawPips(Card c, Graphics2D g,
                          float x, float y,
                          Point2D.Float boundingBox,
                          AffineTransform rotate, AffineTransform rotatePlus180) {
    // drawing the suit symbol, relative to the centre of the card at (x,y)
    // (the bounding box was worked out earlier to be how much room
    // the rank and suit symbols take up, together, and the transforms
    // were set earlier too, by the drawCard method)

        String suit = getSuitString(c);
        AffineTransform origTransform = g.getTransform();

        if ((c.getRank()==Card.ACE)) {
            g.setFont(lf); // twice-size font for suit symbol

            GlyphVector largeSuitGlyph = lf.createGlyphVector(g.getFontRenderContext(),
                                                              suit);
            Rectangle2D largeSuitRectLB = largeSuitGlyph.getLogicalBounds();
            Rectangle2D largeSuitRectVB = largeSuitGlyph.getVisualBounds();
            float posX = (float)(x - largeSuitRectLB.getWidth()/2.0f);
            float posY = (float)(y + largeSuitRectVB.getHeight()/2.0f);
            g.drawString(suit,posX,posY);
        }
        else if (c.getRank()==Card.KING) {
            AffineTransform kingT = new AffineTransform(origTransform);
            double scaleFactor = 1/500.0;
            kingT.scale(cardWidth*scaleFactor,cardWidth*scaleFactor);
            kingT.translate(0.5/scaleFactor,cardHeight*0.5/(scaleFactor*cardWidth));
            g.setTransform(kingT);
            PictureCards.drawKingCrown(g,c.getSuit());
        }
        else if (c.getRank()==Card.QUEEN) {
            AffineTransform qT = new AffineTransform(origTransform);
  		    double scaleFactor = 1/1000.0;
  		    qT.scale(cardWidth*scaleFactor,cardWidth*scaleFactor);
            qT.translate(0.5/scaleFactor,cardHeight*0.5/(scaleFactor*cardWidth));
		    g.setTransform(qT);
		    PictureCards.drawQueenCrown(g,c.getSuit());
        }
        else if (c.getRank()==Card.JACK) {
            AffineTransform jackT = new AffineTransform(origTransform);
  		    double scaleFactor = 1/195.0;
  		    jackT.scale(cardWidth*scaleFactor,cardWidth*scaleFactor);
  		    jackT.translate(0.25/scaleFactor,cardHeight*0.25/(scaleFactor*cardWidth));
		    g.setTransform(jackT);
		    PictureCards.drawJackShield(g,c.getSuit());
        }
        else {
            g.setFont(mf); // medium-sized font for ordinary symbol pips
            GlyphVector mediumSuitGlyph = mf.createGlyphVector(g.getFontRenderContext(),
                                                               suit);
            Rectangle2D mediumSuitRectLB = mediumSuitGlyph.getLogicalBounds();
            Rectangle2D mediumSuitRectVB = mediumSuitGlyph.getVisualBounds();

            // now to figure out some positions for the suit symbols
            float leftX = (float)(x - cardWidth/2.0f +(borderWidth+1.0f)/2.0f
                                     + boundingBox.getX());
            float rightX = (float)(x + cardWidth/2.0f - (borderWidth+1.0f)/2.0f
                                     - boundingBox.getX() - mediumSuitRectLB.getWidth());
            float centreX = (float)(x - mediumSuitRectLB.getWidth()/2.0f);
            float centreY = (float)(y + mediumSuitRectVB.getHeight()/2.0f);
            float highestY = (float)(y-cardHeight/2.0f+(borderWidth+1.0f)/2.0f
                               +sposY+mediumSuitRectVB.getHeight()/2.0f);
            float highY = (centreY + highestY)/2.0f;
            float higherY = (2.0f*centreY + highestY)/3.0f;
            if ((c.getRank()==3) || (c.getRank()==5) || (c.getRank()==9)) {
            // medium-sized pip in the centre
                g.drawString(suit,centreX,centreY);
            }
            if ((c.getRank()==2) || (c.getRank()==3)) {
            // medium-sized pips in the middle: top & bottom
                g.drawString(suit,centreX,highestY);
                g.setTransform(rotatePlus180);
                g.drawString(suit,centreX,highestY);
                g.setTransform(rotate);
            }
            if ((c.getRank()>=4) && (c.getRank()<=10)) {
            // four pips, all four corners
                g.drawString(suit,leftX,highestY);
                g.drawString(suit,rightX,highestY);
                g.setTransform(rotatePlus180);
                g.drawString(suit,leftX,highestY);
                g.drawString(suit,rightX,highestY);
                g.setTransform(rotate);

            }
            if ((c.getRank()>=6) && (c.getRank()<=8)) {
            // two pips left and right of the centre of the card
                g.drawString(suit,leftX,centreY);
                g.drawString(suit,rightX,centreY);
            }
            if ((c.getRank()==7) || (c.getRank()==8)) {
            // a medium-sized pip in the middle top
                g.drawString(suit,centreX,highY);
            }
            if ((c.getRank()==8)) {
            // a medium-sized pip to mirror the middle top pip
                g.setTransform(rotatePlus180);
                g.drawString(suit,centreX,highY);
                g.setTransform(rotate);
            }
            if ((c.getRank()==9) || (c.getRank()==10)) {
            // side pips in the middle
                g.drawString(suit,leftX,higherY);
                g.drawString(suit,rightX,higherY);
                g.setTransform(rotatePlus180);
                g.drawString(suit,leftX,higherY);
                g.drawString(suit,rightX,higherY);
                g.setTransform(rotate);
            }
            if (c.getRank()==10) {
                g.drawString(suit,centreX,(higherY+highestY)/2.0f);
                g.setTransform(rotatePlus180);
                g.drawString(suit,centreX,(higherY+highestY)/2.0f);
                g.setTransform(rotate);
            }
        }
        g.setTransform(origTransform);


    }

    private void drawReverse(Card c, Graphics2D g, float x, float y) {
    // draws the back of the card with a little textured pattern on it,
    // using the colour backColour

        Shape previousClip = g.getClip(); // save for later

        // work out how rounded to make the corners of the pattern
        float innerCornerRadius = patternWidth/8.0f;

        // first draw a blue background
        RoundRectangle2D.Float inner // for drawing an inner border
              = new RoundRectangle2D.Float(x-cardWidth/2.0f+borderWidth,
                                           y-cardHeight/2.0f+borderWidth,
                                           cardWidth+0.5f-2.0f*borderWidth,
                                           cardHeight+0.5f-2.0f*borderWidth,
                                           innerCornerRadius, innerCornerRadius);

        g.setPaint(gameSpecifics.backColour);
        g.fill(inner);
        g.draw(inner);

        // sets the shape (clip) for the pattern on the back of the playing card
        g.setClip(inner);

        // now to draw lots of little white circles on the background colour
        float patternSize = patternWidth/15.0f;
        g.setPaint(Color.white);
        Ellipse2D.Double circ;
        for (float cx=x-cardWidth/2.0f+borderWidth; cx<x+cardWidth; cx=cx+patternSize+patternWidth/60.0f)
            for (float cy=y-cardHeight/2.0f+borderWidth; cy<y+cardHeight; cy=cy+patternSize+patternWidth/60.0f) {
                circ = new Ellipse2D.Double((double)cx,(double)cy,patternSize,patternSize);
                g.fill(circ);
            }

        // return g to its previous clip state
        g.setClip(previousClip);
    }


/** The getSuitString() method returns the character represented by this suit
  * for the card c. However if the font used doesn't have a representation
  * of the symbol, then the expected symbol may not appear.
  */
   public String getSuitString(Card c) {
      if (c.getSuit()==Card.CLUBS)
          return "\u2663"; // 2663 is the unicode hexadecimal value for the clubs symbol
      else if (c.getSuit()==Card.DIAMONDS)
          return "\u2666"; // 2666 is the unicode hexadecimal value for the diamonds symbol
      else if (c.getSuit()==Card.HEARTS)
          return "\u2665"; // 2665 is the unicode hexadecimal value for the hearts symbol
      else
          return "\u2660"; // 2660 is the unicode hexadecimal value for the spades symbol
   }


/** The getRankString() method returns the integer rank of the card c.
  */
   public String getRankString(Card c) {
      if (c.getRank()==1)
          return "A";
      else if (c.getRank()==11)
          return "J";
      else if (c.getRank()==12)
          return "Q";
      else if (c.getRank()==13)
          return "K";
      else if ((c.getRank()>=2) && (c.getRank()<=10))
          return ""+c.getRank();
      else
          return "?";
   }






    private void displayCards(Graphics2D g2) {
    // this method draws the cards on the screen

       float xPos, yPos;
       StackDisplayInfo cs;

       for (int d=0; d<gameSpecifics.cardDisplays.length; d++) {

          // draw this display
          cs = gameSpecifics.cardDisplays[d];
          if (cs.cards.size()==0) { // need to draw an outline for an empty stack
             drawOutline(g2,cs.x,cs.y,cs.orientation);
          }
          else { // draw all the cards
             xPos = cs.x;
             yPos = cs.y;
             Card c;
             for (int i=0; i<cs.cards.size(); i++) {
                // draw this card
                c = cs.cards.get(i);
                if (c!=null) {
                    displayCard(c,g2,xPos,yPos,cs.orientation);
                    if (cardSelected && (cs.stackType==source.type)
                        && (cs.originalPosition==source.stackIndex)
                        && (i==source.position)) // this card is selected
                        addHighlight(g2,xPos,yPos,cs.orientation); // with highlight
                }
                else {
                    if (cs.stackType==SPREAD_RESERVE_RIGID)
                        drawOutline(g2,xPos,yPos,cs.orientation);
                }
                xPos = xPos + (float)(cs.spread*Math.sin(cs.direction));
                yPos = yPos - (float)(cs.spread*Math.cos(cs.direction));
             }
          }
       }
    }

    private void drawOutline(Graphics2D g, float x, float y, int orientation) {
    // draws a dashed outline, e.g. for an empty foundation pile

        // set up a rectangle for the outline
        RoundRectangle2D.Float outline = getCardOutline(x,y,orientation);
        g.setPaint(Color.white);
        Stroke previousStroke = g.getStroke(); // save for later
        g.setStroke(dashedLine);
        g.draw(outline);
        g.setStroke(previousStroke);
    }

    private void displayCard(Card c, Graphics2D g, float x, float y, int angle) {
    // displays the card on the graphics context g
        BufferedImage bi;
        if (c.isFaceUp()) // note: different with face-down cards
            bi = cardImages[4*(c.getRank()-1)+c.getSuit()];
        else                // note: different with face-down cards
            bi = cardImages[52]; // note: different with face-down cards
        int w = (int)(x-bi.getWidth()/2.0f);
        int h = (int)(y-bi.getHeight()/2.0f);
        if (angle==0) {
            g.drawImage(bi,w,h,null);
        }
        else { // rotate by 90 degrees
            AffineTransform a = g.getTransform();
            g.rotate(Math.PI/2.0,(double)x,(double)y);
            g.drawImage(bi,w,h,null);
            g.setTransform(a);
        }
    }

    private void addHighlight(Graphics2D g, float x, float y, int orientation) {
    // drawing the outline of the playing card in bold
        RoundRectangle2D.Float outline = getCardOutline(x,y,orientation);
        Stroke previousStroke = g.getStroke(); // save for later
        g.setStroke(highlight); // makes dashed lines
        g.setPaint(Color.black);
        outline = getHighlightOutline(x,y,orientation);
        g.draw(outline);
        g.setStroke(previousStroke); // return stroke to what it was before
    }


////// Various parameters useful for the user interaction. //////

    // There are really only two states of the user interaction:
    // either a card is currently selected (in which case the next click
    // may denote its destination location) or no card is selected.
    // This variable records this state.
    private boolean cardSelected = false;

    private Rectangle bounds = new Rectangle(); // used for noting an area of the
                                                // display, possibly an area to be
                                                // redrawn soon





    // The variables below describe different types of stack, pile or
    // other card display. In particular, these are used to specify:
    // * how the stack/pile/display is actually displayed
    // * which cards are possible to select
    //
    // Note that the top of any of these pile types (or the area of the
    // base of the stack if the pile is empty) could theoretically
    // be a possible destination, but in practice, if it isn't a
    // destination, then the game-specific code simply won't call a
    // moving method to that pile.
    //
    // In addition, the GameDisplay object with the game-specific
    // information will use the types of the stacks/piles/lists
    // and the index of the stacks/piles/lists in the original display
    // to determine which method to call when a card move is being
    // attempted, e.g. from column to foundation.

    // DESTINATION_PILE
    //    can't have any card selected, not even the top one,
    //    so is only useful as a destination
    //
    // e.g. useful for a foundation pile
    public static final int DESTINATION_PILE=0;

    // STACK_TOP_FLIPPABLE
    //   can select the top card (only the top card), and can flip it
    //
    // e.g. a stock pile of cards
    public static final int STACK_TOP_FLIPPABLE=1;

    // STACK_TOP_NO_FLIP
    //   can select the top card (only the top card), but not flip it
    //
    // e.g. a garden column in Flower Garden Patience, or a discard pile,
    // presumably more useful when the top card is always face-up
    public static final int STACK_TOP_NO_FLIP=2;

    // SPREAD_STACK
    //    any card can be selected (if overlapping, then the uppermost
    //    of the overlap will be the one selected), the top card is flippable
    //
    // e.g. the columns in Demon Patience
    public static final int SPREAD_STACK=3;

    // SPREAD_RESERVE
    //    any card can be selected (if overlapping, then the uppermost
    //    of the overlap will be the one selected), no card is flippable
    //
    // e.g. the bouquet in Flower Garden Patience
    public static final int SPREAD_RESERVE=4;

    // SPREAD_RESERVE_RIGID
    //     any card can be selected (should not be overlapping), with dashed
    //     lines for empty positions, no card is flippable
    //     (e.g. the body in Eagle Patience)
    public static final int SPREAD_RESERVE_RIGID=5;

    // These variables record information about the last selected (source) card
    // and its intended destination target, once known.
    private CardMove source = new CardMove();
    private CardMove target = new CardMove();


////// Various methods for detecting and responding to clicks. //////

	public void mouseEntered(MouseEvent e) {
	// do nothing when the mouse enters the window
	}

	public void mouseExited(MouseEvent e) {
	// do nothing when the mouse exits the window
	}

	public void mousePressed(MouseEvent e) {
	// don't respond to mouse presses
        // System.out.println("mouse pressed");
	}

    public void mouseClicked(MouseEvent e) {
	// don't respond to mouse clicks
        // System.out.println("mouse clicked");
    }

	public void mouseReleased(MouseEvent e) {
	// this event occurs when the mouse is clicked slowly or fast,
	// at a static mouse position or moving in the same place, so
	// we use this to detect a click, not the mouseClicked method

        if (buttonBox.contains((double)e.getX(),(double)e.getY())) {
        // redeal button was hit, so redeal
            cardsAndRules.redeal();
            gameSpecifics.setCardDisplayParameters(); // re-set the display info
            // (not sure why that was needed at all, but without it,
            // it won't redeal??!?!)

            source = new CardMove();
			target = new CardMove();
            repaint();
        }
        else if ((!cardSelected) && cardHitDetection(e.getX(),e.getY())) {
        // user selected a card, first try to see if it's flippable
	        cardSelected = true;
	        // if card selected is face down and can flip, do a flip,
	        doFlipIfNecessary(); // (if a flip happens this will unselect the card)
 	        paintImmediately(bounds);  // call to a JPanel facility!
 	        repaint();
        }
        else if (cardSelected) {
        // user is trying to move a card to a different location
            targetHitDetection(e.getX(),e.getY());
            gameSpecifics.rearrangeCards(source.type,
                                         source.stackIndex,
                                         source.position,
                                         source.displayIndex,
                                         target.type,
                                         target.stackIndex,
                                         target.position,
                                         target.displayIndex);
            cardSelected = false;
            repaint();
        }
	}



    private boolean cardHitDetection(int x, int y) {
    // This method is called precisely when no card is currently selected
    // and the user has just performed a click.
    // This method returns true if the user has clicked on a
    // card (any card, face up or face down) that could now
    // possibly have an action performed on it in the patience game.

       int clickedOn;
       boolean detected = false;

       // The code below makes a strong assumption: none of the visual displays
       // of stacks can possibly overlap in a game. Therefore we can
       // test separately all the card displays, and any card hit detected
       // must therefore be the only hit.

       for (int i=0; i<gameSpecifics.cardDisplays.length; i++) {  // for all the card displays
           StackDisplayInfo d = gameSpecifics.cardDisplays[i];

           // if this display is a spread display, where any card is clickable
           if ((d.stackType==SPREAD_STACK) || (d.stackType==SPREAD_RESERVE)
               || (d.stackType==SPREAD_RESERVE_RIGID)) {
               clickedOn = hitInSpreadStack(x,y,d,true); // true means there must be a card there
               if ((clickedOn>=0)          // not clicked on an empty stack
                   && (d.cards.size()>0)){
                   detected = true;
                   source.type = d.stackType;
                   source.stackIndex = d.originalPosition;
                   source.position = clickedOn;
                   source.displayIndex = i;
                   setBounds(d);
               }
           }

           // else if it's a display where only the top card is selectable
           else if ((d.stackType==STACK_TOP_FLIPPABLE)
                    || (d.stackType==STACK_TOP_NO_FLIP)) {

               if (hitTopOfStack(x,y,d) // not clicked on an empty stack
                   && (d.cards.size()>0)) {
                   detected = true;
                   source.type = d.stackType;
                   source.stackIndex = d.originalPosition;
                   source.position = d.cards.size()-1;
                   source.displayIndex = i;
//           System.out.println("ding: "+d.stackType+", "+sourceCardStack+", "+sourceCardIndex);
                   setBounds(d);
               }
           }
           // else it must be a destination pile, where none is selectable, so do nothing
       }
       return detected;
    }



    private int hitInSpreadStack(int x, int y, StackDisplayInfo d, boolean mustBeCard) {
    // Tests whether the position (x,y) has hit a card in a spread-out display,
    // i.e. one where any card is clickable. If not, it returns -1.
    // If so, it returns the index of the card hit.
    // Cards later in the stack/display are more uppermost so get precedence
    // when cards overlap. If a stack/display is empty then -1 will be returned as
    // there are no cards to click on.
       double clickX = (double)x;
       double clickY = (double)y;
       float posX,posY;
       int clickedOn = -1;
       for (int c=0; c<d.cards.size(); c++) { // for all cards in stack/display
           if ((!mustBeCard) || (d.cards.get(c)!=null)) {  // there is a card here
              posX = d.x +
                    (float)(c*d.spread*Math.sin(d.direction));
              posY = d.y -
                    (float)(c*d.spread*Math.cos(d.direction));
              // (posX,posY) is where the centre of the card is located
              RoundRectangle2D.Float outline = getCardOutline(posX,posY,d.orientation);
              if (outline.contains(clickX,clickY)) {
                  clickedOn = c;
              }
           }
       }
       return clickedOn;
    }


    public boolean hitTopOfStack(int x, int y, StackDisplayInfo d) {
    // Tests whether the position (x,y) has hit a card at the top of the stack,
    // or if the stack is empty, whether (x,y) has hit the outline of the stack base.
       double clickX = (double)x;
       double clickY = (double)y;
       float posX,posY;

       if (d.cards.size()==0) { // the stack is empty
          posX = d.x;  // so we just check the
          posY = d.y;  // base position of the stack
       }
       else { // want to check whether the top card of the stack
              // has been clicked
          posX = d.x +
                 (float)((d.cards.size()-1)
                          *d.spread*Math.sin(d.direction));
          posY = d.y -
                 (float)((d.cards.size()-1)*
                          d.spread*Math.cos(d.direction));
       }
       RoundRectangle2D.Float outline = getCardOutline(posX,posY,d.orientation);
       return (outline.contains(clickX,clickY));
    }

    private void setBounds(StackDisplayInfo d) {
    // Sets the bounds variables boundsX,boundsY,boundsWidth,boundsHeight
    // so that they represent a rectangle just enclosing the display
    // (in preparation for just this display being redrawn later)

        if (d.cards.size()==0) { // a rectangle just larger than the base
            bounds = new Rectangle((int)(d.x -  cardHeight/2) - 1,
                                   (int)(d.y - cardHeight/2) - 1,
                                   (int)(cardHeight + 2),
                                   (int)(cardHeight + 2));
        }
        else { // a rectangle large enough to incorporate the whole stack
               // (this assumes a stack is linearly spread out, not a fan)

            int x1 = (int)d.x;
            int x2 = (int)(d.x +
                          (float)((d.cards.size()-1)
                                   *d.spread
                                   *Math.sin(d.direction)));
            int y1 = (int)d.y;
            int y2 = (int)(d.y -
                          (float)((d.cards.size()-1)
                                   *d.spread
                                   *Math.cos(d.direction)));
            bounds = new Rectangle(Math.min(x1,x2) - (int)(cardWidth/2) - 1,
                                   Math.min(y1,y2) - (int)(cardHeight/2) - 1,
                                   Math.max(x1,x2) - Math.min(x1,x2) + (int)(cardWidth) + 2,
                                   Math.max(y1,y2) - Math.min(y1,y2) + (int)(cardHeight) + 2);
        }
    }



    private void doFlipIfNecessary() {
    // This method is called when the user has just clicked on a
    // card that could have an action performed on it.
    //
    // It detects whether a flip is the right action to do on this
    // card (i.e. the card is face-down, at the top of a pile, and
    // can be flipped), and flips the card, if so.

       int p = source.displayIndex; // which card display
       if ((source.type==STACK_TOP_FLIPPABLE)
           || (source.type==SPREAD_STACK)) { // the top card of a SPREAD_STACK
                                             // can be flipped, too
           Card c = gameSpecifics.cardDisplays[p].cards.get(gameSpecifics.cardDisplays[p].cards.size()-1);
           if (!c.isFaceUp()  // note: different with face-down cards
               &&
               (source.position==gameSpecifics.cardDisplays[p].cards.size()-1)) {
               // card is face-down and top of the stack
               c.setFaceUp(true);   // note: different with face-down cards
               cardSelected = false; // no card selected now since we've flipped it
           }
       }
    }


    private void targetHitDetection(int x, int y) {
    // This method is called when the user has previously selected a card,
    // and has subsequently clicked at position (x,y), and we're now looking
    // to see whether the position (x,y) represents a valid target location
    // for the user to move the card to.

        // As before, none of the stacks overlap, so we can test separately
        // for hit detection.

        boolean detected = false;

        for (int i=0; i<gameSpecifics.cardDisplays.length; i++) {  // for all the card displays
            StackDisplayInfo d = gameSpecifics.cardDisplays[i];
            if (d.stackType==SPREAD_RESERVE_RIGID) { // special case as can target the
                                                     // middle of a spread reserve
                int w = hitInSpreadStack(x,y,d,false);
                if (w>=0) {
                    target.type = d.stackType;
                    target.stackIndex = d.originalPosition;
                    target.position = w;
                    target.displayIndex = i;
                    detected = true;
                }
            }
            else {
                if (hitTopOfStack(x,y,gameSpecifics.cardDisplays[i])) { // of any display type
                    target.type = d.stackType;
                    target.stackIndex = d.originalPosition;
                    target.displayIndex = i;
                    detected = true;
	            }
            }
        }
        // the other possibility is that the user clicked on the playing area
	    if (!detected) {
            // System.out.println("Clicked on a non-destination.");
	        source = new CardMove();
	        target = new CardMove();
        }
    }

    class CardMove {
    // for storing information about a selected card, or the destination
    // for a target location for a card about to be moved

        int type; // the type of the pile containing the
                  // card location that was clicked on

        int stackIndex; // the index of the stack/pile that contains
                        // the card location that was clicked on (i.e. how
                        // the Rules class refers to this stack/pile, so
                        // if it was the second column of an array of stacks,
                        // then this value would be 1)

        int position; // the index within the stack/pile of the
                      // selected card, e.g. if the base of the pile
                      // was clicked on, then this would be 0

        int displayIndex; // the index of this stack/pile within the
                          // master list (i.e. how the GameDisplay class
                          // refers to this stack/pile)

        CardMove() {
            type = -1;
            stackIndex = -1;
            position = -1;
            displayIndex = -1;
        }

    }
}

