package com.montealegreluis.servicebuses.fakes.commandbus;

import com.montealegreluis.servicebuses.commandbus.Command;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;

/** This handler will be ignored by the handler locator since it's not a generic class */
public final class NonGenericCommandHandler implements CommandHandler {
  @Override
  public void execute(Command command) {}
}
