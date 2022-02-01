package com.montealegreluis.servicebuses.querybus;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.servicebuses.Action;
import com.montealegreluis.servicebuses.fakes.querybus.FakeQuery;
import org.junit.jupiter.api.Test;

final class QueryTest {
  @Test
  void it_knows_its_action() {
    var query = new FakeQuery();

    var action = query.action();

    assertEquals(Action.withoutSuffix("FakeQueryAction", "Action"), action);
  }
}
