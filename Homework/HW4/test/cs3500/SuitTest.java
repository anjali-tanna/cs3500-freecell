package cs3500;

import cs3500.freecell.model.hw02.Suit;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** A class to test suit. */
public class SuitTest {

  /** test for the string of Suit.HEART. */
  @Test
  public void getHeartSuitTest() {
    Assert.assertEquals("♥", Suit.HEARTS.getSuit());
  }

  /** test for the string of Suit.DIAMOND. */
  @Test
  public void getDiamondSuitTest() {
    assertEquals("♦", Suit.DIAMOND.getSuit());
  }

  /** test for the string of Suit.SPADE. */
  @Test
  public void getSpadeSuitTest() {
    assertEquals("♠", Suit.SPADES.getSuit());
  }

  /** test for the string of Suit.CLUB. */
  @Test
  public void getClubSuitTest() {
    assertEquals("♣", Suit.CLUBS.getSuit());
  }
}
