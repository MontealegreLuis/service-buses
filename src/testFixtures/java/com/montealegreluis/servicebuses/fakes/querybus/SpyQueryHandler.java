package com.montealegreluis.servicebuses.fakes.querybus;

import com.montealegreluis.servicebuses.ActionException;
import com.montealegreluis.servicebuses.querybus.Query;
import com.montealegreluis.servicebuses.querybus.QueryHandler;
import com.montealegreluis.servicebuses.querybus.Response;

public final class SpyQueryHandler implements QueryHandler<Query, Response> {
  private boolean called = false;

  @Override
  public Response execute(Query Query) throws ActionException {
    called = true;
    return new FakeResponse();
  }

  public boolean wasCalled() {
    return called;
  }
}
