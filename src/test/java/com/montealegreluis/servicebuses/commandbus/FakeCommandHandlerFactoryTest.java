package com.montealegreluis.servicebuses.commandbus;

import com.montealegreluis.servicebuses.fakes.commandbus.FakeCommandHandler;
import com.montealegreluis.servicebuses.fakes.commandbus.FakeCommandHandlerFactory;

final class FakeCommandHandlerFactoryTest extends CommandHandlerFactoryTest {
  @Override
  protected Class<? extends CommandHandler> knownCommandName() {
    return StubCommandHandler.class;
  }

  @Override
  protected Class<? extends CommandHandler> unknownCommandName() {
    return FakeCommandHandler.class;
  }

  @Override
  public CommandHandlerFactory factory() {
    var factory = new FakeCommandHandlerFactory();
    factory.setHandler(new StubCommandHandler());
    return factory;
  }
}
