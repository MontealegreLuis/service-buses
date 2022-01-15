package com.montealegreluis.servicebuses.commandbus;

import java.util.LinkedHashMap;
import java.util.Map;

public final class InMemoryCommandHandlerFactory implements CommandHandlerFactory {
  private final Map<
          Class<? extends CommandHandler<? extends Command>>, CommandHandler<? extends Command>>
      handlers = new LinkedHashMap<>();

  public void add(
      Class<? extends CommandHandler<? extends Command>> commandClass,
      CommandHandler<? extends Command> handler) {
    handlers.put(commandClass, handler);
  }

  @Override
  public CommandHandler commandFromName(Class<? extends CommandHandler> commandClass) {
    CommandHandler<? extends Command> handler = handlers.get(commandClass);
    if (handler == null) {
      throw CannotCreateCommandHandler.forCommand(commandClass, null);
    }
    return handler;
  }
}
