package com.montealegreluis.servicebuses.fakes.domainevents;

import com.montealegreluis.servicebuses.builders.Random;
import com.montealegreluis.servicebuses.domainevents.AggregateRoot;
import com.montealegreluis.servicebuses.domainevents.Identifier;

public final class FakeAggregate extends AggregateRoot {
  private UuidIdentifier id = new UuidIdentifier(Random.uuid());

  public FakeAggregate(UuidIdentifier id) {
    this.id = id;
  }

  public FakeAggregate() {}

  @Override
  public Identifier id() {
    return id;
  }
}
