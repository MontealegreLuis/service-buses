package com.montealegreluis.servicebuses.commandbus;

public interface CommandBus {
  void dispatch(Command command);
}
