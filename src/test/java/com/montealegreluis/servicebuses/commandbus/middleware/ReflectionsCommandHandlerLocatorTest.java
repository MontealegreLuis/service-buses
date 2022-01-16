package com.montealegreluis.servicebuses.commandbus.middleware;

import com.montealegreluis.servicebuses.commandbus.Command;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;
import com.montealegreluis.servicebuses.fakes.commandbus.CommandWithoutHandler;
import com.montealegreluis.servicebuses.fakes.commandbus.FakeCommand;
import com.montealegreluis.servicebuses.fakes.commandbus.FakeCommandHandler;
import com.montealegreluis.servicebuses.fakes.commandbus.middleware.CommandHandlerLocatorTest;

final class ReflectionsCommandHandlerLocatorTest extends CommandHandlerLocatorTest {
  @Override
  protected Class<? extends Command> commandWithoutHandler() {
    return CommandWithoutHandler.class;
  }

  @Override
  protected Class<? extends Command> commandWithHandler() {
    return FakeCommand.class;
  }

  @Override
  protected Class<? extends CommandHandler<? extends Command>> commandHandler() {
    return FakeCommandHandler.class;
  }

  @Override
  protected CommandHandlerLocator locator() {
    return new ReflectionsCommandHandlerLocator(
        "com.montealegreluis.servicebuses.fakes.commandbus");
  }
}
