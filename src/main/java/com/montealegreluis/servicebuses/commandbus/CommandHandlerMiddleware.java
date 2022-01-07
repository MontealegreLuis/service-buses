package com.montealegreluis.servicebuses.commandbus;

public final class CommandHandlerMiddleware implements CommandMiddleware<Command> {
  private final CommandHandlersLocator locator;
  private final CommandHandlerFactory factory;

  public CommandHandlerMiddleware(CommandHandlersLocator locator, CommandHandlerFactory factory) {
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
