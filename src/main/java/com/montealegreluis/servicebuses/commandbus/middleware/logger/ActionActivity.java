package com.montealegreluis.servicebuses.commandbus.middleware.logger;

import static com.montealegreluis.activityfeed.Activity.info;

import com.montealegreluis.activityfeed.Activity;
import com.montealegreluis.servicebuses.Action;
import java.time.Duration;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class ActionActivity {
  public static Activity commandCompleted(Action command, Duration duration) {
    long durationInMilliseconds = duration.toMillis();
    String message =
        String.format(
            "%s has been completed in %d milliseconds",
            command.toSentence(), durationInMilliseconds);
    return info(
        "command",
        message,
        (context) -> {
          context.put("command", command.toSlug());
          context.put("durationInMilliseconds", durationInMilliseconds);
        });
  }
}
