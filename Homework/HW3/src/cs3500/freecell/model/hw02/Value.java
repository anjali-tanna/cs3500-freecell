package cs3500.freecell.model.hw02;

/**
 * The 13 Values in a game of Freecell.
 *
 * <p>ACE(1) TWO(2) THREE(3) FOUR(4) FIVE(5) SIX(6) SEVEN(7) EIGHT(8) NINE(9) TEN(10), JACK(11)
 * QUEEN(12) KING(13);
 */
public enum Value {
  ACE(1),
  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5),
  SIX(6),
  SEVEN(7),
  EIGHT(8),
  NINE(9),
  TEN(10),
  JACK(11),
  QUEEN(12),
  KING(13);
  protected int value;

  Value(int value) {
    if (value < 1 || value > 13) {
      throw new IllegalArgumentException("Card value must be between 1-13");
    }

    this.value = value;
  }

  /**
   * Returns the Value of a Card.
   *
   * @return Value of card
   */
  public int getInt() {
    return this.value;
  }

  @Override
  public String toString() {
    switch (this) {
      case ACE:
        return "A";
      case TWO:
        return "2";
      case THREE:
        return "3";
      case FOUR:
        return "4";
      case FIVE:
        return "5";
      case SIX:
        return "6";
      case SEVEN:
        return "7";
      case EIGHT:
        return "8";
      case NINE:
        return "9";
      case TEN:
        return "10";
      case JACK:
        return "J";
      case QUEEN:
        return "Q";
      case KING:
        return "K";

      default:
        throw new AssertionError("Unknown" + this);
    }
  }
}
