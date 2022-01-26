package com.montealegreluis.servicebuses.commandbus.middleware.handler;

import com.montealegreluis.servicebuses.commandbus.Command;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;
import com.montealegreluis.servicebuses.contracttests.commandbus.middleware.handler.CommandHandlerFactoryTest;
import com.montealegreluis.servicebuses.fakes.commandbus.FakeCommandHandler;
import com.montealegreluis.servicebuses.fakes.commandbus.SpyCommandHandler;

final class InMemoryCommandHandlerFactoryTest extends CommandHandlerFactoryTest {
  @Override
  protected Class<? extends CommandHandler<? extends Command>> knownCommandName() {
    return FakeCommandHandler.class;
  }

  @Override
  protected Class<? extends CommandHandler<? extends Command>> unknownCommandName() {
    return SpyCommandHandler.class;
  }

  @Override
  public CommandHandlerFactory factory() {
    var factory = new InMemoryCommandHandlerFactory();
    factory.add(FakeCommandHandler.class, new FakeCommandHandler());
    return factory;
  }
}
