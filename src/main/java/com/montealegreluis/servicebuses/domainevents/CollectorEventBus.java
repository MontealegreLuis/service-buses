package com.montealegreluis.servicebuses.domainevents;

import java.util.List;

public final class CollectorEventBus implements EventBus {
  private final List<EventsCollector> collectors;

  public CollectorEventBus(List<EventsCollector> collectors) {
    this.collectors = collectors;
  }

  @Override
  public void dispatch(DomainEvents events) {
    events.all().forEach(this::collect);
  }

  private void collect(DomainEvent event) {
    collectors.stream()
        .filter(collector -> collector.accepts(event))
        .forEach(collector -> collector.collect(event));
  }
}
