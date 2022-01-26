package com.montealegreluis.servicebuses.fakes.commandbus;

import com.montealegreluis.servicebuses.commandbus.Command;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;
import com.montealegreluis.servicebuses.commandbus.CommandMiddleware;

public final class FakeCommandMiddleware implements CommandMiddleware<Command> {
  private boolean executed = false;

  @Override
  public void execute(Command command, CommandHandler<Command> next) {
    executed = true;
    next.execute(command);
  }

  public boolean hasBeenExecuted() {
    return executed;
  }
}