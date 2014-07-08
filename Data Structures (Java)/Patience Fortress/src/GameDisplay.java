// This class contains information about the display of the cards,
// and the user interaction with the display, for Fortress Patience.
//
// This file is game-specific.

import java.awt.*;
import java.util.*;

public class GameDisplay {

    private Controller boss;  // a reference to the controller
                              // that employs this object

/** The colour for the background of the Patience playing area. */
    public static final Color baize = new Color(108,124,122); // grey

/** The colour using in drawing the pattern on the backs of the cards. */
    public static final Color backColour = new Color(19,27,193); // blue

/** One big list of all the displays of cards in the playing area. */
    public StackDisplayInfo[] cardDisplays;

    public GameDisplay(Controller control) {
        boss = control;
    }

/** Specifies how wide (ideally) the playing area should be,
  * given the card width. For example, returning (12,10)
  * would indicate that the playing area is to be 12 card widths
  * across and 10 high.
  */
    public Dimension getPlayingAreaDimensions(float cardWidth, float cardHeight) {
        int panelWidth = (int)(60 +  12.5f*cardWidth);
        int panelHeight = (int)(20 + 5.0f*cardHeight + 2.0f*cardWidth);
        return new Dimension(panelWidth,panelHeight);
    }

/*
    Note well, concerning the code in the setCardDisplayParameters method!

    The code in the Controller class makes a strong assumption:
    None of the visual displays of stacks/lists of cards
    can possibly overlap in a game. This is for a good reason - if
    cards overlap, how can we tell which card the user clicked on?

    So the stacks and other displays of cards should be arranged so
    that they can never overlap. Take care! Don't just think about the
    layout of cards when dealing, but also when cards have been moved
    about between stacks.

    Note also:
    With regards to the stack types, these specify not only the
    display, but how a user may interact with a display of cards,
    including which card(s) can be selected. They can be used to
    figure out which method to call, but there is also available
    the index of the cardDisplays variable, in case there's more
    than one pile with the same stack type.


    DESTINATION_PILE
        can't have any card selected, not even the top one,
        so is only useful as a destination
        (e.g. it is useful for a foundation pile)

    STACK_TOP_FLIPPABLE
       can select the top card (only the top card), and can flip it
       (e.g. a stock pile of cards)

    STACK_TOP_NO_FLIP
       can select the top card (only the top card), but not flip it
       (e.g. a garden column in Flower Garden Patience, or a discard pile,
        presumably more useful when the top card is always face-up)

    SPREAD_STACK
       any card can be selected (if overlapping, then the uppermost
       of the overlap will be the one selected), the top card is flippable
       (e.g. the columns in Demon Patience)

    SPREAD_RESERVE
        any card can be selected (if overlapping, then the uppermost
        of the overlap will be the one selected), no card is flippable
        (e.g. the bouquet in Flower Garden Patience)

    SPREAD_RESERVE_RIGID
        any card can be selected (should not be overlapping), with dashed
        lines for empty positions, no card is flippable
        (e.g. the body in Eagle Patience)

*/

/** Initialises the display parameters for the various displays of cards. */
    public void setCardDisplayParameters() {

        cardDisplays = new StackDisplayInfo[boss.cardsAndRules.foundations.length
                                            +boss.cardsAndRules.rows.length];

        float offset = 50.0f;  // a vertical offset to avoid drawing on top
                               // of the redeal button

        // setting up the foundation piles
        for (int p=0; p<boss.cardsAndRules.foundations.length; p++) {
            cardDisplays[p] = new StackDisplayInfo(boss.cardsAndRules.foundations[p].theStack);
            cardDisplays[p].stackType = Controller.DESTINATION_PILE;
            cardDisplays[p].originalPosition = p;
            cardDisplays[p].spread = 0.0;
            cardDisplays[p].direction = 0.0;
            cardDisplays[p].x = 20 + 6.25f*boss.cardWidth;
            cardDisplays[p].y = offset + 1.0f*boss.cardHeight + 0.25f*boss.cardWidth
                                + p*(1.0f*boss.cardHeight + 0.5f*boss.cardWidth);
        }

        // now for the rows
        for (int p=0; p<boss.cardsAndRules.rows.length; p++) {
            cardDisplays[4+p] = new StackDisplayInfo(boss.cardsAndRules.rows[p].theStack);
            cardDisplays[4+p].stackType = Controller.STACK_TOP_NO_FLIP;
            cardDisplays[4+p].originalPosition = p;
            cardDisplays[4+p].spread = 0.25f*boss.cardWidth;
            if (p%2==0) {
                cardDisplays[4+p].direction = 3.0*Math.PI/2.0;  // 9 o' clock direction
                cardDisplays[4+p].x = 20 + 4.75f*boss.cardWidth;
            }
            else {
                cardDisplays[4+p].direction = Math.PI/2.0;  // 3 o' clock direction
                cardDisplays[4+p].x = 20 + 7.75f*boss.cardWidth;
            }
            int d = p/2;
            cardDisplays[4+p].y = offset + 0.5f*boss.cardHeight
                                  + d*(0.5f*boss.cardWidth + boss.cardHeight);
        }
    }

/** Calls the relevant methods in the Rules object to check the rules and move the cards. */
    public void rearrangeCards(int sourceType, // the type of the pile containing the selected card
                               int sourceStack,  // which stack/pile index the selected card is on
                               int sourcePosition,  // the index within the stack/pile of the selected card
                               int sourceDisplay, // which pile (in the display master list) the selected card belongs to
                               int targetType, // the type of the destination pile selected
                               int targetStack, // which stack/pile index the user chose for a target
                               int targetPosition, // the index within the target stack/pile of the destination
                               int targetDisplay) { // which pile (in the display master list) the target belongs to

       if ((sourceType==Controller.STACK_TOP_NO_FLIP)
           && (targetType==Controller.DESTINATION_PILE)) {
           System.out.println(
               "Calling method moveRowCardToFoundationIfPossible("
               +sourceStack+","
               +targetStack+")"
           );
           boss.cardsAndRules.moveRowCardToFoundationIfPossible(sourceStack,targetStack);
       }
       else if ((sourceType==Controller.STACK_TOP_NO_FLIP)
           && (targetType==Controller.STACK_TOP_NO_FLIP)) {
           System.out.println(
               "Calling method moveRowCardToRowIfPossible("
               +sourceStack+","
               +targetStack+")"
           );
           boss.cardsAndRules.moveRowCardToRowIfPossible(sourceStack,targetStack);
       }
    }

}