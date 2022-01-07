package com.montealegreluis.servicebuses.commandbus;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.servicebuses.fakes.commandbus.CommandWithoutHandler;
import com.montealegreluis.servicebuses.fakes.commandbus.FakeCommand;
import com.montealegreluis.servicebuses.fakes.commandbus.FakeCommandHandler;
import org.junit.jupiter.api.Test;

final class CommandHandlersLocatorTest {
  @Test
  void it_fails_when_no_command_handler_exist_for_given_command() {
    var locator = new CommandHandlersLocator("com.montealegreluis.servicebuses.fakes.commandbus");
    var command = new CommandWithoutHandler();

    assertThrows(UnknownCommandHandler.class, () -> locator.search(command.getClass()));
  }

  @Test
  void it_finds_the_command_handler_of_a_given_command() {
    var locator = new CommandHandlersLocator("com.montealegreluis.servicebuses.fakes.commandbus");
    var command = new FakeCommand();
    var handler = new FakeCommandHandler();

    var handlerClass = locator.search(command.getClass());

    assertEquals(handler.getClass(), handlerClass);
  }
}
