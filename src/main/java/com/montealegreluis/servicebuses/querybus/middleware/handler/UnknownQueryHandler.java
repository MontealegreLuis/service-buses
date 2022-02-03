package com.montealegreluis.servicebuses.querybus.middleware.handler;

import com.montealegreluis.servicebuses.ActionException;
import com.montealegreluis.servicebuses.querybus.Query;

public final class UnknownQueryHandler extends ActionException {
  public UnknownQueryHandler(String message) {
    super(message);
  }

  public static UnknownQueryHandler forQuery(Class<? extends Query> query) {
    return new UnknownQueryHandler("Cannot find query handler for query" + query.getName());
  }
}
