package com.montealegreluis.servicebuses.domainevents;

public interface EventBus {
  void dispatch(DomainEvents events);
}
