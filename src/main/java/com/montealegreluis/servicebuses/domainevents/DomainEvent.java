package com.montealegreluis.servicebuses.domainevents;

import com.montealegreluis.servicebuses.Action;
import com.montealegreluis.servicebuses.Input;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public abstract class DomainEvent implements Input {
  protected String name;
  protected Date occurredOn;

  public DomainEvent() {
    name = name();
    occurredOn = Date.from(OffsetDateTime.now(ZoneOffset.UTC).toInstant());
  }

  public Date occurredOn() {
    return occurredOn;
  }

  @Override
  public Action action() {
    return Action.named(getClass().getSimpleName());
  }

  public abstract String name();

  public abstract String aggregateId();
}
