package undecided.common.primitive;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The Strings2 class provides utility methods for manipulating strings.
 */
public class Strings2 {

  public static final String DOUBLE_QUOTE = "\"";
  public static final String EMPTY = "";

  public static boolean isDigit(String value) {
    return false;
  }


  /**
   * Surrounds a string with a specified surround string.
   *
   * @param str the string to surround
   * @param surroundString the string to surround with
   * @return the original string surrounded by the specified surround string
   */
  public static @NotNull String surround(@NotNull String str, @NotNull String surroundString) {
    return surroundString + str + surroundString;
  }

  /**
   * Surrounds a string with a specified surround string. If either the input string or the surround
   * string is null, an empty string will be used as the default value.
   *
   * @param str the string to surround
   * @param surroundString the string to surround with
   * @return the original string surrounded by the specified surround string, or an empty string if
   * either the input string or the surround string is null
   */
  public static @NotNull String surroundSilently(@Nullable String str,
      @Nullable String surroundString) {
    return surround(defaultIfNull(str, EMPTY), defaultIfNull(surroundString, EMPTY));
  }

  /**
   * Checks if a string is null and returns a default value if it is.
   *
   * @param str the string to check for null
   * @param defaultString the default value to return if str is null
   * @return the original string if it is not null, otherwise the default string
   */
  public static String defaultIfNull(String str, String defaultString) {
    if (str == null) {
      return defaultString;
    }
    return str;
  }
}
