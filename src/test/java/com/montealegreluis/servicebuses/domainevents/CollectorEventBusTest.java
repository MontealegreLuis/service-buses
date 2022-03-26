package com.montealegreluis.servicebuses.domainevents;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.montealegreluis.servicebuses.fakes.domainevents.FakeDomainEvent;
import com.montealegreluis.servicebuses.fakes.domainevents.FakePublishableDomainEvent;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

final class CollectorEventBusTest {
  @Test
  void it_dispatches_all_collected_events() {
    var eventsCollector = new DomainEventsCollector();
    var publishableEventsCollector = new PublishableEventsCollector();
    var bus = new CollectorEventBus(List.of(eventsCollector, publishableEventsCollector));
    var events = new DomainEvents();
    events.add(new FakeDomainEvent());
    events.add(new FakeDomainEvent());
    events.add(new FakePublishableDomainEvent());

    bus.dispatch(events);

    assertEquals(3, ((ArrayList<DomainEvent>) eventsCollector.events().all()).size());
    assertEquals(1, ((ArrayList<DomainEvent>) publishableEventsCollector.events().all()).size());
  }
}
