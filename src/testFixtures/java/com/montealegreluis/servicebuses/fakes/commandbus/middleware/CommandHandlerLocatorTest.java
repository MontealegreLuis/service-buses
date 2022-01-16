package com.montealegreluis.servicebuses.fakes.commandbus.middleware;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.montealegreluis.servicebuses.commandbus.Command;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;
import com.montealegreluis.servicebuses.commandbus.UnknownCommandHandler;
import com.montealegreluis.servicebuses.commandbus.middleware.CommandHandlerLocator;
import org.junit.jupiter.api.Test;

public abstract class CommandHandlerLocatorTest {
  @Test
  void it_fails_when_no_command_handler_exist_for_given_command() {
    var locator = locator();
    var commandName = commandWithoutHandler();

    assertThrows(UnknownCommandHandler.class, () -> locator.search(commandName));
  }

  @Test
  void it_finds_the_command_handler_of_a_given_command() {
    var locator = locator();
    var commandName = commandWithHandler();
    var handlerName = commandHandler();

    var handlerClass = locator.search(commandName);

    assertEquals(handlerName, handlerClass);
  }

  protected abstract Class<? extends Command> commandWithoutHandler();

  protected abstract Class<? extends Command> commandWithHandler();

  protected abstract Class<? extends CommandHandler<? extends Command>> commandHandler();

  protected abstract CommandHandlerLocator locator();
}
