package cs3500.freecell.model.hw02;

/**
 * This represents an enumeration of a Card, for a Suit. A card can have a suit of diamond, club,
 * spade, or heart. Each of them has their own symbol. The two suits that are red are the diamonds
 * and the hearts, and the black suits are clubs and spades.
 */
public enum Suit {
  DIAMOND("♦"), CLUB("♣"), SPADE("♠"), HEART("♥");
  private final String symbol;

  /**
   * Constructs the Suit, which is a symbol (out of 4) in a deck of cards.
   *
   * @param symbol represents one of the 4 possible symbols of a Card
   */
  Suit(String symbol) {
    this.symbol = symbol;
  }

  @Override
  public String toString() {
    return this.symbol;
  }
}
