package com.montealegreluis.servicebuses.domainevents;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.servicebuses.Action;
import com.montealegreluis.servicebuses.builders.Random;
import com.montealegreluis.servicebuses.fakes.domainevents.FakeDomainEvent;
import java.util.Date;
import org.junit.jupiter.api.Test;

final class DomainEventTest {
  @Test
  void it_knows_its_name_what_aggregate_recorded_it_and_the_time_when_it_occurred() {
    var aggregateId = Random.uuid();
    var occurredOn = new Date();
    var event = new FakeDomainEvent(aggregateId, occurredOn);

    assertEquals(occurredOn, event.occurredOn());
    assertEquals(aggregateId, event.aggregateId());
    assertEquals("context.aggregate.eventHasOccurred", event.name());
  }

  @Test
  void it_could_be_also_used_as_input_for_actions() {
    var action = Action.named("FakeDomainEvent");
    var event = new FakeDomainEvent();

    assertEquals(action, event.action());
  }
}
