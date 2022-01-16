package com.montealegreluis.servicebuses.commandbus.middleware;

import com.montealegreluis.servicebuses.commandbus.CommandHandler;

public interface CommandHandlerFactory {
  /** @throws CannotCreateCommandHandler If command handler cannot be created */
  CommandHandler commandFromName(Class<? extends CommandHandler> commandClass);
}
