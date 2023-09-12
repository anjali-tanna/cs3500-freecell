package cs3500.freecell.model.hw02;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The class of the model of the Freecell Game.
 */
public class SimpleFreecellModel implements FreecellModel<Card> {

  private ArrayList<Pile> openPiles;
  private ArrayList<Pile> foundationPiles;
  private ArrayList<Pile> cascadePiles;
  private boolean hasGameStarted;

  /**
   * Constructs the new SimpleFreecellModel.
   */
  public SimpleFreecellModel() {
    this.openPiles = new ArrayList<Pile>();
    this.foundationPiles = new ArrayList<Pile>();
    this.cascadePiles = new ArrayList<Pile>();
    this.hasGameStarted = false;

  }

  @Override
  public List<Card> getDeck() {
    ArrayList<Card> deck = new ArrayList<>();
    for (Value value : Value.values()) {
      for (Suit suit : Suit.values()) {
        deck.add(new Card(suit, value));
      }
    }
    return deck;
  }

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
      throws IllegalArgumentException {
    if (!deckValidation(deck)) {
      throw new IllegalArgumentException("The deck is invalid");
    }
    if (numOpenPiles < 1) {
      throw new IllegalArgumentException("At least 1 open pile is needed");
    }
    if (numCascadePiles < 4) {
      throw new IllegalArgumentException("At least 4 cascade piles are needed");
    }
    if (hasGameStarted) {
      this.openPiles.clear();
      this.foundationPiles.clear();
      this.cascadePiles.clear();
    }
    hasGameStarted = true;
    if (shuffle) {
      Collections.shuffle(deck);
    }
    this.initializationOfPiles(numCascadePiles, numOpenPiles);
    this.dealOutCards(deck, numCascadePiles);

  }

  @Override
  public boolean isGameOver() {
    for (int i = 0; i < 4; ++i) {
      if (foundationPiles.get(i).size() != 13) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex,
      PileType destination, int destPileNumber)
      throws IllegalArgumentException, IllegalStateException {

    if (indexValidation(getPileType(source), getPileType(destination), pileNumber, destPileNumber)
        && inPile(pileNumber, source, cardIndex)) {

      Pile sourcePile = getPileType(source).get(pileNumber);
      Pile destinationPile = getPileType(destination).get(destPileNumber);
      Card movingCard = sourcePile.getCards().get(cardIndex);

      sourcePile.remove(movingCard);

      if (moveValidation(sourcePile, destinationPile, movingCard)) {
        destinationPile.add(movingCard);

      } else {
        sourcePile.add(movingCard);
        throw new IllegalArgumentException("The move is invalid");
      }
    } else {
      throw new IllegalArgumentException("The Index is invalid");
    }
  }

  @Override
  public int getNumCardsInFoundationPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (index < 0) {
      throw new IllegalArgumentException("The index is invalid");
    }
    if (!hasGameStarted) {
      throw new IllegalStateException("The game has not started");
    }
    return this.foundationPiles.get(index).size();
  }

  @Override
  public int getNumCascadePiles() {
    if (!hasGameStarted) {
      return -1;
    }
    return this.cascadePiles.size();
  }

