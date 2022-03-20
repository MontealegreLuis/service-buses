package com.montealegreluis.servicebuses.fakes.domainevents;

import com.montealegreluis.servicebuses.builders.Random;
import com.montealegreluis.servicebuses.domainevents.DomainEvent;
import java.util.Date;

public final class FakeDomainEvent extends DomainEvent {
  private final String aggregateId;

  public FakeDomainEvent() {
    this(Random.uuid(), new Date());
  }

  public FakeDomainEvent(String aggregateId, Date occurredOn) {
    this.aggregateId = aggregateId;
    this.occurredOn = occurredOn;
  }

  @Override
  public String name() {
    return "context.aggregate.eventHasOccurred";
  }

  @Override
  public String aggregateId() {
    return aggregateId;
  }

  @Override
  public int hashCode() {
    return name().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (!(obj instanceof FakeDomainEvent)) return false;

    FakeDomainEvent other = (FakeDomainEvent) obj;
    return other.name().equals(name());
  }
}
