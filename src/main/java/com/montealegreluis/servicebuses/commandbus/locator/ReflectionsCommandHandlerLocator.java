package com.montealegreluis.servicebuses.commandbus.locator;

import com.montealegreluis.servicebuses.ReflectionsActionMapper;
import com.montealegreluis.servicebuses.commandbus.Command;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;
import java.util.Map;

public final class ReflectionsCommandHandlerLocator implements CommandHandlerLocator {
  private final Map<Class<? extends Command>, Class<? extends CommandHandler<? extends Command>>>
      commandHandlers;

  public ReflectionsCommandHandlerLocator(String packageName) {
    ReflectionsActionMapper<
            Class<? extends Command>, Class<? extends CommandHandler<? extends Command>>>
        mapper = new ReflectionsActionMapper<>(packageName);
    commandHandlers = mapper.map(CommandHandler.class);
  }

  @Override
  public Class<? extends CommandHandler<? extends Command>> search(
      Class<? extends Command> commandClass) throws UnknownCommandHandler {
    var commandHandlerClass = commandHandlers.get(commandClass);

    if (null == commandHandlerClass) throw UnknownCommandHandler.forCommand(commandClass);

    return commandHandlerClass;
  }
}
