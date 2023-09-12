import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.Suit;
import cs3500.freecell.model.hw02.Value;
import org.junit.Before;
import org.junit.Test;

/**
 * A class for the Suit tests.
 */
public class SuitTest {

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
    assertEquals("♦", card1.getSuit().toString());
    assertEquals("♠", card2.getSuit().toString());
    assertEquals("♥", card3.getSuit().toString());
  }
}
