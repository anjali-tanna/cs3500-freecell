package cs3500;

import cs3500.freecell.model.FreecellModelState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.FreecellModelCreator;
import cs3500.freecell.model.hw04.MulticardMoveModel;

/** Tests for the FreecellModelCreator class. Tests the create() method. */
public class FreecellModelCreatorTest {
  FreecellModelCreator multiCardModel = new FreecellModelCreator();
  FreecellModelCreator singleCardModel = new FreecellModelCreator();

  @Test(expected = IllegalArgumentException.class)
  public void createNullTest() {
    assertEquals(true, multiCardModel.create(null) instanceof MulticardMoveModel);
  }

  @Test
  public void createTest() {
    assertEquals(
        true,
        multiCardModel.create(FreecellModelCreator.GameType.MULTIMOVE)
            instanceof MulticardMoveModel);
    assertEquals(
        false,
        singleCardModel.create(FreecellModelCreator.GameType.SINGLEMOVE)
            instanceof MulticardMoveModel);
    assertEquals(
        true,
        singleCardModel.create(FreecellModelCreator.GameType.SINGLEMOVE) instanceof FreecellModel);
  }

  @Test
  public void createSingleMoveTest() {
    FreecellModelState model =
        FreecellModelCreator.create(FreecellModelCreator.GameType.SINGLEMOVE);

    assertEquals(true, model instanceof FreecellModel);
    assertEquals(false, model instanceof MulticardMoveModel);
  }

  @Test
  public void createMultiMoveTest() {
    FreecellModelState model = FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);

    assertEquals(true, model instanceof FreecellModel);
    assertEquals(true, model instanceof MulticardMoveModel);
  }
}
