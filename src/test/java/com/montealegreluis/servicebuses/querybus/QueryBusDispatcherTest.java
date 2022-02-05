package com.montealegreluis.servicebuses.querybus;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.servicebuses.ActionException;
import com.montealegreluis.servicebuses.fakes.querybus.*;
import com.montealegreluis.servicebuses.querybus.factory.CannotCreateQueryHandler;
import com.montealegreluis.servicebuses.querybus.factory.InMemoryQueryHandlerFactory;
import com.montealegreluis.servicebuses.querybus.locator.ReflectionsQueryHandlerLocator;
import com.montealegreluis.servicebuses.querybus.locator.UnknownQueryHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class QueryBusDispatcherTest {
  @Test
  void it_locates_and_executes_a_query_handler() throws ActionException {
    var query = new FakeQuery();
    var handler = new FakeQueryHandler();
    factory.add(handler.getClass(), handler);

    var response = queryBus.dispatch(query);

    assertEquals(new FakeResponse(), response);
    assertEquals(query, handler.executedQuery(), "Query was not executed");
  }

  @Test
  void it_fails_when_no_handler_is_registered_for_a_query() {
    var query = new QueryWithoutHandler();

    assertThrows(UnknownQueryHandler.class, () -> queryBus.dispatch(query));
  }

  @Test
  void it_fails_if_query_handler_cannot_be_created() {
    var command = new FakeQuery();

    assertThrows(CannotCreateQueryHandler.class, () -> queryBus.dispatch(command));
  }

  @BeforeEach
  void let() {
    var locator =
        new ReflectionsQueryHandlerLocator("com.montealegreluis.servicebuses.fakes.querybus");
    factory = new InMemoryQueryHandlerFactory();
    queryBus = new QueryBusDispatcher(locator, factory);
  }

  private InMemoryQueryHandlerFactory factory;
  private QueryBusDispatcher queryBus;
}
