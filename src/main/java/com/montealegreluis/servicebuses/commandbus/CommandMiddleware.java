package com.montealegreluis.servicebuses.commandbus;

public interface CommandMiddleware<T extends Command> {
  void execute(T command, CommandHandler<T> next);
}
