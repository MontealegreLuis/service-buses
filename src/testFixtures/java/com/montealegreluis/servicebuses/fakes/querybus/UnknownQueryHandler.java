package com.montealegreluis.servicebuses.fakes.querybus;

import com.montealegreluis.servicebuses.ActionException;
import com.montealegreluis.servicebuses.querybus.Query;
import com.montealegreluis.servicebuses.querybus.QueryHandler;
import com.montealegreluis.servicebuses.querybus.Response;

public final class UnknownQueryHandler implements QueryHandler<Query, Response> {
  @Override
  public Response execute(Query Query) throws ActionException {
    return new FakeResponse();
  }
}
