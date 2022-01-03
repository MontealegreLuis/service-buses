package com.montealegreluis.servicebuses.commandbus;

import com.montealegreluis.servicebuses.Action;
import com.montealegreluis.servicebuses.Input;

public interface Command extends Input {
  default Action action() {
    return Action.withoutSuffix(this.getClass().getSimpleName(), "Input");
  }
}
