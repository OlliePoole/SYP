// Do NOT change the code in this file.
//
// However, you can read through the code in this file to understand
// what it does. You might need to make use of some
// of the methods, variables and/or constants of this class.


/** An instance of this class is an object representing a standard
  * playing card placed on a playing surface in a game of Patience.
  * The card has a suit (one of CLUBS, DIAMONDS, HEARTS or SPADES),
  * a rank (one of ACE, 2, 3, ... 10, JACK, QUEEN, KING).
  * The card can be displayed face-up or face-down.
  *
  * The class also has some standard "getter" and "setter" methods.
  */
public class Card {

   // some handy constants to denote suits
   public static final int CLUBS = 0;
   public static final int DIAMONDS = 1;
   public static final int HEARTS = 2;
   public static final int SPADES = 3;

   // some handy constants to denote ranks other than 2-10
   public static final int ACE = 1;
   public static final int JACK = 11;
   public static final int QUEEN = 12;
   public static final int KING = 13;

   private int rank;  // Each card has a rank
                      // (1=Ace, 2, 3 .. 10, 11=Jack, 12=Queen, 13=King)

   private int suit;  // Each card has a suit
                      // (should be either CLUBS or DIAMONDS or HEARTS or SPADES)

   private boolean faceUp; // Each card is either displayed face up or face down
                           // This specifies whether the card lies face-up or not.

/** Constructs a card from the given suit, rank and a boolean depicting whether
  * the card is face up or not.
  * Datatype invariant: The suit of a valid card is in the range CLUBS to SPADES,
  * and the range of the card must be from 1 to 13 (ACE to KING).
  */
   public Card(int rank, int suit, boolean faceUp) {
       if ((CLUBS<=suit) && (suit<=SPADES) && (ACE<=rank) && (rank<=KING)) {
           this.rank = rank;
           this.suit = suit;
           this.faceUp = faceUp;
       }
   }

/** Constructs a card from the given suit and rank, displayed face-up.
  * The suit of a valid card must be in the range CLUBS to SPADES,
  * and the rank of the card must be in the range 1 (ACE) to 13 (KING).
  */
   public Card(int rank, int suit) {
       if ((CLUBS<=suit) && (suit<=SPADES) && (ACE<=rank) && (rank<=KING)) {
           this.rank = rank;
           this.suit = suit;
           faceUp = true;
       }
   }

/** Tests whether the suit and rank of this card are equal to that of
  * another card.
  */
   public boolean equals(Card c) {
       return (this.rank == c.rank) && (this.suit==c.suit);
   }

/** A "getter" method that simply returns the boolean specifying whether
  * this card is face up or not.
  */
   public boolean isFaceUp() {
      return faceUp;
   }

/** A "setter" method that simply sets the boolean specifying whether
  * this card is face up or not.
  */
   public void setFaceUp(boolean faceUp) {
      this.faceUp = faceUp;
   }

/** A "getter" method that simply returns the suit of this card, either
  * CLUBS, HEARTS, DIAMONDS or SPADES.
  */
   public int getSuit() {
      return suit;
   } // (there is no "setter" method for suit as the suit of a card is fixed)

/** A "getter" method that simply returns the rank of this card.
  * The integer representing the rank of an Ace is 1; the integers 2 to 10 represent
  * the ranks of 2 up to 10; the rank of a Jack is 11, the rank of a Queen is 12;
  * the rank of a King is 13.
  */
   public int getRank() {
      return rank;
   } // (there is no "setter" method for rank as the rank of a card is fixed)

/** A "getter" method that simply returns the colour of this playing card.
  */
   public String getColour() {
        if ((suit==Card.HEARTS)
            || (suit==Card.DIAMONDS))
            return "red";
        else
            return "black";
   }

}