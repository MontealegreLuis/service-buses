package com.montealegreluis.servicebuses.domainevents;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class DomainEvents {
  private final List<DomainEvent> events = new ArrayList<>();

  public void add(DomainEvent event) {
    events.add(event);
  }

  public Iterable<DomainEvent> all() {
    return events;
  }

  public boolean isEmpty() {
    return events.isEmpty();
  }

  @Override
  public String toString() {
    String names = "";
    if (!events.isEmpty()) {
      names = events.stream().map(event -> event.name() + ",").collect(Collectors.joining());
      names = names.substring(0, names.length() - 1);
    }

    return "DomainEvents{size=" + events.size() + ",events=[" + names + "]}";
  }
}
