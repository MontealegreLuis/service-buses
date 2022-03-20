package com.montealegreluis.servicebuses.domainevents;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.servicebuses.fakes.domainevents.FakeDomainEvent;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

final class DomainEventsTest {
  @Test
  void it_formats_in_user_friendly_format_a_collection_of_events() {
    var events = new DomainEvents();
    events.add(new FakeDomainEvent());
    events.add(new FakeDomainEvent());

    var allEvents = events.toString();

    assertEquals(
        "DomainEvents{size=2,events=[context.aggregate.eventHasOccurred,context.aggregate.eventHasOccurred]}",
        allEvents);
  }

  @Test
  void it_is_initially_empty() {
    var events = new DomainEvents();

    var empty = events.isEmpty();

    assertTrue(empty);
    assertEquals("DomainEvents{size=0,events=[]}", events.toString());
  }

  @Test
  void it_knows_when_it_is_empty() {
    var events = new DomainEvents();
    assertTrue(events.isEmpty());

    events.add(new FakeDomainEvent());
    assertFalse(events.isEmpty());
  }

  @Test
  void it_has_access_to_all_its_events() {
    var events = new DomainEvents();
    var eventA = new FakeDomainEvent();
    var eventB = new FakeDomainEvent();
    events.add(eventA);
    events.add(eventB);

    var all = events.all();

    assertEquals(eventA, ((ArrayList<DomainEvent>) all).get(0));
    assertEquals(eventB, ((ArrayList<DomainEvent>) all).get(1));
  }

  @Test
  void it_can_be_compared_to_other_domain_events() {
    var eventsA = new DomainEvents();
    eventsA.add(new FakeDomainEvent());
    var eventsB = new DomainEvents();
    eventsB.add(new FakeDomainEvent());
    eventsB.add(new FakeDomainEvent());

    assertNotEquals(eventsA, eventsB);
    assertNotEquals(eventsA, null);
    assertEquals(eventsA, eventsA);
    var eventsC = new DomainEvents();
    eventsC.add(new FakeDomainEvent());
    assertEquals(eventsA, eventsC);
    assertEquals(eventsA.hashCode(), eventsC.hashCode());
    assertNotEquals(eventsA.hashCode(), eventsB.hashCode());
  }
}
