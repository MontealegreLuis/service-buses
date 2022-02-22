package com.montealegreluis.servicebuses;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.assertions.IllegalArgumentException;
import org.junit.jupiter.api.Test;

final class ActionExceptionTest {
  @Test
  void it_includes_the_action_where_the_exception_was_thrown() {
    var action = Action.withoutSuffix("SearchProducts", "");
    var cause = new RuntimeException("Cannot connect to MySQL server");

    var exception = new ActionException("Something went wrong", action, cause) {};

    assertTrue(exception.action().isPresent());
    assertEquals(action, exception.action().get());
  }

  @Test
  void it_can_add_the_action_that_created_it() {
    var action = Action.withoutSuffix("SearchProducts", "");
    var cause = new RuntimeException("Cannot connect to MySQL server");

    var exception = new ActionException("Something went wrong", cause) {};
    assertTrue(exception.action().isEmpty());

    exception.setAction(action);

    assertTrue(exception.action().isPresent());
    assertEquals(action, exception.action().get());
  }

  @Test
  void it_prevents_setting_a_null_action() {
    var cause = new RuntimeException("Cannot connect to MySQL server");
    var exception = new ActionException("Something went wrong", cause) {};

    assertThrows(IllegalArgumentException.class, () -> exception.setAction(null));
  }
}
