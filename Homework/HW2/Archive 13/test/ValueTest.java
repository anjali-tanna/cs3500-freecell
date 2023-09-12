import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.Suit;
import cs3500.freecell.model.hw02.Value;
import org.junit.Before;
import org.junit.Test;

/**
 * A class for the Value tests.
 */
public class ValueTest {

  private Card card1;
  private Card card2;
  private Card card3;
  private Card card4;
  private Card card5;

  @Before
  public void initializeData() {
    card1 = new Card(Suit.DIAMOND, Value.FIVE);
    card2 = new Card(Suit.SPADE, Value.TWO);
    card3 = new Card(Suit.HEART, Value.SEVEN);
    card4 = new Card(Suit.DIAMOND, Value.JACK);
    card5 = new Card(Suit.HEART, Value.QUEEN);
  }

  // test for toString
  @Test
  public void testToString() {
    initializeData();
    assertEquals("5", card1.getValue().toString());
    assertEquals("2", card2.getValue().toString());
    assertEquals("7", card3.getValue().toString());
    assertEquals("J", card4.getValue().toString());
    assertEquals("Q", card5.getValue().toString());
  }

  // test for getInteger
  @Test
  public void testGetInteger() {
    initializeData();
    assertEquals(5, card1.getValue().getInteger());
    assertEquals(2, card2.getValue().getInteger());
    assertEquals(7, card3.getValue().getInteger());
    assertEquals(11, card4.getValue().getInteger());
    assertEquals(12, card5.getValue().getInteger());
  }
}
