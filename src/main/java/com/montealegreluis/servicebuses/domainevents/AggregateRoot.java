package com.montealegreluis.servicebuses.domainevents;

public abstract class AggregateRoot {
  private DomainEvents events = new DomainEvents();

  protected void recordThat(DomainEvent event) {
    events.add(event);
  }

  public DomainEvents events() {
    final DomainEvents recordedEvents = events;
    events = new DomainEvents();
    return recordedEvents;
  }

  public abstract Identifier id();
}
