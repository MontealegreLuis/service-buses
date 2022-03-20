package com.montealegreluis.servicebuses.domainevents;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.servicebuses.builders.Random;
import com.montealegreluis.servicebuses.fakes.domainevents.FakeAggregate;
import com.montealegreluis.servicebuses.fakes.domainevents.FakeDomainEvent;
import com.montealegreluis.servicebuses.fakes.domainevents.UuidIdentifier;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

final class AggregateRootTest {
  @Test
  void it_records_domain_events() {
    var aggregate = new FakeAggregate();
    aggregate.recordThat(new FakeDomainEvent());
    aggregate.recordThat(new FakeDomainEvent());

    var events = aggregate.events();

    assertEquals(2, ((ArrayList<DomainEvent>) events.all()).size());
  }

  @Test
  void it_flushes_its_events_after_reading_them() {
    var aggregate = new FakeAggregate();
    aggregate.recordThat(new FakeDomainEvent());
    aggregate.recordThat(new FakeDomainEvent());
    aggregate.events();
    aggregate.recordThat(new FakeDomainEvent());

    var events = aggregate.events();

    assertEquals(1, ((ArrayList<DomainEvent>) events.all()).size());
  }

  @Test
  void it_knows_its_identifier() {
    var id = new UuidIdentifier(Random.uuid());
    var aggregate = new FakeAggregate(id);

    var aggregateId = aggregate.id();

    assertEquals(id, aggregateId);
  }
}
