package com.montealegreluis.servicebuses.domainevents;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.servicebuses.fakes.domainevents.FakeDomainEvent;
import com.montealegreluis.servicebuses.fakes.domainevents.FakeEventBus;
import org.junit.jupiter.api.Test;

final class EventBusTest {
  @Test
  void it_dispatches_recorded_events() {
    var bus = new FakeEventBus();
    var events = new DomainEvents();
    var eventA = new FakeDomainEvent();
    var eventB = new FakeDomainEvent();
    events.add(eventA);
    events.add(eventB);

    bus.dispatch(events);

    assertEquals(2, bus.dispatchedEventsCount());
    assertTrue(bus.containsEvent(eventA));
    assertTrue(bus.containsEvent(eventB));
  }
}
