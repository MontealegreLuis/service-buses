package com.montealegreluis.servicebuses.fakes.querybus;

import com.montealegreluis.servicebuses.ActionException;
import com.montealegreluis.servicebuses.querybus.Query;
import com.montealegreluis.servicebuses.querybus.QueryHandler;
import com.montealegreluis.servicebuses.querybus.Response;

/** This handler will be ignored by the handler locator since it's not a generic class */
public final class NonGenericQueryHandler implements QueryHandler {
  @Override
  public Response execute(Query Query) throws ActionException {
    return new FakeResponse();
  }
}
