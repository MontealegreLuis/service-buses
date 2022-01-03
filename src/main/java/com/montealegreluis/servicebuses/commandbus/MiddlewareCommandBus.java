package com.montealegreluis.servicebuses.commandbus;

import com.montealegreluis.assertions.Assert;
import java.util.ArrayList;
import java.util.List;

public final class MiddlewareCommandBus implements CommandBus, CommandHandler<Command> {
  public final List<CommandMiddleware<Command>> middleware;
  private List<CommandMiddleware<Command>> runtimeMiddleware;

  public MiddlewareCommandBus(List<CommandMiddleware<Command>> middleware) {
    Assert.notEmpty(middleware, "Cannot dispatch command on an empty command bus");
    this.middleware = middleware;
  }

  @Override
  public void dispatch(Command command) {
    runtimeMiddleware = new ArrayList<>(middleware);
    execute(command);
  }

  @Override
  public void execute(Command command) {
    if (runtimeMiddleware.isEmpty()) return;

    CommandMiddleware<Command> middleware = runtimeMiddleware.remove(0);
    middleware.execute(command, this);
  }
}
