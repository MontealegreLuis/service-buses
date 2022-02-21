package com.montealegreluis.servicebuses;

public abstract class DomainException extends ActionException {
  public DomainException(String message) {
    super(message);
  }

  public DomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
