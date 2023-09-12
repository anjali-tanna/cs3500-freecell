import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.Suit;
import cs3500.freecell.model.hw02.Value;
import org.junit.Before;
import org.junit.Test;

/**
 * The class for testiing the Card methods.
 */
public class CardTest {

  private Card card1;
  private Card card2;
  private Card card3;

  @Before
  public void initializeData() {
    card1 = new Card(Suit.DIAMOND, Value.FIVE);
    card2 = new Card(Suit.SPADE, Value.TWO);
    card3 = new Card(Suit.HEART, Value.SEVEN);
  }

  // test for toString
  @Test
  public void testToString() {
    initializeData();
    assertEquals("5♦", card1.toString());
    assertEquals("2♠", card2.toString());
    assertEquals("7♥", card3.toString());
  }

  // test for getSuit
  @Test
  public void testGetSuit() {
    initializeData();
    assertEquals(Suit.DIAMOND, card1.getSuit());
    assertEquals(Suit.SPADE, card2.getSuit());
    assertEquals(Suit.HEART, card3.getSuit());
  }

  // test for getValue
  @Test
  public void testGetValue() {
    initializeData();
    assertEquals(Value.FIVE, card1.getValue());
    assertEquals(Value.TWO, card2.getValue());
    assertEquals(Value.SEVEN, card3.getValue());
  }

  // test for cardColor
  @Test
  public void testCardColor() {
    initializeData();
    assertEquals("Red", card1.cardColor());
    assertEquals("Black", card2.cardColor());
    assertEquals("Red", card3.cardColor());
  }

  // test for equals
  @Test
  public void testEquals() {
    assertEquals(true, card1.equals(card1));
    assertEquals(true, card2.equals(card2));
    assertNotEquals(true, card2.equals(card3));
    assertNotEquals(true, card3.equals(card1));
  }
}
