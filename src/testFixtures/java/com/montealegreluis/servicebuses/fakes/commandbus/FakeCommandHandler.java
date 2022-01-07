package com.montealegreluis.servicebuses.fakes.commandbus;

import com.montealegreluis.servicebuses.commandbus.Command;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;

public final class FakeCommandHandler implements CommandHandler<FakeCommand> {
  private Command command;

  @Override
  public void execute(FakeCommand command) {
    this.command = command;
  }

  public Command executedCommand() {
    return command;
  }
}
