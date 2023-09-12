package cs3500.freecell.view;

import cs3500.freecell.model.FreecellModel;

/**
 * The class that represents the view of the game.
 */
public class FreecellTextView implements FreecellView {

  private final FreecellModel<?> model;

  /**
   * The creation of the model of the game.
   *
   * @param model represents a model of the game
   */
  public FreecellTextView(FreecellModel<?> model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null!");
    }
    this.model = model;
  }

  /**
   * A method that translates into a String.
   *
   * @return in string format
   */
  public String toString() {
    if (model.getNumOpenPiles() == -1) {
      return "";
    }
    String stringEx = "";
    for (int i = 0; i < 4; ++i) {
      if (i != 0) {
        stringEx += "\n";
      }
      stringEx += "F" + (i + 1) + ":";
      if (model.getNumCardsInFoundationPile(i) != 0) {
        stringEx += " ";
      }
      for (int j = 0; j < model.getNumCardsInFoundationPile(i) - 1; ++i) {
        stringEx += model.getFoundationCardAt(i, j).toString() + ", ";
      }
    }
    for (int i = 0; i < model.getNumCascadePiles(); ++i) {
      if (i != 0) {
        stringEx += "\n";
      }
      stringEx += "O" + (i + 1) + ":";
      if (model.getNumCardsInCascadePile(i) != 0) {
        stringEx += " ";
      }
      for (int j = 0; j < model.getNumCardsInOpenPile(i) - 1; ++i) {
        stringEx += model.getCascadeCardAt(i, j) + ", ";
      }
    }
    for (int i = 0; i < model.getNumOpenPiles(); ++i) {
      if (i != 0 && i != model.getNumOpenPiles() - 1) {
        stringEx += "\n";
      }
      stringEx += "C" + (i + 1) + ":";
      if (model.getNumCardsInOpenPile(i) != 0) {
        stringEx += " ";
      }
      for (int j = 0; j < model.getNumCardsInOpenPile(i); ++i) {
        stringEx += model.getOpenCardAt(j) + ", ";
      }
    }
    return stringEx;
  }
}