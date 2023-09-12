package cs3500.freecell.model.hw02;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/** Represents a SimpleFreecellModel, which is the model of the Freecell Game. */
public class SimpleFreecellModel implements FreecellModel<Card> {

  private ArrayList<Pile> openPile;
  private ArrayList<Pile> cascadePile;
  private ArrayList<Pile> foundationPile;
  private boolean gameInProgress;

  /** Constructor for a SimpleFreecellModel. */
  public SimpleFreecellModel() {
    this.openPile = new ArrayList<Pile>();
    this.cascadePile = new ArrayList<Pile>();
    this.foundationPile = new ArrayList<Pile>();
    this.gameInProgress = false;
  }

  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<>();
    for (Suit s : Suit.values()) {
      for (Value v : Value.values()) {
        Card card = new Card(v, s);
        deck.add(card);
      }
    }
    return deck;
  }

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
      throws IllegalArgumentException {
    if (!isValidDeck(deck)) {
      throw new IllegalArgumentException("Deck is invalid.");
    }
    if (numOpenPiles < 1) {
      throw new IllegalArgumentException("1 open pile required.");
    }
    if (numCascadePiles < 4) {
      throw new IllegalArgumentException("4 cascade piles required.");
    }
    if (gameInProgress) {
      this.openPile.clear();
      this.foundationPile.clear();
      this.cascadePile.clear();
    }
    gameInProgress = true;
    if (shuffle) {
      Collections.shuffle(deck);
    }
    this.initPiles(numCascadePiles, numOpenPiles);
    this.deal(deck, numCascadePiles);
  }

  @Override
  public void move(
      PileType source, int pileNumber, int cardIndex, PileType destination, int destPileNumber)
      throws IllegalArgumentException, IllegalStateException {
    if (validIndex(getType(source), getType(destination), pileNumber, destPileNumber)
        && inPile(pileNumber, source, cardIndex)) {

      Pile sourcePile = getType(source).get(pileNumber);
      Pile destPile = getType(destination).get(destPileNumber);
      Card action = sourcePile.getCardList().get(cardIndex);
      sourcePile.remove(action);
      if (validMove(sourcePile, destPile, action)) {
        destPile.add(action);

      } else {
        sourcePile.add(action);
        throw new IllegalArgumentException("Invalid move.");
      }
    } else {
      throw new IllegalArgumentException("Invalid index.");
    }
  }

  @Override
  public boolean isGameOver() {
    for (int i = 0; i < 4; ++i) {
      if (foundationPile.get(i).size() != 13) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int getNumCardsInFoundationPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (!gameInProgress) {
      if (index < 0) {
        throw new IllegalArgumentException("Invalid index.");
      }
      throw new IllegalStateException("There is no game in progress.");
    }
    return this.foundationPile.get(index).size();
  }

  @Override
  public int getNumCascadePiles() {
    if (!gameInProgress) {
      return -1;
    } else {
      return this.cascadePile.size();
    }
  }

  @Override
  public int getNumCardsInCascadePile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (index < 0 || index > getNumCascadePiles() - 1) {
      throw new IllegalArgumentException("Invalid index.");
    }
    if (!gameInProgress) {
      throw new IllegalStateException("There is no game in progress.");
    } else {
      return this.cascadePile.get(index).size();
    }
  }

  @Override
  public int getNumCardsInOpenPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (index < 0 || index > getNumOpenPiles() - 1) {
      throw new IllegalArgumentException("Invalid index.");
    }
    if (!gameInProgress) {
      throw new IllegalStateException("There is no game in progress.");
    } else {
      return this.openPile.get(index).size();
    }
  }

  @Override
  public int getNumOpenPiles() {
    if (!gameInProgress) {
      return -1;
    } else {
      return this.openPile.size();
    }
  }

  @Override
  public Card getFoundationCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    if (pileIndex < 0) {
      throw new IllegalArgumentException("Invalid: pile out of range");
    }
    if (cardIndex < 0 || cardIndex > (foundationPile.get(pileIndex).size() - 1)) {
      throw new IllegalArgumentException("Invalid: card out of range");
    }
    if (!gameInProgress) {
      throw new IllegalArgumentException("Game is not in progress.");
    } else {
      return foundationPile.get(pileIndex).getCardList().get(cardIndex);
    }
  }

  @Override
  public Card getCascadeCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    if (cardIndex < 0 || cardIndex >= (cascadePile.get(pileIndex).size() - 1)) {
      throw new IllegalArgumentException("Invalid: card out of range");
    }
    if (pileIndex < 0 || pileIndex >= cascadePile.size()) {
      throw new IllegalArgumentException("Invalid: pile out of range");
    }
    if (!gameInProgress) {
      throw new IllegalArgumentException("Game is not in progress.");
    } else {
      return cascadePile.get(pileIndex).getCardList().get(cardIndex);
    }
  }

  @Override
  public Card getOpenCardAt(int pileIndex) throws IllegalArgumentException, IllegalStateException {
    if (pileIndex < 0 || pileIndex > (openPile.size() - 1)) {
      throw new IllegalArgumentException("Pile index is invalid.");
    }
    if (!gameInProgress) {
      throw new IllegalStateException("Game not in progress.");
    }
    if (openPile.get(pileIndex).getCardList().size() == 0) {
      return null;
    } else {
      return openPile.get(pileIndex).getCardList().get(0);
    }
  }

  /**
   * Checks for a full deck of cards that is valid.
   *
   * @param deck a list of cards
   * @return true if it's a valid deck, else false
   */
  private boolean isValidDeck(List<Card> deck) {

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

    List<Card> fullDeck =
        new ArrayList<Card>(
            Arrays.asList(
                h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, h12, h13, c1, c2, c3, c4, c5, c6, c7,
                c8, c9, c10, c11, c12, c13, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13,
                s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13));

    int validDeckSize = 52;
    if (deck.size() != validDeckSize) {
      return false;
    }
    for (Card card : deck) {
      if (!card.validCard()) {
        return false;
      }
    }
    for (int j = 0; j < deck.size(); j++) {
      for (int k = j + 1; k < deck.size(); k++) {
        if (deck.get(j).equals((deck.get(k)))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Retrieves the PileType.
   *
   * @param type the type of the pile
   * @return the PileType to get
   */
  private ArrayList<Pile> getType(PileType type) {
    if (type == PileType.CASCADE) {
      return cascadePile;
    } else if (type == PileType.FOUNDATION) {
      return foundationPile;
    } else {
      return openPile;
    }
  }

  /**
   * Deals the deck of cards.
   *
   * @param deck represents the deck of cards
   */
  private void deal(List<Card> deck, int numCascadePiles) {
    int cascadeIndex = 0;
    for (Card card : deck) {
      this.cascadePile.get(cascadeIndex % numCascadePiles).add(card);
      cascadeIndex++;
    }
  }

  /**
   * Initializes the fields in SimpleFreecellModel.
   *
   * @param numCascadePiles number of cascade piles
   * @param numOpenPiles number of open piles
   */
  private void initPiles(int numCascadePiles, int numOpenPiles) {
    this.openPile = new ArrayList<Pile>();
    for (int i = 0; i < numOpenPiles; i++) {
      this.openPile.add(new Pile(PileType.OPEN, new ArrayList<>()));
    }
    this.foundationPile = new ArrayList<Pile>();
    for (int i = 0; i < 4; i++) {
      this.foundationPile.add(new Pile(PileType.FOUNDATION, new ArrayList<>()));
    }
    this.cascadePile = new ArrayList<Pile>();
    for (int i = 0; i < numCascadePiles; i++) {
      this.cascadePile.add(new Pile(PileType.CASCADE, new ArrayList<>()));
    }
  }

  /**
   * Determines if a given card is in the pile.
   *
   * @param pileNum represents the number of the Pile
   * @param type represents the PileType of the Pile
   * @param ind represents where the card is in the pile
   * @return true if the card in is the pile, and false if it is not.
   */
  private boolean inPile(int pileNum, PileType type, int ind) {
    return ind == getType(type).get(pileNum).getCardList().size() - 1;
  }

  /**
   * Determines whether or not the index is valid.
   *
   * @param source represents the pile where the card is moving from
   * @param destPile represents the pile where the card is moving to
   * @param pileNum represents the number of the Pile for the Cards
   * @param destPileNum represents the number of the destination Pile for the Cards
   * @return whether the index is valid or not
   */
  private boolean validIndex(
      ArrayList<Pile> source, ArrayList<Pile> destPile, int pileNum, int destPileNum) {
    return pileNum < source.size()
        && pileNum >= 0
        && destPileNum < destPile.size()
        && destPileNum >= 0;
  }

  /**
   * Determines whether or not the move is valid.
   *
   * @param source represents the pile where the card is moving from
   * @param destPile represents the pile where the card is moving to
   * @param action represents the card that is being moved
   * @return whether the move is valid or not
   */
  private boolean validMove(Pile source, Pile destPile, Card action) {
    return destPile.getRules(source, action);
  }
}
