package com.montealegreluis.servicebuses.domainevents;

public class PublishableEventsCollector extends EventsCollector {
  @Override
  public boolean accepts(DomainEvent event) {
    return event instanceof PublishableEvent;
  }
}
