package com.montealegreluis.servicebuses.querybus;

import com.montealegreluis.servicebuses.ActionException;

public interface QueryBus {
  <R extends Response> R dispatch(Query query) throws ActionException;
}
