package com.montealegreluis.servicebuses.querybus.locator;

import com.montealegreluis.servicebuses.contracttests.querybus.locator.QueryHandlerLocatorTest;
import com.montealegreluis.servicebuses.fakes.querybus.FakeQuery;
import com.montealegreluis.servicebuses.fakes.querybus.FakeQueryHandler;
import com.montealegreluis.servicebuses.fakes.querybus.QueryWithoutHandler;
import com.montealegreluis.servicebuses.querybus.Query;
import com.montealegreluis.servicebuses.querybus.QueryHandler;
import com.montealegreluis.servicebuses.querybus.Response;

final class ReflectionsQueryHandlerLocatorTest extends QueryHandlerLocatorTest {
  @Override
  protected Class<? extends Query> queryWithoutHandler() {
    return QueryWithoutHandler.class;
  }

  @Override
  protected Class<? extends Query> queryWithHandler() {
    return FakeQuery.class;
  }

  @Override
  protected Class<? extends QueryHandler<? extends Query, ? extends Response>> queryHandler() {
    return FakeQueryHandler.class;
  }

  @Override
  protected QueryHandlerLocator locator() {
    return new ReflectionsQueryHandlerLocator("com.montealegreluis.servicebuses.fakes.querybus");
  }
}
