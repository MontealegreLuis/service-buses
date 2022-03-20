package com.montealegreluis.servicebuses.fakes.domainevents;

import com.montealegreluis.servicebuses.domainevents.DomainEvents;
import com.montealegreluis.servicebuses.domainevents.EventBus;

public final class FakeEventBus implements EventBus {
  private DomainEvents domainEvents;

  @Override
  public void dispatch(DomainEvents events) {
    domainEvents = events;
  }

  public DomainEvents dispatchedEvents() {
    return domainEvents;
  }
}
