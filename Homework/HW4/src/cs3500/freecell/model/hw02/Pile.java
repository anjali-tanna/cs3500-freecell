package cs3500.freecell.model.hw02;

import java.util.List;
import java.util.ArrayList;
import cs3500.freecell.model.PileType;

/**
 * Represents a Pile which consists of a type (Open, Foundation, or Cascade) and list of cards in
 * that pile.
 */
public class Pile {

  /**
   * Constructs a Pile.
   *
   * @param pileType represents the PileType
   * @param cardList represents the list of cards
   */
  public Pile(PileType pileType, ArrayList<Card> cardList) {
    this.pileType = pileType;
    this.cardList = cardList;
  }

  private final PileType pileType;
  private List<Card> cardList;

  /**
   * Gets the PileType of the given Pile.
   *
   * @return either CASCADE, OPEN, or FOUNDATION, depending on the Pile's type
   */
  public PileType getPileType() {
    return this.pileType;
  }

  /**
   * Gets the list of Cards of the given Pile.
   *
   * @return the list of Cards in the given Pile
   */
  public List<Card> getCardList() {
    return this.cardList;
  }

  /**
   * Returns the number of cards in the given Pile.
   *
   * @return int of the number of cards in the Pile
   */
  public int size() {
    return this.cardList.size();
  }

  /**
   * Adds a Card to the Pile.
   *
   * @param c Represents a card
   */
  public void addCard(Card c) {
    this.cardList.add(c);
  }

  /**
   * A method that removes a given card from the Cards.
   *
   * @param card Represents a Card
   */
  public void remove(Card card) {
    this.cardList.remove(card);
  }

  /**
   * Determines if the move is valid and the game rules.
   *
   * @param source Represents the Source Pile
   * @param action represents the Card to Move
   * @return a boolean that is determined by whether or not a move is valid.
   */
  public boolean getRules(Pile source, Card action) {
    boolean emptyDest = this.cardList.isEmpty();

    if (source.getPileType() == PileType.FOUNDATION
        || (this.pileType == PileType.OPEN && !emptyDest)) {
      return false;
    }

    if (this.pileType == PileType.FOUNDATION) {
      if (!emptyDest) {
        Card dest = this.cardList.get(this.cardList.size() - 1);
        boolean suitMatch = dest.getSuit() == action.getSuit();
        boolean onePlus = dest.getValue().getInt() + 1 == action.getValue().getInt();
        return suitMatch && onePlus;
      } else {
        boolean ace = action.getValue().getInt() == 1;
        return ace;
      }
    }

    if (this.pileType == PileType.CASCADE && !emptyDest) {
      Card destCard = this.cardList.get(this.cardList.size() - 1);
      boolean colorNotMatch = !destCard.getColor().equals(action.getColor());
      boolean less = destCard.getValue().getInt() == action.getValue().getInt() + 1;
      return colorNotMatch && less;
    }

    return true;
  }
}
