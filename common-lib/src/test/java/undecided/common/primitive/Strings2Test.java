package undecided.common.primitive;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Strings2Test {

  @Test
  public void testSurround() {
    String str = "Hello";
    String surroundString = "*";
    String result = Strings2.surround(str, surroundString);
    assertEquals("*Hello*", result);
  }

  @Test
  public void testSurroundWithEmpty() {
    String str = "Hello";
    String surroundString = "";
    String result = Strings2.surround(str, surroundString);
    assertEquals("Hello", result);
  }

  @Test
  public void testSurroundWithNull() {
    String str = "";
    String surroundString = "*";
    String result = Strings2.surround(str, surroundString);
    assertEquals("**", result);
  }

  @Test
  public void testSurroundLongString() {
    String str = "This is a long string";
    String surroundString = "#";
    String result = Strings2.surround(str, surroundString);
    assertEquals("#This is a long string#", result);
  }

  @Test
  public void testDefaultIfNullWithNullString() {
    String str = null;
    String defaultString = "Default";
    String result = Strings2.defaultIfNull(str, defaultString);
    assertEquals("Default", result);
  }

  @Test
  public void testDefaultIfNullWithNonNullString() {
    String str = "Hello";
    String defaultString = "Default";
    String result = Strings2.defaultIfNull(str, defaultString);
    assertEquals("Hello", result);
  }

  @Test
  public void testSurroundSilentlyWithValidInput() {
    String str = "Hello";
    String surroundString = "*";
    String result = Strings2.surroundSilently(str, surroundString);
    assertEquals("*Hello*", result);
  }

  @Test
  public void testSurroundSilentlyWithEmptyString() {
    String str = "";
    String surroundString = "*";
    String result = Strings2.surroundSilently(str, surroundString);
    assertEquals("**", result);
  }

  @Test
  public void testSurroundSilentlyWithNullString() {
    String str = null;
    String surroundString = "*";
    String result = Strings2.surroundSilently(str, surroundString);
    assertEquals("**", result);
  }

  @Test
  public void testSurroundSilentlyWithNullSurroundString() {
    String str = "Hello";
    String surroundString = null;
    String result = Strings2.surroundSilently(str, surroundString);
    assertEquals("Hello", result);
  }
}
