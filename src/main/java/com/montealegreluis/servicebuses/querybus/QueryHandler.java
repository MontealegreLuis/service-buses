package com.montealegreluis.servicebuses.querybus;

import com.montealegreluis.servicebuses.ActionException;

public interface QueryHandler<Q extends Query, R extends Response> {
  R execute(Q Query) throws ActionException;
}
