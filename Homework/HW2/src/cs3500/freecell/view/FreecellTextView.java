package cs3500.freecell.view;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.Pile;
import java.util.List;

/** Class for FreecellTextView which displays the Freecell game. */
public class FreecellTextView implements FreecellView {
  private final FreecellModel<?> model;
  protected List<Pile> openPile;
  protected List<Pile> cascadePile;
  protected List<Pile> foundationPile;
  private List<Card> aDeck;

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
  }

  @Override
  public String toString() {
    try {
      if (this.aDeck.size() == 0) {
        return "";
      } else {
        return this.toStringHelp();
      }
    } catch (IllegalArgumentException e) {
      return "";
    }
  }

  /**
   * Return the present state of the started game as a string.
   *
   * @return String of game state of the started game.
   */
  public String toStringHelp() {
    String result = "";

    for (int i = 0; i < foundationPile.size(); i++) {
      result = result + foundationPile.get(i).toString();
    }

    for (int i = 0; i < openPile.size(); i++) {
      result = result + openPile.get(i).toString();
    }

    for (int i = 0; i < cascadePile.size(); i++) {
      result = result + cascadePile.get(i).toString();
    }

    result = result.substring(0, result.lastIndexOf("\n"));

    return result;
  }
}
