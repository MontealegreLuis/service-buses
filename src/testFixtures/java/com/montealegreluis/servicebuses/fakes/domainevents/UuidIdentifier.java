package com.montealegreluis.servicebuses.fakes.domainevents;

import com.montealegreluis.servicebuses.domainevents.Identifier;

public final class UuidIdentifier implements Identifier {
  private final String uuid;

  public UuidIdentifier(String uuid) {
    this.uuid = uuid;
  }
}
