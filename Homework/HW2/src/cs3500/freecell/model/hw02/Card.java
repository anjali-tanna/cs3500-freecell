package cs3500.freecell.model.hw02;

import java.util.Objects;

/** Represents a single card from the deck. */
public class Card {

  protected final Value value;
  protected final Suit suit;

  public Card(Value value, Suit suit) throws NullPointerException {
    this.value = Objects.requireNonNull(value);
    this.suit = Objects.requireNonNull(suit);
  }

  /**
   * A method that returns the Value of a Card.
   *
   * @return the Value of the Card (int)
   */
  public Value getValue() {
    return this.value;
  }

  /**
   * A method that returns the Suit of a Card.
   *
   * @return the Suit of the Card (Hearts, Clubs, Diamonds, or Spades)
   */
  public Suit getSuit() {
    return this.suit;
  }

  /**
   * A method that returns the Card as a String.
   *
   * @return the Card as a string
   */
  @Override
  public String toString() {
    return this.value.toString() + this.suit.toString();
  }

  /**
   * A method that determines the color of a Card.
   *
   * @return the Color of the Card (red or black)
   */
  public String getColor() {
    if (this.suit == Suit.SPADES || this.suit == Suit.CLUBS) {
      return "Black";
    } else {
      return "Red";
    }
  }

  /**
   * Determines if a Card is valid.
   *
   * @return a valid card or not
   */
  public boolean validCard() {
    boolean validValue = false;
    for (Value val : Value.values()) {
      if (this.value.equals(val)) {
        validValue = true;
        break;
      }
    }
    boolean validSuit = false;
    for (Suit suit : Suit.values()) {
      if (this.suit.equals(suit)) {
        validSuit = true;
        break;
      }
    }
    return validSuit && validValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (!(o instanceof Card)) {
      return false;
    } else {
      Card card = (Card) o;
      return (this.suit == card.suit) && (this.value == card.value);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.value) + Objects.hash(this.suit);
  }
}
