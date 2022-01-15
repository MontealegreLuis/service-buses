package com.montealegreluis.servicebuses.commandbus;

import com.montealegreluis.servicebuses.fakes.commandbus.FakeCommandHandler;

final class InMemoryCommandHandlerFactoryTest extends CommandHandlerFactoryTest {
  @Override
  protected Class<? extends CommandHandler<? extends Command>> knownCommandName() {
    return FakeCommandHandler.class;
  }

  @Override
  protected Class<? extends CommandHandler<? extends Command>> unknownCommandName() {
    return StubCommandHandler.class;
  }

  @Override
  public CommandHandlerFactory factory() {
    var factory = new InMemoryCommandHandlerFactory();
    factory.add(FakeCommandHandler.class, new FakeCommandHandler());
    return factory;
  }
}
