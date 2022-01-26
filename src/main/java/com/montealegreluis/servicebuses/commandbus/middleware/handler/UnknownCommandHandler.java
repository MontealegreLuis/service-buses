package com.montealegreluis.servicebuses.commandbus.middleware.handler;

import com.montealegreluis.servicebuses.commandbus.Command;

public final class UnknownCommandHandler extends RuntimeException {
  public UnknownCommandHandler(String message) {
    super(message);
  }

  public static UnknownCommandHandler forCommand(Class<? extends Command> command) {
    return new UnknownCommandHandler("Cannot find command handler for command" + command.getName());
  }
}
