package cs3500.freecell.model.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Test class for Card. */
public class CardTest {

  Card heart5 = new Card(Value.FIVE, Suit.HEARTS);
  Card spade11 = new Card(Value.JACK, Suit.SPADES);
  Card diamond3 = new Card(Value.THREE, Suit.DIAMOND);
  Card club13 = new Card(Value.KING, Suit.CLUBS);
  Card heart1 = new Card(Value.ACE, Suit.HEARTS);
  Card club4 = new Card(Value.FOUR, Suit.CLUBS);
  Card diamond12 = new Card(Value.QUEEN, Suit.DIAMOND);
  Card spade8 = new Card(Value.EIGHT, Suit.SPADES);

  // Tests for the constructor
  @Test
  public void constructorValueTest() {
    assertEquals(Value.FIVE, heart5.value);
  }

  @Test
  public void constructorSuitTest() {
    assertEquals(Suit.SPADES, spade8.suit);
  }

  // Tests for the getValue() method
  @Test
  public void getAceValueTest() {
    assertEquals(Value.ACE, heart1.getValue());
  }

  @Test
  public void getThreeValueTest() {
    assertEquals(Value.THREE, diamond3.getValue());
  }

  @Test
  public void getKingValueTest() {
    assertEquals(Value.KING, club13.getValue());
  }

  // Tests for the getSuit() method
  @Test
  public void getClubSuitTest() {
    assertEquals(Suit.CLUBS, club4.getSuit());
  }

  @Test
  public void getDiamondSuitTest() {
    assertEquals(Suit.DIAMOND, diamond12.getSuit());
  }

  @Test
  public void getSpadeSuitTest() {
    assertEquals(Suit.SPADES, spade11.getSuit());
  }

  @Test
  public void getHeartSuitTest() {
    assertEquals(Suit.HEARTS, heart5.getSuit());
  }

  // Tests for the toString() method
  @Test
  public void toStringHeart() {
    assertEquals("A♥", heart1.toString());
  }

  @Test
  public void toStringSpade() {
    assertEquals("8♠", spade8.toString());
  }

  @Test
  public void toStringDiamond() {
    assertEquals("Q♦", diamond12.toString());
  }

  @Test
  public void toStringClub() {
    assertEquals("4♣", club4.toString());
  }

  // Tests for the getColor() method
  @Test
  public void getColorRed() {
    assertEquals("Red", heart1.getColor());
  }

  @Test
  public void getColorBlack() {
    assertEquals("Black", club4.getColor());
  }
}
