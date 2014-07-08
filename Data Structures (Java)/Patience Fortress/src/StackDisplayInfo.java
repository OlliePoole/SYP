// Do NOT change the code in this file.
//
// You do NOT need to understand any of the code in this file,
// you can ignore it entirely. (If you are curious, it is simply
// a container class for storing information about to display a
// bunch of cards on the screen.)

import java.util.*;

public class StackDisplayInfo {

       // for representing stacks of cards, or other displays of cards
       // e.g. one card display might be a foundation pile, another card
       // display might be a column of cards

    public ArrayList<Card> cards;  // a reference to the list of cards in this display

    public float x,y; // specifies the positions of the base card (position 0)

    public double spread; // how far apart the cards are laid, when displayed

    public int orientation; // the orientation of the cards (assuming all cards
                            // angled the same, 0 is upright, 90 is 90 degrees rotated
                            // (can only cope with those two values at the moment!)

    public double direction; // specifies the direction (by giving an angle) of
                                // the top card of the stack, relative to the base.

    public int stackType; // specifies which cards can be clicked on

    public int originalPosition; // for retrieving positions of stacks in
                                 // their original arrays or lists

    public StackDisplayInfo(ArrayList<Card> a) {
          cards = a;
          orientation = 0; // default value, for backwards compatibility
                           // with Patiences that didn't know about orientation
    }

}