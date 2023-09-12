package cs3500.freecell.model.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Test class for Value. */
public class ValueTest {

  /** test for the string of Value.ACE. */
  @Test
  public void TestToString1() {
    assertEquals("K", Value.KING.toString());
  }

  /** test for the string of Value.Four. */
  @Test
  public void TestToString2() {
    assertEquals("4", Value.FOUR.toString());
  }
}
