package com.montealegreluis.servicebuses.querybus.factory;

import com.montealegreluis.servicebuses.contracttests.querybus.factory.QueryHandlerFactoryTest;
import com.montealegreluis.servicebuses.fakes.querybus.FakeQueryHandler;
import com.montealegreluis.servicebuses.fakes.querybus.SpyQueryHandler;
import com.montealegreluis.servicebuses.querybus.Query;
import com.montealegreluis.servicebuses.querybus.QueryHandler;
import com.montealegreluis.servicebuses.querybus.Response;

final class InMemoryQueryHandlerFactoryTest extends QueryHandlerFactoryTest {
  @Override
  protected Class<? extends QueryHandler<? extends Query, ? extends Response>> knownQueryName() {
    return FakeQueryHandler.class;
  }

  @Override
  protected Class<? extends QueryHandler<? extends Query, ? extends Response>> unknownQueryName() {
    return SpyQueryHandler.class;
  }

  @Override
  public QueryHandlerFactory factory() {
    var factory = new InMemoryQueryHandlerFactory();
    factory.add(FakeQueryHandler.class, new FakeQueryHandler());
    return factory;
  }
}
