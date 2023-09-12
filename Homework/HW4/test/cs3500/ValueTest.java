package cs3500;

import cs3500.freecell.model.hw02.Value;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Test class for Value. */
public class ValueTest {

  /** test for the string of Value.KING. */
  @Test
  public void TestToString1() {
    Assert.assertEquals("K", Value.KING.toString());
  }

  /** test for the string of Value.Four. */
  @Test
  public void TestToString2() {
    assertEquals("4", Value.FOUR.toString());
  }
}
