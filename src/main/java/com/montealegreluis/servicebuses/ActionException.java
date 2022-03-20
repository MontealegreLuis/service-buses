package com.montealegreluis.servicebuses;

import com.montealegreluis.assertions.Assert;
import java.util.Optional;

public abstract class ActionException extends Exception {
  protected Action action;

  public ActionException(String message) {
    super(message);
  }

  public ActionException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * When used in combination with error handler middleware all action exceptions will include the
   * action that produce them
   *
   * @see <a
   *     href="https://github.com/MontealegreLuis/service-buses-middleware/blob/main/src/main/java/com/montealegreluis/servicebusesmiddleware/commandbus/middleware/error/CommandErrorHandlerMiddleware.java">Command
   *     Error Handler Middleware</a>
   * @see <a
   *     href="https://github.com/MontealegreLuis/service-buses-middleware/blob/main/src/main/java/com/montealegreluis/servicebusesmiddleware/querybus/middleware/error/QueryErrorHandlerMiddleware.java">Query
   *     Error Handler Middleware</a>
   */
  public ActionException(String message, Action action, Throwable cause) {
    super(message, cause);
    this.setAction(action);
  }

  public void setAction(Action action) {
    Assert.notNull(action, "Action cannot be null");
    this.action = action;
  }

  public Optional<Action> action() {
    return Optional.ofNullable(action);
  }

  public String code() {
    return TextConverter.camelCaseToKebabCase(getClass().getSimpleName());
  }
}
