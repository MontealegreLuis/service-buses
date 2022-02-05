package com.montealegreluis.servicebuses.querybus.factory;

import com.montealegreluis.servicebuses.querybus.Query;
import com.montealegreluis.servicebuses.querybus.QueryHandler;
import com.montealegreluis.servicebuses.querybus.Response;
import java.util.LinkedHashMap;
import java.util.Map;

public final class InMemoryQueryHandlerFactory implements QueryHandlerFactory {
  private final Map<
          Class<? extends QueryHandler<? extends Query, ? extends Response>>,
          QueryHandler<? extends Query, ? extends Response>>
      handlers = new LinkedHashMap<>();

  public void add(
      Class<? extends QueryHandler<? extends Query, ? extends Response>> queryClass,
      QueryHandler<? extends Query, ? extends Response> handler) {
    handlers.put(queryClass, handler);
  }

  @Override
  public QueryHandler<? extends Query, ? extends Response> queryFromName(
      Class<? extends QueryHandler<? extends Query, ? extends Response>> queryClass)
      throws CannotCreateQueryHandler {
    QueryHandler<? extends Query, ? extends Response> handler = handlers.get(queryClass);
    if (handler == null) {
      throw CannotCreateQueryHandler.forQuery(queryClass, null);
    }
    return handler;
  }
}
