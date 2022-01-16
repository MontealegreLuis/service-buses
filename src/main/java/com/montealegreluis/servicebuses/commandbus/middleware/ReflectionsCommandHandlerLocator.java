package com.montealegreluis.servicebuses.commandbus.middleware;

import com.montealegreluis.servicebuses.commandbus.Command;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;
import com.montealegreluis.servicebuses.commandbus.UnknownCommandHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import org.reflections.Reflections;

public final class ReflectionsCommandHandlerLocator implements CommandHandlerLocator {
  private final Map<Class<? extends Command>, Class<? extends CommandHandler<? extends Command>>>
      commandHandlers;

  public ReflectionsCommandHandlerLocator(String packageName) {
    commandHandlers = mapHandlers(packageName);
  }

  private Map<Class<? extends Command>, Class<? extends CommandHandler<? extends Command>>>
      mapHandlers(String packageName) {
    Reflections reflections = new Reflections(packageName);
    var commandHandlers = reflections.getSubTypesOf(CommandHandler.class);

    Map<Class<? extends Command>, Class<? extends CommandHandler<? extends Command>>> handlers =
        new HashMap<>();

    for (var handler : commandHandlers) {
      Type[] genericInterfaces = handler.getGenericInterfaces();
      if (!(genericInterfaces[0] instanceof ParameterizedType)) {
        continue;
      }
      ParameterizedType parameterizedType = (ParameterizedType) genericInterfaces[0];

      @SuppressWarnings("unchecked")
      Class<? extends Command> commandClass =
          (Class<? extends Command>) parameterizedType.getActualTypeArguments()[0];

      @SuppressWarnings("unchecked")
      Class<? extends CommandHandler<? extends Command>> commandHandler =
          (Class<? extends CommandHandler<? extends Command>>) handler;

      handlers.put(commandClass, commandHandler);
    }

    return handlers;
  }

  @Override
  public Class<? extends CommandHandler<? extends Command>> search(
      Class<? extends Command> commandClass) {
    var commandHandlerClass = commandHandlers.get(commandClass);

    if (null == commandHandlerClass) throw UnknownCommandHandler.forCommand(commandClass);

    return commandHandlerClass;
  }
}
