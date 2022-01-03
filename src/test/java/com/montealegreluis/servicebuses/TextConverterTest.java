package com.montealegreluis.servicebuses;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

final class TextConverterTest {
  @Test
  void it_converts_from_camel_case_to_kebab_case() {
    var camelCase = "UploadPngFile";

    var kebabCase = TextConverter.camelCaseToKebabCase(camelCase);

    assertEquals("upload-png-file", kebabCase);
  }

  @Test
  void it_converts_camel_case_to_sentence() {
    var camelCase = "UpdateAvatarImage";

    var sentence = TextConverter.camelCaseToSentence(camelCase);

    assertEquals("Update avatar image", sentence);
  }
}
