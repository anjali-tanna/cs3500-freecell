package cs3500.freecell.model.hw02;

import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

/** Test class for SimpleFreecellModel. */
public class SimpleFreecellModelTest {
  private SimpleFreecellModel freecellModel;
  private SimpleFreecellModel freecellModel2;
  private List<Card> deckFull;

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

  List<Card> rightDeck;

  // the data is initialized
  @Before
  public void initializeData() {
    freecellModel = new SimpleFreecellModel();
    deckFull = freecellModel.getDeck();
    List<Card> deck1 =
        new ArrayList<Card>(
            Arrays.asList(
                h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, h12, h13, c1, c2, c3, c4, c5, c6, c7,
                c8, c9, c10, c11, c12, c13, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13,
                s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13));
  }

  // test for getDeck
  @Test
  public void getDeckTest() {
    initializeData();
    assertEquals(52, freecellModel.getDeck().size());
  }

  @Test
  public void getDeckTest2() {
    assertEquals(Value.values().length * Suit.values().length, freecellModel.getDeck().size());
  }

  // test for startGame
  @Test
  public void testStartGame() {
    initializeData();
    rightDeck.add(s3);
    try {
      freecellModel.startGame(rightDeck, 2, 6, false);
    } catch (IllegalArgumentException exception) {
      assertEquals("Invalid deck", exception.getMessage());
    }
  }

