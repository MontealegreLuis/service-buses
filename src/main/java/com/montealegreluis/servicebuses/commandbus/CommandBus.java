package com.montealegreluis.servicebuses.commandbus;

import com.montealegreluis.servicebuses.ActionException;

public interface CommandBus {
  void dispatch(Command command) throws ActionException;
}
