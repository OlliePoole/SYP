// Do NOT change the code in this file.
//
// DO read through the stack manipulation code in this file
// thoroughly and understand what it does.

import java.util.*;

/** This class represents a stack of Card objects and
  * provides the standard stack methods:
  *
  *    isEmpty()
  *    size()
  *    push(Card c)
  *    pop()
  *    peek()
  *
  */
public class CardStack {

/** The variable theStack is the arrayList that is used to implement
  * the stack. It stores the cards contained in this stack.
  * The top of the stack is at position theStack.size()-1
  */
    public ArrayList<Card> theStack;

/** This constructor method sets up the stack as an empty stack.
  */
    public CardStack() {
        theStack = new ArrayList<Card>();
    }

/** Pre:  true
  * Post: The value returned is the size of the stack.
  */
    public int size() {
        return theStack.size();
    }

/** Pre:  true
  * Post: The boolean true is returned precisely
  *       when the stack is empty
  */
    public boolean isEmpty() {
        return (theStack.size()==0);
   }

/** Pre:  true
  * Post: The Card c has been pushed onto the top of the stack,
  *       and the rest of the stack remains unchanged.
  */
    public void push(Card c) {
        theStack.add(c);
    }

/** Pre:  The stack is not empty (i.e. theStack.size()>0).
  * Post: The value returned was formerly the top card in the stack,
  *       and the rest of the stack remains unchanged.
  */
    public Card pop() {
        return theStack.remove(theStack.size()-1);
    }

/** Pre:  The stack is not empty (i.e. theStack.size()>0).
  * Post: The value returned is the top card in the stack,
  *       and the whole of the stack remains unchanged.
  */
    public Card peek() {
        return theStack.get(theStack.size()-1);
    }


}