package cs3500.freecell.model.hw02;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a SimpleFreecellModel, which implements the FreecellModelState interface and the
 * methods getDeck, startGame, move, and isGameOver. It handles the three types of Piles by
 * representing each pile with an ArrayList of Pile. It differentiates from the interface in that
 * only one card can be moved instead of multiple. It is parametrized over the Card type Card.
 */
public class SimpleFreecellModel implements FreecellModel<Card> {

  protected ArrayList<Pile> openPile;
  protected ArrayList<Pile> cascadePile;
  protected ArrayList<Pile> foundationPile;
  private boolean gameInProgress;

  /**
   * Constructor for a SimpleFreecellModel, which includes Open Piles, Cascade Piles, Foundation
   * Piles, and whether or not the game is in progress.
   */
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
    if (deck == null) {
      throw new IllegalArgumentException("Deck cannot be null.");
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
        destPile.addCard(action);

      } else {
        sourcePile.addCard(action);
        throw new IllegalArgumentException("Invalid move.");
      }
    } else {
      throw new IllegalArgumentException("Invalid index.");
    }
  }

  @Override
  public boolean isGameOver() {
    return foundationPile.size() == 4
        && foundationPile.stream().allMatch(t -> (t.getCardList().size()) == 13);
  }

  @Override
  public int getNumCardsInFoundationPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (!gameInProgress) {
      throw new IllegalStateException("There is no game in progress.");
    }
    if (index < 0 || index > foundationPile.size()) {
      throw new IllegalArgumentException("Invalid index.");
    }
    return this.foundationPile.get(index).getCardList().size();
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
    if (!gameInProgress) {
      throw new IllegalStateException("There is no game in progress.");
    } else if (index > cascadePile.size() - 1 || index < 0) {
      throw new IllegalArgumentException("Invalid index.");
    } else {
      return this.cascadePile.get(index).getCardList().size();
    }
  }

  @Override
  public int getNumCardsInOpenPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (!gameInProgress) {
      throw new IllegalStateException("There is no game in progress.");
    }
    if (index < 0 || index > getNumOpenPiles() - 1) {
      throw new IllegalArgumentException("Invalid index.");
    } else {
      return this.openPile.get(index).getCardList().size();
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
    if (!gameInProgress) {
      throw new IllegalStateException("Game is not in progress.");
    } else if (pileIndex < 0 || pileIndex > foundationPile.size()) {
      throw new IllegalArgumentException("Invalid: pile out of range");
    } else if (cardIndex < 0
        || cardIndex > (foundationPile.get(pileIndex).getCardList().size() - 1)) {
      throw new IllegalArgumentException("Invalid: card out of range");
    } else {
      return foundationPile.get(pileIndex).getCardList().get(cardIndex);
    }
  }

  @Override
  public Card getCascadeCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    if (!gameInProgress) {
      throw new IllegalStateException("Game is not in progress.");
    } else if (pileIndex < 0 || pileIndex > cascadePile.size() - 1) {
      throw new IllegalArgumentException("Invalid: pile out of range");
    } else if (cardIndex < 0 || cardIndex > (cascadePile.get(pileIndex).getCardList().size() - 1)) {
      throw new IllegalArgumentException("Invalid: card out of range");
    } else {
      return cascadePile.get(pileIndex).getCardList().get(cardIndex);
    }
  }

  @Override
  public Card getOpenCardAt(int pileIndex) throws IllegalArgumentException, IllegalStateException {
    if (!gameInProgress) {
      throw new IllegalStateException("Game not in progress.");
    } else if (pileIndex < 0 || pileIndex > (openPile.size() - 1)) {
      throw new IllegalArgumentException("Pile index is invalid.");
    } else if (openPile.get(pileIndex).getCardList().size() == 0) {
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
      this.cascadePile.get(cascadeIndex % numCascadePiles).addCard(card);
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
