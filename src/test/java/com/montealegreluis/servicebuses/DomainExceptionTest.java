package com.montealegreluis.servicebuses;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class DomainExceptionTest {
  @Test
  void it_knows_its_code() {
    var exception = new ExpiredTaxForm("Provided tax form is expired");

    assertEquals("expired-tax-form", exception.code());
  }

  @Test
  void it_knows_its_cause_exception() {
    var cause = new RuntimeException("Internal Server Error");
    var exception = new ExpiredTaxForm("Provided tax form is expired", cause);

    assertEquals("expired-tax-form", exception.code());
    assertEquals(cause, exception.getCause());
  }

  private static class ExpiredTaxForm extends DomainException {
    public ExpiredTaxForm(String message) {
      super(message);
    }

    public ExpiredTaxForm(String message, Throwable cause) {
      super(message, cause);
    }
  }
}
