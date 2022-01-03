package com.montealegreluis.servicebuses.commandbus;

public interface CommandHandler<T extends Command> {
  void execute(T command);
}
