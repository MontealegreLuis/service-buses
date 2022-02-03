package com.montealegreluis.servicebuses.querybus.middleware.handler;

import com.montealegreluis.servicebuses.querybus.QueryHandler;

public interface QueryHandlerFactory {
  QueryHandler queryFromName(Class<? extends QueryHandler> queryClass)
      throws CannotCreateQueryHandler;
}
