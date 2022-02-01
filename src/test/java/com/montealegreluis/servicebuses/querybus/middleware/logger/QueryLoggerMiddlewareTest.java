package com.montealegreluis.servicebuses.querybus.middleware.logger;

import static com.montealegreluis.servicebuses.ActionActivity.queryCompleted;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.montealegreluis.activityfeed.ActivityFeed;
import com.montealegreluis.servicebuses.ActionException;
import com.montealegreluis.servicebuses.fakes.commandbus.middleware.logger.FixedOffsetInstantClock;
import com.montealegreluis.servicebuses.fakes.querybus.FakeQuery;
import com.montealegreluis.servicebuses.fakes.querybus.FakeQueryHandler;
import com.montealegreluis.servicebuses.fakes.querybus.FakeResponse;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class QueryLoggerMiddlewareTest {
  @Test
  void it_logs_the_duration_of_a_query() throws ActionException {
    var query = new FakeQuery();
    var next = new FakeQueryHandler();
    var activity = queryCompleted(query.action(), duration);

    var response = middleware.execute(query, next);

    verify(feed, times(1)).record(activity);
    assertTrue(next.wasCalled());
    assertEquals(new FakeResponse(), response);
  }

  @BeforeEach
  void let() {
    feed = mock(ActivityFeed.class);
    duration = Duration.ofMillis(500);
    var clock = new FixedOffsetInstantClock(Instant.now(), ZoneOffset.UTC, duration);
    middleware = new QueryLoggerMiddleware(feed, clock);
  }

  private ActivityFeed feed;
  private Duration duration;
  private QueryLoggerMiddleware middleware;
}
