package com.montealegreluis.servicebuses.fakes.commandbus;

import com.montealegreluis.servicebuses.commandbus.Command;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;

public final class SpyCommandHandler implements CommandHandler<Command> {
  private boolean called = false;

  @Override
  public void execute(Command command) {
    called = true;
  }

  public boolean wasCalled() {
    return called;
  }
}
