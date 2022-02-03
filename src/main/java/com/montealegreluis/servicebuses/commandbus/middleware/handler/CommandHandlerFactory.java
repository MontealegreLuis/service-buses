package com.montealegreluis.servicebuses.commandbus.middleware.handler;

import com.montealegreluis.servicebuses.commandbus.Command;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;

public interface CommandHandlerFactory {
  CommandHandler<? extends Command> commandFromName(
      Class<? extends CommandHandler<? extends Command>> commandClass)
      throws CannotCreateCommandHandler;
}
