package com.montealegreluis.servicebuses.querybus.middleware.handler;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.servicebuses.ActionException;
import com.montealegreluis.servicebuses.fakes.querybus.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class QueryHandlerMiddlewareTest {
  @Test
  void it_locates_and_executes_a_query_handler() throws ActionException {
    var query = new FakeQuery();
    var handler = new FakeQueryHandler();
    factory.add(handler.getClass(), handler);
    var next = new SpyQueryHandler();

    var response = middleware.execute(query, next);

    assertEquals(new FakeResponse(), response);
    assertEquals(query, handler.executedQuery(), "Query was not executed");
  }

  @Test
  void it_fails_when_no_handler_is_registered_for_a_query() {
    var query = new QueryWithoutHandler();
    var handler = new SpyQueryHandler();

    assertThrows(UnknownQueryHandler.class, () -> middleware.execute(query, handler));
  }

  @BeforeEach
  void let() {
    factory = new InMemoryQueryHandlerFactory();
    var locator =
        new ReflectionsQueryHandlerLocator("com.montealegreluis.servicebuses.fakes.querybus");
    middleware = new QueryHandlerMiddleware(locator, factory);
  }

  private QueryHandlerMiddleware middleware;
  private InMemoryQueryHandlerFactory factory;
}
