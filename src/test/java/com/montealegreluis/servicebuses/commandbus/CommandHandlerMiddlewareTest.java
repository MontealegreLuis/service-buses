package com.montealegreluis.servicebuses.commandbus;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.servicebuses.fakes.commandbus.CommandWithoutHandler;
import com.montealegreluis.servicebuses.fakes.commandbus.FakeCommand;
import com.montealegreluis.servicebuses.fakes.commandbus.FakeCommandHandler;
import com.montealegreluis.servicebuses.fakes.commandbus.FakeCommandHandlerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class CommandHandlerMiddlewareTest {
  @Test
  void it_locates_and_executes_a_command_handler() {
    var command = new FakeCommand();
    var handler = new FakeCommandHandler();
    factory.setHandler(handler);
    var next = new StubCommandHandler();

    middleware.execute(command, next);

    assertEquals(command, handler.executedCommand(), "Command was not executed");
  }

  @Test
  void it_fails_when_no_handler_is_registered_for_a_command() {
    var command = new CommandWithoutHandler();
    var handler = new StubCommandHandler();

    assertThrows(UnknownCommandHandler.class, () -> middleware.execute(command, handler));
  }

  @BeforeEach
  void let() {
    factory = new FakeCommandHandlerFactory();
    var locator = new CommandHandlersLocator("com.montealegreluis.servicebuses.fakes.commandbus");
    middleware = new CommandHandlerMiddleware(locator, factory);
  }

  private CommandHandlerMiddleware middleware;
  private FakeCommandHandlerFactory factory;
}
