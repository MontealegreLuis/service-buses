package com.montealegreluis.servicebuses.commandbus.factory;

import com.montealegreluis.servicebuses.ActionException;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;

public final class CannotCreateCommandHandler extends ActionException {
  public static CannotCreateCommandHandler forCommand(
      Class<? extends CommandHandler> commandClass, Throwable cause) {
    return new CannotCreateCommandHandler(
        "Cannot create handler for command with class " + commandClass.getName(), cause);
  }

  private CannotCreateCommandHandler(String message, Throwable cause) {
    super(message, cause);
  }
}
