package cs3500.freecell.model.hw02;

/**
 * One of the 13 different card numbers/signs.
 * */
public enum Value {
  ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5),
  SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
  JACK(11), QUEEN(12), KING(13);

  private final int cardNum;

  /**
   * Constructs the Value of the card which is one of the 13 different card numbers/signs.
   *
   * @param cardNum represents the card number/sign of the Card
   */
  Value(int cardNum) {
    this.cardNum = cardNum;
  }

  @Override
  public String toString() {
    if (this.cardNum < 11) {
      return Integer.toString(this.cardNum);
    }
    if (this.cardNum == 11) {
      return "J";
    }
    if (this.cardNum == 12) {
      return "Q";
    }
    else {
      return "K";
    }

  }

  /**
   * A method that gets the card number in integer form.
   * @return the card number as an integer
   */
  public int getInteger() {

    return this.cardNum;
  }
}
