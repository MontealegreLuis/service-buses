package com.montealegreluis.servicebuses;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TextConverter {
  private static final Pattern pattern =
      Pattern.compile("((?<=[a-z\\d])[A-Z]|(?<=[A-Z\\d])[A-Z](?=[a-z]))");

  public static String camelCaseToKebabCase(String text) {
    Matcher matcher = pattern.matcher(text);
    return matcher.replaceAll("-$1").toLowerCase();
  }

  public static String camelCaseToSentence(String text) {
    Matcher matcher = pattern.matcher(text);
    String sentence = matcher.replaceAll(" $1").toLowerCase();
    return sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
  }
}
