package cs3500.freecell.view;

import cs3500.freecell.model.FreecellModel;
import java.io.IOException;

/**
 * FreecellTextView implements the FreecellView interface and the methods toString, renderBoard, and
 * renderMessaage. It handles the FreecellModel and an Appendable to display the game state as a
 * string.
 */
public class FreecellTextView implements FreecellView {
  private final FreecellModel<?> model;
  private final Appendable ap;

  /**
   * Constructs a FreecellTextView.
   *
   * @param model represents the game model
   */
  public FreecellTextView(FreecellModel<?> model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }

    this.model = model;
    this.ap = null;
  }

  /**
   * A second constructor to display the FreecellTextView.
   *
   * @param model represents the game model
   */
  public <Card> FreecellTextView(FreecellModel<Card> model, Appendable ap) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    this.model = model;
    this.ap = ap;
  }

  @Override
  public void renderBoard() throws IOException {
    try {
      ap.append(this.toString());
      ap.append("\n");
    } catch (IOException e) {
      throw new IOException("Illegal Input.");
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.ap.append(message);
    } catch (IOException e) {
      throw new IOException("Unable to write appendable.");
    }
  }

  @Override
  public String toString() {
    if (this.model.getNumCascadePiles() == -1) {
      return "";
    }
    String stringView = "";
    for (int i = 0; i < 4; ++i) {
      if (i != 0) {
        stringView += "\n";
      }
      stringView += "F" + (i + 1) + ":";
      if (model.getNumCardsInFoundationPile(i) != 0) {
        stringView += " ";
      }
      for (int j = 0; j < model.getNumCardsInFoundationPile(i); ++j) {
        stringView += model.getFoundationCardAt(i, j).toString();
        if (j < model.getNumCardsInCascadePile(i) - 1) {
          stringView += ", ";
        }
      }
    }
    stringView += "\n";
    for (int i = 0; i < model.getNumOpenPiles(); ++i) {
      if (i != 0) {
        stringView += "\n";
      }
      stringView += "O" + (i + 1) + ":";
      if (model.getNumCardsInOpenPile(i) != 0) {
        stringView += " ";
      }
      for (int j = 0; j < model.getNumCardsInOpenPile(i); ++j) {
        stringView += model.getOpenCardAt(i).toString();
        if (j < model.getNumCardsInOpenPile(i) - 1) {
          stringView += ", ";
        }
      }
    }
    stringView += "\n";
    for (int i = 0; i < model.getNumCascadePiles(); ++i) {
      if (i != 0) {
        stringView += "\n";
      }
      stringView += "C" + (i + 1) + ":";
      if (model.getNumCardsInCascadePile(i) != 0) {
        stringView += " ";
      }
      for (int j = 0; j < model.getNumCardsInCascadePile(i); ++j) {
        stringView += model.getCascadeCardAt(i, j).toString();
        if (j < model.getNumCardsInCascadePile(i) - 1) {
          stringView += ", ";
        }
      }
    }
    return stringView;
  }
}
