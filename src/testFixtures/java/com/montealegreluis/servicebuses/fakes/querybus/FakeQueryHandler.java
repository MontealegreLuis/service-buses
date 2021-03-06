package com.montealegreluis.servicebuses.fakes.querybus;

import com.montealegreluis.servicebuses.ActionException;
import com.montealegreluis.servicebuses.querybus.Query;
import com.montealegreluis.servicebuses.querybus.QueryHandler;
import com.montealegreluis.servicebuses.querybus.Response;

public final class FakeQueryHandler implements QueryHandler<FakeQuery, Response> {
  private Query query;

  @Override
  public Response execute(FakeQuery query) throws ActionException {
    this.query = query;
    return new FakeResponse();
  }

  public Query executedQuery() {
    return query;
  }
}
