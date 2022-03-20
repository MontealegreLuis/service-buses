package com.montealegreluis.servicebuses;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.assertions.IllegalArgumentException;
import org.junit.jupiter.api.Test;

final class ActionTest {
  @Test
  void it_cannot_be_empty_or_null() {
    assertThrows(IllegalArgumentException.class, () -> Action.withoutSuffix(null, "Action"));
    assertThrows(IllegalArgumentException.class, () -> Action.withoutSuffix("", "Action"));
    assertThrows(IllegalArgumentException.class, () -> Action.withoutSuffix(" ", "Action"));
  }

  @Test
  void it_removes_suffix_from_its_name() {
    var name = "UpdatePersonalInformationAction";
    var suffix = "Action";

    var action = Action.withoutSuffix(name, suffix);

    assertEquals("UpdatePersonalInformation", action.name());
  }

  @Test
  void it_can_be_created_with_a_given_name() {
    var name = "SearchConcerts";

    var action = Action.named(name);

    assertEquals(name, action.name());
  }

  @Test
  void it_converts_its_name_to_a_slug() {
    var action = Action.withoutSuffix("ChangeBusinessAddressAction", "Action");

    var slug = action.toSlug();

    assertEquals("change-business-address", slug);
  }

  @Test
  void it_converts_its_name_to_a_sentence() {
    var action = Action.withoutSuffix("ScheduleDepositAction", "Action");

    var sentence = action.toSentence();

    assertEquals("Schedule deposit", sentence);
  }

  @Test
  void it_converts_its_name_to_words() {
    var action = Action.withoutSuffix("SearchSongsByGenre", "Action");

    var words = action.toWords();

    assertEquals("search songs by genre", words);
  }
}
