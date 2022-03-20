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
    events.add(new FakeDomainEvent());
    events.add(new FakeDomainEvent());

    bus.dispatch(events);

    assertEquals(events, bus.dispatchedEvents());
  }
}
