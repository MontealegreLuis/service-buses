package com.montealegreluis.servicebuses.commandbus.middleware.error;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.montealegreluis.servicebuses.DomainException;
import org.junit.jupiter.api.Test;

final class DomainExceptionTest {
  @Test
  void it_knows_its_code() {
    var exception = new ExpiredTaxForm("Provided tax form is expired");

    assertEquals("expired-tax-form", exception.code());
  }

  private static class ExpiredTaxForm extends DomainException {
    public ExpiredTaxForm(String message) {
      super(message);
    }
  }
}
