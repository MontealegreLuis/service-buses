package com.montealegreluis.servicebuses.domainevents;

public abstract class EventsCollector {
  protected DomainEvents events = new DomainEvents();

  public abstract boolean accepts(DomainEvent event);

  public void collect(DomainEvent event) {
    events.add(event);
  }

  public DomainEvents events() {
    DomainEvents collected = events;
    events = new DomainEvents();
    return collected;
  }
}
