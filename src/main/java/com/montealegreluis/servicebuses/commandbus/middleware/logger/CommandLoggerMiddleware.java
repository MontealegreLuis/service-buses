package com.montealegreluis.servicebuses.commandbus.middleware.logger;

import static com.montealegreluis.servicebuses.commandbus.middleware.logger.ActionActivity.commandCompleted;

import com.montealegreluis.activityfeed.ActivityFeed;
import com.montealegreluis.servicebuses.commandbus.Command;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;
import com.montealegreluis.servicebuses.commandbus.CommandMiddleware;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

public final class CommandLoggerMiddleware implements CommandMiddleware<Command> {
  private final ActivityFeed feed;
  private final Clock clock;

  public CommandLoggerMiddleware(ActivityFeed feed, Clock clock) {
    this.feed = feed;
    this.clock = clock;
  }

  @Override
  public void execute(Command command, CommandHandler<Command> next) {
    Instant start = clock.instant();
    next.execute(command);
    Instant end = clock.instant();

    feed.record(commandCompleted(command.action(), Duration.between(start, end)));
  }
}
