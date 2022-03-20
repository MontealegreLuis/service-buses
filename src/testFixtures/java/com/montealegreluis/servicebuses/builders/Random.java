package com.montealegreluis.servicebuses.builders;

import com.github.javafaker.Faker;

public final class Random {
  private static final Faker faker = new Faker();

  public static String uuid() {
    return faker.internet().uuid();
  }
}
