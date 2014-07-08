// You are to complete the methods in this file with empty bodies.
//
// Don't remove or change any code that is already in this file.
// Don't add any more global variables to this class.
// You can, however, add more methods to this class, if that helps.

import java.util.*;

/**
 * This class implements the game rules, providing methods to deal the cards and
 * move cards according to the rules of Fortress Patience.
 */
public class Rules {

    /**
     * the four foundation piles
     */
    public CardStack[] foundations = new CardStack[4];

    /**
     * the ten rows, where each row is a stack of cards
     */
    public CardStack[] rows = new CardStack[10];

    /**
     * Constructs a new Rules object, and redeals all the cards ready for a game
     * of Fortress Patience.
     */
    public Rules() {
        redeal(); // this method (implemented below) will
        // shuffle and deal the cards
    }

    /**
     * This method is called when the user tries to move the uppermost card of
     * the r-th row, to the f-th foundation pile.
     *
     * Pre: 0<=r<10 (i.e. the index of the row is within range) && 0<=f<4 (i.e.
     * the index of the foundation is within range) && !rows[r].isEmpty() (i.e.
     * there is at least one card in the row)
     *
     * Post: If the rules permit, the uppermost card of the r-th row been moved
     * onto the f-th foundation pile, leaving all other cards where they were.
     * If the rules do not permit this move, all cards are left exactly where
     * they were before.
     */
    public void moveRowCardToFoundationIfPossible(int r, int f) {

        boolean suitCheck = true;

        if (!(foundations[f].isEmpty()))
        {
            Card card1 = foundations[f].peek();

            if (card1.getSuit() == rows[r].peek().getSuit())
            {
                for (int x = 0; x < 4; x++)
                {
                    if (!(foundations[x].isEmpty()))
                    {
                        if (card1.getSuit() == foundations[x].peek().getSuit() && x != f)
                        {
                            suitCheck = false;
                        }
                    }

                }
                if (suitCheck)
                {
                    if (foundations[f].peek().getRank() - rows[r].peek().getRank() == -1 || foundations[f].peek().getRank() - rows[r].peek().getRank() == 12)
                    {
                        foundations[f].push(rows[r].pop());
                    }
                    
                }

            }

        } 
        else
        {
            for (int x = 0; x < 4; x++)
            {
                if (!(foundations[x].isEmpty()))
                {

                    if (rows[r].peek().getSuit() == foundations[x].peek().getSuit() && x != f)
                    {
                        suitCheck = false;
                    }
                }

            }
            if (suitCheck)
            {
                foundations[f].push(rows[r].pop());
            }

        }

    }

    /**
     * This method is called when the user tries to move the uppermost card of
     * the r1-th row, onto the uppermost end of the r2-th row.
     *
     * Pre: 0<=r1<10 (i.e. the index of the source row is within range) &&
     * 0<=r2<10 (i.e. the index of the destination row is within range)
     *
     * Post: If the rules permit, the uppermost card of the r1-th row has been
     * moved onto the uppermost end of the r2-th row, leaving all other cards
     * where they were. If the rules do not permit this move, all cards are left
     * exactly where they were before.
     *
     */
    public void moveRowCardToRowIfPossible(int r1, int r2) {

        if (!(rows[r1].isEmpty()) && (!(rows[r2].isEmpty()))) 
        {
            Card card1 = rows[r1].peek();
            Card card2 = rows[r2].peek();

            if (card1.getColour().equals(card2.getColour()))
            {
                if (card1.getRank() - card2.getRank() == -1 || card1.getRank() - card2.getRank() == 1)
                {
                    //Same colour and within 1 rank
                    rows[r2].push(rows[r1].pop());
                }

            }
        } else
        {
            rows[r2].push(rows[r1].pop());
        }

    }

    /**
     * Shuffles and redeals the cards.
     */
    public void redeal() {
        clearAllCards(); // clears the playing area of all cards
        dealCards();     // deals the cards according to the initial
        // layout for this game of Patience
    }

    /**
     * Clears the area of all cards.
     */
    public void clearAllCards() {

        // the four foundation piles are initially all empty
        for (int f = 0; f < foundations.length; f++) {
            foundations[f] = new CardStack();
        }

        // the ten rows are made empty
        // (the cards will be dealt into them later)
        for (int row = 0; row < rows.length; row++) {
            rows[row] = new CardStack();
        }
    }

    private void dealCards() {

        // First the cards need to be shuffled. To do this, we
        // now obtain a random permutation of the numbers 0..51
        int[] shuffledNumbers = randomPackShuffle();

        int cardIndex = 0;  // refers to the next card to be dealt
        int rowIndex = 0;   // refers to the next row to put a card on

        // The cards are simply dealt one by one on the rows. This
        // leaves rows 0 and 1 with six cards each, and the other 8 rows
        // with five cards each:
        while (cardIndex < 52) {
            rows[rowIndex].push(
                    new Card(1 + shuffledNumbers[cardIndex] / 4, // a number in the range 1-13 for rank
                            shuffledNumbers[cardIndex] % 4));  // a number in the range 0-3 for suit
            cardIndex++;
            rowIndex = (rowIndex + 1) % 10;
        }
    }

    private int[] randomPackShuffle() {
    // produces an array containing the numbers 0-51, in a random order

        // first set up the contents of the array as 0-51, in that order
        int[] cardNumbers = new int[52];
        for (int c = 0; c < cardNumbers.length; c++) {
            cardNumbers[c] = c;
        }

        // now to do the random shuffle
        int index = cardNumbers.length - 1;
        int selected, tmp;
        while (index > 0) {

            // pick a random position from 0 up to the index position
            selected = (int) (Math.random() * (index + 1));

            // swaps the number at the selected position into the index position
            tmp = cardNumbers[index];
            cardNumbers[index] = cardNumbers[selected];
            cardNumbers[selected] = tmp;

            // having fixed the element at the index position, we now move left
            index--;
        }
        return cardNumbers;
    }

}
