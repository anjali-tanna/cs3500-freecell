package cs3500.freecell.controller;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw02.Suit;
import cs3500.freecell.model.hw02.Value;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/** Tests for the SimpleFreecellController class. Tests a variety of different cases. */
public class SimpleFreecellControllerTest {

  FreecellModel fcm = new SimpleFreecellModel();
  FreecellModel<Card> model = new SimpleFreecellModel();
  Appendable ap = new StringBuffer();
  String notShuffled =
      "F1:\n"
          + "F2:\n"
          + "F3:\n"
          + "F4:\n"
          + "O1:\n"
          + "O2:\n"
          + "O3:\n"
          + "O4:\n"
          + "C1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
          + "C2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
          + "C3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
          + "C4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠";

  // Generates a deck used for testing purposes. When the cards are dealt
  // into the 4 cascade piles, they are in descending order and sorted by color.
  // The deck has 4 piles in the order SPADES, CLUBS, HEARTS, and DIAMOND.
  private List<Card> generateTestDeck() {
    List<Card> deck = new ArrayList<>();
    for (int i = 12; i >= 0; i--) {
      Value value = Value.values()[i];
      for (int j = 0; j < 4; j++) {
        Suit suit = Suit.values()[j];
        Card card = new Card(value, suit);
        deck.add(card);
      }
    }
    return deck;
  }

  // Test for controller with a null readable, throws error
  @Test(expected = IllegalArgumentException.class)
  public void nullReadableTest() {
    SimpleFreecellController controllerNull =
        new SimpleFreecellController(fcm, null, new StringBuffer());
  }

  // Test for controller with a null appendable, throws error
  @Test(expected = IllegalArgumentException.class)
  public void nullAppendableTest() {
    SimpleFreecellController controllerNull =
        new SimpleFreecellController(fcm, new StringReader(""), null);
  }

  // Test for controller with a null deck, throws error
  @Test(expected = IllegalArgumentException.class)
  public void nullDeckTest() {
    Readable rd = new StringReader("");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(null, 8, 4, false);
  }

  // Test for valid moves
  @Test
  public void validPlayGameTest() {
    Readable rd = new StringReader("C1 2 O3");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(model.getDeck(), 8, 4, false);
    assertNotEquals(true, ap.toString());
  }

  // Test to see if Shuffle works from the controller
  @Test
  public void shuffleFromControllerTest() {
    Readable rd = new StringReader("q");
    Readable rd2 = new StringReader("q");
    StringBuffer ap2 = new StringBuffer();
    FreecellController x = new SimpleFreecellController(fcm, rd, ap);
    FreecellController y = new SimpleFreecellController(fcm, rd2, ap2);
    x.playGame(model.getDeck(), 8, 4, true);
    y.playGame(model.getDeck(), 8, 4, false);
    assertNotEquals(ap.toString(), ap2.toString());
  }

  // Test to see if it doesn't start game with a partial deck
  @Test
  public void partialDeckTest() {
    Readable rd = new StringReader("Q");
    List deck = model.getDeck();
    deck.remove(2);
    deck.remove(1);
    new SimpleFreecellController(fcm, rd, ap).playGame(deck, 8, 4, false);
    assertEquals("Could not start game.", ap.toString());
  }

  // Test to see if it doesn't start game with a partial deck
  @Test
  public void testDuplicatesDeck() {
    Readable rd = new StringReader("Q");
    List deck = model.getDeck();
    Object dup = deck.get(1);
    deck.add(dup);
    deck.add(dup);
    new SimpleFreecellController(fcm, rd, ap).playGame(deck, 8, 4, false);
    assertEquals("Could not start game.", ap.toString());
  }

  // Test for invalid number of cascade piles, cannot start game, returns error message
  @Test
  public void invalidCascadesPlayGameTest() {
    Readable rd = new StringReader("");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(model.getDeck(), 2, 4, false);
    assertEquals("Could not start game.", ap.toString());
  }

  // Test for invalid number of open piles, cannot start game, returns error message
  @Test
  public void invalidOpensPlayGameTest() {
    Readable rd = new StringReader("");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(model.getDeck(), 6, -1, false);
    assertEquals("Could not start game.", ap.toString());
  }

  // Test for controller starting again if an invalid source pile input is given
  @Test
  public void invalidLowercaseInputPlayGameTest() {
    Readable rd = new StringReader("c1 O1 Q");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(model.getDeck(), 8, 4, false);
    assertEquals(true, ap.toString().contains("Invalid source. Please input again."));
  }

