package com.montealegreluis.servicebuses.commandbus;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

abstract class CommandHandlerFactoryTest {
  @Test
  void it_fails_to_create_an_unknown_command_handler() {
    var factory = factory();
    var unknownCommandName = unknownCommandName();

    assertThrows(
        CannotCreateCommandHandler.class, () -> factory.commandFromName(unknownCommandName));
  }

  @Test
  void it_creates_a_command_handler() {
    var factory = factory();
    var knownCommandName = knownCommandName();

    var commandHandler = factory.commandFromName(knownCommandName);

    assertNotNull(commandHandler);
  }

  protected abstract Class<? extends CommandHandler> knownCommandName();

  protected abstract Class<? extends CommandHandler> unknownCommandName();

  public abstract CommandHandlerFactory factory();
}
