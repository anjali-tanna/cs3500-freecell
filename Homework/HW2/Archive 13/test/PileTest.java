import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.Pile;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw02.Suit;
import cs3500.freecell.model.hw02.Value;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * The class for the Pile tests.
 */
public class PileTest {

  private SimpleFreecellModel newModel;
  private List<Card> deckOfCards;
  private Card threeOfHearts;
  private Card fourOfHearts;
  private Card twoOfClubs;
  private Card aceOfSpades;
  private ArrayList<Card> loCard1;
  private ArrayList<Card> loCard2;
  private ArrayList<Card> loCard3;
  private Pile pile1;

  @Before
  // the data is initialized
  public void initializeData() {
    newModel = new SimpleFreecellModel();
    threeOfHearts = new Card(Suit.HEART, Value.THREE);
    fourOfHearts = new Card(Suit.HEART, Value.FOUR);
    twoOfClubs = new Card(Suit.CLUB, Value.TWO);
    deckOfCards = newModel.getDeck();
    loCard1 = new ArrayList<Card>();
    loCard2 = new ArrayList<Card>();
    loCard3 = new ArrayList<Card>();
    loCard1.add(threeOfHearts);
    loCard1.add(twoOfClubs);
    loCard2.add(fourOfHearts);
  }

  // test for getCards
  @Test
  public void testGetCards() {
   // assertEquals(loCard1, loCard1.getCards());
  }

  // test for getPileType

  // test for size
  @Test
  public void testSize() {
    initializeData();
    assertEquals(2, loCard1.size());
    assertEquals(1, loCard2.size());
    assertEquals(0, loCard3.size());

  }
  // test for remove

  // test for add
  @Test
  public void testAdd() {
    initializeData();
    assertEquals(true, loCard1.add(threeOfHearts));
    assertNotEquals(loCard2, loCard1.add(threeOfHearts));
    assertNotEquals(loCard1, loCard1.add(twoOfClubs));

  }

  // test for gameRules

}


