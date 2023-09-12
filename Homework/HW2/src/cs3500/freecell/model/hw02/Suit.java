package cs3500.freecell.model.hw02;

/**
 * The four types of Suits in a game of Freecell.
 *
 * <p>CLUBS("♣") DIAMOND("♦") HEARTS("♥") SPADES("♠")
 */
public enum Suit {
  CLUBS("♣"),
  DIAMOND("♦"),
  HEARTS("♥"),
  SPADES("♠");
  private final String suit;

  Suit(String suit) {
    this.suit = suit;
  }

  public String getSuit() {
    return this.suit;
  }

  /**
   * Gets the Suit of a card as a String.
   *
   * @return the Suit of a Card
   */
  public String toString() {
    switch (this) {
      case CLUBS:
        return "♣";
      case DIAMOND:
        return "♦";
      case HEARTS:
        return "♥";
      case SPADES:
        return "♠";
      default:
        throw new AssertionError("Unknown operations " + this);
    }
  }
}
