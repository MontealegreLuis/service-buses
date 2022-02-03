package com.montealegreluis.servicebuses.querybus.middleware.handler;

import com.montealegreluis.servicebuses.ActionException;
import com.montealegreluis.servicebuses.querybus.Query;
import com.montealegreluis.servicebuses.querybus.QueryHandler;
import com.montealegreluis.servicebuses.querybus.Response;
import com.montealegreluis.servicebuses.querybus.middleware.QueryMiddleware;

public final class QueryHandlerMiddleware implements QueryMiddleware {
  private final QueryHandlerLocator locator;
  private final QueryHandlerFactory factory;

  public QueryHandlerMiddleware(QueryHandlerLocator locator, QueryHandlerFactory factory) {
    this.locator = locator;
    this.factory = factory;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Response execute(Query query, QueryHandler<Query, Response> next) throws ActionException {
    var handler =
        (QueryHandler<Query, Response>) factory.queryFromName(locator.search(query.getClass()));
    return handler.execute(query);
  }
}
