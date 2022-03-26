package com.montealegreluis.servicebuses.fakes.domainevents;

import com.montealegreluis.servicebuses.builders.Random;
import com.montealegreluis.servicebuses.domainevents.DomainEvent;
import com.montealegreluis.servicebuses.domainevents.PublishableEvent;

public final class FakePublishableDomainEvent extends DomainEvent implements PublishableEvent {
  @Override
  public String name() {
    return "context.aggregate.fakeEventOccurred";
  }

  @Override
  public String aggregateId() {
    return Random.uuid();
  }
}
