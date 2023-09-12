package cs3500.freecell.model;

import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.MulticardMoveModel;

/**
 * Factory class that creates a model of Freecell. The class is either a SINGLEMOVE or a MULTIMOVE.
 * The SINGLEMOVE model allows for only a single card to be moved at a time. A MULTIMOVE model
 * allows for multiple cards to be moved in one turn.
 */
public class FreecellModelCreator {

  /** Enum representing an SINGLEMOVE model and a MULTIMOVE model in the game of Freecell. */
  public enum GameType {
    SINGLEMOVE,
    MULTIMOVE
  }

  /**
   * Creates a game of Freecell based on the given type of game (Single or Multi).
   *
   * @param type Type of Freecell game, either Single or Multi.
   * @return A model {@code FreecellModel} for a single move game, or a mutlimove game
   */
  public static FreecellModel create(GameType type) {
    switch (type) {
      case SINGLEMOVE:
        return new SimpleFreecellModel();
      case MULTIMOVE:
        return new MulticardMoveModel();
      default:
        throw new IllegalArgumentException("GameType is invalid.");
    }
  }
}
