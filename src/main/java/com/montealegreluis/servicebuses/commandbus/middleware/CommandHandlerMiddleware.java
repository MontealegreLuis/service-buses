package com.montealegreluis.servicebuses.commandbus.middleware;

import com.montealegreluis.servicebuses.commandbus.*;

public final class CommandHandlerMiddleware implements CommandMiddleware<Command> {
  private final CommandHandlerLocator locator;
  private final CommandHandlerFactory factory;

  public CommandHandlerMiddleware(CommandHandlerLocator locator, CommandHandlerFactory factory) {
    this.locator = locator;
    this.factory = factory;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void execute(Command command, CommandHandler<Command> next) {
    var handler = factory.commandFromName(locator.search(command.getClass()));
    handler.execute(command);
  }
}
