package cs3500.freecell.model.hw02;

import java.util.Objects;

/**
 * Represents a single card from the deck, with a single Value (Ace through King) and a single Suit
 * (Spade, Club, Heart, Diamond).
 */
public class Card {

  public final Value value;
  public final Suit suit;

  /**
   * Constructs a Card.
   *
   * @param value represents the Value (Ace through King)
   * @param suit represents the Suit (Spade, Club, Heart, Diamond)
   */
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

  /**
   * Makes sure cards next to each other alternate in color.
   *
   * @param last a previous card from the same pile
   * @return true if this card and the given card have different color suits, false otherwise
   */
  public boolean alternate(Card last) {
    switch (suit) {
      case HEARTS:
        return ((last.suit == Suit.CLUBS || last.suit == Suit.SPADES)
            && this.value.getInt() == last.value.getInt() - 1);
      case DIAMOND:
        return ((last.suit == Suit.CLUBS || last.suit == Suit.SPADES)
            && this.value.getInt() == last.value.getInt() - 1);
      case CLUBS:
        return ((last.suit == Suit.HEARTS || last.suit == Suit.DIAMOND)
            && this.value.getInt() == last.value.getInt() - 1);
      case SPADES:
        return ((last.suit == Suit.HEARTS || last.suit == Suit.DIAMOND)
            && this.value.getInt() == last.value.getInt() - 1);
      default:
        return false;
    }
  }
}
