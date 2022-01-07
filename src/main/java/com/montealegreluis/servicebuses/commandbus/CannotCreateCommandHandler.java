package com.montealegreluis.servicebuses.commandbus;

public final class CannotCreateCommandHandler extends RuntimeException {
  public static CannotCreateCommandHandler forCommand(
      Class<? extends CommandHandler> commandClass, Throwable cause) {
    return new CannotCreateCommandHandler(
        "Cannot create handler for command with class " + commandClass.getName(), cause);
  }

  private CannotCreateCommandHandler(String message, Throwable cause) {
    super(message, cause);
  }
}
