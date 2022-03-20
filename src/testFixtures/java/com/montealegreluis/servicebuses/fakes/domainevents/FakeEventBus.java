package com.montealegreluis.servicebuses.fakes.domainevents;

import com.montealegreluis.servicebuses.domainevents.DomainEvent;
import com.montealegreluis.servicebuses.domainevents.DomainEvents;
import com.montealegreluis.servicebuses.domainevents.EventBus;
import java.util.ArrayList;

public final class FakeEventBus implements EventBus {
  private DomainEvents domainEvents;

  @Override
  public void dispatch(DomainEvents events) {
    domainEvents = events;
  }

  public int dispatchedEventsCount() {
    return ((ArrayList<DomainEvent>) domainEvents.all()).size();
  }

  public boolean containsEvent(DomainEvent event) {
    return ((ArrayList<DomainEvent>) domainEvents.all()).contains(event);
  }
}
