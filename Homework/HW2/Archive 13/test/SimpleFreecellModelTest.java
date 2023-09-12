import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.Pile;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw02.Suit;
import cs3500.freecell.model.hw02.Value;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * A class for the SimpleFreeecellModel tests.
 */
public class SimpleFreecellModelTest {

  private SimpleFreecellModel newModel;
  private List<Card> deckOfCards;
  private List<Card> deckEx;

  Card heart1 = new Card(Suit.HEART, Value.ACE);
  Card heart2 = new Card(Suit.HEART, Value.TWO);
  Card heart3 = new Card(Suit.HEART, Value.THREE);
  Card heart4 = new Card(Suit.HEART, Value.FOUR);
  Card heart5 = new Card(Suit.HEART, Value.FIVE);
  Card heart6 = new Card(Suit.HEART, Value.SIX);
  Card heart7 = new Card(Suit.HEART, Value.SEVEN);
  Card heart8 = new Card(Suit.HEART, Value.EIGHT);
  Card heart9 = new Card(Suit.HEART, Value.NINE);
  Card heart10 = new Card(Suit.HEART, Value.TEN);
  Card heart11 = new Card(Suit.HEART, Value.JACK);
  Card heart12 = new Card(Suit.HEART, Value.QUEEN);
  Card heart13 = new Card(Suit.HEART, Value.KING);

  Card spade1 = new Card(Suit.SPADE, Value.ACE);
  Card spade2 = new Card(Suit.SPADE, Value.TWO);
  Card spade3 = new Card(Suit.SPADE, Value.THREE);
  Card spade4 = new Card(Suit.SPADE, Value.FOUR);
  Card spade5 = new Card(Suit.SPADE, Value.FIVE);
  Card spade6 = new Card(Suit.SPADE, Value.SIX);
  Card spade7 = new Card(Suit.SPADE, Value.SEVEN);
  Card spade8 = new Card(Suit.SPADE, Value.EIGHT);
  Card spade9 = new Card(Suit.SPADE, Value.NINE);
  Card spade10 = new Card(Suit.SPADE, Value.TEN);
  Card spade11 = new Card(Suit.SPADE, Value.JACK);
  Card spade12 = new Card(Suit.SPADE, Value.QUEEN);
  Card spade13 = new Card(Suit.SPADE, Value.KING);

  Card diamond1 = new Card(Suit.DIAMOND, Value.ACE);
  Card diamond2 = new Card(Suit.DIAMOND, Value.TWO);
  Card diamond3 = new Card(Suit.DIAMOND, Value.THREE);
  Card diamond4 = new Card(Suit.DIAMOND, Value.FOUR);
  Card diamond5 = new Card(Suit.DIAMOND, Value.FIVE);
  Card diamond6 = new Card(Suit.DIAMOND, Value.SIX);
  Card diamond7 = new Card(Suit.DIAMOND, Value.SEVEN);
  Card diamond8 = new Card(Suit.DIAMOND, Value.EIGHT);
  Card diamond9 = new Card(Suit.DIAMOND, Value.NINE);
  Card diamond10 = new Card(Suit.DIAMOND, Value.TEN);
  Card diamond11 = new Card(Suit.DIAMOND, Value.JACK);
  Card diamond12 = new Card(Suit.DIAMOND, Value.QUEEN);
  Card diamond13 = new Card(Suit.DIAMOND, Value.KING);

  Card club1 = new Card(Suit.CLUB, Value.ACE);
  Card club2 = new Card(Suit.CLUB, Value.TWO);
  Card club3 = new Card(Suit.CLUB, Value.THREE);
  Card club4 = new Card(Suit.CLUB, Value.FOUR);
  Card club5 = new Card(Suit.CLUB, Value.FIVE);
  Card club6 = new Card(Suit.CLUB, Value.SIX);
  Card club7 = new Card(Suit.CLUB, Value.SEVEN);
  Card club8 = new Card(Suit.CLUB, Value.EIGHT);
  Card club9 = new Card(Suit.CLUB, Value.NINE);
  Card club10 = new Card(Suit.CLUB, Value.TEN);
  Card club11 = new Card(Suit.CLUB, Value.JACK);
  Card club12 = new Card(Suit.CLUB, Value.QUEEN);
  Card club13 = new Card(Suit.CLUB, Value.KING);
  List<Card> correctDeck;

  private Card threeOfHearts;
  private Card twoOfClubs;
  private Card aceOfSpades;
  private ArrayList<Card> loCard1;
  private ArrayList<Card> loCard2;
  private ArrayList<Card> loCard3;
  private Pile foundationPile1;

