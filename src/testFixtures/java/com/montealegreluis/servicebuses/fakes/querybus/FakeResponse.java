package com.montealegreluis.servicebuses.fakes.querybus;

import com.montealegreluis.servicebuses.querybus.Response;

public final class FakeResponse implements Response {
  @Override
  public boolean equals(Object another) {
    if (another == null) return false;
    return another instanceof FakeResponse;
  }
}
