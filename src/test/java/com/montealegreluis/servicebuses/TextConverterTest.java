package com.montealegreluis.servicebuses;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

final class TextConverterTest {
  @Test
  void it_converts_from_camel_case_to_kebab_case() {
    assertEquals("upload-png-file", TextConverter.camelCaseToKebabCase("UploadPngFile"));
    assertEquals("custom-xml-parser", TextConverter.camelCaseToKebabCase("CustomXMLParser"));
  }

  @Test
  void it_converts_camel_case_to_sentence() {
    assertEquals("Update avatar image", TextConverter.camelCaseToSentence("UpdateAvatarImage"));
    assertEquals("Convert to xml", TextConverter.camelCaseToSentence("ConvertToXML"));
  }
}
