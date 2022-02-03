package com.montealegreluis.servicebuses.contracttests.commandbus.middleware.handler;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.servicebuses.commandbus.Command;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;
import com.montealegreluis.servicebuses.commandbus.middleware.handler.CannotCreateCommandHandler;
import com.montealegreluis.servicebuses.commandbus.middleware.handler.CommandHandlerFactory;
import org.junit.jupiter.api.Test;

public abstract class CommandHandlerFactoryTest {
  @Test
  void it_fails_to_create_an_unknown_command_handler() {
    var factory = factory();
    var unknownCommandName = unknownCommandName();

    assertThrows(
        CannotCreateCommandHandler.class, () -> factory.commandFromName(unknownCommandName));
  }

  @Test
  void it_creates_a_command_handler() throws CannotCreateCommandHandler {
    var factory = factory();
    var knownCommandName = knownCommandName();

    var commandHandler = factory.commandFromName(knownCommandName);

    assertNotNull(commandHandler);
  }

  protected abstract Class<? extends CommandHandler<? extends Command>> knownCommandName();

  protected abstract Class<? extends CommandHandler<? extends Command>> unknownCommandName();

  public abstract CommandHandlerFactory factory();
}
