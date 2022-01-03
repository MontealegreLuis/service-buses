package com.montealegreluis.servicebuses;

import com.google.common.base.CaseFormat;

public final class TextConverter {
  public static String camelCaseToKebabCase(String text) {
    return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, text);
  }

  public static String camelCaseToSentence(String text) {
    String sentence = camelCaseToKebabCase(text).replace("-", " ");
    return sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
  }
}