  @Override
  public int getNumCardsInCascadePile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (index < 0 || index > getNumCascadePiles() - 1) {
      throw new IllegalArgumentException("The index is invalid");
    }
    if (!hasGameStarted) {
      throw new IllegalStateException("The game has not started");
    }
    return this.cascadePiles.get(index).size();
  }

  @Override
  public int getNumCardsInOpenPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (index < 0 || index > getNumOpenPiles() - 1) {
      throw new IllegalArgumentException("The index is invalid");
    }
    if (!hasGameStarted) {
      throw new IllegalStateException("The game has not started");
    } else {
      return this.openPiles.get(index).size();
    }
  }

  @Override
  public int getNumOpenPiles() {
    if (!hasGameStarted) {
      return -1;
    } else {
      return this.openPiles.size();
    }
  }

  @Override
  public Card getFoundationCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    if (pileIndex < 0) {
      throw new IllegalArgumentException("The pile index is invalid");
    }
    if (cardIndex < 0 || cardIndex > (this.foundationPiles.size() - 1)) {
      throw new IllegalArgumentException("The card index is invalid");
    }
    if (!hasGameStarted) {
      throw new IllegalStateException("The game has not started");
    } else {
      return foundationPiles.get(pileIndex).getCards().get(cardIndex);
    }
  }

  @Override
  public Card getCascadeCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    if (cardIndex < 0 || cardIndex >= this.cascadePiles.get(pileIndex).size()) {
      throw new IllegalArgumentException("The card index is invalid");
    }
    if (pileIndex < 0 || pileIndex >= cascadePiles.size()) {
      throw new IllegalArgumentException("The pile index is invalid");
    }
    if (!hasGameStarted) {
      throw new IllegalStateException("The game has not started");
    }
    return cascadePiles.get(pileIndex).getCards().get(cardIndex);
  }

  @Override
  public Card getOpenCardAt(int pileIndex) throws IllegalArgumentException, IllegalStateException {
    if (pileIndex < 0) {
      throw new IllegalArgumentException("The pile index is invalid");
    }
    if (!hasGameStarted) {
      throw new IllegalStateException("The game has not started");
    }
    if (openPiles.get(pileIndex).getCards().size() == 0) {
      return null;
    } else {
      return openPiles.get(pileIndex).getCards().get(0);
    }
  }

  /**
   * A method that checks whether is a deck of cards is valid. Self-note: helper method for
   * startGame.
   *
   * @param deck represents the deck of Cards.
   */
  public boolean deckValidation(List<Card> deck) {
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
    List<Card> correctDeck = new ArrayList<Card>(Arrays.asList(
        heart1, heart2, heart3, heart4, heart5,
        heart6, heart7, heart8, heart9, heart10, heart11, heart12, heart13, spade1, spade2, spade3,
        spade4, spade5, spade6, spade7, spade8, spade9, spade10, spade11, spade11, spade12, spade13,
        diamond1, diamond2, diamond3, diamond4, diamond5, diamond6, diamond7, diamond8, diamond9,
        diamond10, diamond11, diamond12, diamond13, club1, club2, club3, club4, club5, club6,
        club7, club8, club9, club10, club11, club12, club13));
    int correctDeckSize = 52;
    if (deck.size() != correctDeckSize) {
      return false;
    }
    for (Card currCard : deck) {
      if (!currCard.cardValidation()) {
        return false;
      }
    }
    for (int i = 0; i < deck.size(); i++) {
      for (int j = i + 1; j < deck.size(); j++) {
        if (deck.get(i).equals((deck.get(j)))) {
          return false;
        }
      }
    }
    return true;
  }


  /**
   * A method that initializes the different fields in the SimpleFreecellModel. Self-note: A helper
   * method for the startGame method.
   *
   * @param numCascadePiles number of cascade piles
   * @param numOpenPiles    number of open piles
   */
  private void initializationOfPiles(int numCascadePiles, int numOpenPiles) {
    this.openPiles = new ArrayList<Pile>();
    for (int i = 0; i < numOpenPiles; i++) {
      this.openPiles.add(new Pile(PileType.OPEN, new ArrayList<>()));
    }
    this.foundationPiles = new ArrayList<Pile>();
    for (int i = 0; i < 4; i++) {
      this.foundationPiles.add(new Pile(PileType.FOUNDATION, new ArrayList<>()));
    }
    this.cascadePiles = new ArrayList<Pile>();
    for (int i = 0; i < numCascadePiles; i++) {
      this.cascadePiles.add(new Pile(PileType.CASCADE, new ArrayList<>()));
    }
  }

  /**
   * A method that deals out the deck of cards.
   *
   * @param deck represents the deck of cards
   */
  private void dealOutCards(List<Card> deck, int numCascadePiles) {
    int cascadeInd = 0;
    for (Card card : deck) {
      this.cascadePiles.get(cascadeInd % numCascadePiles).add(card);
      cascadeInd++;
    }
  }

  /**
   * A method that determines which PileType to retrieve.
   *
   * @param type represents the type of the pile
   * @return the pileType that is to be retrieved
   */
  private ArrayList<Pile> getPileType(PileType type) {
    if (type == PileType.CASCADE) {
      return cascadePiles;
    } else if (type == PileType.FOUNDATION) {
      return foundationPiles;
    } else {
      return openPiles;
    }
  }

  /**
   * A method that determines if a given card is in the pile.
   *
   * @param pileNumber represents the number of the Pile for the Cards
   * @param pileType   represents the pileType of the Pile
   * @param cardIndex  represents where the card is in the pile
   * @return true if the card in is the pile, and false if it is not.
   */
  private boolean inPile(int pileNumber, PileType pileType, int cardIndex) {
    return cardIndex == getPileType(pileType).get(pileNumber).getCards().size() - 1;
  }

  /**
   * A method that determines if the move being tried is valid in the game.
   *
   * @param sourcePile      represents the pile where the card is moving from
   * @param destinationPile represents the pile where the card is moving to
   * @param movingCard      represents the card that is being moved
   * @return whether the move is valid or not
   */
  private boolean moveValidation(Pile sourcePile, Pile destinationPile, Card movingCard) {
    return destinationPile.gameRules(sourcePile, movingCard);
  }

  /**
   * A method that determines if the index is valid.
   *
   * @param sourcePile            represents the pile where the card is moving from
   * @param destinationPile       represents the pile where the card is moving to
   * @param pileNumber            represents the number of the Pile for the Cards
   * @param destinationPileNumber represents the number of the destination Pile for the Cards
   * @return whether the index is valid or not
   */
  private boolean indexValidation(ArrayList<Pile> sourcePile, ArrayList<Pile> destinationPile,
      int pileNumber, int destinationPileNumber) {
    return pileNumber < sourcePile.size() && pileNumber >= 0
        && destinationPileNumber < destinationPile.size()
        && destinationPileNumber >= 0;

  }
}


