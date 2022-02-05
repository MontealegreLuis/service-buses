package com.montealegreluis.servicebuses.commandbus;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.servicebuses.ActionException;
import com.montealegreluis.servicebuses.commandbus.factory.CannotCreateCommandHandler;
import com.montealegreluis.servicebuses.commandbus.factory.InMemoryCommandHandlerFactory;
import com.montealegreluis.servicebuses.commandbus.locator.ReflectionsCommandHandlerLocator;
import com.montealegreluis.servicebuses.commandbus.locator.UnknownCommandHandler;
import com.montealegreluis.servicebuses.fakes.commandbus.CommandWithoutHandler;
import com.montealegreluis.servicebuses.fakes.commandbus.FakeCommand;
import com.montealegreluis.servicebuses.fakes.commandbus.FakeCommandHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class CommandBusDispatcherTest {
  @Test
  void it_locates_and_executes_a_command_handler() throws ActionException {
    var command = new FakeCommand();
    var handler = new FakeCommandHandler();
    factory.add(handler.getClass(), handler);

    commandBus.dispatch(command);

    assertEquals(command, handler.executedCommand(), "Command was not executed");
  }

  @Test
  void it_fails_when_no_handler_is_registered_for_a_command() {
    var command = new CommandWithoutHandler();

    assertThrows(UnknownCommandHandler.class, () -> commandBus.dispatch(command));
  }

  @Test
  void it_fails_if_command_handler_cannot_be_created() {
    var command = new FakeCommand();

    assertThrows(CannotCreateCommandHandler.class, () -> commandBus.dispatch(command));
  }

  @BeforeEach
  void let() {
    var locator =
        new ReflectionsCommandHandlerLocator("com.montealegreluis.servicebuses.fakes.commandbus");
    factory = new InMemoryCommandHandlerFactory();
    commandBus = new CommandBusDispatcher(locator, factory);
  }

  private InMemoryCommandHandlerFactory factory;
  private CommandBusDispatcher commandBus;
}
