package com.montealegreluis.servicebuses;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.servicebuses.commandbus.Command;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;
import com.montealegreluis.servicebuses.fakes.commandbus.FakeCommand;
import com.montealegreluis.servicebuses.fakes.commandbus.FakeCommandHandler;
import com.montealegreluis.servicebuses.fakes.commandbus.SpyCommandHandler;
import com.montealegreluis.servicebuses.fakes.querybus.FakeQuery;
import com.montealegreluis.servicebuses.fakes.querybus.FakeQueryHandler;
import com.montealegreluis.servicebuses.fakes.querybus.SpyQueryHandler;
import com.montealegreluis.servicebuses.querybus.Query;
import com.montealegreluis.servicebuses.querybus.QueryHandler;
import com.montealegreluis.servicebuses.querybus.Response;
import org.junit.jupiter.api.Test;

final class ReflectionsActionMapperTest {
  @Test
  void it_ignores_commands_that_are_not_parameterized() {
    ReflectionsActionMapper<
            Class<? extends Command>, Class<? extends CommandHandler<? extends Command>>>
        mapper = new ReflectionsActionMapper<>("com.montealegreluis.servicebuses.fakes.commandbus");

    var handlersMap = mapper.map(CommandHandler.class);

    assertEquals(2, handlersMap.size());
    assertTrue(handlersMap.containsKey(FakeCommand.class));
    assertEquals(FakeCommandHandler.class, handlersMap.get(FakeCommand.class));
    assertTrue(handlersMap.containsKey(Command.class));
    assertEquals(SpyCommandHandler.class, handlersMap.get(Command.class));
  }

  @Test
  void it_ignores_queries_that_are_not_parameterized() {
    ReflectionsActionMapper<
            Class<? extends Query>,
            Class<? extends QueryHandler<? extends Query, ? extends Response>>>
        mapper = new ReflectionsActionMapper<>("com.montealegreluis.servicebuses.fakes.querybus");
    var handlersMap = mapper.map(QueryHandler.class);

    assertEquals(2, handlersMap.size());
    assertTrue(handlersMap.containsKey(FakeQuery.class));
    assertEquals(FakeQueryHandler.class, handlersMap.get(FakeQuery.class));
    assertTrue(handlersMap.containsKey(Query.class));
    assertEquals(SpyQueryHandler.class, handlersMap.get(Query.class));
  }
}
