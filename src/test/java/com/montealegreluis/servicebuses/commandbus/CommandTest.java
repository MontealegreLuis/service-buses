package com.montealegreluis.servicebuses.commandbus;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.servicebuses.Action;
import com.montealegreluis.servicebuses.fakes.commandbus.FakeCommand;
import org.junit.jupiter.api.Test;

final class CommandTest {
  @Test
  void it_knows_its_action() {
    var command = new FakeCommand();

    var action = command.action();

    assertEquals(Action.withoutSuffix("FakeCommandAction", "Action"), action);
  }
}
