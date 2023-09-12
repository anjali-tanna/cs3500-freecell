package cs3500.freecell.model.hw04;

import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import java.util.List;

/**
 * MulticardMoveModel is a Freecell operation extending from the FreecellModel. However, it handles
 * multi-move operations for the game.
 */
public class MulticardMoveModel extends SimpleFreecellModel {
  // keep tracks of the amount of empty piles in the model
  private int emptyCascade = 0;
  private int emptyOpen = 0;

  @Override
  public void move(
      PileType src, int pileNum, int cardIndex, PileType dest, int destinationPileNum) {
    emptyOpen = 0;
    emptyCascade = 0;
    int size = this.getSize(src, pileNum);
    List<Card> targetPile = this.getTargetList(src, pileNum, cardIndex);
    this.isEmpty();
    boolean emptyValid = targetPile.size() <= ((emptyOpen + 1) * Math.pow(2, emptyCascade));
    if (src == PileType.FOUNDATION
        && emptyValid
        && movePileTo(targetPile, dest, destinationPileNum)) {
      this.moveList(targetPile, dest, destinationPileNum);
      foundationPile.get(pileNum).getCardList().remove(cardIndex);
    } else if (src == PileType.OPEN
        && emptyValid
        && movePileTo(targetPile, dest, destinationPileNum)) {
      this.moveList(targetPile, dest, destinationPileNum);
      openPile.get(pileNum).getCardList().remove(cardIndex);
    } else if (src == PileType.CASCADE
        && emptyValid
        && validCascadeMove(pileNum, cardIndex)
        && targetPile.size() > 0
        && movePileTo(targetPile, dest, destinationPileNum)) {
      this.moveList(targetPile, dest, destinationPileNum);
      cascadePile.get(pileNum).getCardList().removeAll(targetPile);
    } else {
      throw new IllegalArgumentException("Invalid move");
    }
  }

  /** Makes sure the amount of empty cascade and open piles are empty. */
  private void isEmpty() {
    for (int i = 0; i < cascadePile.size(); i++) {
      if (cascadePile.get(i).getCardList().size() == 0) {
        emptyCascade = emptyCascade + 1;
      } // does nothing when empty, no other case
    }
    for (int i = 0; i < openPile.size(); i++) {
      if (openPile.get(i).getCardList().size() == 0) {
        emptyOpen = emptyOpen + 1;
      } // does nothing when empty, no other case
    }
  }

  /**
   * Returns the size of the source pile.
   *
   * @param source is the source pile type
   * @param pileNum is the source pile number
   * @return the size of the given source pile
   */
  private int getSize(PileType source, int pileNum) {
    if (source == PileType.FOUNDATION && pileNum >= 0 && pileNum < foundationPile.size()) {
      return this.foundationPile.get(pileNum).getCardList().size();
    } else if (source == PileType.OPEN && pileNum >= 0 && pileNum < openPile.size()) {
      return this.openPile.get(pileNum).getCardList().size();
    } else if (source == PileType.CASCADE && pileNum >= 0 && pileNum < cascadePile.size()) {
      return this.cascadePile.get(pileNum).getCardList().size();
    } else {
      throw new IllegalArgumentException("Invalid Source");
    }
  }

  /**
   * Checks if the source pile is valid and returns the pile of cards that is being moved.
   *
   * @param source is the source pile type
   * @param pileNum is the source pile number
   * @param cardIndex is the source pile card index
   * @return the list of cards that is being moved
   */
  private List<Card> getTargetList(PileType source, int pileNum, int cardIndex) {
    int sourceSize = getSize(source, pileNum);
    if (source == PileType.OPEN && cardIndex == sourceSize - 1) {
      return this.openPile.get(pileNum).getCardList().subList(cardIndex, sourceSize);
    } else if (source == PileType.FOUNDATION && cardIndex == sourceSize - 1) {
      return this.foundationPile.get(pileNum).getCardList().subList(cardIndex, sourceSize);
    } else if (source == PileType.CASCADE && validCascadeMove(pileNum, cardIndex)) {
      return this.cascadePile.get(pileNum).getCardList().subList(cardIndex, sourceSize);
    } else {
      throw new IllegalArgumentException("Invalid source");
    }
  }

  /**
   * Checks if the given cascade pile is valid to move.
   *
   * @param pileNum is the cascade pile number
   * @param cardIndex is the cascade pile's starting card index
   * @return true if the cards are valid to be moved from the given cascade pile, false otherwise
   */
  private boolean validCascadeMove(int pileNum, int cardIndex) {
    int cascadeSize = cascadePile.get(pileNum).getCardList().size();
    boolean t = true;
    try {
      List<Card> targetL = cascadePile.get(pileNum).getCardList().subList(cardIndex, cascadeSize);
      for (int i = 0; i < targetL.size() - 1; i++) {
        t = t && targetL.get(i + 1).alternate(targetL.get(i));
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid source");
    }
    return t;
  }

  /**
   * Checks if the destination pile is valid.
   *
   * @param moving is the list of cards that is being moved
   * @param dest is the destination pile type
   * @param destinationPileNum is the destination pile number
   * @return true if the destination is valid, false otherwise
   */
  private boolean movePileTo(List<Card> moving, PileType dest, int destinationPileNum) {
    Card firstCard = moving.get(0);
    if (dest == PileType.FOUNDATION) {
      int foundSize = foundationPile.get(destinationPileNum).getCardList().size();
      if (foundSize == 0) {
        return moving.size() == 1 && firstCard.value.getInt() == 1;
      } else {
        return moving.size() == 1
            && firstCard.suit
                == foundationPile.get(destinationPileNum).getCardList().get(foundSize - 1).suit
            && firstCard.value.getInt() - 1
                == foundationPile
                    .get(destinationPileNum)
                    .getCardList()
                    .get(foundSize - 1)
                    .value
                    .getInt();
      }
    } else if (dest == PileType.OPEN) {
      return moving.size() == 1 && openPile.get(destinationPileNum).getCardList().size() == 0;
    } else if (dest == PileType.CASCADE) {
      int casSize = cascadePile.get(destinationPileNum).getCardList().size();
      if (casSize == 0) {
        return true;
      } else {
        return firstCard.alternate(
            cascadePile.get(destinationPileNum).getCardList().get(casSize - 1));
      }
    } else {
      throw new IllegalArgumentException("Invalid destination");
    }
  }

  /**
   * Once source pile and destination pile are valid, the list of cards are moved.
   *
   * @param moving is the list of cards that is being moved
   * @param dest is the destination pile type
   * @param destinationPileNum is the destination pile number
   */
  private void moveList(List<Card> moving, PileType dest, int destinationPileNum) {
    if (dest == PileType.FOUNDATION) {
      foundationPile.get(destinationPileNum).getCardList().addAll(moving);
    } else if (dest == PileType.OPEN) {
      openPile.get(destinationPileNum).getCardList().addAll(moving);
    } else if (dest == PileType.CASCADE) {
      cascadePile.get(destinationPileNum).getCardList().addAll(moving);
    } else {
      throw new IllegalArgumentException("Invalid Move");
    }
  }
}