  // Tests for the startGame() method
  @Test(expected = IllegalArgumentException.class)
  public void invalidCascadesTest() {
    freecellModel.startGame(freecellModel.getDeck(), 3, 3, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidOpensTest() {
    freecellModel.startGame(freecellModel.getDeck(), 6, 0, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullDeckTest() {
    freecellModel.startGame(null, 6, 2, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullDeckTest2() {
    List<Card> deck = freecellModel.getDeck();
    deck.remove(0);
    deck.add(null);
    freecellModel.startGame(deck, 6, 2, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void emptyDeckTest() {
    freecellModel.startGame(new ArrayList<>(), 6, 2, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void extraPilesTest() {
    List<Card> deck = freecellModel.getDeck();
    deck.add(deck.get(0));
    freecellModel.startGame(deck, 6, 2, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void duplicatesTest() {
    List<Card> deck = freecellModel.getDeck();
    deck.remove(deck.size() - 1);
    deck.add(deck.get(0));
    freecellModel.startGame(deck, 6, 2, true);
  }

  @Test
  public void isGameOverTest() {
    SimpleFreecellModel empty = new SimpleFreecellModel();
    assertFalse(empty.isGameOver());
  }

  @Test
  public void isGameOverStartTest() {
    initializeData();
    freecellModel.startGame(freecellModel.getDeck(), 8, 4, true);
    assertEquals(false, freecellModel.isGameOver());
  }

  @Test
  public void isGameOverMidTest() {
    freecellModel.startGame(freecellModel.getDeck(), 52, 4, false);
    for (int j = 0; j < 2; j++) {
      for (int i = 0; i < 13; i++) {
        freecellModel.move(
            cs3500.freecell.model.PileType.CASCADE,
            (i * 4) + j,
            0,
            cs3500.freecell.model.PileType.FOUNDATION,
            j);
      }
    }
    assertFalse(freecellModel.isGameOver());
  }

  // Cascade out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void getNumCardsInCascadePileTest() {
    initializeData();
    freecellModel.startGame(deckFull, 5, 3, false);
    freecellModel.getNumCardsInCascadePile(8);
  }

  // Open out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void getNumCardsInOpenPileTest() {
    initializeData();
    freecellModel.startGame(deckFull, 5, 2, false);
    freecellModel.getNumCardsInOpenPile(-2);
  }

  // Foundation out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void getNumCardsInFoundationPileTest() {
    initializeData();
    freecellModel.startGame(deckFull, 3, 4, false);
    freecellModel.getNumCardsInFoundationPile(5);
  }

  // Cascade game not started
  @Test(expected = IllegalStateException.class)
  public void notStartedGetNumCardsInCascadePileTest() {
    initializeData();
    freecellModel.getNumCardsInCascadePile(6);
  }

  // Open game not started
  @Test(expected = IllegalStateException.class)
  public void notStartedGetNumCardsInOpenPileTest() {
    initializeData();
    freecellModel.getNumCardsInOpenPile(-1);
  }

  // Foundation game not started
  @Test(expected = IllegalStateException.class)
  public void notStartedGetNumCardsInFoundationPileTest() {
    initializeData();
    freecellModel.getNumCardsInFoundationPile(4);
  }

  // Cascade Index
  @Test
  public void startedGetNumCardsInCascadePileTest() {
    initializeData();
    freecellModel.startGame(deckFull, 6, 4, false);
    assertNotEquals(0, freecellModel.getNumCardsInCascadePile(5));
  }

  // Open Index
  @Test
  public void startedGetNumCardsInOpenPileTest() {
    initializeData();
    freecellModel.startGame(deckFull, 6, 4, false);
    assertEquals(0, freecellModel.getNumCardsInOpenPile(0));
  }

  // Foundation Index
  @Test
  public void startedGetNumCardsInFoundationPileTest() {
    initializeData();
    freecellModel.startGame(deckFull, 6, 4, false);
    assertEquals(0, freecellModel.getNumCardsInFoundationPile(3));
  }

  // getNumOpenPiles Tests
  @Test
  public void getNumOpenPilesTest() {
    initializeData();
    assertEquals(-1, freecellModel.getNumOpenPiles());
    freecellModel.startGame(deckFull, 6, 4, true);
  }

  @Test
  public void getNumOpenPilesTest2() {
    initializeData();
    assertEquals(4, freecellModel.getNumOpenPiles());
  }

  // getNumCascadePiles Tests
  @Test
  public void getNumCascadePilesTest() {
    initializeData();
    assertEquals(-1, freecellModel.getNumCascadePiles());
    freecellModel.startGame(deckFull, 6, 4, false);
  }

  @Test
  public void getNumCascadePilesTest2() {
    initializeData();
    assertEquals(6, freecellModel.getNumCascadePiles());
  }

  // getNumCascadePiles Tests
  @Test(expected = IllegalArgumentException.class)
  public void getNumCascadePilesAfterInvalidGame() {
    initializeData();
    assertEquals(-1, freecellModel.getNumCascadePiles());
    freecellModel.startGame(deckFull, 0, 5, true);
    assertEquals(2, freecellModel.getNumCascadePiles());
  }

  // test for getFoundationCardAt
  @Test(expected = IllegalArgumentException.class)
  public void getFoundationCardAtTest1() {
    freecellModel.startGame(deckFull, 4, 4, false);
    freecellModel.getFoundationCardAt(-1, 0);
    freecellModel.getFoundationCardAt(0, -1);
  }

  // test for getOpenCardAt
  @Test(expected = IllegalArgumentException.class)
  public void getOpenCardAtTest1() {
    freecellModel.startGame(deckFull, 4, 4, false);
    freecellModel.getOpenCardAt(-1);
    freecellModel.getOpenCardAt(-1);
  }

  // test for getCascadeCardAt
  @Test(expected = IllegalArgumentException.class)
  public void getCascadeCardAtTest1() {
    freecellModel.startGame(deckFull, 4, 4, false);
    freecellModel.getCascadeCardAt(-1, 0);
    freecellModel.getCascadeCardAt(0, -1);
  }

  // test for getFoundationCardAt
  @Test(expected = IllegalStateException.class)
  public void getFoundationCardAtTest() {
    freecellModel.getFoundationCardAt(1, 2);
  }

  // test for getOpenCardAt
  @Test(expected = IllegalStateException.class)
  public void getOpenCardAtTest() {
    freecellModel.getOpenCardAt(1);
  }

  // test for getCascadeCardAt
  @Test(expected = IllegalStateException.class)
  public void getCascadeCardAtTest() {
    freecellModel.getCascadeCardAt(0, 3);
  }

  @Test
  public void isGameOverTest1() {
    freecellModel.startGame(deckFull, 8, 1, false);
    freecellModel2.startGame(deckFull, 52, 1, false);

    for (int i = 0; i < 52; i++) {
      freecellModel2.move(
          cs3500.freecell.model.PileType.CASCADE,
          i,
          0,
          cs3500.freecell.model.PileType.FOUNDATION,
          i % 4);
    }
    assertEquals(false, freecellModel.isGameOver());
    assertEquals(true, freecellModel2.isGameOver());
  }
}
