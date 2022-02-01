package com.montealegreluis.servicebuses.querybus;

import com.montealegreluis.servicebuses.Action;
import com.montealegreluis.servicebuses.Input;

public interface Query extends Input {
  default Action action() {
    return Action.withoutSuffix(this.getClass().getSimpleName(), "Input");
  }
}
