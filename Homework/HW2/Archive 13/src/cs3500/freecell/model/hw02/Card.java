package cs3500.freecell.model.hw02;

import java.util.Objects;

/**
 * A Class that epresents a Card class.
 */
public class Card {

  public final Suit suit;
  public final Value value;

  /**
   * The Constructor for the Card.
   *
   * @param suit  represens a symbol (out of 4) in a deck of cards
   * @param value represents the card number/sign of the Card
   * @throws NullPointerException so that values cannot be null
   */
  public Card(Suit suit, Value value) throws NullPointerException {
    this.suit = Objects.requireNonNull(suit);
    this.value = Objects.requireNonNull(value);
  }

  @Override
  public String toString() {
    return this.value.toString() + this.suit;
  }

  /**
   * A method that returns the Suit of the card.
   *
   * @return the suit of the card.
   */
  public Suit getSuit() {

    return this.suit;
  }

  /**
   * A method that returns the Value of the card.
   *
   * @return the value of the card.
   */
  public Value getValue() {
    return this.value;
  }

  /**
   * A method that determines the color of a Card.
   *
   * @return the Color of the Card (red or black)
   */
  public String cardColor() {
    if (this.suit == Suit.DIAMOND
        || this.suit == Suit.HEART) {
      return "Red";
    } else {
      return "Black";
    }
  }

  /**
   * A method that determines if a card is valid.
   *
   * @return whether a card is a valid card or not
   */
  public boolean cardValidation() {
    boolean valueValid = false;
    for (Value cardValue : Value.values()) {
      if (this.value.equals(cardValue)) {
        valueValid = true;
        break;
      }
    }
    boolean suitValid = false;
    for (Suit cardSuit : Suit.values()) {
      if (this.suit.equals(cardSuit)) {
        suitValid = true;
        break;
      }
    }
    return valueValid && suitValid;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (!(obj instanceof Card)) {
      return false;
    } else {
      Card card2 = (Card) obj;
      return (this.suit == card2.suit) && (this.value == card2.value);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.suit) + Objects.hash(this.value);
  }
}




