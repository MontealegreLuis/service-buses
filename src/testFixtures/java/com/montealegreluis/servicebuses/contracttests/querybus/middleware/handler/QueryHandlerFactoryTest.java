package com.montealegreluis.servicebuses.contracttests.querybus.middleware.handler;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.montealegreluis.servicebuses.querybus.Query;
import com.montealegreluis.servicebuses.querybus.QueryHandler;
import com.montealegreluis.servicebuses.querybus.Response;
import com.montealegreluis.servicebuses.querybus.middleware.handler.CannotCreateQueryHandler;
import com.montealegreluis.servicebuses.querybus.middleware.handler.QueryHandlerFactory;
import org.junit.jupiter.api.Test;

public abstract class QueryHandlerFactoryTest {
  @Test
  void it_fails_to_create_an_unknown_query_handler() {
    var factory = factory();
    var unknownQueryName = unknownQueryName();

    assertThrows(CannotCreateQueryHandler.class, () -> factory.queryFromName(unknownQueryName));
  }

  @Test
  void it_creates_a_command_handler() throws CannotCreateQueryHandler {
    var factory = factory();
    var knownCommandName = knownQueryName();

    var queryHandler = factory.queryFromName(knownCommandName);

    assertNotNull(queryHandler);
  }

  protected abstract Class<? extends QueryHandler<? extends Query, ? extends Response>>
      knownQueryName();

  protected abstract Class<? extends QueryHandler<? extends Query, ? extends Response>>
      unknownQueryName();

  public abstract QueryHandlerFactory factory();
}
