package com.montealegreluis.servicebuses.commandbus.factory;

import com.montealegreluis.servicebuses.commandbus.Command;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;
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
  public CommandHandler<? extends Command> commandFromName(
      Class<? extends CommandHandler<? extends Command>> commandClass)
      throws CannotCreateCommandHandler {
    CommandHandler<? extends Command> handler = handlers.get(commandClass);
    if (handler == null) {
      throw CannotCreateCommandHandler.forCommand(commandClass, null);
    }
    return handler;
  }
}
