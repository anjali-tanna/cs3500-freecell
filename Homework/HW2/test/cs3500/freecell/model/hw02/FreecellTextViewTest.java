package cs3500.freecell.model.hw02;

import cs3500.freecell.view.FreecellTextView;
import java.util.Arrays;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

/** Test class for FreecellTextView. */
public class FreecellTextViewTest {
  private SimpleFreecellModel freecellModel;

  Card h1 = new Card(Value.ACE, Suit.HEARTS);
  Card h2 = new Card(Value.TWO, Suit.HEARTS);
  Card h3 = new Card(Value.THREE, Suit.HEARTS);
  Card h4 = new Card(Value.FOUR, Suit.HEARTS);
  Card h5 = new Card(Value.FIVE, Suit.HEARTS);
  Card h6 = new Card(Value.SIX, Suit.HEARTS);
  Card h7 = new Card(Value.SEVEN, Suit.HEARTS);
  Card h8 = new Card(Value.EIGHT, Suit.HEARTS);
  Card h9 = new Card(Value.NINE, Suit.HEARTS);
  Card h10 = new Card(Value.TEN, Suit.HEARTS);
  Card h11 = new Card(Value.JACK, Suit.HEARTS);
  Card h12 = new Card(Value.QUEEN, Suit.HEARTS);
  Card h13 = new Card(Value.KING, Suit.HEARTS);

  Card c1 = new Card(Value.ACE, Suit.CLUBS);
  Card c2 = new Card(Value.TWO, Suit.CLUBS);
  Card c3 = new Card(Value.THREE, Suit.CLUBS);
  Card c4 = new Card(Value.FOUR, Suit.CLUBS);
  Card c5 = new Card(Value.FIVE, Suit.CLUBS);
  Card c6 = new Card(Value.SIX, Suit.CLUBS);
  Card c7 = new Card(Value.SEVEN, Suit.CLUBS);
  Card c8 = new Card(Value.EIGHT, Suit.CLUBS);
  Card c9 = new Card(Value.NINE, Suit.CLUBS);
  Card c10 = new Card(Value.TEN, Suit.CLUBS);
  Card c11 = new Card(Value.JACK, Suit.CLUBS);
  Card c12 = new Card(Value.QUEEN, Suit.CLUBS);
  Card c13 = new Card(Value.KING, Suit.CLUBS);

  Card d1 = new Card(Value.ACE, Suit.DIAMOND);
  Card d2 = new Card(Value.TWO, Suit.DIAMOND);
  Card d3 = new Card(Value.THREE, Suit.DIAMOND);
  Card d4 = new Card(Value.FOUR, Suit.DIAMOND);
  Card d5 = new Card(Value.FIVE, Suit.DIAMOND);
  Card d6 = new Card(Value.SIX, Suit.DIAMOND);
  Card d7 = new Card(Value.SEVEN, Suit.DIAMOND);
  Card d8 = new Card(Value.EIGHT, Suit.DIAMOND);
  Card d9 = new Card(Value.NINE, Suit.DIAMOND);
  Card d10 = new Card(Value.TEN, Suit.DIAMOND);
  Card d11 = new Card(Value.JACK, Suit.DIAMOND);
  Card d12 = new Card(Value.QUEEN, Suit.DIAMOND);
  Card d13 = new Card(Value.KING, Suit.DIAMOND);

  Card s1 = new Card(Value.ACE, Suit.SPADES);
  Card s2 = new Card(Value.TWO, Suit.SPADES);
  Card s3 = new Card(Value.THREE, Suit.SPADES);
  Card s4 = new Card(Value.FOUR, Suit.SPADES);
  Card s5 = new Card(Value.FIVE, Suit.SPADES);
  Card s6 = new Card(Value.SIX, Suit.SPADES);
  Card s7 = new Card(Value.SEVEN, Suit.SPADES);
  Card s8 = new Card(Value.EIGHT, Suit.SPADES);
  Card s9 = new Card(Value.NINE, Suit.SPADES);
  Card s10 = new Card(Value.TEN, Suit.SPADES);
  Card s11 = new Card(Value.JACK, Suit.SPADES);
  Card s12 = new Card(Value.QUEEN, Suit.SPADES);
  Card s13 = new Card(Value.KING, Suit.SPADES);

  List<Card> deckFull =
      Arrays.asList(
          h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, h12, h13, c1, c2, c3, c4, c5, c6, c7, c8,
          c9, c10, c11, c12, c13, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, s1, s2,
          s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13);

  @Test
  public void toStringTest() {
    freecellModel.startGame(freecellModel.getDeck(), 8, 4, false);
    assertEquals(
        "F1:\nF2:\nF3:\nF4:\n"
            + "O1:\nO2:\nO3:\nO4:\n"
            + "C1: A♣, 3♣, 5♣, 7♣, 9♣, J♣, K♣\n"
            + "C2: A♦, 3♦, 5♦, 7♦, 9♦, J♦, K♦\n"
            + "C3: A♥, 3♥, 5♥, 7♥, 9♥, J♥, K♥\n"
            + "C4: A♠, 3♠, 5♠, 7♠, 9♠, J♠, K♠\n"
            + "C5: 2♣, 4♣, 6♣, 8♣, 10♣, Q♣\n"
            + "C6: 2♦, 4♦, 6♦, 8♦, 10♦, Q♦\n"
            + "C7: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n"
            + "C8: 2♠, 4♠, 6♠, 8♠, 10♠, Q♠",
        freecellModel.toString());
  }

  @Test
  public void testToStringNew() {
    freecellModel.startGame(deckFull, 6, 4, false);
    FreecellTextView testTextView = new FreecellTextView(freecellModel);
    assertEquals(
        "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: 1♦ 9♦ 4♣ 12♣ 7♥ 2♠ 10♠\n"
            + "C2: 2♦ 10♦ 5♣ 13♣ 8♥ 3♠ 11♠\n"
            + "C3: 3♦ 11♦ 6♣ 1♥ 9♥ 4♠ 12♠\n"
            + "C4: 4♦ 12♦ 7♣ 2♥ 10♥ 5♠ 13♠\n"
            + "C5: 5♦ 13♦ 8♣ 3♥ 11♥ 6♠\n"
            + "C6: 6♦ 1♣ 9♣ 4♥ 12♥ 7♠\n"
            + "C7: 7♦ 2♣ 10♣ 5♥ 13♥ 8♠\n"
            + "C8: 8♦ 3♣ 11♣ 6♥ 1♠ 9♠",
        testTextView.toString());
  }
}
