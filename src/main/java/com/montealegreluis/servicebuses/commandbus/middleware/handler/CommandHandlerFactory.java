package com.montealegreluis.servicebuses.commandbus.middleware.handler;

import com.montealegreluis.servicebuses.commandbus.CommandHandler;

public interface CommandHandlerFactory {
  CommandHandler commandFromName(Class<? extends CommandHandler> commandClass)
      throws CannotCreateCommandHandler;
}
