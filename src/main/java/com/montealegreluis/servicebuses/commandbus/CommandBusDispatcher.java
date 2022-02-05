package com.montealegreluis.servicebuses.commandbus;

import com.montealegreluis.servicebuses.ActionException;
import com.montealegreluis.servicebuses.commandbus.factory.CommandHandlerFactory;
import com.montealegreluis.servicebuses.commandbus.locator.CommandHandlerLocator;

public final class CommandBusDispatcher implements CommandBus {
  private final CommandHandlerLocator locator;
  private final CommandHandlerFactory factory;

  public CommandBusDispatcher(CommandHandlerLocator locator, CommandHandlerFactory factory) {
    this.locator = locator;
    this.factory = factory;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void dispatch(Command command) throws ActionException {
    final var commandHandlerName = locator.search(command.getClass());
    final var handler = (CommandHandler<Command>) factory.commandFromName(commandHandlerName);

    handler.execute(command);
  }
}
