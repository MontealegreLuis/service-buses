package com.montealegreluis.servicebuses;

import com.montealegreluis.assertions.Assert;
import lombok.EqualsAndHashCode;

/**
 * An action can be either a Command or a Query.
 *
 * <p>Actions are used to extract information from the Command or Query executed by any of the
 * buses. Examples of use are:
 *
 * <ul>
 *   <li>Logging
 *   <li>Error messages
 *   <li>API response bodies
 */
@EqualsAndHashCode
public final class Action {
  private final String name;

  public static Action withoutSuffix(String name, String suffix) {
    Assert.notBlank(name, "Action name cannot be null or empty. '%s' given");
    return new Action(name.replace(suffix, ""));
  }

  private Action(String name) {
    this.name = name;
  }

  public String name() {
    return name;
  }

  public String toSlug() {
    return TextConverter.camelCaseToKebabCase(name);
  }

  public String toSentence() {
    return TextConverter.camelCaseToSentence(name);
  }

  public String toWords() {
    return TextConverter.camelCaseToSentence(name).toLowerCase();
  }
}
