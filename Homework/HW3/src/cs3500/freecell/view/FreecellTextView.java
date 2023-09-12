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
    String display = "";
    for (int i = 1; i < 5; i++) {
      display += "F" + Integer.toString(i) + ":";
      for (int k = 0; k < this.model.getNumCardsInFoundationPile(i - 1); k++) {
        String card = model.getFoundationCardAt(i - 1, k).toString();
        display += " " + card;
      }
      display += "\n";
    }
    for (int j = 0; j < this.model.getNumOpenPiles(); j++) {
      display += "O" + Integer.toString(j + 1) + ":";
      for (int l = 0; l < this.model.getNumCardsInOpenPile(j); l++) {
        String card = model.getOpenCardAt(l).toString();
        display += " " + card;
      }
      display += "\n";
    }
    for (int a = 0; a < this.model.getNumCascadePiles(); a++) {
      display += "C" + Integer.toString(a + 1) + ":";
      for (int b = 0; b < this.model.getNumCardsInCascadePile(a); b++) {
        String card = model.getCascadeCardAt(a, b).toString();
        display += " " + card;
      }
      if (a < this.model.getNumCascadePiles() - 1) {
        display += '\n';
      }
    }
    return display;
  }
}
