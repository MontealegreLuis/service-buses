package com.montealegreluis.servicebuses.querybus.middleware.handler;

import com.montealegreluis.servicebuses.ReflectionsActionMapper;
import com.montealegreluis.servicebuses.querybus.Query;
import com.montealegreluis.servicebuses.querybus.QueryHandler;
import com.montealegreluis.servicebuses.querybus.Response;
import java.util.Map;

public final class ReflectionsQueryHandlerLocator implements QueryHandlerLocator {
  private final Map<
          Class<? extends Query>,
          Class<? extends QueryHandler<? extends Query, ? extends Response>>>
      queryHandlers;

  public ReflectionsQueryHandlerLocator(String packageName) {
    ReflectionsActionMapper<
            Class<? extends Query>,
            Class<? extends QueryHandler<? extends Query, ? extends Response>>>
        mapper = new ReflectionsActionMapper<>(packageName);
    queryHandlers = mapper.map(QueryHandler.class);
  }

  @Override
  public Class<? extends QueryHandler<? extends Query, ? extends Response>> search(
      Class<? extends Query> queryClass) throws UnknownQueryHandler {
    var queryHandlerClass = queryHandlers.get(queryClass);

    if (null == queryHandlerClass) throw UnknownQueryHandler.forQuery(queryClass);

    return queryHandlerClass;
  }
}
