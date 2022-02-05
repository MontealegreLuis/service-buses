package com.montealegreluis.servicebuses.querybus;

import com.montealegreluis.servicebuses.ActionException;
import com.montealegreluis.servicebuses.querybus.factory.QueryHandlerFactory;
import com.montealegreluis.servicebuses.querybus.locator.QueryHandlerLocator;

public final class QueryBusDispatcher implements QueryBus {
  private final QueryHandlerLocator locator;
  private final QueryHandlerFactory factory;

  public QueryBusDispatcher(QueryHandlerLocator locator, QueryHandlerFactory factory) {
    this.locator = locator;
    this.factory = factory;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <R extends Response> R dispatch(Query query) throws ActionException {
    final var queryHandlerName = locator.search(query.getClass());
    final var handler = (QueryHandler<Query, Response>) factory.queryFromName(queryHandlerName);

    return (R) handler.execute(query);
  }
}
