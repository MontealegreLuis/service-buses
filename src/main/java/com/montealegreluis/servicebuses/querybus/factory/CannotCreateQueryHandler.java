package com.montealegreluis.servicebuses.querybus.factory;

import com.montealegreluis.servicebuses.ActionException;
import com.montealegreluis.servicebuses.querybus.QueryHandler;

public final class CannotCreateQueryHandler extends ActionException {
  public static CannotCreateQueryHandler forQuery(
      Class<? extends QueryHandler> queryClass, Throwable cause) {
    return new CannotCreateQueryHandler(
        "Cannot create handler for query with class " + queryClass.getName(), cause);
  }

  private CannotCreateQueryHandler(String message, Throwable cause) {
    super(message, cause);
  }
}
