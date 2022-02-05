package com.montealegreluis.servicebuses.commandbus.middleware.handler;

import com.montealegreluis.servicebuses.ActionException;
import com.montealegreluis.servicebuses.commandbus.*;
import com.montealegreluis.servicebuses.commandbus.factory.CommandHandlerFactory;
import com.montealegreluis.servicebuses.commandbus.locator.CommandHandlerLocator;
import com.montealegreluis.servicebuses.commandbus.middleware.CommandMiddleware;

public final class CommandHandlerMiddleware implements CommandMiddleware {
  private final CommandHandlerLocator locator;
  private final CommandHandlerFactory factory;

  public CommandHandlerMiddleware(CommandHandlerLocator locator, CommandHandlerFactory factory) {
    this.locator = locator;
    this.factory = factory;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void execute(Command command, CommandHandler<Command> next) throws ActionException {
    var handler =
        (CommandHandler<Command>) factory.commandFromName(locator.search(command.getClass()));
    handler.execute(command);
  }
}
