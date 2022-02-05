package com.montealegreluis.servicebuses.fakes.querybus.middleware;

import com.montealegreluis.servicebuses.ActionException;
import com.montealegreluis.servicebuses.fakes.querybus.FakeResponse;
import com.montealegreluis.servicebuses.querybus.Query;
import com.montealegreluis.servicebuses.querybus.QueryHandler;
import com.montealegreluis.servicebuses.querybus.Response;
import com.montealegreluis.servicebuses.querybus.middleware.QueryMiddleware;

public final class FakeQueryMiddleware implements QueryMiddleware {
  private boolean executed = false;

  @Override
  public Response execute(Query query, QueryHandler<Query, Response> next) throws ActionException {
    executed = true;
    return new FakeResponse();
  }

  public boolean wasExecuted() {
    return executed;
  }
}