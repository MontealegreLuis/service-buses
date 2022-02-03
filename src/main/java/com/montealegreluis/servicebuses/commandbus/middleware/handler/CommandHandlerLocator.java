package com.montealegreluis.servicebuses.commandbus.middleware.handler;

import com.montealegreluis.servicebuses.commandbus.Command;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;

public interface CommandHandlerLocator {
  Class<? extends CommandHandler<? extends Command>> search(Class<? extends Command> commandClass)
      throws UnknownCommandHandler;
}