  // Test for controller starting again if an invalid source pile input is given
  @Test
  public void invalidIntegerInputPlayGameTest() {
    Readable rd = new StringReader("1 O1 Q");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(model.getDeck(), 8, 4, false);
    assertEquals(true, ap.toString().contains("Invalid source. Please input again."));
  }

  // Test for controller starting again if an invalid source pile input is given
  @Test
  public void invalidSourcePileInputPlayGameTest() {
    Readable rd = new StringReader("Z1 O1 Q");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(model.getDeck(), 8, 4, false);
    assertEquals(true, ap.toString().contains("Invalid source. Please input again."));
  }

  // Test for controller starting again if an invalid source pile index is given
  @Test
  public void invalidSourcePileIndexPlayGameTest() {
    Readable rd = new StringReader("OO O2 q");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(model.getDeck(), 6, 2, false);
    assertEquals(true, ap.toString().contains("Invalid source. Please input again."));
  }

  // Test for controller starting again if an invalid card index is given
  @Test
  public void invalidCardIndexPlayGameTest() {
    Readable rd = new StringReader("C3 aa O3 q");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 4, 4, false);
    assertEquals(true, ap.toString().contains("Invalid card index. Please input again."));
    assertEquals(false, ap.toString().contains("O3: A♠"));
  }

  // Test for controller starting again if an invalid letter for the destination pile is given
  @Test
  public void invalidDestPileLetterPlayGameTest() {
    Readable rd = new StringReader("C2 7 M3 Q");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 7, 3, false);
    assertEquals(true, ap.toString().contains("Invalid destination. Please input again."));
  }

  // Test for controller starting again if a lowercase letter is used for the destination pile
  @Test
  public void invalidDestPileLowercasePlayGameTest() {
    Readable rd = new StringReader("C2 7 f3 Q");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 7, 3, false);
    assertEquals(true, ap.toString().contains("Invalid destination. Please input again."));
  }

  // Test for controller starting again if an invalid index is given
  @Test
  public void invalidDestPileNumberPlayGameTest() {
    Readable rd = new StringReader("C6 9 OW O2 q");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 6, 4, false);
    assertEquals(true, ap.toString().contains("Invalid destination. Please input again."));
  }

  // Test for controller starting again if an invalid symbol for the source is given
  @Test
  public void invalidSourceSymbolPlayGameTest() {
    Readable rd = new StringReader("% 9 OW O2 q");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 6, 4, false);
    assertEquals(true, ap.toString().contains("Invalid source. Please input again."));
  }

  // Test for controller starting again if an invalid symbol for the card index is given
  @Test
  public void invalidIndexSymbolPlayGameTest() {
    Readable rd = new StringReader("C6 * F3 O2 q");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 6, 4, false);
    assertEquals(true, ap.toString().contains("Invalid card index. Please input again."));
  }

  // Test for controller starting again if an invalid symbol for the destination is given
  @Test
  public void invalidDestSymbolPlayGameTest() {
    Readable rd = new StringReader("C6 9 & O2 q");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 6, 4, false);
    assertEquals(true, ap.toString().contains("Invalid destination. Please input again."));
  }

  // Test for quitting the game if the source pile is given a "q"
  @Test
  public void sourcePileQuitLowercaseTest() {
    Readable rd = new StringReader("q");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 4, 4, false);
    assertEquals(true, ap.toString().contains("Game quit prematurely."));
  }

  // Test for quitting the game if the source pile is given a "Q"
  @Test
  public void sourcePileQuitUppercaseTest() {
    Readable rd = new StringReader("Q");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 8, 3, false);
    assertEquals(true, ap.toString().contains("Game quit prematurely."));
  }

  // Test for quitting the game if the card index is given a "q"
  @Test
  public void cardIndexQuitLowercaseTest() {
    Readable rd = new StringReader("C6 q");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 6, 4, false);
    assertEquals(true, ap.toString().contains("Game quit prematurely."));
  }

  // Test for quitting the game if the card index is given a "Q"
  @Test
  public void cardIndexQuitUppercaseTest() {
    Readable rd = new StringReader("C3 Q");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 4, 4, false);
    assertEquals(true, ap.toString().contains("Game quit prematurely."));
  }

  // Test for quitting the game if the destination pile is given a "q"
  @Test
  public void destPileQuitLowercaseTest() {
    Readable rd = new StringReader("C1 13 q");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    FreecellModel<Card> model = new SimpleFreecellModel();
    controller.playGame(generateTestDeck(), 4, 4, false);
    assertEquals(true, ap.toString().contains("Game quit prematurely."));
  }

  // Test for quitting the game if the destination pile is given a "Q"
  @Test
  public void destPileQuitUppercaseTest() {
    Readable rd = new StringReader("C1 6 Q");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 4, 4, false);
    assertEquals(true, ap.toString().contains("Game quit prematurely."));
  }

  // Test for quitting the game with three "q"s
  @Test
  public void threeQuitTest() {
    Readable rd = new StringReader("Q Q Q");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 4, 4, false);
    assertEquals(true, ap.toString().contains("Game quit prematurely."));
  }

  // Tests the controller to tell the user to re-input if it starts with a C, but gives
  // a value that is not an integer when looking for a source pile
  @Test
  public void reInputIntegerCascadeSourcePileTest() {
    Readable rd = new StringReader("CAnjali");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 8, 4, false);
    assertEquals(model.toString() + "Invalid source. Please input again.", this.ap.toString());
  }

  // Tests the controller to tell the user to re-input if it starts with a O, but gives
  // a value that is not an integer when looking for a source pile
  @Test
  public void reInputIntegerOpenSourcePileTest() {
    Readable rd = new StringReader("OAnjali");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 8, 4, false);
    assertEquals(model.toString() + "Invalid source. Please input again.", this.ap.toString());
  }

  // Tests the controller to tell the user to re-input if it starts with a F, but gives
  // a value that is not an integer when looking for a source pile
  @Test
  public void reInputIntegerFoundationSourcePileTest() {
    Readable rd = new StringReader("FAnjali");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 8, 4, false);
    assertEquals(model.toString() + "Invalid source. Please input again.", this.ap.toString());
  }

  // Test for the controller running again if a move is invalid
  @Test
  public void invalidMovePlayGameTest() {
    Readable rd = new StringReader("C1 55 O2 q");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 6, 4, false);
    assertEquals(true, ap.toString().contains("Invalid move. Try again."));
  }

  // Test for printing Game Over message when the game has ended
  @Test
  public void gameOverPlayGameTest() {
    StringBuilder won = new StringBuilder();
    for (int i = 1; i <= 4; i++) {
      for (int j = 13; j >= 1; j--) {
        won.append("C" + i + " " + j + " F" + i + " ");
      }
    }
    Readable rd = new StringReader(won.toString());
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 4, 4, false);
    assertEquals(true, ap.toString().contains("\nGame over."));
  }

  // To test that the controller will ask the client to try again when the client inputs an
  // invalid move and the model throws an error
  @Test
  public void testTryAgainMove() {
    Readable rd = new StringReader("C0 3 O1");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 8, 4, false);
    assertEquals(
        model.toString()
            + model.toString()
            + model.toString()
            + " Invalid move. Try again. "
            + "Invalid parameters given.",
        this.ap.toString());
  }

  // Tests the controller will tell the user that the game is over
  @Test
  public void testGameOver() {
    Readable rd =
        new StringReader(
            ""
                + "C1 1 F1 C2 1 F1 C3 1 F1 C4 1 F1 C5 1 F1 C6 1 F1 C7 "
                + "1 F1 C8 1 F1 C9 1 F1 C10 1 F1 "
                + "C11 1 F1 C12 1 F1 C13 1 F1 "
                + "C14 1 F2 C15 1 F2 C16 1 F2 C17 1 F2 C18 1 F2 C19 "
                + "1 F2 C20 1 F2 C21 1 F2 C22 1 F2 "
                + "C23 1 F2 C24 1 F2 C25 1 F2 C26 1 F2 "
                + "C27 1 F3 C28 1 F3 C29 1 F3 C30 1 F3 C31 1 F3 C32 1 "
                + "F3 C33 1 F3 C34 1 F3 C35 1 F3 "
                + "C36 1 F3 C37 1 F3 C38 1 F3 C39 1 F3 "
                + "C40 1 F4 C41 1 F4 C42 1 F4 C43 1 F4 C44 1 F4 C45 1 "
                + "F4 C46 1 F4 C47 1 F4 C48 1 F4 "
                + "C49 1 F4 C50 1 F4 C51 1 F4 C52 1 F4");
    SimpleFreecellController<Card> controller = new SimpleFreecellController(fcm, rd, ap);
    controller.playGame(generateTestDeck(), 52, 4, false);
    assertEquals(
        model.toString() + "\n" + "Game over.", this.ap.toString().substring(72552, 73053));
  }
}
