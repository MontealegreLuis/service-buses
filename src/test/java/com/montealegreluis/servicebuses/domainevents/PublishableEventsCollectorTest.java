package com.montealegreluis.servicebuses.domainevents;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.servicebuses.fakes.domainevents.FakeDomainEvent;
import com.montealegreluis.servicebuses.fakes.domainevents.FakePublishableDomainEvent;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class PublishableEventsCollectorTest {
  @Test
  void it_accepts_only_publishable_events() {
    assertTrue(collector.accepts(new FakePublishableDomainEvent()));
    assertFalse(collector.accepts(new FakeDomainEvent()));
  }

  @Test
  void it_resets_events_after_collecting_them() {
    collector.collect(new FakePublishableDomainEvent());
    collector.collect(new FakePublishableDomainEvent());
    collector.events(); // collect events

    var noEvents = collector.events().all(); // collect them a second time

    assertTrue(((ArrayList<DomainEvent>) noEvents).isEmpty());
  }

  @BeforeEach
  void let() {
    collector = new PublishableEventsCollector();
  }

  private PublishableEventsCollector collector;
}
