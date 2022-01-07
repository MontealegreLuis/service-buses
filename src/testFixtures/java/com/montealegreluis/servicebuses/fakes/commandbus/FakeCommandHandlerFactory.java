package com.montealegreluis.servicebuses.fakes.commandbus;

import com.montealegreluis.servicebuses.commandbus.CannotCreateCommandHandler;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;
import com.montealegreluis.servicebuses.commandbus.CommandHandlerFactory;

public final class FakeCommandHandlerFactory implements CommandHandlerFactory {
  private CommandHandler handler = null;

  public FakeCommandHandlerFactory() {}

  public FakeCommandHandlerFactory(CommandHandler handler) {
    this.handler = handler;
  }

  public void setHandler(CommandHandler handler) {
    this.handler = handler;
  }

  @Override
  public CommandHandler commandFromName(Class<? extends CommandHandler> commandClass) {
    if (handler == null || !commandClass.equals(handler.getClass())) {
      throw CannotCreateCommandHandler.forCommand(commandClass, null);
    }
    return handler;
  }
}
