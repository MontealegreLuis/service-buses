package com.montealegreluis.servicebuses.querybus.locator;

import com.montealegreluis.servicebuses.querybus.Query;
import com.montealegreluis.servicebuses.querybus.QueryHandler;
import com.montealegreluis.servicebuses.querybus.Response;

public interface QueryHandlerLocator {
  Class<? extends QueryHandler<? extends Query, ? extends Response>> search(
      Class<? extends Query> queryClass) throws UnknownQueryHandler;
}
