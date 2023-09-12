package cs3500.freecell.model.hw02;

import cs3500.freecell.model.PileType;
import java.util.ArrayList;

/**
 * Represents a Pile, which consists of a PileType and the Cards (Arraylist).
 */
public class Pile {

  /**
   * Constructs a Pile.
   * @param pileType represents which (1 out of 3) type of pile the Pile is
   * @param cards represents the cards in the pile
   */
  public Pile(PileType pileType, ArrayList<Card> cards) {
    this.pileType = pileType;
    this.cards = cards;
  }

  private final PileType pileType;
  private final ArrayList<Card> cards;

  /**
   * A method that returns the cards in the ArrayList of Cards.
   */
  public ArrayList<Card> getCards() {
    return this.cards;
  }

  /**
   * A method that determines the pileType of a pile.
   * @return the pileType of the Pile
   */
  public PileType getPileType() {
    return this.pileType;
  }

  public int size() {
    return this.cards.size();
  }

  /**
   * A method that removes a given card from the Cards.
   * @param card Represents a Card
   */
  public void remove(Card card) {
    this.cards.remove(card);
  }

  /**
   * A method that adds a given card to the Cards.
   * @param card Represents a Card
   */
  public void add(Card card) {
    this.cards.add(card);
  }

  /**
   * A method that determines if a move is valid within the game rules
   * Self-note: Helper method for the method move.
   * @param sourcePile represents the original pile
   * @param movingCard represents the moving card
   * @returna whether the move is valid or not based on the game rules
   */
  public boolean gameRules(Pile sourcePile, Card movingCard) {
    boolean emptyDestination = this.cards.isEmpty();
    if (sourcePile.getPileType() == PileType.FOUNDATION
        || (this.pileType == PileType.OPEN && !emptyDestination)) {
      return false;
    }
    if (this.pileType == PileType.FOUNDATION) {
      if (!emptyDestination) {
        Card destinationCard = this.cards.get(this.cards.size() - 1);
        boolean isValueOneGreater = destinationCard.getValue().getInteger() + 1
            == movingCard.getValue().getInteger();
        boolean isSuitSame = destinationCard.getSuit() == movingCard.getSuit();
        return isSuitSame && isValueOneGreater;
      }
      else {
        boolean isCardAce = movingCard.getValue().getInteger() == 1;
        return isCardAce;
      }
    }
    if (this.pileType == PileType.CASCADE && !emptyDestination) {
      Card destinationCard = this.cards.get(this.cards.size() - 1);
      boolean isValueLess = destinationCard.getValue().getInteger()
          == movingCard.getValue().getInteger() + 1;
      boolean isColorSame = destinationCard.cardColor().equals(movingCard.cardColor());
      return isValueLess && isColorSame;
    }
    return true;
  }
}
