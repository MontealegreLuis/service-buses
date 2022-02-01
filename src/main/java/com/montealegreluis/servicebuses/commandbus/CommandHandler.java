package com.montealegreluis.servicebuses.commandbus;

import com.montealegreluis.servicebuses.ActionException;

public interface CommandHandler<T extends Command> {
  void execute(T command) throws ActionException;
}
