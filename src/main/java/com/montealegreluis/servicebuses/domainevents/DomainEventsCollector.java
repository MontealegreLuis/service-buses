package com.montealegreluis.servicebuses.domainevents;

public final class DomainEventsCollector extends EventsCollector {
  @Override
  public boolean accepts(DomainEvent event) {
    return true;
  }
}