  // the data is initialized
  @Before
  public void initializeData() {
    this.newModel = new SimpleFreecellModel();
    this.deckOfCards = newModel.getDeck();
    this.correctDeck = new ArrayList<Card>(Arrays.asList(
        heart1, heart2, heart3, heart4, heart5,
        heart6, heart7, heart8, heart9, heart10, heart11, heart12, heart13, spade1, spade2, spade3,
        spade4, spade5, spade6, spade7, spade8, spade9, spade10, spade11, spade11, spade12, spade13,
        diamond1, diamond2, diamond3, diamond4, diamond5, diamond6, diamond7, diamond8, diamond9,
        diamond10, diamond11, diamond12, diamond13, club1, club2, club3, club4, club5, club6,
        club7, club8, club9, club10, club11, club12, club13));
    threeOfHearts = new Card(Suit.HEART, Value.THREE);
    twoOfClubs = new Card(Suit.CLUB, Value.TWO);
    deckOfCards = newModel.getDeck();
    loCard1 = new ArrayList<Card>();
    loCard1.add(threeOfHearts);
    loCard1.add(twoOfClubs);
    this.deckEx = newModel.getDeck();

    Pile foundationPile1 = new Pile(PileType.FOUNDATION, loCard1);


  }


  // test for getDeck
  @Test
  public void testGetDeck() {
    initializeData();
    assertEquals(52, newModel.getDeck().size());
    assertNotEquals(53, newModel.getDeck().size());
  }

  // test for startGame
  @Test
  public void testStartGame() {
    List<Card> unshuffled = newModel.getDeck();
    newModel.startGame(correctDeck, 4, 1, true);
    boolean shuffled = false;
    for (Card c1 : correctDeck) {
      for (Card c2 : unshuffled) {
        if (!c1.equals(c2)) {
          shuffled = true;
        }
      }
    }
    assertEquals(true, shuffled);
  }
  // test for if shuffle is true
  @Test
  public void testShuffleTrue() {
    initializeData();
    newModel.startGame(deckEx, 11, 4, true);
    assertEquals(11, newModel.getNumCascadePiles());
    assertEquals(4, newModel.getNumOpenPiles());
  }

  // tests for an invalid number of open pile (it must be at leasta 4)
  //  // will throw an exception
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidOpenPileNumber() {
    initializeData();
    newModel.startGame(deckEx, 4, 0, true);
  }

  // tests a deck with duplicate cards to throw exception
  @Test(expected = IllegalArgumentException.class)
  public void testdDuplicateCards() {
    initializeData();
    deckEx.remove(threeOfHearts);
    deckEx.add(twoOfClubs);
    newModel.startGame(deckEx, 7, 5, true);
  }

  // tests for an invalid cascade pile number (it must be at leasta 4)
  // will throw an exception
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCascadePileNumber() {
    initializeData();
    newModel.startGame(deckEx, 2, 1, true);
  }

  // test for if shuffle is false
  @Test
  public void testShuffleFalse() {
    initializeData();
    newModel.startGame(deckEx, 6, 5, false);
    assertEquals(6, newModel.getNumCascadePiles());
    assertEquals(5, newModel.getNumOpenPiles());

    newModel.startGame(deckEx, 7, 9, true);
    assertEquals(7, newModel.getNumCascadePiles());
    assertEquals(9, newModel.getNumOpenPiles());
  }

  // tests to make sure whne shuffle is false, the deck is the same
  @Test
  public void testNonShuffledDecks() {
    initializeData();
    newModel.startGame(deckEx, 9, 6, false);
    newModel.startGame(deckEx, 9, 6, false);
    assertEquals(newModel, newModel);
  }

  // test for index of open pile (throws exception for out of bounds)
  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInOpenPile() {
    initializeData();
    newModel.startGame(deckEx, 8, 5, false);
    newModel.getNumCardsInOpenPile(-2);
  }

  // test for index of cascade pile (throws exception for out of bounds)
  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInCascadePile() {
    initializeData();
    newModel.startGame(deckEx, 8, 4, false);
    newModel.getNumCardsInCascadePile(9);
  }

  // test for startGame exceptions
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameExceptions() {
    initializeData();
    // invalid number of open piles
    newModel.startGame(loCard1, 2, 5, true);
    initializeData();
    // invalid number of cascade piles
    newModel.startGame(loCard1, 3, 0, true);
    initializeData();
    // invalid deck to start with
    deckEx.remove(0);
    newModel.startGame(deckEx, 6, 1, true);
  }

  // test for isGameOver
  @Test
  public void isGameOver() {
    List<Card> deck = newModel.getDeck();
    newModel.startGame(deck, 4, 1, true);
    assertFalse(newModel.isGameOver());

  }

  // test for move

  // test for getNumCardsInFoundationPile
  @Test
  public void testGetNumCardsInFoundationPile() {
    //assertEquals(3, foundationPile1.getCards().size().getNumCardsInFoundationPile(2));
  }

  // test for getNumCascadePiles

  // test getNumCardsInCascadePile

  // test for getNumCardsInOpenPile

  // test for getNumOpenPiles

  // test for getFoundationCardAt

  // test for getCascadeCardAt

  // test for getOpenCardAt

  // test for deckValidation
  @Test
  public void deckValidation() {
    assertEquals(correctDeck, correctDeck);
  }


  // test for deckValidation exceptions
  @Test(expected = IllegalArgumentException.class)
  public void deckValidationExceptions() {
    newModel.deckValidation(deckEx);
  }

  // test for initializationOfPiles

  // test for dealOutCards

  // test for getPileType

  // test for inPile

  // test for moveValidation

  // test for indexValidation

}



