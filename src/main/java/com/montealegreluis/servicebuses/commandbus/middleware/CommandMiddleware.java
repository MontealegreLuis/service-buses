package com.montealegreluis.servicebuses.commandbus.middleware;

import com.montealegreluis.servicebuses.commandbus.Command;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;

public interface CommandMiddleware {
  void execute(Command command, CommandHandler<Command> next);
}
