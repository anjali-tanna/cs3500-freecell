package cs3500;

import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw02.Suit;
import cs3500.freecell.model.hw02.Value;
import cs3500.freecell.view.FreecellTextView;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Test class for FreecellTextView. */
public class FreecellTextViewTest {

  private SimpleFreecellModel freecellModel;
  Appendable ap = new StringBuffer();
  SimpleFreecellModel model = new SimpleFreecellModel();
  FreecellTextView text = new FreecellTextView(model, ap);
  String expected =
      "F1:\n"
          + "F2:\n"
          + "F3:\n"
          + "F4:\n"
          + "O1:\n"
          + "O2:\n"
          + "O3:\n"
          + "O4:\n"
          + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
          + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
          + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
          + "C4: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦";

  // Setup method to initialize a clean model
  private void setup() {
    this.freecellModel = new SimpleFreecellModel();
  }

  // Generates a deck used for testing purposes. When the cards are dealt
  // into the 4 cascade piles, they are in descending order and sorted by color.
  // The deck has 4 piles in the order SPADES, CLUBS, HEARTS, and DIAMOND.
  private List<Card> generateTestDeck() {
    List<Card> deck = new ArrayList<>();
    for (int i = 12; i >= 0; i--) {
      Value value = Value.values()[i];
      for (int j = 0; j < 4; j++) {
        Suit suit = Suit.values()[j];
        Card current = new Card(value, suit);
        deck.add(current);
      }
    }
    return deck;
  }

  // The game state should be formatted correctly after the game has begun
  @Test
  public void toStringTest() {
    setup();
    freecellModel.startGame(generateTestDeck(), 4, 4, false);
    assertEquals(expected, freecellModel.toString());
  }

  // Tests the renderBoard method for when a game has not started
  @Test
  public void beforeGameTest() throws IOException {
    freecellModel.startGame(freecellModel.getDeck(), 4, 4, false);
    freecellModel.toString();
    text.renderBoard();
    assertEquals("", ap.toString());
  }

  // Tests the renderBoard method for when a has not started but not shuffled
  @Test
  public void gameStartedNoShuffleTest() throws IOException {
    freecellModel.startGame(freecellModel.getDeck(), 4, 4, false);
    freecellModel.toString();
    text.renderBoard();
    assertEquals(expected, ap.toString());
  }

  // Tests the renderBoard method for when a has not started and shuffled
  @Test
  public void gameStartedShuffleTest() throws IOException {
    freecellModel.startGame(freecellModel.getDeck(), 4, 4, true);
    freecellModel.toString();
    text.renderBoard();
    assertEquals(generateTestDeck(), ap.toString());
  }
}
