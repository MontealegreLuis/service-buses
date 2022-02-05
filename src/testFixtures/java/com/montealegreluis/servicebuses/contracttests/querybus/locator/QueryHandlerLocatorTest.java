package com.montealegreluis.servicebuses.contracttests.querybus.locator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.montealegreluis.servicebuses.querybus.Query;
import com.montealegreluis.servicebuses.querybus.QueryHandler;
import com.montealegreluis.servicebuses.querybus.Response;
import com.montealegreluis.servicebuses.querybus.locator.QueryHandlerLocator;
import com.montealegreluis.servicebuses.querybus.locator.UnknownQueryHandler;
import org.junit.jupiter.api.Test;

public abstract class QueryHandlerLocatorTest {
  @Test
  void it_fails_when_no_command_handler_exist_for_given_command() {
    var locator = locator();
    var queryName = queryWithoutHandler();

    assertThrows(UnknownQueryHandler.class, () -> locator.search(queryName));
  }

  @Test
  void it_finds_the_command_handler_of_a_given_command() throws UnknownQueryHandler {
    var locator = locator();
    var queryName = queryWithHandler();
    var handlerName = queryHandler();

    var handlerClass = locator.search(queryName);

    assertEquals(handlerName, handlerClass);
  }

  protected abstract Class<? extends Query> queryWithoutHandler();

  protected abstract Class<? extends Query> queryWithHandler();

  protected abstract Class<? extends QueryHandler<? extends Query, ? extends Response>>
      queryHandler();

  protected abstract QueryHandlerLocator locator();
}
