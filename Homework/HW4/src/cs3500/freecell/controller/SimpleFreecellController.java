package cs3500.freecell.controller;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.view.FreecellTextView;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the FreecellController. Given certain inputs it appends the game state appropriately
 * on the given Appendable field which is to then be printed out later to the user.
 */
public class SimpleFreecellController<Card> implements FreecellController<Card> {

  private FreecellModel<Card> model;
  private Readable rd;
  private Appendable ap;

  /**
   * The constructor for the FreecellController.
   *
   * @param model represents the model of the FreecellModel
   * @param rd {@code Readable} object to receive the input
   * @param ap {@code Appendable} object to deliver the output
   * @throws IllegalArgumentException throws an IllegalArugumentException for null models, decks,
   *     and objects
   */
  public SimpleFreecellController(FreecellModel<Card> model, Readable rd, Appendable ap)
      throws IllegalArgumentException {
    if (ap == null || rd == null) {
      throw new IllegalArgumentException("Objects cannot be null");
    }
    this.model = model;
    this.rd = rd;
    this.ap = ap;
  }

  @Override
  public void playGame(List<Card> deck, int numCascades, int numOpens, boolean shuffle)
      throws IllegalStateException, IllegalArgumentException {
    Scanner sc = new Scanner(this.rd);
    if (deck == null || model == null) {
      throw new IllegalArgumentException("Cannot start game with empty model or deck");
    }
    FreecellTextView text = new FreecellTextView(model, ap);
    try {
      this.model.startGame(deck, numCascades, numOpens, shuffle);
    } catch (IllegalArgumentException e) {
      try {
        text.renderMessage("Could not start game.");
      } catch (IOException io) {
        io.printStackTrace();
      }
      return;
    }
    while (!model.isGameOver()) {
      PileType src;
      int srcPileNum;
      int cardIndex;
      PileType dest;
      int destinationPileNum;

      try {
        text.renderBoard();
      } catch (IOException io) {
        throw new IllegalStateException("Cannot render board.");
      }

      try {
        text.renderMessage("\n");
      } catch (IOException io) {
        io.printStackTrace();
      }
      if (!sc.hasNext()) {
        throw new IllegalStateException("Readable is complete.");
      }
      String sourceString = sc.next();
      while (!validPile(sourceString)) {
        try {
          text.renderMessage("\nInvalid move. Try again.");
        } catch (IOException io) {
          io.printStackTrace();
        }
        sourceString = sc.next();
      }

      if (shouldGameQuit(sourceString)) {
        try {
          text.renderMessage("\nGame quit prematurely.");
        } catch (IOException io) {
          io.printStackTrace();
        }
        return;
      }

      src = getPileType(sourceString);
      srcPileNum = getPileNum(sourceString) - 1;

      String stringCardIndex = sc.next();
      while (!isValidIndex(stringCardIndex)) {
        try {
          text.renderMessage("\nInvalid move. Try again.");
        } catch (IOException io) {
          io.printStackTrace();
        }
        stringCardIndex = sc.next();
      }

      if (shouldGameQuit(stringCardIndex)) {
        try {
          text.renderMessage("\nGame quit prematurely.");
        } catch (IOException io) {
          io.printStackTrace();
        }
        return;
      }
      cardIndex = Integer.valueOf(stringCardIndex) - 1;

      String destString = sc.next();
      while (!validPile(destString)) {
        try {
          text.renderMessage("\nInvalid move. Try again.");
        } catch (IOException io) {
          io.printStackTrace();
        }
        destString = sc.next();
      }

      if (shouldGameQuit(destString)) {
        try {
          text.renderMessage("\nGame quit prematurely.");
        } catch (IOException io) {
          io.printStackTrace();
        }
        return;
      }
      dest = getPileType(destString);
      destinationPileNum = getPileNum(destString) - 1;

      try {
        model.move(src, srcPileNum, cardIndex, dest, destinationPileNum);
      } catch (Exception e) {
        try {
          text.renderMessage("\nInvalid move. Try again." + e.getMessage());
        } catch (IOException ioException) {
          ioException.printStackTrace();
          throw new IllegalStateException("Invalid input given.");
        }
      }
    }
    displayEndGame(text);
  }

  // returns the game state if the game has ended
  private void displayEndGame(FreecellTextView text) {
    try {
      text.renderMessage(model.toString());
    } catch (IOException ex) {
      // nothing to catch
    }
    try {
      text.renderMessage("\nGame over.");
    } catch (IOException ex) {
      // nothing to catch
    }
  }

  // determines whether card index of given card is valid
  private boolean isValidIndex(String cardIndex) {
    return cardIndex.equals("q") || cardIndex.equals("Q") || stringInt(cardIndex);
  }

  // extracts pile number given source pile string (must be valid)
  private int getPileNum(String src) {
    String pileNumString = src.substring(1);
    return Integer.valueOf(pileNumString);
  }

  // extracts pile type given source pile string (must be valid)
  private PileType getPileType(String src) {
    String ptString = src.substring(0, 1);
    switch (ptString) {
      case "F":
        return PileType.FOUNDATION;
      case "C":
        return PileType.CASCADE;
      case "O":
        return PileType.OPEN;
      default:
        return null;
    }
  }

  // Checks whether if string indicates a quit game
  private boolean shouldGameQuit(String src) {
    return src.equals("Q") || src.equals("q");
  }

  // is the given pile valid
  private boolean validPile(String s) {
    // Valid if it is to quit the game
    if (s.equals("q") || s.equals("Q")) {
      return true;
    }
    // String should be at least two characters if the first is not q
    if (s.length() < 2) {
      return false;
    }
    String pile = s.substring(0, 1);
    String pIndex = s.substring(1);

    if (!(pile.equals("C") || pile.equals("O") || pile.equals("F"))) {
      return false;
    }
    // Pile index converted to an int
    return stringInt(pIndex);
  }

  // check is a string can be parsed for an int
  private boolean stringInt(String str) {
    if (str == null) {
      return false;
    }
    try {
      Scanner scanner = new Scanner(str);
      int n = scanner.nextInt();
    } catch (Exception exception) {
      return false;
    }
    return true;
  }
}
