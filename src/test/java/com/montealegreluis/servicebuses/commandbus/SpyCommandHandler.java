package com.montealegreluis.servicebuses.commandbus;

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
