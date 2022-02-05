package com.montealegreluis.servicebuses.querybus.factory;

import com.montealegreluis.servicebuses.querybus.Query;
import com.montealegreluis.servicebuses.querybus.QueryHandler;
import com.montealegreluis.servicebuses.querybus.Response;

public interface QueryHandlerFactory {
  QueryHandler<? extends Query, ? extends Response> queryFromName(
      Class<? extends QueryHandler<? extends Query, ? extends Response>> queryClass)
      throws CannotCreateQueryHandler;
}
