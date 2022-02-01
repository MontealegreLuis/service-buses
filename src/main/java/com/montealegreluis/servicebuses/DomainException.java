package com.montealegreluis.servicebuses;

public abstract class DomainException extends ActionException {
  public DomainException(String message) {
    super(message);
  }

  public DomainException(String message, Throwable cause) {
    super(message, cause);
  }

  public String code() {
    return TextConverter.camelCaseToKebabCase(getClass().getSimpleName());
  }
}
