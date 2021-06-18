package utils.common;

public class TextUtils {

  public static String removeSpecialCharacters(String str) {
    str = str.replaceAll("[^a-zA-Z0-9]", " ");
    return str;
  }
}
