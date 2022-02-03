package com.montealegreluis.servicebuses;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import org.reflections.Reflections;

public final class ReflectionsActionMapper<Action, ActionHandler> {
  private final Reflections reflections;

  public ReflectionsActionMapper(String packageName) {
    reflections = new Reflections(packageName);
  }

  public Map<Action, ActionHandler> map(Class<?> actionHandlerClass) {
    var actionHandlers = reflections.getSubTypesOf(actionHandlerClass);

    Map<Action, ActionHandler> handlers = new HashMap<>();

    for (var handler : actionHandlers) {
      Type[] genericInterfaces = handler.getGenericInterfaces();
      if (!(genericInterfaces[0] instanceof ParameterizedType)) {
        continue;
      }
      ParameterizedType parameterizedType = (ParameterizedType) genericInterfaces[0];

      @SuppressWarnings("unchecked")
      Action commandClass = (Action) parameterizedType.getActualTypeArguments()[0];

      @SuppressWarnings("unchecked")
      ActionHandler commandHandler = (ActionHandler) handler;

      handlers.put(commandClass, commandHandler);
    }
    return handlers;
  }
}
