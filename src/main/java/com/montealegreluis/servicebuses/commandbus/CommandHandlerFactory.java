package com.montealegreluis.servicebuses.commandbus;

public interface CommandHandlerFactory {
  CommandHandler commandFromName(Class<? extends CommandHandler> commandClass);
}
